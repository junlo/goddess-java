package com.bjike.goddess.socialinsurance.service;

import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.socialinsurance.dto.CusPermissionOperateDTO;
import com.bjike.goddess.socialinsurance.entity.CusPermissionOperate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
* 客户权限配置操作对象业务实现
* @Author:			[ caiwenxian ]
* @Date:			[  2017-12-12 05:53 ]
* @Description:	[ 客户权限配置操作对象业务实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@CacheConfig(cacheNames ="socialinsuranceSerCache")
@Service
public class CusPermissionOperateSerImpl extends ServiceImpl<CusPermissionOperate, CusPermissionOperateDTO> implements CusPermissionOperateSer { 

 }