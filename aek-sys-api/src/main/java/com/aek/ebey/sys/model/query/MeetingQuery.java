package com.aek.ebey.sys.model.query;

import com.aek.common.core.base.page.PageHelp;
import com.aek.ebey.sys.model.SysMeeting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MeetingQuery extends PageHelp<SysMeeting> {
	
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
