package com.aek.ebey.sys.model;

import com.aek.common.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 机构培训表
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
@ApiModel(value = "SysTrain")
@TableName("sys_train")
public class SysTrain extends BaseModel {

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
	@TableField(value="train_time")
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
