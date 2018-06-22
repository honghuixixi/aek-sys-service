package com.aek.ebey.sys.web.request;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 机构培训表
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
@ApiModel(value = "SysTrainRequest")
public class SysTrainRequest{

    private static final long serialVersionUID = 1L;
	/**
	 * 所属租户ID
	 */
	@ApiModelProperty(value = "所属租户ID")
	private Long tenantId;
	/**
	 * 培训编号
	 */
	@ApiModelProperty(value = "培训编号")
	private String num;
	/**
	 * 培训主题
	 */
	@ApiModelProperty(value = "培训主题")
	private String subject;
	/**
	 * 培训类型(1，岗位基础培训 2，设备操作培训 3，科室业务培训 4，继续教育)
	 */
	@ApiModelProperty(value = "培训类型(1，岗位基础培训 2，设备操作培训 3，科室业务培训 4，继续教育)")
	private Integer type;
	/**
	 * 培训时间
	 */
	@ApiModelProperty(value = "培训时间")
	private Date trainTime;
	/**
	 * 培训讲师
	 */
	@ApiModelProperty(value = "培训讲师")
	private String lecturer;
	/**
	 * 培训地点
	 */
	@ApiModelProperty(value = "培训地点")
	private String locale;
	/**
	 * 培训对象
	 */
	@ApiModelProperty(value = "培训对象")
	private String target;
	/**
	 * 培训内容
	 */
	@ApiModelProperty(value = "培训内容")
	private String content;
	/**
	 * 附件，分割
	 */
	@ApiModelProperty(value = "附件，分割")
	private String files;
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getTrainTime() {
		return trainTime;
	}
	public void setTrainTime(Date trainTime) {
		this.trainTime = trainTime;
	}
	public String getLecturer() {
		return lecturer;
	}
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
}
