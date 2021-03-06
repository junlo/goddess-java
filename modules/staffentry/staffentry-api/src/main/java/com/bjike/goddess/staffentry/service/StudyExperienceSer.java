package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.dto.StudyExperienceDTO;
import com.bjike.goddess.staffentry.entity.StudyExperience;
import com.bjike.goddess.staffentry.to.StudyExperienceTO;

import java.util.List;

/**
 * 学习经历业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:56]
 * @Description: [学习经历业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface StudyExperienceSer extends Ser<StudyExperience,StudyExperienceDTO> {

    /**
     * 添加学习经历
     * @param studyExperienceTOs 学习经历数据集合
     * @throws SerException
     */
    default void insertStudyExperiences(List<StudyExperienceTO> studyExperienceTOs ) throws SerException {return ;}
}
