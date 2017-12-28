package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.dto.CredentialDTO;
import com.bjike.goddess.staffentry.entity.Credential;
import com.bjike.goddess.staffentry.to.CredentialTO;

import java.util.List;

/**
 * 证书情况业务接口
 * @Author: [tanghaixiang]
 * @Date: [2017-03-09 15:51]
 * @Description: [证书情况业务接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CredentialSer extends Ser<Credential,CredentialDTO> {

    /**
     * 添加证书情况
     * @param credentialTOs 证书情况数据集合
     * @throws SerException
     */
    default void insertCredentials(List<CredentialTO> credentialTOs ) throws SerException {return ;}
}
