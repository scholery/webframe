package com.frame.model.bean;

public class SysProperty {
    private Integer id;

    private String propertyCatalog;

    private String propertyCode;

    private String propertyName;

    private String status;

    private String propertyValue;

    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPropertyCatalog() {
        return propertyCatalog;
    }

    public void setPropertyCatalog(String propertyCatalog) {
        this.propertyCatalog = propertyCatalog == null ? null : propertyCatalog.trim();
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode == null ? null : propertyCode.trim();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue == null ? null : propertyValue.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}