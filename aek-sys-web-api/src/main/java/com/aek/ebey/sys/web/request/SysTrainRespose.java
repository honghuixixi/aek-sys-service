package com.aek.ebey.sys.web.request;

import com.aek.ebey.sys.model.SysTrain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "SysTrainRespose")
public class SysTrainRespose extends SysTrain{
	
	/**
	 * 所属租户名称
	 */
	@ApiModelProperty(value = "所属租户名称")
	private String teantName;

	public String getTeantName() {
		return teantName;
	}

	public void setTeantName(String teantName) {
		this.teantName = teantName;
	}
	
	

}
