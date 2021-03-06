package com.bjike.goddess.attainment.api;

import com.bjike.goddess.attainment.bo.SurveyActualizeBO;
import com.bjike.goddess.attainment.dto.SurveyActualizeDTO;
import com.bjike.goddess.attainment.to.SurveyActualizeTO;
import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * 调研实施记录业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:58 ]
 * @Description: [ 调研实施记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SurveyActualizeAPI {

    /**
     * 保存
     *
     * @param to 调研实施记录传输对象
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO save(SurveyActualizeTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 调研实施记录传输对象
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO update(SurveyActualizeTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 调研实施记录数据id
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 结束调研
     *
     * @param id 调研实施记录数据id
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO over(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 调研实施记录数据传输对象
     * @return
     * @throws SerException
     */
    default List<SurveyActualizeBO> maps(SurveyActualizeDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取调研实施记录数据
     *
     * @param id 调研实施记录数据id
     * @return
     * @throws SerException
     */
    default SurveyActualizeBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }
}