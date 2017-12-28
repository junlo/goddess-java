package com.bjike.goddess.salaryconfirm.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.salaryconfirm.dto.InvoiceSubmitDTO;
import com.bjike.goddess.salaryconfirm.entity.InvoiceSubmit;

/**
* 上交发票持久化接口, 继承基类可使用ｊｐａ命名查询
* @Author:			[ Jason ]
* @Date:			[  2017-05-17 05:47 ]
* @Description:	[ 上交发票持久化接口, 继承基类可使用ｊｐａ命名查询 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface InvoiceSubmitRep extends JpaRep<InvoiceSubmit ,InvoiceSubmitDTO> { 

 }