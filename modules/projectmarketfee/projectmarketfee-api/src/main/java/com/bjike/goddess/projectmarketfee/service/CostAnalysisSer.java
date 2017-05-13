package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisBO;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisCountBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.entity.CostAnalysis;
import com.bjike.goddess.projectmarketfee.to.CostAnalysisTO;

import java.util.List;

/**
 * 费用效益分析业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:35 ]
 * @Description: [ 费用效益分析业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostAnalysisSer extends Ser<CostAnalysis, CostAnalysisDTO> {
    /**
     * 添加
     *
     * @param to 费用效益分析信息
     * @return class CostAnalysisBO
     * @throws SerException
     */
    default CostAnalysisBO save(CostAnalysisTO to) throws SerException {
        return null;

    }

    /**
     * 编辑
     *
     * @param to 费用效益分析信息
     * @throws SerException
     */
    default void edit(CostAnalysisTO to) throws SerException {
    }

    /**
     * 分页查找
     *
     * @param dto 费用效益分页信息
     * @return class CostAnalysisBO
     * @throws SerException
     */
    default List<CostAnalysisBO> list(CostAnalysisDTO dto) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 费用效益分析id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }

    /**
     * 通过id查找
     *
     * @param id 费用效益分析id
     * @return class CostAnalysisBO
     * @throws SerException
     */
    default CostAnalysisBO findByID(String id) throws SerException {
        return null;
    }

    /**
     * 地区汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class CostAnalysisCountBO
     * @throws SerException
     */
    default List<CostAnalysisCountBO> arrivalCount(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 项目组汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class CostAnalysisCountBO
     * @throws SerException
     */
    default List<CostAnalysisCountBO> projectGroupCount(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 项目名称汇总
     *
     * @param year  年份
     * @param month 月份
     * @return class CostAnalysisCountBO
     * @throws SerException
     */
    default List<CostAnalysisCountBO> projectNameCount(Integer year, Integer month) throws SerException {
        return null;
    }

    /**
     * 查找明细
     *
     * @param arrival      地区
     * @param projectGroup 项目组
     * @param projectName  项目名称
     * @return class CostAnalysisBO
     * @throws SerException
     */
    default List<CostAnalysisBO> findDetail(String arrival, String projectGroup, String projectName) throws SerException {
        return null;
    }
}