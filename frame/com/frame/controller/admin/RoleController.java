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
import com.frame.api.IRole;
import com.frame.bean.GridBean;
import com.frame.model.bean.Domain;
import com.frame.model.bean.Role;
import com.frame.model.bean.RoleCriteria;
import com.frame.util.Constants;
import com.frame.util.NumberUtil;
import com.frame.util.StringUtil;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {
	@Autowired
	IRole roleService;

	@Autowired
	IDomain domainService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String showDomainRoles(HttpServletRequest request, Model model) throws Exception {
		List<Domain> domains = domainService.getDomains(null);
		model.addAttribute("domains", domains);
		return "admin/domainRoles";
	}

	@RequestMapping(value = "/domainRoles/{domainId}", method = RequestMethod.GET)
	public String showDomainRole(@PathVariable int domainId, HttpServletRequest request, Model model) throws Exception {
		model.addAttribute("domainId", domainId);
		return "admin/roles";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public String saveRole(@RequestParam(value = "domainId") int domainId,
			@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "roleName") String roleName,
			@RequestParam(value = "roleCode") String roleCode,
			@RequestParam(value = "roleType", required = false) String roleType,
			@RequestParam(value = "defaultFlag", required = false) String defaultFlag,
			@RequestParam(value = "remarks", required = false) String remarks, HttpServletResponse response,
			MultipartHttpServletRequest request, Model model) throws Exception {
		Role role = new Role();
		role.setDefaultFlag(defaultFlag);
		role.setDomainId(domainId);
		role.setRemarks(remarks);
		role.setRoleCode(roleCode);
		role.setRoleName(roleName);
		role.setRoleType(roleType);
		if (null != id) {
			role.setId(id);
			this.roleService.updateRole(role);
		} else {
			this.roleService.addRole(role);
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
	@ResponseBody
	public Role getRole(@PathVariable int roleId, HttpServletRequest request, Model model) throws Exception {

		Role role = null;
		try {
			role = this.roleService.getRoleById(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@RequestMapping(value = { "", "/{roleIds}" }, method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteRole(@PathVariable String roleIds, HttpServletRequest request, Model model) throws Exception {

		try {
			List<Integer> ids = new ArrayList<Integer>();
			if (roleIds != null) {
				String[] dd = roleIds.split(",");
				Integer temp;
				for (String id : dd) {
					temp = NumberUtil.Obj2Int(id);
					if (temp != null) {
						ids.add(temp);
					}
				}
			}

			this.roleService.deleteRole(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	@ResponseBody
	public GridBean getRoles(@RequestParam int domainId, HttpServletRequest request, Model model) throws Exception {
		String pageTemp = request.getParameter("page");
		String rowsTemp = request.getParameter("rows");
		int page = StringUtil.isEmpty(pageTemp) ? 1 : Integer.parseInt(pageTemp);
		int rows = StringUtil.isEmpty(rowsTemp) ? 10 : Integer.parseInt(rowsTemp);

		RoleCriteria example = new RoleCriteria();
		example.or().andDomainIdEqualTo(domainId);
		GridBean grid = this.roleService.findRolesForGrid(example, page, rows);

		return grid;
	}

	@RequestMapping(value = "/isRoleExist", method = RequestMethod.GET)
	@ResponseBody
	public boolean isRoleExist(@RequestParam int domainId, @RequestParam(value = "roleCode") String roleCode,
			HttpServletRequest request, Model model) throws Exception {

		RoleCriteria example = new RoleCriteria();
		example.or().andDomainIdEqualTo(domainId).andRoleCodeEqualTo(roleCode);
		List<Role> roles = this.roleService.findRoles(example);
		if (null != roles && roles.size() > 0) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/setDefault", method = RequestMethod.POST)
	@ResponseBody
	public String setDefault(@RequestParam(value = "id") Integer id, HttpServletRequest request, Model model)
			throws Exception {
		Role role = this.roleService.getRoleById(id);
		if (null == role) {
			return Constants.WEB_JSON_STATUS_FAIL;
		}
		this.roleService.setDefault(id);
		return Constants.WEB_JSON_STATUS_SUCCESS;
	}
}
