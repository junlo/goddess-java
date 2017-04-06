package com.bjike.goddess.eggert.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 评估信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-02 02:19 ]
 * @Description: [ 评估信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "eggert_assessmentinfo")
public class AssessmentInfo extends BaseEntity {

    /**
     * 调研目的
     */
    @Column(name = "purpose", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研目的'")
    private String purpose;

    /**
     * 调研对象
     */
    @Column(name = "object", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研对象'")
    private String object;

    /**
     * 调研内容
     */
    @Column(name = "content", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研内容'")
    private String content;

    /**
     * 调研内容明细
     */
    @Column(name = "contentSubsidiary", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研内容明细'")
    private String contentSubsidiary;

    /**
     * 调研工具
     */
    @Column(name = "tool", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '调研工具'")
    private String tool;

    /**
     * 答题时间
     */
    @Column(name = "answerTime", nullable = false, columnDefinition = "DATE   COMMENT '答题时间'")
    private LocalDate answerTime;

    /**
     * 未评估
     */
    @Column(name = "noAssessment", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '未评估'")
    private String noAssessment;

    /**
     * 已接受评估
     */
    @Column(name = "hasBeenEvaluated", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '已接受评估'")
    private String hasBeenEvaluated;

    /**
     * 通过评估
     */
    @Column(name = "byEvaluating", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '通过评估'")
    private String byEvaluating;

    /**
     * 计分项总分
     */
    @Column(name = "scoringTotalScore", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT ''")
    private String scoringTotalScore;

    /**
     * 答案
     */
    @Column(name = "answer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '答案'")
    private String answer;

    /**
     * 意见
     */
    @Column(name = "opinion", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '意见'")
    private String opinion;


    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSubsidiary() {
        return contentSubsidiary;
    }

    public void setContentSubsidiary(String contentSubsidiary) {
        this.contentSubsidiary = contentSubsidiary;
    }

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
    }

    public LocalDate getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(LocalDate answerTime) {
        this.answerTime = answerTime;
    }

    public String getNoAssessment() {
        return noAssessment;
    }

    public void setNoAssessment(String noAssessment) {
        this.noAssessment = noAssessment;
    }

    public String getHasBeenEvaluated() {
        return hasBeenEvaluated;
    }

    public void setHasBeenEvaluated(String hasBeenEvaluated) {
        this.hasBeenEvaluated = hasBeenEvaluated;
    }

    public String getByEvaluating() {
        return byEvaluating;
    }

    public void setByEvaluating(String byEvaluating) {
        this.byEvaluating = byEvaluating;
    }

    public String getScoringTotalScore() {
        return scoringTotalScore;
    }

    public void setScoringTotalScore(String scoringTotalScore) {
        this.scoringTotalScore = scoringTotalScore;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}