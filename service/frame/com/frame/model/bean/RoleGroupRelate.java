package com.frame.model.bean;

public class RoleGroupRelate {
    private Integer id;

    private Integer sysRoleGroupId;

    private Integer relatedSysRoleId;

    private String relatedSysRoleCode;

    private String status;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysRoleGroupId() {
        return sysRoleGroupId;
    }

    public void setSysRoleGroupId(Integer sysRoleGroupId) {
        this.sysRoleGroupId = sysRoleGroupId;
    }

    public Integer getRelatedSysRoleId() {
        return relatedSysRoleId;
    }

    public void setRelatedSysRoleId(Integer relatedSysRoleId) {
        this.relatedSysRoleId = relatedSysRoleId;
    }

    public String getRelatedSysRoleCode() {
        return relatedSysRoleCode;
    }

    public void setRelatedSysRoleCode(String relatedSysRoleCode) {
        this.relatedSysRoleCode = relatedSysRoleCode == null ? null : relatedSysRoleCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}