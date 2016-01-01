package com.frame.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.frame.api.IDomain;
import com.frame.api.IModule;
import com.frame.api.IRole;
import com.frame.api.IRoleModuleRelate;
import com.frame.bean.GridBean;
import com.frame.bean.TreeGridBean;
import com.frame.model.bean.Domain;
import com.frame.model.bean.Module;
import com.frame.model.bean.ModuleCriteria;
import com.frame.model.bean.Role;
import com.frame.model.bean.RoleCriteria;
import com.frame.model.bean.RoleModuleRelate;
import com.frame.model.bean.RoleModuleRelateCriteria;
import com.frame.util.Constants;
import com.frame.util.NumberUtil;

@Controller
@RequestMapping("/admin/domains")
public class DomainController {
	@Autowired
	IModule moduleService;

	@Autowired
	IDomain domainService;

	@Autowired
	IRole roleService;

	@Autowired
	IRoleModuleRelate roleModules;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String showDomains(HttpServletRequest request, Model model) throws Exception {
		List<Domain> domains = domainService.getDomains(null);
		model.addAttribute("domains", domains);
		return "admin/domainModules";
	}

	@RequestMapping(value = "/{domainId}", method = RequestMethod.GET)
	public String getDomain(@PathVariable int domainId, HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("domainId", domainId);
		return "admin/modules";
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public List<Domain> getDomains(HttpServletRequest request, Model model) throws Exception {
		List<Domain> domains = this.domainService.getDomains(null);
		return domains;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String saveDomain(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "domainCode") String code, @RequestParam(value = "domainName") String name,
			@RequestParam(value = "domainShortName", required = false) String shortName,
			@RequestParam(value = "domainTitleKey", required = false) String titleKey,
			@RequestParam(value = "domainIcon", required = false) String icon,
			@RequestParam(value = "domainSmallIcon", required = false) String smallIcon,
			@RequestParam(value = "index", required = false) Integer index,
			@RequestParam(value = "remarks", required = false) String remarks, HttpServletResponse response,
			MultipartHttpServletRequest request, Model model) throws Exception {

		Domain domain = new Domain();
		domain.setDomainCode(code);
		domain.setDomainIcon(icon);
		domain.setDomainName(name);
		domain.setDomainShortName(shortName);
		domain.setDomainSmallIcon(smallIcon);
		domain.setDomainTitleKey(titleKey);
		domain.setRemarks(remarks);
		domain.setSortIndex(index);
		if (null != id) {
			domain.setId(id);
			domainService.updateDomain(domain);
		} else {
			domainService.addDomain(domain);
		}
		return "admin/domainModules";
	}

	@RequestMapping(value = "/{domainId}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteDomain(@PathVariable int domainId, HttpServletRequest request, Model model) throws Exception {
		try {
			domainService.deleteDomain(domainId);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/{domainId}/roles", method = RequestMethod.GET)
	@ResponseBody
	public List<Role> getDomainRoles(@PathVariable int domainId, HttpServletRequest request, Model model)
			throws Exception {

		RoleCriteria example = new RoleCriteria();
		example.or().andDomainIdEqualTo(domainId);
		List<Role> roles = this.roleService.findRoles(example);
		return roles;
	}

	@RequestMapping(value = "/{domainId}/modules", method = RequestMethod.GET)
	@ResponseBody
	public GridBean getDomainModules(@PathVariable int domainId, HttpServletRequest request, Model model)
			throws Exception {

		GridBean grid = new GridBean();
		grid.setPage(0);
		List<TreeGridBean> treeNodes = new ArrayList<TreeGridBean>();
		getSubModules(treeNodes, domainId, 0, 0);
		grid.setRecords(treeNodes.size());
		grid.setRows(treeNodes.toArray());
		return grid;
	}

	private void getSubModules(List<TreeGridBean> nodes, int domainId, int parentModuleId, int level) {

		ModuleCriteria example = new ModuleCriteria();
		example.or().andDomainIdEqualTo(domainId).andParentModuleIdEqualTo(parentModuleId);
		example.setOrderByClause("sort_index");
		List<Module> modules = moduleService.findModules(example);

		if (modules.size() == 0) {
			return;
		}
		TreeGridBean temp;
		for (Module obj : modules) {
			temp = new TreeGridBean();
			nodes.add(temp);
			temp.setObj(obj);
			temp.settId(obj.getId() + "");
			temp.setParent(obj.getParentModuleId() + "");
			temp.setLevel(level);
			boolean isLeaf = this.moduleService.getSubModuleNum(obj.getId()) > 0 ? false : true;
			temp.setLeaf(isLeaf);
			if (isLeaf == false) {
				temp.setExpand(true);
				getSubModules(nodes, domainId, obj.getId(), level + 1);
			}
		}
	}

	@RequestMapping(value = "/{domainId}/modules", method = RequestMethod.POST)
	@ResponseBody
	public String saveDomainModule(@PathVariable int domainId, @RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "parentModuleId") Integer parentId, @RequestParam(value = "moduleCode") String code,
			@RequestParam(value = "moduleName") String name,
			@RequestParam(value = "moduleIcon", required = false) String icon,
			@RequestParam(value = "moduleUrl", required = false) String url,
			@RequestParam(value = "index", required = false) Integer index,
			@RequestParam(value = "remarks", required = false) String remarks, HttpServletResponse response,
			MultipartHttpServletRequest request, Model model) throws Exception {

		response.setContentType("text/html;charset=utf-8");
		try {
			Module module = new Module();
			module.setDomainId(domainId);
			module.setParentModuleId(parentId);
			module.setModuleCode(code);
			module.setModuleName(name);
			module.setModuleIcon(icon);
			module.setModuleUrl(url);
			module.setSortIndex(index);
			module.setRemarks(remarks);
			if (null != id) {
				module.setId(id);
				moduleService.updateModule(module);
			} else {
				moduleService.addModule(module);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/{domainId}/modules/{moduleIds}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteDomainModule(@PathVariable int domainId, @PathVariable String moduleIds,
			HttpServletRequest request, Model model) throws Exception {

		try {
			List<Integer> ids = new ArrayList<Integer>();
			if (moduleIds != null) {
				String[] dd = moduleIds.split(",");
				Integer temp;
				for (String id : dd) {
					temp = NumberUtil.Obj2Int(id);
					if (temp != null) {
						ids.add(temp);
					}
				}
			}

			moduleService.deleteModule(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/{domainId}/modules/{moduleId}", method = RequestMethod.GET)
	@ResponseBody
	public Module getDomainModule(@PathVariable int domainId, @PathVariable int moduleId, HttpServletRequest request,
			Model model) throws Exception {
		Module obj = null;
		try {
			obj = moduleService.getModuleById(moduleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@RequestMapping(value = "/{domainId}/modules/isModuleExist", method = RequestMethod.GET)
	@ResponseBody
	public boolean isModuleExist(@PathVariable int domainId, @RequestParam(value = "moduleCode") String moduleCode,
			HttpServletRequest request, Model model) throws Exception {

		ModuleCriteria example = new ModuleCriteria();
		example.or().andDomainIdEqualTo(domainId).andModuleCodeEqualTo(moduleCode);

		List<Module> modules = this.moduleService.findModules(example);
		if (null != modules && modules.size() > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/{domainId}/permissions", method = RequestMethod.POST)
	@ResponseBody
	public String setDomaiPermissions(@PathVariable int domainId, @RequestParam(value = "roleId") Integer roleId,
			@RequestParam(value = "moduleIds", required = false) String moduleIds, HttpServletRequest request,
			Model model) throws Exception {

		try {
			List<Integer> modules = new ArrayList<Integer>();
			if (moduleIds != null) {
				String[] ids = moduleIds.split(",");
				Integer temp;
				for (String id : ids) {
					temp = NumberUtil.Obj2Int(id);
					if (temp != null) {
						modules.add(temp);
					}
				}
			}
			this.roleModules.syncRoleModules(roleId, modules);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/{domainId}/permissions", method = RequestMethod.GET)
	@ResponseBody
	public List<RoleModuleRelate> getDomaiPermissions(@PathVariable int domainId,
			@RequestParam(value = "roleId") Integer roleId, HttpServletRequest request, Model model) throws Exception {

		try {
			RoleModuleRelateCriteria example = new RoleModuleRelateCriteria();
			example.or().andRoleIdEqualTo(roleId);
			List<RoleModuleRelate> roleModules = this.roleModules.findRoleModuleRelates(example);

			return roleModules;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
