package com.frame.controller.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frame.api.IModule;
import com.frame.api.IUser;
import com.frame.api.IUserRoleRelate;
import com.frame.bean.MenuItem;
import com.frame.exception.AuthorizationException;
import com.frame.model.bean.Module;
import com.frame.model.bean.ModuleCriteria;
import com.frame.model.bean.User;
import com.frame.util.Constants;
import com.frame.web.ApplicationModel;
import com.frame.web.MessageSourceHelper;
import com.frame.web.SessionManager;

@Controller
public class WebAppMainController {
	/**
	 * Logger for this class
	 */
	private final Log logger = LogFactory.getLog(this.getClass());
	//private static final int appDomainId = 1;
	@Autowired
	IModule moduleService;

	@Autowired
	IUser userService;

	@Autowired
	IUserRoleRelate roleRelate;

	@RequestMapping("/index")
	public String gotoIndex(HttpServletRequest request, Model model)
			throws Exception {
		return "index";
	}

	@RequestMapping("/logout")
	public String doLogout(HttpServletRequest request, Model model)
			throws Exception {
		// 注销session
		SessionManager.getInstance()
				.removeSession(request.getSession().getId());
		request.getSession().invalidate();
		return "redirect:login";
	}

	@RequestMapping("/login")
	public String gotoLogin(HttpServletRequest request, Model model)
			throws Exception {
		if (request.getSession().getAttribute("appModel") != null) {
			return "redirect:index";
		}
		model.addAttribute("systemAppName", getSystemAppName());
		model.addAttribute("serverRoot", Constants.SERVER_ROOT);
		return "login";
	}

	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model)
			throws Exception {
		model.addAttribute("code", request.getParameter("code"));
		model.addAttribute("message", request.getParameter("message"));
		return "error/error";
	}

	@RequestMapping("/building")
	public String building(HttpServletRequest request, Model model)
			throws Exception {
		return "error/building";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam(value = "remberMeFlag", required = false) String remberMeFlag,
			@RequestParam("rand") String randStr, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {

		userId = StringUtils.trim(userId);
		password = StringUtils.trim(password);
		/*try {
			String rand = (String) request.getSession().getAttribute("rand");
			if (StringUtil.isEmpty(randStr) || StringUtil.isEmpty(rand)
					|| rand.equals(randStr) == false) {
				request.getSession().setAttribute("userId", userId);
				throw new AuthorizationException(
						MessageSourceHelper.getText("user.login.error.rand"));
			}
		} catch (Exception e) {
			throw e;
		}*/

		List<Integer> moduleIds = new ArrayList<Integer>();
		User user = null;
		try {
			request.getSession().setAttribute("userId", null);
			user = login(userId, password, moduleIds);
		} catch (AuthorizationException e) {
			request.getSession().setAttribute("userId", userId);
			throw e;
		}

		ApplicationModel applicationModel = new ApplicationModel();

		//applicationModel.setDomainId(appDomainId);
		
		applicationModel.setAppName(getSystemAppName());

		applicationModel.setUserName(user.getUserName());

		applicationModel.setUserId(user.getId());

		applicationModel.setUserAccount(user.getUserAccount());

		applicationModel.setLoginTime(new Date());

		applicationModel.setUserLocaltion(request.getRemoteHost());

		applicationModel.setServerLocaltion(request.getHeader("host"));

		MenuItem[] menuItems = getDomainMenuItems(0, moduleIds);

		applicationModel.setMenuItems(menuItems);

		request.getSession().setAttribute("appModel", applicationModel);
		request.getSession().setAttribute("serverRoot", Constants.SERVER_ROOT);

		// 统计session
		SessionManager.getInstance().addSession(request.getSession().getId(),
				request.getSession());
		return "redirect:index";
	}

	protected String getSystemAppName() throws Exception {

		return MessageSourceHelper.getText("project.name");

	}

	protected String getSystemAppVersion() throws Exception {

		return "1.0";

	}

	protected User login(String userAccount, String password,
			List<Integer> moduleIds) throws Exception {

		User user = userService.getUserWithPass(userAccount);

		if (user == null) {
			throw new AuthorizationException(
					MessageSourceHelper
							.getText("user.login.error.userorpassword"));
		}
		
		if (password.equals(user.getPassword()) == false) {
			throw new AuthorizationException(
					MessageSourceHelper
							.getText("user.login.error.userorpassword"));
		}

		moduleIds.clear();
		moduleIds.addAll(this.userService.getUserModuleIds(user.getId()));

		logger.debug("User '" + user.getUserAccount() + "' Log in");

		return user;

	}

	protected MenuItem[] getDomainMenuItems(int parentModuleId, List<Integer> canAccessModules)
			throws Exception {

		List<MenuItem> menuItems = new ArrayList<MenuItem>();

		ModuleCriteria example = new ModuleCriteria();
		example.or().andParentModuleIdEqualTo(parentModuleId);
		example.setOrderByClause("sort_index");
		//query.setDomainId(appDomainId);
		List<Module> modules = moduleService.findModules(example);

		if (null == modules || modules.size() == 0) {
			return  menuItems.toArray(new MenuItem[menuItems.size()]);
		}
		MenuItem menuItem;
		for (Module module : modules) {

			if (canAccessModules.contains(module.getId()) == false) {
				continue;
			}

			menuItem = new MenuItem();

			menuItem.setMenuId(module.getId());

			menuItem.setLabel(module.getModuleName());

			menuItem.setIcon(module.getModuleIcon());

			menuItem.setPath(module.getModuleUrl());

			menuItem.setDomainCode(module.getModuleCode());

			MenuItem[] subItems = getDomainMenuItems(module.getId(), canAccessModules);

			menuItem.setSubItems(subItems);

			menuItems.add(menuItem);
		}

		return menuItems.toArray(new MenuItem[menuItems.size()]);
	}

}
