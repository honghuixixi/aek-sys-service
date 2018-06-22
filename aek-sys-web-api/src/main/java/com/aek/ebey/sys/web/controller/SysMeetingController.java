package com.aek.ebey.sys.web.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aek.common.core.BeanMapper;
import com.aek.common.core.Result;
import com.aek.common.core.base.BaseController;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.sys.model.SysMeeting;
import com.aek.ebey.sys.model.query.MeetingQuery;
import com.aek.ebey.sys.service.SysMeetingService;
import com.aek.ebey.sys.utils.Utils;
import com.aek.ebey.sys.web.request.SysMeetingRequest;
import com.aek.ebey.sys.web.request.SysMeetingRespose;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * 机构会议记录表 前端控制器
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
@RestController
@RequestMapping("/sys/sysMeeting")
@Api(value = "SysMeetingController", description = "机构会议")
public class SysMeetingController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(SysMeetingController.class);

	@Autowired
	private SysMeetingService sysMeetingService;
	
	@PreAuthorize("hasAuthority('MEETING_RECORD_MANAGE')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "机构会议", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@RequestBody SysMeetingRequest request) {
		logger.debug("<---------------------------------" + JSON.toJSONString(request));
		AuthUser user = WebSecurityUtils.getCurrentUser();
		Long tenantId = user.getTenantId();
		SysMeeting sysMeeting = BeanMapper.map(request, SysMeeting.class);
		sysMeeting.setNum(Utils.getMeetingNum());
		sysMeeting.setCreateBy(user.getId());
		sysMeeting.setCreateName(user.getRealName());
		sysMeeting.setTenantId(tenantId);
		this.sysMeetingService.insert(sysMeeting);
		return response();
	}

	/**
	 * 根据id查询会议信息
	 */
	@PreAuthorize("hasAnyAuthority('MEETING_RECORD_MANAGE,MEETING_ARCHIVE_VIEW')")
	@GetMapping(value = "/{id}")
	@ApiOperation(value = " 根据id查询会议信息")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<SysMeetingRespose> getById(@PathVariable Long id) {
		logger.debug("<---------------------------------" + JSON.toJSONString(id));
		AuthUser user = WebSecurityUtils.getCurrentUser();
		SysMeeting sysMeeting = sysMeetingService.selectById(id);
		if(sysMeeting!=null){
			SysMeetingRespose sysMeetingRespose = BeanMapper.map(sysMeeting, SysMeetingRespose.class);
			sysMeetingRespose.setTeantName(user.getTenantName());
			return response(sysMeetingRespose);
		}
		return response(null);
	}
	
	/**
	 * 分页查询会议列表
	 * 
	 * @param query
	 * @return
	 */
	@GetMapping(value = "/search")
	@ApiOperation(value = "分页查询会议列表", httpMethod = "GET", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "起始页 [默认1]", paramType = "query", required = false),
			@ApiImplicitParam(name = "pageSize", value = "分页大小[默认10]", paramType = "query", required = false),
			@ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", required = false),
			@ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", required = false),
			@ApiImplicitParam(name = "tenantId", value = "租户ID", paramType = "query", required = false),
			@ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", required = false),
			@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false) })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Page<SysMeeting>> search(MeetingQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		Page<SysMeeting> page = this.sysMeetingService.search(query);
		return response(page);
	}
	
	/**
	 * 编辑
	 */
	@PreAuthorize("hasAuthority('MEETING_RECORD_MANAGE')")
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑会议")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@RequestBody SysMeeting request){
		AuthUser user = WebSecurityUtils.getCurrentUser();
		request.setUpdateBy(user.getId());
		request.setUpdateTime(new Date());
		this.sysMeetingService.updateById(request);
		return response();
	}
	
	/**
	 * 删除
	 */
	@PreAuthorize("hasAuthority('MEETING_RECORD_MANAGE')")
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "删除会议")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> delete(@PathVariable Long id){
		SysMeeting sysMeeting = sysMeetingService.selectById(id);
		if(sysMeeting!=null){
			sysMeeting.setDelFlag(true);
			this.sysMeetingService.updateById(sysMeeting);
		}
		return response();
	}
}
