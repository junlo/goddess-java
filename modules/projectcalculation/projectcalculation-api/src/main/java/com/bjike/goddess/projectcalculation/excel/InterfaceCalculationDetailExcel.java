package com.bjike.goddess.projectcalculation.excel;

import com.bjike.goddess.businessproject.enums.MakeContract;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.common.utils.excel.ExcelHeader;

import java.time.LocalDate;

/**
 * 项目界面测算明细
 *
 * @Author: [ wanyi ]
 * @Date: [ 2017-12-08 03:04 ]
 * @Description: [ 项目界面测算明细 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InterfaceCalculationDetailExcel extends BaseTO {

    /**
     * 计划测算时间
     */
    @ExcelHeader(name = "计划测算时间")
    private LocalDate calculationTime;

    /**
     * 地区
     */
    @ExcelHeader(name = "地区")
    private String area;

    /**
     * 业务类型
     */
    @ExcelHeader(name = "业务类型")
    private String businessType;

    /**
     * 业务方向科目
     */
    @ExcelHeader(name = "业务方向科目")
    private String businessSubject;

    /**
     * 总包单位名称
     */
    @ExcelHeader(name = "总包单位名称")
    private String majorCompany;

    /**
     * 分包单位名称
     */
    @ExcelHeader(name = "分包单位名称")
    private String subCompany;

    /**
     * 所属项目组
     */
    @ExcelHeader(name = "所属项目组")
    private String projectGroup;

    /**
     * 类型
     */
    @ExcelHeader(name = "类型")
    private String type;

    /**
     * 专业/工期
     */
    @ExcelHeader(name = "专业/工期")
    private String major;

    /**
     * 是否有合同派工
     */
    @ExcelHeader(name = "是否有合同派工")
    private Boolean taskContract;

    /**
     * 内部项目名称/编号
     */
    @ExcelHeader(name = "内部项目名称/编号")
    private String internalProjectNum;

    /**
     * 项目负责人
     */
    @ExcelHeader(name = "项目负责人")
    private String projectCharge;

    /**
     * 是否有项目立项
     */
    @ExcelHeader(name = "是否有项目立项")
    private MakeContract makeContract;

    /**
     * 派工金额
     */
    @ExcelHeader(name = "派工金额")
    private Double taskMoney;

    /**
     * 预估金额
     */
    @ExcelHeader(name = "预估金额")
    private Double forecastMoney;

    /**
     * 派工界面A
     */
    @ExcelHeader(name = "派工界面A")
    private String dispatchInterfaceA;

    /**
     * 规模数量
     */
    @ExcelHeader(name = "规模数量")
    private Double scale;

    /**
     * 税点
     */
    @ExcelHeader(name = "税点")
    private String taxPoint;

    /**
     * 增值税发票额
     */
    @ExcelHeader(name = "增值税发票额")
    private Double invoice;

    /**
     * 付款方式
     */
    @ExcelHeader(name = "付款方式")
    private String paymentMethod;

    /**
     * 结算批次
     */
    @ExcelHeader(name = "结算批次")
    private String settlementBatch;

    /**
     * 预估开工时间
     */
    @ExcelHeader(name = "预估开工时间")
    private LocalDate expectStartDate;

    /**
     * 预计完工时间
     */
    @ExcelHeader(name = "预计完工时间")
    private LocalDate expectCompleteTime;

    /**
     * 测算进度
     */
    @ExcelHeader(name = "测算进度")
    private String calculationProgress;

    /**
     * 是否外包
     */
    @ExcelHeader(name = "是否外包")
    private Boolean epiboly;

    /**
     * 决策时间
     */
    @ExcelHeader(name = "决策时间")
    private LocalDate decisionTime;

    /**
     * 实施方
     */
    @ExcelHeader(name = "实施方")
    private String implementingParty;

    /**
     * 界面内容
     */
    @ExcelHeader(name = "界面内容")
    private String interfaceContent;

    /**
     * 条目
     */
    @ExcelHeader(name = "条目")
    private String entry;

    /**
     * 外包单价
     */
    @ExcelHeader(name = "外包单价")
    private Double outsourcedUnitPrice;

    /**
     * 条目金额
     */
    @ExcelHeader(name = "条目金额")
    private Double entryMoney;

    /**
     * 界面派工金额
     */
    @ExcelHeader(name = "界面派工金额")
    private Double interfaceAssignment;

    /**
     * 外包总金额
     */
    @ExcelHeader(name = "外包总金额")
    private Double totalAmountOfOutsourced;

    /**
     * 备注
     */
    @ExcelHeader(name = "备注")
    private String note;


    public LocalDate getCalculationTime() {
        return calculationTime;
    }

    public void setCalculationTime(LocalDate calculationTime) {
        this.calculationTime = calculationTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubject() {
        return businessSubject;
    }

    public void setBusinessSubject(String businessSubject) {
        this.businessSubject = businessSubject;
    }

    public String getMajorCompany() {
        return majorCompany;
    }

    public void setMajorCompany(String majorCompany) {
        this.majorCompany = majorCompany;
    }

    public String getSubCompany() {
        return subCompany;
    }

    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Boolean getTaskContract() {
        return taskContract;
    }

    public void setTaskContract(Boolean taskContract) {
        this.taskContract = taskContract;
    }

    public String getInternalProjectNum() {
        return internalProjectNum;
    }

    public void setInternalProjectNum(String internalProjectNum) {
        this.internalProjectNum = internalProjectNum;
    }

    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    public MakeContract getMakeContract() {
        return makeContract;
    }

    public void setMakeContract(MakeContract makeContract) {
        this.makeContract = makeContract;
    }

    public Double getTaskMoney() {
        return taskMoney;
    }

    public void setTaskMoney(Double taskMoney) {
        this.taskMoney = taskMoney;
    }

    public Double getForecastMoney() {
        return forecastMoney;
    }

    public void setForecastMoney(Double forecastMoney) {
        this.forecastMoney = forecastMoney;
    }

    public String getDispatchInterfaceA() {
        return dispatchInterfaceA;
    }

    public void setDispatchInterfaceA(String dispatchInterfaceA) {
        this.dispatchInterfaceA = dispatchInterfaceA;
    }

    public Double getScale() {
        return scale;
    }

    public void setScale(Double scale) {
        this.scale = scale;
    }

    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }

    public Double getInvoice() {
        return invoice;
    }

    public void setInvoice(Double invoice) {
        this.invoice = invoice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSettlementBatch() {
        return settlementBatch;
    }

    public void setSettlementBatch(String settlementBatch) {
        this.settlementBatch = settlementBatch;
    }

    public LocalDate getExpectStartDate() {
        return expectStartDate;
    }

    public void setExpectStartDate(LocalDate expectStartDate) {
        this.expectStartDate = expectStartDate;
    }

    public LocalDate getExpectCompleteTime() {
        return expectCompleteTime;
    }

    public void setExpectCompleteTime(LocalDate expectCompleteTime) {
        this.expectCompleteTime = expectCompleteTime;
    }

    public String getCalculationProgress() {
        return calculationProgress;
    }

    public void setCalculationProgress(String calculationProgress) {
        this.calculationProgress = calculationProgress;
    }

    public Boolean getEpiboly() {
        return epiboly;
    }

    public void setEpiboly(Boolean epiboly) {
        this.epiboly = epiboly;
    }

    public LocalDate getDecisionTime() {
        return decisionTime;
    }

    public void setDecisionTime(LocalDate decisionTime) {
        this.decisionTime = decisionTime;
    }

    public String getImplementingParty() {
        return implementingParty;
    }

    public void setImplementingParty(String implementingParty) {
        this.implementingParty = implementingParty;
    }

    public String getInterfaceContent() {
        return interfaceContent;
    }

    public void setInterfaceContent(String interfaceContent) {
        this.interfaceContent = interfaceContent;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Double getOutsourcedUnitPrice() {
        return outsourcedUnitPrice;
    }

    public void setOutsourcedUnitPrice(Double outsourcedUnitPrice) {
        this.outsourcedUnitPrice = outsourcedUnitPrice;
    }

    public Double getEntryMoney() {
        return entryMoney;
    }

    public void setEntryMoney(Double entryMoney) {
        this.entryMoney = entryMoney;
    }

    public Double getInterfaceAssignment() {
        return interfaceAssignment;
    }

    public void setInterfaceAssignment(Double interfaceAssignment) {
        this.interfaceAssignment = interfaceAssignment;
    }

    public Double getTotalAmountOfOutsourced() {
        return totalAmountOfOutsourced;
    }

    public void setTotalAmountOfOutsourced(Double totalAmountOfOutsourced) {
        this.totalAmountOfOutsourced = totalAmountOfOutsourced;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}