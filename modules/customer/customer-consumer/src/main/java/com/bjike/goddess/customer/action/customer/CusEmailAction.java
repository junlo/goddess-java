package com.bjike.goddess.customer.action.customer;

import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.interceptor.login.LoginAuth;
import com.bjike.goddess.common.consumer.restful.ActResult;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.api.CusEmailAPI;
import com.bjike.goddess.customer.api.CusPermissionAPI;
import com.bjike.goddess.customer.bo.CusEmailBO;
import com.bjike.goddess.customer.dto.CusEmailDTO;
import com.bjike.goddess.customer.to.CusEmailTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;
import com.bjike.goddess.customer.vo.CusEmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 客户邮件发送定制
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-16T19:08:18.885 ]
 * @Description: [ 客户邮件发送定制 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("cusemail")
public class CusEmailAction {

    @Autowired
    private CusEmailAPI cusEmailAPI;
    @Autowired
    private CusPermissionAPI cusPermissionAPI;


    /**
     * 功能导航权限
     *
     * @param guidePermissionTO 导航类型数据
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/guidePermission")
    public Result guidePermission(@Validated(GuidePermissionTO.TestAdd.class) GuidePermissionTO guidePermissionTO, BindingResult bindingResult, HttpServletRequest request) throws ActException {
        try {

            Boolean isHasPermission = cusEmailAPI.guidePermission(guidePermissionTO);
            if (!isHasPermission) {
                //int code, String msg
                return new ActResult(0, "没有权限", false);
            } else {
                return new ActResult(0, "有权限", true);
            }
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户邮件汇总列表总条数
     *
     * @param cusEmailDTO 客户邮件汇总dto
     * @des 获取所有客户邮件汇总总条数
     * @version v1
     */
    @GetMapping("v1/count")
    public Result count(CusEmailDTO cusEmailDTO) throws ActException {
        try {
            Long count = cusEmailAPI.countCusEmail(cusEmailDTO);
            return ActResult.initialize(count);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 客户邮件汇总列表
     *
     * @param cusEmailDTO 客户邮件汇总信息dto
     * @return class CusEmailVO
     * @des 获取所有客户邮件汇总信息
     * @version v1
     */
    @GetMapping("v1/listCusEmail")
    public Result findListCusEmail(CusEmailDTO cusEmailDTO) throws ActException {
        try {

            List<CusEmailVO> cusEmailVOList = BeanTransform.copyProperties(
                    cusEmailAPI.listCusEmail(cusEmailDTO), CusEmailVO.class, true);
            return ActResult.initialize(cusEmailVOList);

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 添加客户邮件汇总
     *
     * @param cusEmailTO 客户邮件汇总基本信息数据to
     * @return class CusEmailVO
     * @des 添加客户邮件汇总, 行业不能为空发送间隔汇总间隔等都不能为空
     * @version v1
     */
    @LoginAuth
    @PostMapping("v1/add")
    public Result addCusEmail(@Validated(CusEmailTO.TestAdd.class) CusEmailTO cusEmailTO, BindingResult bindingResult) throws ActException {
        try {

            CusEmailBO cusEmailBO1 = cusEmailAPI.addCusEmail(cusEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusEmailBO1, CusEmailVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 编辑客户邮件汇总
     *
     * @param cusEmailTO 客户邮件汇总基本信息数据bo
     * @return class CusEmailVO
     * @des 添加客户邮件汇总, 行业不能为空发送间隔汇总间隔等都不能为空
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/edit")
    public Result editCusEmail(@Validated(CusEmailTO.TestAdd.class) CusEmailTO cusEmailTO, BindingResult bindingResult) throws ActException {
        try {

            CusEmailBO cusEmailBO1 = cusEmailAPI.editCusEmail(cusEmailTO);
            return ActResult.initialize(BeanTransform.copyProperties(cusEmailBO1, CusEmailVO.class, true));

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 删除
     *
     * @param id id
     * @des 根据id删除客户邮件汇总信息记录
     * @version v1
     */
    @LoginAuth
    @DeleteMapping("v1/delete/{id}")
    public Result deleteCusEmail(@PathVariable String id) throws ActException {
        try {

            cusEmailAPI.deleteCusEmail(id);
            return new ActResult("delete success!");

        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 冻结
     *
     * @param id id
     * @des 根据id冻结客户邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/congeal/{id}")
    public Result congeal(@PathVariable String id) throws ActException {
        try {
            cusEmailAPI.congealCusEmail(id);
            return new ActResult("congeal success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 解冻
     *
     * @param id id
     * @des 根据id解冻客户邮件汇总记录
     * @version v1
     */
    @LoginAuth
    @PutMapping("v1/thaw/{id}")
    public Result thaw(@PathVariable String id) throws ActException {
        try {
            cusEmailAPI.thawCusEmail(id);
            return new ActResult("thaw success!");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


    /**
     * 汇总
     *
     * @param works 行业
     * @return class CusEmailVO
     * @des 根据行业汇总
     * @version v1
     */
    @GetMapping("v1/collect")
    public Result CollectSign(@RequestParam String[] works) throws ActException {
        try {
            List<CusEmailVO> collectEmailVOList = BeanTransform.copyProperties(
                    cusEmailAPI.collectCusEmail(works), CusEmailVO.class, true);
            return ActResult.initialize(collectEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 一个客户邮件
     *
     * @param id id
     * @return class CusEmailVO
     * @des 获取所有客户邮件汇总信息
     * @version v1
     */
    @GetMapping("v1/getCusEmailById/{id}")
    public Result getCusEmailById(@PathVariable String id) throws ActException {
        try {
            CusEmailVO cusEmailVOList = BeanTransform.copyProperties(
                    cusEmailAPI.getCusEmailById(id), CusEmailVO.class, true);
            return ActResult.initialize(cusEmailVOList);
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }


}