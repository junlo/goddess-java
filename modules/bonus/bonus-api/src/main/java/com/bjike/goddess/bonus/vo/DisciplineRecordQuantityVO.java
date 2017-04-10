package com.bjike.goddess.bonus.vo;

/**
 * 奖励处罚人数汇总表现层对象
 *
 * @Author: [dengjunren]
 * @Date: [2017-04-10 10:54]
 * @Description: [ 奖励处罚人数汇总表现层对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class DisciplineRecordQuantityVO {

    /**
     * 开始时间
     */
    private String start;

    /**
     * 结束时间
     */
    private String end;

    /**
     * 项目组
     */
    private String department;

    /**
     * 地区
     */
    private String area;

    /**
     * 奖罚指标
     */
    private String target;

    /**
     * 奖励人数
     */
    private Integer reward;

    /**
     * 处罚人数
     */
    private Integer push;

    /**
     * 最后得分
     */
    private Double total;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getPush() {
        return push;
    }

    public void setPush(Integer push) {
        this.push = push;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
