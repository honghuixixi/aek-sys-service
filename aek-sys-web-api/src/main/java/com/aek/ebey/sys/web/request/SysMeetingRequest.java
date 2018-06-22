package com.aek.ebey.sys.web.request;

import java.util.Date;

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
@ApiModel(value = "SysMeetingRequest")
public class SysMeetingRequest {

    private static final long serialVersionUID = 1L;
	/**
	 * 所属租户ID
	 */
	@ApiModelProperty(value = "所属租户ID")
	private Long tenantId;
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
	public Long getTenantId() {
		return tenantId;
	}
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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



}
