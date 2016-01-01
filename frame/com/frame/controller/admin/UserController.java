package com.frame.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.frame.api.IUser;
import com.frame.api.IUserRoleRelate;
import com.frame.bean.GridBean;
import com.frame.model.bean.User;
import com.frame.model.bean.UserRoleRelate;
import com.frame.model.bean.UserRoleRelateCriteria;
import com.frame.util.Constants;
import com.frame.util.NumberUtil;
import com.frame.util.StringUtil;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	@Autowired
	IUser userService;

	@Autowired
	IUserRoleRelate userRole;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String showUsers(HttpServletRequest request, Model model) throws Exception {
		return "admin/users";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "userName") String userName, @RequestParam(value = "userAccount") String userAccount,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "userType", required = false) String userType,
			@RequestParam(value = "remarks", required = false) String remarks, HttpServletResponse response,
			MultipartHttpServletRequest request, Model model) throws Exception {

		User user = new User();
		user.setPassword(password);
		user.setRemarks(remarks);
		user.setUserAccount(userAccount);
		user.setUserName(userName);
		user.setUserType(userType);
		if (null != id) {
			user.setId(id);
			if (StringUtil.isEmpty(password)) {
				user.setPassword(null);
			}
			this.userService.updateUser(user);
		} else {
			if (StringUtil.isEmpty(password)) {
				user.setPassword(DigestUtils.md5Hex("user"));
			}
			this.userService.addUser(user);
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@PathVariable int userId, HttpServletRequest request, Model model) throws Exception {

		User user = null;
		try {
			user = this.userService.getUserById(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/{userIds}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable String userIds, HttpServletRequest request, Model model) throws Exception {

		try {
			List<Integer> ids = new ArrayList<Integer>();
			if (userIds != null) {
				String[] dd = userIds.split(",");
				Integer temp;
				for (String id : dd) {
					temp = NumberUtil.Obj2Int(id);
					if (temp != null) {
						ids.add(temp);
					}
				}
			}
			this.userService.deleteUser(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseBody
	public GridBean getUsers(HttpServletRequest request, Model model) throws Exception {
		String pageTemp = request.getParameter("page");
		String rowsTemp = request.getParameter("rows");
		int page = StringUtil.isEmpty(pageTemp) ? 1 : Integer.parseInt(pageTemp);
		int rows = StringUtil.isEmpty(rowsTemp) ? 10 : Integer.parseInt(rowsTemp);

		GridBean grid = this.userService.findUsersForGrid(null, page, rows);
		return grid;
	}

	@RequestMapping(value = "/isUserExist", method = RequestMethod.GET)
	@ResponseBody
	public boolean isUserExist(@RequestParam(value = "userAccount") String userAccount, HttpServletRequest request,
			Model model) throws Exception {

		User user = this.userService.getUserWithPass(userAccount);
		if (null != user) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/{userId}/roles", method = RequestMethod.GET)
	@ResponseBody
	public List<UserRoleRelate> getUserRoles(@PathVariable int userId, HttpServletRequest request, Model model)
			throws Exception {

		try {
			UserRoleRelateCriteria example = new UserRoleRelateCriteria();
			example.or().andUserIdEqualTo(userId);
			List<UserRoleRelate> userRoles = this.userRole.findUserRoleRelates(example);
			return userRoles;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/{userId}/roles", method = RequestMethod.POST)
	@ResponseBody
	public String saveUserRoles(@PathVariable int userId,
			@RequestParam(value = "roleIds", required = false) String roleIds, HttpServletRequest request, Model model)
					throws Exception {

		try {
			List<Integer> roles = new ArrayList<Integer>();
			if (roleIds != null) {
				String[] ids = roleIds.split(",");
				Integer temp;
				for (String id : ids) {
					temp = NumberUtil.Obj2Int(id);
					if (temp != null) {
						roles.add(temp);
					}
				}
			}
			this.userRole.syncUserRoleRelate(userId, roles);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}
}
