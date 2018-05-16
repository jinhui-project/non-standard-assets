package com.jinhui.model.abs;

import java.util.Date;

public class PeWhiteList {
    /**
     * id
     */
    private Integer id;

    /**
     * 应收债务人
     */
    private String receivableDebtor;

    /**
     * 原始权益人
     */
    private String originalHolder;

    /**
     * 应收账期
     */
    private Integer receivablePeriod;

    /**
     * 业务合同
     */
    private String businessContract;

    /**
     * 是否生效：0-否  1-是
     */
    private String status;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取应收债务人
     *
     * @return receivable_debtor - 应收债务人
     */
    public String getReceivableDebtor() {
        return receivableDebtor;
    }

    /**
     * 设置应收债务人
     *
     * @param receivableDebtor 应收债务人
     */
    public void setReceivableDebtor(String receivableDebtor) {
        this.receivableDebtor = receivableDebtor == null ? null : receivableDebtor.trim();
    }

    /**
     * 获取原始权益人
     *
     * @return original_holder - 原始权益人
     */
    public String getOriginalHolder() {
        return originalHolder;
    }

    /**
     * 设置原始权益人
     *
     * @param originalHolder 原始权益人
     */
    public void setOriginalHolder(String originalHolder) {
        this.originalHolder = originalHolder == null ? null : originalHolder.trim();
    }

    /**
     * 获取应收账期
     *
     * @return receivable_period - 应收账期
     */
    public Integer getReceivablePeriod() {
        return receivablePeriod;
    }

    /**
     * 设置应收账期
     *
     * @param receivablePeriod 应收账期
     */
    public void setReceivablePeriod(Integer receivablePeriod) {
        this.receivablePeriod = receivablePeriod;
    }

    /**
     * 获取业务合同
     *
     * @return business_contract - 业务合同
     */
    public String getBusinessContract() {
        return businessContract;
    }

    /**
     * 设置业务合同
     *
     * @param businessContract 业务合同
     */
    public void setBusinessContract(String businessContract) {
        this.businessContract = businessContract == null ? null : businessContract.trim();
    }

    /**
     * 获取是否生效：0-否  1-是
     *
     * @return status - 是否生效：0-否  1-是
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置是否生效：0-否  1-是
     *
     * @param status 是否生效：0-否  1-是
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}