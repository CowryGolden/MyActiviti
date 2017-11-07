package com.zzrenfeng.base.entity;

/**
 * @功能描述：系统环境变量实体类
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2017年9月23日 上午11:41:34
 * 
 * @修  改  人：
 * @修改日期：
 * @修改描述：
 *
 */
public class SysSetting extends BaseDomain {
    /**
     * 环境变量主键
     */
    private String settingId;

    /**
     * 设置项键(对应数据字典的ID，类外键)
     */
    private String itemKey;

    /**
     * 设置项值
     */
    private String itemValue;

    /**
     * 备注
     */
    private String memo;

    /**
     * 环境变量主键
     * @return settingId 环境变量主键
     */
    public String getSettingId() {
        return settingId;
    }

    /**
     * 环境变量主键
     * @param settingId 环境变量主键
     */
    public void setSettingId(String settingId) {
        this.settingId = settingId == null ? null : settingId.trim();
    }

    /**
     * 设置项键
     * @return itemKey 设置项键
     */
    public String getItemKey() {
        return itemKey;
    }

    /**
     * 设置项键
     * @param itemKey 设置项键
     */
    public void setItemKey(String itemKey) {
        this.itemKey = itemKey == null ? null : itemKey.trim();
    }

    /**
     * 设置项值
     * @return itemValue 设置项值
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * 设置项值
     * @param itemValue 设置项值
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    /**
     * 备注
     * @return memo 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}