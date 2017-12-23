package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.SupplierInfoBO;
import com.bjike.goddess.supplier.bo.SupplierInfoRegistraDataBO;
import com.bjike.goddess.supplier.dto.SupplierInfoDTO;
import com.bjike.goddess.supplier.to.SupplierInfoRegistraDataTO;
import com.bjike.goddess.supplier.to.SupplierInfoTO;

import java.util.List;

/**
 * 供应商信息管理业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 10:33 ]
 * @Description: [ 供应商信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierInfoAPI {
    /**
     * 供应商信息管理总条数
     */
    default Long countSupplierInfo(SupplierInfoDTO supplierInfoDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取供应商信息管理
     *
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 供应商信息管理列表
     *
     * @return class SupplierInfoBO
     */
    default List<SupplierInfoBO> listSupplierInfo(SupplierInfoDTO headersCustomDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param supplierInfoTO 供应商信息管理
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO addSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param supplierInfoTO 供应商信息管理
     * @return class SupplierInfoBO
     */
    default SupplierInfoBO editSupplierInfo(SupplierInfoTO supplierInfoTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteSupplierInfo(String id) throws SerException {
        return;
    }

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] exportExcel() throws SerException;

    /**
     * 导出Excel
     *
     * @throws SerException
     */
    byte[] templateExport() throws SerException;

    /**
     * 导入
     *
     * @param supplierInfoTOList 工龄补助
     */
    void importExcel(List<SupplierInfoTO> supplierInfoTOList) throws SerException;
    /**
     * 供应商信息详情获取数据
     *
     * @param id 供应商信息管理id
     * @return class SupplierInfoRegistraDataBO
     */
    default SupplierInfoRegistraDataBO linkSupplierData(String id) throws SerException {
        return null;
    }

    /**
     * 供应商信息详情编辑
     *
     * @param supplierInfoRegistraDataTO 供应商信息详情
     */
    default void addSupplierDetail(SupplierInfoRegistraDataTO supplierInfoRegistraDataTO) throws SerException {
        return;
    }
}