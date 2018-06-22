package com.aek.ebey.sys.model.query;

import com.aek.common.core.base.page.PageHelp;
import com.aek.ebey.sys.model.SysTrain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class TrainQuery extends PageHelp<SysTrain> {
	/**
	 * 开始日期
	 */
	@ApiModelProperty(value = "开始日期")
	private String startDate;
	
	/**
	 * 结束日期
	 */
	@ApiModelProperty(value = "结束日期")
	private String endDate;
	
	/**
	 * 类型
	 */
	@ApiModelProperty(value = "培训类型(1，岗位基础培训 2，设备操作培训 3，科室业务培训 4，继续教育)")
	private Integer type;
	
	
	/**
	 * 检索关键字
	 */
	@ApiModelProperty(value = "检索关键字")
	private String keyword;
	
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	
	/**
	 * 租户ID
	 */
	@ApiModelProperty(value = "租户ID")
	private Long tenantId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

}
