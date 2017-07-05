package com.bjike.goddess.lendreimbursement.enums;

/**
 * 导航权限
 * @Author: [lijuntao]
 * @Date: [2017-06-20 12:53]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public enum GuideAddrStatus {
    /**
     * 列表
     */
    LIST(0),
    /**
     * 添加
     */
    ADD(1),
    /**
     * 编辑
     */
    EDIT(2),
    /**
     * 删除
     */
    DELETE(3),
    /**
     * 冻结
     */
    CONGEL(4),
    /**
     * 解冻
     */
    THAW(5),
    /**
     * 汇总
     */
    COLLECT(6),
    /**
     * 上传附件
     */
    UPLOAD(7),
    /**
     * 下载附件
     */
    DOWNLOAD(8),
    /**
     * 导入
     */
    IMPORT(9),
    /**
     * 导出
     */
    EXPORT(10),
    /**
     * 查看
     */
    SEE(11),
    /**
     * 查看附件
     */
    SEEFILE(12);

    private int code;

    GuideAddrStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
