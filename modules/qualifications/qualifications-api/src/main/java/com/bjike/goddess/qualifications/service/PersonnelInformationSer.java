package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.bo.PersonnelInformationBO;
import com.bjike.goddess.qualifications.dto.PersonnelInformationDTO;
import com.bjike.goddess.qualifications.entity.PersonnelInformation;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.PersonnelInformationTO;

import java.util.List;

/**
 * 人员信息资料业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:24 ]
 * @Description: [ 人员信息资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PersonnelInformationSer extends Ser<PersonnelInformation, PersonnelInformationDTO> {

    /**
     * 保存
     *
     * @param to 人员资料信息传输对象
     * @return
     * @throws SerException
     */
    default PersonnelInformationBO save(PersonnelInformationTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 人员资料信息传输对象
     * @return
     * @throws SerException
     */
    default PersonnelInformationBO update(PersonnelInformationTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 人员资料信息id
     * @return
     * @throws SerException
     */
    default PersonnelInformationBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 获取全部
     *
     * @return
     * @throws SerException
     */
    default List<PersonnelInformationBO> all() throws SerException {
        return null;
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return
     * @throws SerException
     */
    default PersonnelInformationBO getById(String id) throws SerException {
        return null;
    }


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
}