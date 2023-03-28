package com.Kent.pojo;

/**
 * 品牌
 * <p>
 * 在實體類別中，基本數據類型建議使用其對應的包裝類型
 */
public class Brand {
    // id 主键
    private int id;
    // 品牌名称
    private String brandName;
    // 企业名称
    private String companyName;
    // 排序字段
    private int ordered;
    // 描述信息
    private String description;
    // 状态：0：禁用  1;
    // int 如果沒有設定值，預設是0，有可能影響到業務邏輯，因此使用封裝過後的類型 Integer，
    // 他的預設值就是 null，不會影響到業務邏輯
    private Integer status = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

