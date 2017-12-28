package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.LeaseCarCostBO;
import com.bjike.goddess.dispatchcar.dto.LeaseCarCostDTO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;
import com.bjike.goddess.dispatchcar.to.LeaseCarCostTO;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.OpinionBO;

import java.util.List;

/**
 * 租车费用基本信息业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-13 10:55 ]
 * @Description: [ 租车费用基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LeaseCarCostAPI {

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
     * 新增租车费用基本信息
     *
     * @param to 租车费用基本信息
     * @return 租车费用基本信息
     */
    LeaseCarCostBO addModel(LeaseCarCostTO to) throws SerException;

    /**
     * 编辑租车费用基本信息
     *
     * @param to 租车费用基本信息
     * @return 租车费用基本信息
     */
    LeaseCarCostBO editModel(LeaseCarCostTO to) throws SerException;

    /**
     * 删除租车费用基本信息
     *
     * @param id 租车费用基本信息id
     */
    void delete(String id) throws SerException;

    /**
     * 分页查询租车费用基本信息
     *
     * @param dto 分页条件
     * @return 租车费用基本信息结果集
     */
    List<LeaseCarCostBO> pageList(LeaseCarCostDTO dto) throws SerException;

    /**
     * 查询总记录数
     *
     * @param dto 查询条件
     * @return 总记录数
     */
    Long count(LeaseCarCostDTO dto) throws SerException;

    LeaseCarCostBO findById(String id) throws SerException ;

    /**
     * 查出所有未冻结的部门
     */
    List<OpinionBO> findDeapartment() throws SerException;

    /**
     * 查询所有地区
     */
    List<AreaBO> findArea() throws SerException;
}