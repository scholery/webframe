package com.frame.model.bean;

public class Domain {
    private Integer id;

    private String domainCode;

    private String domainName;

    private String domainShortName;

    private String domainTitleKey;

    private String domainIcon;

    private String domainSmallIcon;

    private Integer sortIndex;

    private String status;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomainCode() {
        return domainCode;
    }

    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode == null ? null : domainCode.trim();
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName == null ? null : domainName.trim();
    }

    public String getDomainShortName() {
        return domainShortName;
    }

    public void setDomainShortName(String domainShortName) {
        this.domainShortName = domainShortName == null ? null : domainShortName.trim();
    }

    public String getDomainTitleKey() {
        return domainTitleKey;
    }

    public void setDomainTitleKey(String domainTitleKey) {
        this.domainTitleKey = domainTitleKey == null ? null : domainTitleKey.trim();
    }

    public String getDomainIcon() {
        return domainIcon;
    }

    public void setDomainIcon(String domainIcon) {
        this.domainIcon = domainIcon == null ? null : domainIcon.trim();
    }

    public String getDomainSmallIcon() {
        return domainSmallIcon;
    }

    public void setDomainSmallIcon(String domainSmallIcon) {
        this.domainSmallIcon = domainSmallIcon == null ? null : domainSmallIcon.trim();
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
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