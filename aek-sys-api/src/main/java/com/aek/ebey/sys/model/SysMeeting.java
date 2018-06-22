package com.aek.ebey.sys.model;

import java.util.Date;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 机构会议记录表
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
@ApiModel(value = "SysMeeting")
@TableName("sys_meeting")
public class SysMeeting extends BaseModel {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID")
	private Long id;
	/**
	 * 所属租户ID
	 */
	@TableField(value="tenant_id")
	@ApiModelProperty(value = "所属租户ID")
	private Long tenantId;
	/**
	 * 会议编号
	 */
	@ApiModelProperty(value = "会议编号")
	private String num;
	/**
	 * 会议主题
	 */
	@ApiModelProperty(value = "会议主题")
	private String subject;
	/**
	 * 会议发起人
	 */
	@ApiModelProperty(value = "会议发起人")
	private String originator;
	/**
	 * 会议时间
	 */
	@TableField(value="meeting_time")
	@ApiModelProperty(value = "会议时间")
	private Date meetingTime;
	/**
	 * 会议地点
	 */
	@ApiModelProperty(value = "会议地点")
	private String locale;
	/**
	 * 参会人
	 */
	@ApiModelProperty(value = "参会人")
	private String attendee;
	/**
	 * 会议内容
	 */
	@ApiModelProperty(value = "会议内容")
	private String content;
	/**
	 * 附件，分割
	 */
	@ApiModelProperty(value = "附件，分割")
	private String files;
	/**
	 * 创建人ID
	 */
	@TableField(value="create_by")
	@ApiModelProperty(value = "创建人ID")
	private Long createBy;
	/**
	 * 创建人
	 */
	@TableField(value="create_name")
	@ApiModelProperty(value = "创建人")
	private String createName;
	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	@ApiModelProperty(value = "创建时间")
	private Date createTime=new Date();
	/**
	 * 更新人
	 */
	@TableField(value="update_by")
	@ApiModelProperty(value = "更新人")
	private Long updateBy;
	/**
	 * 更新时间
	 */
	@TableField(value="update_time")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime=new Date();
	/**
	 * 删除标志 1 删除 0 未删除
	 */
	@TableField(value="del_flag")
	@ApiModelProperty(value = "删除标志 1 删除 0 未删除")
	private Boolean delFlag;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getOriginator() {
		return originator;
	}

	public void setOriginator(String originator) {
		this.originator = originator;
	}

	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getAttendee() {
		return attendee;
	}

	public void setAttendee(String attendee) {
		this.attendee = attendee;
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

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

}
