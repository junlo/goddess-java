package com.bjike.goddess.customer.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.customer.entity.CustomerDetail;

import javax.persistence.Column;

/**
 * 客户家庭成员业务传输对象
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T09:48:29.104 ]
 * @Description: [ 客户家庭成员业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CusFamilyMemberBO extends BaseBO {

    /**
     * 称谓
     */
    private String title;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String relationWay;

    /**
     * 性格爱好
     */
    private String charactLove;

    /**
     * 工作单位
     */
    private String workPlace;

    /**
     * 职位
     */
    private String jobPost;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 客户详细
     */
    private CustomerDetailBO customerDetailBO;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationWay() {
        return relationWay;
    }

    public void setRelationWay(String relationWay) {
        this.relationWay = relationWay;
    }

    public String getCharactLove() {
        return charactLove;
    }

    public void setCharactLove(String charactLove) {
        this.charactLove = charactLove;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getJobPost() {
        return jobPost;
    }

    public void setJobPost(String jobPost) {
        this.jobPost = jobPost;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public CustomerDetailBO getCustomerDetailBO() {
        return customerDetailBO;
    }

    public void setCustomerDetailBO(CustomerDetailBO customerDetailBO) {
        this.customerDetailBO = customerDetailBO;
    }
}