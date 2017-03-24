package com.bjike.goddess.businessproject.service;

import com.bjike.goddess.businessproject.bo.CollectEmailBO;
import com.bjike.goddess.businessproject.dto.CollectEmailDTO;
import com.bjike.goddess.businessproject.dto.DispatchSheetDTO;
import com.bjike.goddess.businessproject.entity.CollectEmail;
import com.bjike.goddess.businessproject.enums.BusinessCooperate;
import com.bjike.goddess.businessproject.enums.BusinessType;
import com.bjike.goddess.businessproject.enums.ContractProperty;
import com.bjike.goddess.businessproject.to.CollectEmailTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 商务项目合同邮件发送定制业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.890 ]
 * @Description: [ 商务项目合同邮件发送定制业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessprojectSerCache")
@Service
public class CollectEmailSerImpl extends ServiceImpl<CollectEmail, CollectEmailDTO> implements CollectEmailSer {

    @Autowired
    private UserAPI userAPI;
    @Autowired
    private SiginManageSer siginManageAPI;
    @Autowired
    private BaseInfoManageSer baseInfoManageAPI;
    @Autowired
    private DispatchSheetSer dispatchSheetAPI;


    @Cacheable
    @Override
    public List<CollectEmailBO> listCollectEmail(CollectEmailDTO collectEmailDTO) throws SerException {
        List<CollectEmail> list = super.findByCis(collectEmailDTO, true);
        return BeanTransform.copyProperties(list, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO addCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setCreateTime(LocalDateTime.now());
        collectEmail.setStatus(Status.THAW);
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));
        //设置上次发送时间
        collectEmail.setLastSendTime(LocalDateTime.now());

        super.save(collectEmail);

        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CollectEmailBO editCollectEmail(CollectEmailTO collectEmailTO) throws SerException {
        List<String> sendObjectList = collectEmailTO.getSendObjectList();
        StringBuffer emails = new StringBuffer("");
        if (sendObjectList != null && sendObjectList.size() > 0) {
            for (String emailStr : sendObjectList) {
                emails.append(emailStr + ";");
            }
        }
        CollectEmail collectEmail = BeanTransform.copyProperties(collectEmailTO, CollectEmail.class, true);
        collectEmail.setModifyTime(LocalDateTime.now());
        collectEmail.setCreatePersion(userAPI.currentUser().getUsername());

        //设置发送间隔
        String unit = sendUnitConverse(collectEmail.getCollectSendUnit().getCode());
        collectEmail.setSendNumAndUnit(collectEmail.getSendNum() + unit);

        //设置发送对象
        collectEmail.setSendObject(String.valueOf(emails));

        super.update(collectEmail);
        return BeanTransform.copyProperties(collectEmail, CollectEmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteCollectEmail(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void congealCollectEmail(String id) throws SerException {
        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.CONGEAL);
        super.update(collectEmail);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public void thawCollectEmail(String id) throws SerException {
        CollectEmail collectEmail = super.findById(id);
        collectEmail.setStatus(Status.THAW);
        super.update(collectEmail);
    }


    @Cacheable
    @Override
    public List<CollectEmailBO> collectCollectEmail(String[] area) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<String> areas = Arrays.asList(area);
//        if (areas == null || areas.size() == 0) {
//            areas = siginManageAPI.listArea();
//        }
        //先查有几个地区
//        List<String> areas = siginManageAPI.listArea();

        //再获取业务类型
        List<Integer> busType = Arrays.asList(BusinessType.MOBILECOMMUNICATION.getCode(), BusinessType.SOFTDEVELOP.getCode()
                , BusinessType.INTELLIGENCESYSTEM.getCode(), BusinessType.ADVERT.getCode());
        //再获取合作方式
        List<Integer> cooperStatus = Arrays.asList(BusinessCooperate.RENTCONTRACT.getCode(),
                BusinessCooperate.CHARCONTRACT.getCode(), BusinessCooperate.DISTRIBUTECONTRACT.getCode(), BusinessCooperate.SALECONTRACT.getCode());
        //再获合同属性
        List<Integer> distribute = Arrays.asList(ContractProperty.FRAMECONTRACT.getCode(), ContractProperty.SINGLECONTRACT.getCode());

        //立项情况
        List<String> makeProjects = Arrays.asList("已立项", "未立项");

        //签订合同情况
        List<String> signConditions = Arrays.asList("已签订", "未签订");


        for (String areaStr : areas) {

            String[] fields = new String[]{"count", "type", "remark"};

            /**
             * 再获取业务类型
             */
            String sql = "select count(*) as count , area as type ,businessType as enumConvert  from  businessproject_siginmanage " +
                    "where businessType in (" + busType + ") and area = " + areaStr + " group by area , businessType order by businessType asc  ";
            List<Map<String, String>> busTypeMapList = new ArrayList<>();
            List<CollectEmailBO> collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

            /**
             * 再获取合作方式
             */
            sql = "select count(*) as count , area as type ,businessCooperate as enumConvert  from  businessproject_siginmanage " +
                    "where businessCooperate in (" + cooperStatus + ") and area = " + areaStr + " group by area , businessCooperate order by businessCooperate asc  ";
            List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);

            /**
             * 再获合同属性
             */
            sql = "select count(*) as count , area as type ,contractProperty as enumConvert  from  businessproject_siginmanage " +
                    "where contractProperty in (" + distribute + ") and area = " + areaStr + " group by area , contractProperty order by contractProperty asc  ";
            List<Map<String, String>> propertyMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

            /**
             * 立项情况
             */
            sql = "select count(*) as count , area as type ,makeProject as remark  from  businessproject_siginmanage " +
                    "where makeProject in (" + makeProjects + ") and area = " + areaStr + " group by makeProject , area order by makeProject asc  ";
            List<Map<String, String>> makeProjectMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            makeProjectMapList = sqlQueryString(makeProjects, collectEmailBOS, makeProjectMapList);
            /**
             * 签订合同情况
             */
            sql = "select count(*) as count , area as type ,siginStatus as remark  from  businessproject_siginmanage " +
                    "where siginStatus in (" + signConditions + ") and area = " + areaStr + " group by siginStatus , area order by siginStatus asc  ";
            List<Map<String, String>> signMapList = new ArrayList<>();
            collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);


            CollectEmailBO collectEmailBO = new CollectEmailBO();
            collectEmailBO.setType(areaStr);
            collectEmailBO.setBusTypeMap(busTypeMapList);
            collectEmailBO.setCooperWaysMap(cooperStatusMapList);
            collectEmailBO.setContractPropertyMap(propertyMapList);
            collectEmailBO.setMakeProjectMap(makeProjectMapList);
            collectEmailBO.setSignMap(signMapList);
            collectEmailBOList.add(collectEmailBO);
        }

        //合计计算合同签订与立项每个业务类型等总数
        collectEmailBOList = calcuteSiginCount(busType, cooperStatus, distribute, makeProjects, signConditions, collectEmailBOList);

        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectBaseInfoEmail(String[] firstCompany) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();
        List<String> firstCompanys = Arrays.asList(firstCompany);

        //再获取业务类型
        List<Integer> busType = Arrays.asList(BusinessType.MOBILECOMMUNICATION.getCode(), BusinessType.SOFTDEVELOP.getCode()
                , BusinessType.INTELLIGENCESYSTEM.getCode(), BusinessType.ADVERT.getCode());
        //再获取合作方式
        List<Integer> cooperStatus = Arrays.asList(BusinessCooperate.RENTCONTRACT.getCode(),
                BusinessCooperate.CHARCONTRACT.getCode(), BusinessCooperate.DISTRIBUTECONTRACT.getCode(), BusinessCooperate.SALECONTRACT.getCode());
        //再获合同属性
        List<Integer> distribute = Arrays.asList(ContractProperty.FRAMECONTRACT.getCode(), ContractProperty.SINGLECONTRACT.getCode());

        //合同归档情况
        List<String> signConditions = Arrays.asList("已归档", "未归档");


        for (String firstCompanyStr : firstCompanys) {

            String[] fields = new String[]{"count", "type", "remark"};

            /**
             * 再获取业务类型
             */
            String sql = "select count(*) as count , firstCompany as type ,businessType as enumConvert  from  businessproject_baseinfomanage " +
                    "where businessType in (" + busType + ") and firstCompany = " + firstCompanyStr + " group by firstCompany , businessType order by businessType asc  ";
            List<Map<String, String>> busTypeMapList = new ArrayList<>();
            List<CollectEmailBO> collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

            /**
             * 再获取合作方式
             */
            sql = "select count(*) as count , firstCompany as type ,businessCooperate as enumConvert  from  businessproject_baseinfomanage " +
                    "where businessCooperate in (" + cooperStatus + ") and firstCompany = " + firstCompanyStr + " group by firstCompany , businessCooperate order by businessCooperate asc  ";
            List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
            collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);

            /**
             * 再获合同属性
             */
            sql = "select count(*) as count , firstCompany as type ,contractProperty as enumConvert  from  businessproject_baseinfomanage " +
                    "where contractProperty in (" + distribute + ") and firstCompany = " + firstCompanyStr + " group by firstCompany , contractProperty order by contractProperty asc  ";
            List<Map<String, String>> propertyMapList = new ArrayList<>();
            collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

            /**
             * 合同归档情况
             */
            sql = "select count(*) as count , firstCompany as type ,fileCondition as remark  from  businessproject_baseinfomanage " +
                    "where fileCondition in (" + signConditions + ") and firstCompany = " + firstCompanyStr + " group by fileCondition , firstCompany order by siginStatus asc  ";
            List<Map<String, String>> signMapList = new ArrayList<>();
            collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
            signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);


            CollectEmailBO collectEmailBO = new CollectEmailBO();
            collectEmailBO.setType(firstCompanyStr);
            collectEmailBO.setBusTypeMap(busTypeMapList);
            collectEmailBO.setCooperWaysMap(cooperStatusMapList);
            collectEmailBO.setContractPropertyMap(propertyMapList);
            collectEmailBO.setSignMap(signMapList);
            collectEmailBOList.add(collectEmailBO);
        }

        //合计计算合同基本信息每个业务类型等总数
        collectEmailBOList = calcuteBaseInfoCount(busType, cooperStatus, distribute,  signConditions, collectEmailBOList);

        return collectEmailBOList;
    }

    @Override
    public List<CollectEmailBO> collectDispatchEmail(String[] area) throws SerException {
        List<CollectEmailBO> collectEmailBOList = new ArrayList<>();

        String[] fields = new String[]{"count", "type", "remark"};
        for(String areaStr :area ){
            String sql = "select count(*) as count  , area as type , dispatchProject as remark from  businessproject_dispatchsheet " +
                    "where  area = " + areaStr + " group by dispatchProject   ";

            List<CollectEmailBO> collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fields);
            Integer countDispatchName = 0;
            if( collectEmailBOS != null && collectEmailBOS.size() >0 ){
                countDispatchName = collectEmailBOS.get(0).getCounts();
            }

            //已完工
            sql = "select count(*) as count , area as type , completeProject as remark from  businessproject_dispatchsheet " +
                    "where  area = " + areaStr + " and completeProject ='已完工'   ";
            collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fields);
            Integer countComplete = 0;
            if( collectEmailBOS != null && collectEmailBOS.size() >0 ){
                countComplete = collectEmailBOS.get(0).getCounts();
            }

            //未完工
            sql = "select count(*) as count , area as type , completeProject as remark from  businessproject_dispatchsheet " +
                    "where  area = " + areaStr + " and completeProject ='未完工'   ";
            collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fields);
            Integer countNotComplete = 0;
            if( collectEmailBOS != null && collectEmailBOS.size() >0 ){
                countNotComplete = collectEmailBOS.get(0).getCounts();
            }

            String[] fieldMoney = new String[]{"money", "type"};
            //金额
            sql = "select sum(money) as money , area as type  from  businessproject_dispatchsheet " +
                    "where  area = " + areaStr ;
            collectEmailBOS = dispatchSheetAPI.findBySql(sql, CollectEmailBO.class, fieldMoney);
            Double money = 0d;
            if( collectEmailBOS != null && collectEmailBOS.size() >0 ){
                money = collectEmailBOS.get(0).getMoney();
            }

            CollectEmailBO cbo = new CollectEmailBO();
            cbo.setType( areaStr );
            cbo.setDispatchProjectCount( countDispatchName );
            cbo.setComplete(  countComplete );
            cbo.setNotComplete( countNotComplete );
            cbo.setMoney( money );

            collectEmailBOList.add( cbo );
        }

        //计算合计
        int sumProjectAll = collectEmailBOList.stream().mapToInt(CollectEmailBO::getDispatchProjectCount).sum();
        int sumCompleteAll = collectEmailBOList.stream().mapToInt(CollectEmailBO::getComplete).sum();
        int sumNotCompleteAll = collectEmailBOList.stream().mapToInt(CollectEmailBO::getNotComplete).sum();
        Double sumMoneyAll = collectEmailBOList.stream().mapToDouble(CollectEmailBO::getMoney).sum();

        CollectEmailBO cbo = new CollectEmailBO();
        cbo.setType( "合计" );
        cbo.setDispatchProjectCount( sumProjectAll );
        cbo.setComplete(  sumCompleteAll );
        cbo.setNotComplete( sumNotCompleteAll );
        cbo.setMoney( sumMoneyAll );

        collectEmailBOList.add( cbo );

        return  collectEmailBOList;
    }

    /**
     * 合计计算合同基本信息每个业务类型等总数
     * @param busType
     * @param cooperStatus
     * @param distribute
     * @param signConditions
     * @param collectEmailBOList
     * @return
     * @throws SerException
     */
    public List<CollectEmailBO> calcuteBaseInfoCount(List<Integer> busType, List<Integer> cooperStatus,
                                                  List<Integer> distribute, List<String> signConditions,
                                                  List<CollectEmailBO> collectEmailBOList) throws SerException {
        String[] fields = new String[]{"count", "remark"};

        /**
         * 再获取业务类型
         */
        String sql = "select count(*) as count , businessType as enumConvert  from  businessproject_baseinfomanage " +
                "where businessType in (" + busType + ")  group by businessType order by businessType asc  ";
        List<Map<String, String>> busTypeMapList = new ArrayList<>();
        List<CollectEmailBO> collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

        /**
         * 再获取合作方式
         */
        sql = "select count(*) as count , businessCooperate as enumConvert  from  businessproject_baseinfomanage " +
                "where businessCooperate in (" + cooperStatus + ")  group by  businessCooperate order by businessCooperate asc  ";
        List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
        collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);


        /**
         * 再获合同属性
         */
        sql = "select count(*) as count  ,contractProperty as enumConvert  from  businessproject_baseinfomanage " +
                "where contractProperty in (" + distribute + ")  group by  contractProperty order by contractProperty asc  ";
        List<Map<String, String>> propertyMapList = new ArrayList<>();
        collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

        /**
         * 合同归档情况
         */
        sql = "select count(*) as count , fileCondition as remark  from  businessproject_baseinfomanage " +
                "where fileCondition in (" + signConditions + ")  group by fileCondition  order by fileCondition asc  ";
        List<Map<String, String>> signMapList = new ArrayList<>();
        collectEmailBOS = baseInfoManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);


        CollectEmailBO collectEmailBO = new CollectEmailBO();
        collectEmailBO.setType("合计");
        collectEmailBO.setBusTypeMap(busTypeMapList);
        collectEmailBO.setCooperWaysMap(cooperStatusMapList);
        collectEmailBO.setContractPropertyMap(propertyMapList);
        collectEmailBO.setSignMap(signMapList);
        collectEmailBOList.add(collectEmailBO);

        return collectEmailBOList;
    }


    /**
     * 合计计算合同签订与立项每个业务类型等总数
     * @param busType
     * @param cooperStatus
     * @param distribute
     * @param makeProjects
     * @param signConditions
     * @param collectEmailBOList
     * @return
     * @throws SerException
     */
    public List<CollectEmailBO> calcuteSiginCount(List<Integer> busType, List<Integer> cooperStatus,
                                             List<Integer> distribute, List<String> makeProjects,
                                             List<String> signConditions,
                                             List<CollectEmailBO> collectEmailBOList) throws SerException {
        String[] fields = new String[]{"count", "remark"};

        /**
         * 再获取业务类型
         */
        String sql = "select count(*) as count , businessType as enumConvert  from  businessproject_siginmanage " +
                "where businessType in (" + busType + ")  group by  businessType order by businessType asc  ";
        List<Map<String, String>> busTypeMapList = new ArrayList<>();
        List<CollectEmailBO> collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        busTypeMapList = sqlQueryInt("BusinessType", busType, collectEmailBOS, busTypeMapList);

        /**
         * 再获取合作方式
         */
        sql = "select count(*) as count , businessCooperate as enumConvert  from  businessproject_siginmanage " +
                "where businessCooperate in (" + cooperStatus + ")  group by  businessCooperate order by businessCooperate asc  ";
        List<Map<String, String>> cooperStatusMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        cooperStatusMapList = sqlQueryInt("BusinessCooperate", cooperStatus, collectEmailBOS, cooperStatusMapList);


        /**
         * 再获合同属性
         */
        sql = "select count(*) as count  ,contractProperty as enumConvert  from  businessproject_siginmanage " +
                "where contractProperty in (" + distribute + ")  group by  contractProperty order by contractProperty asc  ";
        List<Map<String, String>> propertyMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        propertyMapList = sqlQueryInt("ContractProperty", distribute, collectEmailBOS, propertyMapList);

        /**
         * 立项情况
         */
        sql = "select count(*) as count , makeProject as remark  from  businessproject_siginmanage " +
                "where makeProject in (" + makeProjects + ")  group by makeProject  order by makeProject asc  ";
        List<Map<String, String>> makeProjectMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        makeProjectMapList = sqlQueryString(makeProjects, collectEmailBOS, makeProjectMapList);
        /**
         * 签订合同情况
         */
        sql = "select count(*) as count , siginStatus as remark  from  businessproject_siginmanage " +
                "where siginStatus in (" + signConditions + ")  group by siginStatus  order by siginStatus asc  ";
        List<Map<String, String>> signMapList = new ArrayList<>();
        collectEmailBOS = siginManageAPI.findBySql(sql, CollectEmailBO.class, fields);
        signMapList = sqlQueryString(signConditions, collectEmailBOS, signMapList);


        CollectEmailBO collectEmailBO = new CollectEmailBO();
        collectEmailBO.setType("合计");
        collectEmailBO.setBusTypeMap(busTypeMapList);
        collectEmailBO.setCooperWaysMap(cooperStatusMapList);
        collectEmailBO.setContractPropertyMap(propertyMapList);
        collectEmailBO.setMakeProjectMap(makeProjectMapList);
        collectEmailBO.setSignMap(signMapList);
        collectEmailBOList.add(collectEmailBO);

        return collectEmailBOList;
    }

    /**
     * 发送间隔单位转换
     */
    private String sendUnitConverse(int code) {
        String unit = "";
        switch (code) {
            case 0:
                unit = "分钟";
                break;
            case 1:
                unit = "小时";
                break;
            case 2:
                unit = "天";
                break;
            case 3:
                unit = "周";
                break;
            case 4:
                unit = "月";
                break;
            case 5:
                unit = "季度";
                break;
            case 6:
                unit = "年";
                break;
            default:
                unit = "";
                break;
        }
        return unit;
    }

    /**
     * 数据库查询返回，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryString(List<String> obj, List<CollectEmailBO> collectEmailBOS, List<Map<String, String>> mapList) throws SerException {
        if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
            if (obj.size() == collectEmailBOS.size()) {
                for (CollectEmailBO cbo : collectEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    areaMap.put("remark", cbo.getRemark());
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (collectEmailBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (CollectEmailBO cb : collectEmailBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的  如：地区
                List<String> diffrent = new ArrayList<>();
                for (String o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add(o);
                    }
                }

                //存map
                for (String o : obj) {
                    for (CollectEmailBO cbo : collectEmailBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            areaMap.put("remark", cbo.getRemark());
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
                        } else {
                            areaMap.put("remark", o);
                            areaMap.put("count", 0 + "");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }


    /**
     * 将数据库返回的枚举int值转换，然后添加map数组
     */
    public List<Map<String, String>> sqlQueryInt(String enumStr, List<Integer> obj, List<CollectEmailBO> collectEmailBOS, List<Map<String, String>> mapList) throws SerException {

        if (collectEmailBOS != null && collectEmailBOS.size() > 0) {
            if (obj.size() == collectEmailBOS.size()) {
                for (CollectEmailBO cbo : collectEmailBOS) {
                    Map<String, String> areaMap = new HashMap<>();
                    if (enumStr.equals("BusinessType")) {
                        areaMap.put("remark", BusinessType.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("BusinessCooperate")) {
                        areaMap.put("remark", BusinessCooperate.getStrConvert(cbo.getEnumConvert()));
                    } else if (enumStr.equals("ContractProperty")) {
                        areaMap.put("remark", ContractProperty.getStrConvert(cbo.getEnumConvert()));
                    }
                    areaMap.put("count", String.valueOf(cbo.getCounts()));
                    mapList.add(areaMap);
                }
            } else if (collectEmailBOS.size() < obj.size()) {
                List<String> cbStr = new ArrayList<>();
                for (CollectEmailBO cb : collectEmailBOS) {
                    cbStr.add(cb.getRemark());
                }

                //获取到所有不同的int值  如：枚举
                List<Integer> diffrent = new ArrayList<>();
                for (Integer o : obj) {
                    if (!cbStr.contains(o)) {
                        diffrent.add(o);
                    }
                }

                //存map
                for (Integer o : obj) {
                    for (CollectEmailBO cbo : collectEmailBOS) {
                        Map<String, String> areaMap = new HashMap<>();
                        if (!diffrent.contains(o) && cbo.getRemark().equals(o)) {
                            if (enumStr.equals("BusinessType")) {
                                areaMap.put("remark", BusinessType.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("BusinessCooperate")) {
                                areaMap.put("remark", BusinessCooperate.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ContractProperty")) {
                                areaMap.put("remark", ContractProperty.getStrConvert(cbo.getEnumConvert()));
                            }
                            areaMap.put("count", String.valueOf(cbo.getCounts()));
                        } else {
                            if (enumStr.equals("BusinessType")) {
                                areaMap.put("remark", BusinessType.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("BusinessCooperate")) {
                                areaMap.put("remark", BusinessCooperate.getStrConvert(cbo.getEnumConvert()));
                            } else if (enumStr.equals("ContractProperty")) {
                                areaMap.put("remark", ContractProperty.getStrConvert(cbo.getEnumConvert()));
                            }
                            areaMap.put("count", 0 + "");
                        }
                        mapList.add(areaMap);
                    }
                }

            }
        }
        return mapList;
    }


}