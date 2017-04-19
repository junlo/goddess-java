package com.bjike.goddess.staffactivity.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.staffactivity.bo.ActivityFundSummaryBO;
import com.bjike.goddess.staffactivity.bo.ActivitySchemeBO;
import com.bjike.goddess.staffactivity.dto.ActivitySchemeDTO;
import com.bjike.goddess.staffactivity.entity.ActivityScheme;
import com.bjike.goddess.staffactivity.to.ActivitySchemeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 活动方案业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffactivitySerCache")
@Service
public class ActivitySchemeSerImpl extends ServiceImpl<ActivityScheme, ActivitySchemeDTO> implements ActivitySchemeSer {

    /**
     * 分页查询活动方案
     *
     * @param dto 活动方案dto
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    @Override
    public List<ActivitySchemeBO> list(ActivitySchemeDTO dto) throws SerException {
        List<ActivityScheme> list = super.findByPage(dto);
        List<ActivitySchemeBO> listBO = BeanTransform.copyProperties(list, ActivitySchemeBO.class);
        return listBO;
    }

    /**
     * 保存活动方案
     *
     * @param to 活动方案to
     * @return class ActivitySchemeBO
     * @throws SerException
     */
    @Override
    @Transactional
    public ActivitySchemeBO save(ActivitySchemeTO to) throws SerException {
        ActivityScheme entity = BeanTransform.copyProperties(to, ActivityScheme.class, true);
        entity = super.save(entity);
        ActivitySchemeBO bo = BeanTransform.copyProperties(entity, ActivitySchemeBO.class);
        return bo;
    }

    /**
     * 根据id删除活动方案
     *
     * @param id 活动方案唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新活动方案
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(ActivitySchemeTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            ActivityScheme model = super.findById(to.getId());
            if (model != null) {
                updateActivityScheme(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新活动方案
     *
     * @param to 活动方案to
     * @param model 活动方案
     * @throws SerException
     */
    private void updateActivityScheme(ActivitySchemeTO to, ActivityScheme model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 上传文件
     *
     * @param maps 文件名，文件字节
     * @param path 上传路径
     * @throws SerException
     */
    @Override
    @Transactional
    public void upload(Map<String, byte[]> maps, String path) throws SerException {

    }

    /**
     * 运营商务部意见
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional
    public void yYOpinion(ActivitySchemeTO to) throws SerException {
        update(to);
    }

    /**
     * 总经办意见
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional
    public void zjbOpinion(ActivitySchemeTO to) throws SerException {
        update(to);
    }

    /**
     * 是否持续开展
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional
    public void ifContinueLaunch(ActivitySchemeTO to) throws SerException {
        update(to);
    }

    /**
     * 运营资金评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional
    public void yYFundEvaluate(ActivitySchemeTO to) throws SerException {
        update(to);
    }

    /**
     * 监督者评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional
    public void supervisorEvaluate(ActivitySchemeTO to) throws SerException {
        update(to);
    }

    /**
     * 总经办评价
     *
     * @param to 活动方案to
     * @throws SerException
     */
    @Override
    @Transactional
    public void zjbEvaluate(ActivitySchemeTO to) throws SerException {
        update(to);
    }

    /**
     * 公司各地区活动经费汇总
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return class ActivityFundSummaryBO
     * @throws SerException
     */
    @Override
    public List<ActivityFundSummaryBO> activityFundSummary(String startTime, String endTime) throws SerException {
        LocalDateTime beginTime = DateUtil.parseDateTime(startTime);
        LocalDateTime finishTime = DateUtil.parseDateTime(endTime);
        LocalDateTime[] activityTime = new LocalDateTime[]{beginTime, finishTime};
        String timeSlot = getTimeSlot(beginTime, finishTime);
        List<String> areas = findAllAreas();
        List<ActivityFundSummaryBO> boList = new ArrayList<>(0);
        for (String area : areas) {
            ActivitySchemeDTO dto = new ActivitySchemeDTO();
            dto.getConditions().add(Restrict.between("activityTime", activityTime));
            dto.getConditions().add(Restrict.eq("area", area));
            List<ActivityScheme> schemeList = super.findByCis(dto);
            Double activityFund = schemeList.stream().mapToDouble(c -> c.getTotalActivityFund()).sum();
            ActivityFundSummaryBO bo = new ActivityFundSummaryBO();
            bo.setActivityTime(timeSlot);
            bo.setArea(area);
            bo.setActivityFund(activityFund);
            boList.add(bo);
        }

        //计算合计项
        countCombinedItem(timeSlot, areas, boList);
        return boList;
    }

    private void countCombinedItem(String timeSlot, List<String> areas, List<ActivityFundSummaryBO> boList) {
        String countAreaNo = String.valueOf(areas.size());
        Double totalFund = boList.stream().mapToDouble(c -> c.getActivityFund()).sum();
        ActivityFundSummaryBO bo = new ActivityFundSummaryBO();
        bo.setActivityTime(timeSlot);
        bo.setArea(countAreaNo);
        bo.setActivityFund(totalFund);
        boList.add(bo);
    }

    /**
     * 获取时间区间
     *
     * @param beginTime 起始时间
     * @param finishTime 结束时间
     * @return
     */
    private String getTimeSlot(LocalDateTime beginTime, LocalDateTime finishTime) {
        String beginTimeString = beginTime.toString().replace('T', ' ');
        String finishTimeString = finishTime.toString().replace('T', ' ');
        return new StringBuilder(beginTimeString).append("~").append(finishTimeString).toString();
    }


    /**
     * 获取活动方案中的所有地区
     *
     * @return 所有地区
     * @throws SerException
     */
    private List<String> findAllAreas() throws SerException {
        Set<String> areaSet = new HashSet<String>();
        List<ActivityScheme> schemeList = super.findAll();
        for (ActivityScheme scheme : schemeList) {
            if (StringUtils.isNotBlank(scheme.getArea())) {
                areaSet.add(scheme.getArea());
            }
        }
        List<String> areaList = new ArrayList<>(areaSet);
        return areaList;
    }
}