package com.bjike.goddess.checkhost.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 住宿申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkhost_stayapply")
public class StayApply extends BaseEntity {

    /**
     * 新入职员工姓名
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '新入职员工姓名'")
    private String name;

    /**
     * 申请入住日期
     */
    @Column(name = "stayDate", nullable = false, columnDefinition = "DATE   COMMENT '申请入住日期'")
    private LocalDate stayDate;

    /**
     * 申请入住地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申请入住地区'")
    private String area;

    /**
     * 申请入住宿舍
     */
    @Column(name = "stayDormitory", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申请入住宿舍'")
    private String stayDormitory;

    /**
     * 申请入住原因
     */
    @Column(name = "stayCause", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申请入住原因'")
    private String stayCause;

    /**
     * 住宿负责人
     */
    @Column(name = "stayHead", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '住宿负责人'")
    private String stayHead;

    /**
     * 福利模块负责人审核
     */
    @Column(name = "headAudit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '福利模块负责人审核'")
    private String headAudit;

    /**
     * 新员工确认入住
     */
    @Column(name = "is_stay", nullable = false, columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '新员工确认入住'", insertable = false)
    private Boolean stay;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStayDate() {
        return stayDate;
    }

    public void setStayDate(LocalDate stayDate) {
        this.stayDate = stayDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStayDormitory() {
        return stayDormitory;
    }

    public void setStayDormitory(String stayDormitory) {
        this.stayDormitory = stayDormitory;
    }

    public String getStayCause() {
        return stayCause;
    }

    public void setStayCause(String stayCause) {
        this.stayCause = stayCause;
    }

    public String getStayHead() {
        return stayHead;
    }

    public void setStayHead(String stayHead) {
        this.stayHead = stayHead;
    }

    public String getHeadAudit() {
        return headAudit;
    }

    public void setHeadAudit(String headAudit) {
        this.headAudit = headAudit;
    }

    public Boolean getStay() {
        return stay;
    }

    public void setStay(Boolean stay) {
        this.stay = stay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}