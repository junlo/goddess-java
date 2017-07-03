package com.bjike.goddess.marketactivitymanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketactivitymanage.bo.MarketServeRecordBO;
import com.bjike.goddess.marketactivitymanage.dto.MarketServeRecordDTO;
import com.bjike.goddess.marketactivitymanage.entity.MarketServeRecord;
import com.bjike.goddess.marketactivitymanage.to.CustomerInfoTO;
import com.bjike.goddess.marketactivitymanage.to.GuidePermissionTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordImprotTO;
import com.bjike.goddess.marketactivitymanage.to.MarketServeRecordTO;
import com.bjike.goddess.marketactivitymanage.type.AuditType;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 市场招待记录业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-18T10:56:16.081 ]
 * @Description: [ 市场招待记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarketServeRecordSer extends Ser<MarketServeRecord, MarketServeRecordDTO> {


    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 分页查询市场招待记录
     *
     * @param dto 市场招待记录dto
     * @return class MarketServeRecordBO
     * @throws SerException
     */
    List<MarketServeRecordBO> list(MarketServeRecordDTO dto) throws SerException;

    /**
     * 保存市场招待记录
     *
     * @param to
     * @return
     * @throws SerException
     */
    MarketServeRecordBO save(MarketServeRecordTO to) throws SerException;

    /**
     * 根据id删除市场招待记录
     *
     * @param id 市场招待记录唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新市场招待记录
     *
     * @param to
     * @throws SerException
     */
    void update(MarketServeRecordTO to) throws SerException;

    /**
     * 资金模块意见
     *
     * @param id 市场招待记录唯一标识
     * @param fundModuleOpinion 资金模块意见
     * @throws SerException
     */
    void fundModuleOpinion(String id, String fundModuleOpinion) throws SerException;

    /**
     * 决策层审核意见
     *
     * @param id 市场招待记录唯一标识
     * @param executiveAuditOpinion 决策层审核意见
     * @throws SerException
     */
    void executiveOpinion(String id, AuditType executiveAuditOpinion) throws SerException;

    /**
     * 查看详情
     *
     * @param id 市场招待记录唯一标识
     * @return class MarketServeApplyBO
     * @throws SerException
     */
    MarketServeRecordBO checkDetails(String id) throws SerException;

    /**
     * 添加客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    void addClientInfo(CustomerInfoTO to) throws SerException;

    /**
     * 编辑客户信息
     *
     * @param to 客户信息to
     * @throws SerException
     */
    void editClientInfo(CustomerInfoTO to) throws SerException;

    /**
     * 查看所有的项目名
     *
     * @throws SerException
     */
    List<String> findAllProjectName() throws SerException;

    /**
     * 查看所有的地区
     *
     * @throws SerException
     */
    List<String> findAllAreas() throws SerException;
    /**
     * 导出Excel
     * @param areas
     * @param startTime
     * @param endTime
     * @throws SerException
     */
    byte[] exportExcel(String[] areas, String startTime, String endTime) throws SerException;

    /**
     * 导出Excel
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     *  导入
     * @param marketServeRecordImprotTOS 市场活动申请记录
     */
    void importExcel(List<MarketServeRecordImprotTO> marketServeRecordImprotTOS) throws SerException;
}