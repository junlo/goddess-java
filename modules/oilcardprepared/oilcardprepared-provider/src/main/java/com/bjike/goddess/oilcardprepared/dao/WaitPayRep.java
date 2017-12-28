package com.bjike.goddess.oilcardprepared.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.oilcardprepared.dto.WaitPayDTO;
import com.bjike.goddess.oilcardprepared.entity.WaitPay;

/**
 * 等待付款持久化接口, 继承基类可使用ｊｐａ命名查询
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-15 09:50 ]
 * @Description: [ 等待付款持久化接口, 继承基类可使用ｊｐａ命名查询 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitPayRep extends JpaRep<WaitPay, WaitPayDTO> {

}