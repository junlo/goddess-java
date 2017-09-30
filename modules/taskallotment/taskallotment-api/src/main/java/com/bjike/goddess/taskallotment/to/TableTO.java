package com.bjike.goddess.taskallotment.to;

import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.taskallotment.enums.Status;

/**
 * 项目表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:58 ]
 * @Description: [ 项目表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class TableTO extends BaseTO {

    /**
     * 表名称
     */
    private String name;
    /**
     * 状态
     */
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}