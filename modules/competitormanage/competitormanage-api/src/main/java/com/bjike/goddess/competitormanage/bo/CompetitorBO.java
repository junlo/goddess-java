package com.bjike.goddess.competitormanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.competitormanage.enums.BusinessType;

/**
 * 竞争对手信息业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-21 04:49 ]
 * @Description: [ 竞争对手信息业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CompetitorBO extends BaseBO {

    /**
     * 数据状态
     */
    private Status status;

    /**
     * 市场信息收集序号
     */
    private String markInfoCode;

    /**
     * 项目名称
     */
    private String project;

    /**
     * 地区
     */
    private String area;

    /**
     * 竞争对手名称
     */
    private String competitor;

    /**
     * 业务类型
     */
    private BusinessType businessType;

    /**
     * 基本资料描述
     */
    private String basicInfoDesc;

    /**
     * 电话
     */
    private String phoneNum;

    /**
     * 地址
     */
    private String address;

    /**
     * 组织结构
     */
    private String organization;

    /**
     * 级别定义方式
     */
    private String leveldefinition;

    /**
     * 其他备注
     */
    private String remark;

    /**
     * 主管部门
     */
    private String directDepartment;

    /**
     * 主管名称
     */
    private String director;

    /**
     * 主管职权
     */
    private String directAuthority;

    /**
     * 负责事项
     */
    private String chargeItems;

    /**
     * 客户信息表序号
     */
    private String customerInfoCode;

    /**
     * 分管部门
     */
    private String branchedDepartment;

    /**
     * 负责人名称
     */
    private String chargeMan;

    /**
     * 负责人职权
     */
    private String chargeManAuthority;

    /**
     * 接口人
     */
    private String interfaceMan;

    /**
     * 创建时间
     */
    private String createTime;

    public String getMarkInfoCode() {
        return markInfoCode;
    }

    public void setMarkInfoCode(String markInfoCode) {
        this.markInfoCode = markInfoCode;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    public String getBasicInfoDesc() {
        return basicInfoDesc;
    }

    public void setBasicInfoDesc(String basicInfoDesc) {
        this.basicInfoDesc = basicInfoDesc;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLeveldefinition() {
        return leveldefinition;
    }

    public void setLeveldefinition(String leveldefinition) {
        this.leveldefinition = leveldefinition;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDirectDepartment() {
        return directDepartment;
    }

    public void setDirectDepartment(String directDepartment) {
        this.directDepartment = directDepartment;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDirectAuthority() {
        return directAuthority;
    }

    public void setDirectAuthority(String directAuthority) {
        this.directAuthority = directAuthority;
    }

    public String getChargeItems() {
        return chargeItems;
    }

    public void setChargeItems(String chargeItems) {
        this.chargeItems = chargeItems;
    }

    public String getCustomerInfoCode() {
        return customerInfoCode;
    }

    public void setCustomerInfoCode(String customerInfoCode) {
        this.customerInfoCode = customerInfoCode;
    }

    public String getBranchedDepartment() {
        return branchedDepartment;
    }

    public void setBranchedDepartment(String branchedDepartment) {
        this.branchedDepartment = branchedDepartment;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getChargeManAuthority() {
        return chargeManAuthority;
    }

    public void setChargeManAuthority(String chargeManAuthority) {
        this.chargeManAuthority = chargeManAuthority;
    }

    public String getInterfaceMan() {
        return interfaceMan;
    }

    public void setInterfaceMan(String interfaceMan) {
        this.interfaceMan = interfaceMan;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}