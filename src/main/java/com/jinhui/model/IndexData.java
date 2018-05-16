package com.jinhui.model;

public class IndexData {
    private Integer id;

    private String datas;

    private String createAt;

    private String updateAt;
    
    private String asset;
    
    private String haveProducts;
    
    private String services;
    
    private String averageRate;
    
    private String products;
    
    private String funds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas == null ? null : datas.trim();
    }

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public String getHaveProducts() {
		return haveProducts;
	}

	public void setHaveProducts(String haveProducts) {
		this.haveProducts = haveProducts;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(String averageRate) {
		this.averageRate = averageRate;
	}

	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}

	public String getFunds() {
		return funds;
	}

	public void setFunds(String funds) {
		this.funds = funds;
	}

	@Override
	public String toString() {
		return "IndexData [id=" + id + ", datas=" + datas + ", createAt="
				+ createAt + ", updateAt=" + updateAt + ", asset=" + asset
				+ ", haveProducts=" + haveProducts + ", services=" + services
				+ ", averageRate=" + averageRate + ", products=" + products
				+ ", funds=" + funds + "]";
	}

}