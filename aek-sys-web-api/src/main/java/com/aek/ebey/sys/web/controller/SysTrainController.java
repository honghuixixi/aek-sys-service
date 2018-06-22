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
import com.aek.ebey.sys.model.SysTrain;
import com.aek.ebey.sys.model.query.TrainQuery;
import com.aek.ebey.sys.service.SysTrainService;
import com.aek.ebey.sys.utils.Utils;
import com.aek.ebey.sys.web.request.SysMeetingRespose;
import com.aek.ebey.sys.web.request.SysTrainRequest;
import com.aek.ebey.sys.web.request.SysTrainRespose;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * <p>
 * 机构培训表  前端控制器
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
@RestController
@RequestMapping("/sys/sysTrain")
@Api(value = "SysTrainController", description = "机构培训")
public class SysTrainController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(SysTrainController.class);

	@Autowired
	private SysTrainService sysTrainService;

	@PreAuthorize("hasAuthority('TRAIN_RECORD_MANAGE')")
	@PostMapping(value = "/add")
	@ApiOperation(value = "机构培训", httpMethod = "POST", produces = "application/json")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> add(@RequestBody SysTrainRequest request) {
		logger.debug("<---------------------------------" + JSON.toJSONString(request));
		AuthUser user = WebSecurityUtils.getCurrentUser();
		Long tenantId = user.getTenantId();
		SysTrain sysTrain = BeanMapper.map(request, SysTrain.class);
		sysTrain.setNum(Utils.getTrainNum());
		sysTrain.setCreateBy(user.getId());
		sysTrain.setCreateName(user.getRealName());
		sysTrain.setTenantId(tenantId);
		this.sysTrainService.insert(sysTrain);
		return response();
	}

	/**
	 * 根据id查询培训信息
	 */
	@PreAuthorize("hasAnyAuthority('TRAIN_RECORD_MANAGE,TRAIN_ARCHIVE_MANAGE')")
	@GetMapping(value = "/{id}")
	@ApiOperation(value = " 根据id查询培训信息")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<SysTrainRespose> getById(@PathVariable Long id) {
		logger.debug("<---------------------------------" + JSON.toJSONString(id));
		AuthUser user = WebSecurityUtils.getCurrentUser();
		SysTrain sysTrain = sysTrainService.selectById(id);
		if(sysTrain!=null){
			SysTrainRespose sysTrainRespose = BeanMapper.map(sysTrain, SysTrainRespose.class);
			sysTrainRespose.setTeantName(user.getTenantName());
			return response(sysTrainRespose);
		}
		return response(null);
	}
	
	/**
	 * 分页查询培训列表
	 * 
	 * @param query
	 * @return
	 */
	@GetMapping(value = "/search")
	@ApiOperation(value = "分页查询培训列表", httpMethod = "GET", produces = "application/json")
	@ApiImplicitParams({ @ApiImplicitParam(name = "pageNo", value = "起始页 [默认1]", paramType = "query", required = false),
			@ApiImplicitParam(name = "pageSize", value = "分页大小[默认10]", paramType = "query", required = false),
			@ApiImplicitParam(name = "startDate", value = "开始时间", paramType = "query", required = false),
			@ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", required = false),
			@ApiImplicitParam(name = "tenantId", value = "租户ID", paramType = "query", required = false),
			@ApiImplicitParam(name = "endDate", value = "结束时间", paramType = "query", required = false),
			@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "query", required = false) })
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Page<SysTrain>> search(TrainQuery query) {
		logger.debug("<---------------------------------"+JSON.toJSONString(query));
		Page<SysTrain> page = this.sysTrainService.search(query);
		return response(page);
	}
	
	/**
	 * 编辑
	 */
	@PreAuthorize("hasAuthority('TRAIN_RECORD_MANAGE')")
	@PostMapping(value = "/edit")
	@ApiOperation(value = "编辑培训")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> edit(@RequestBody SysTrain request){
		AuthUser user = WebSecurityUtils.getCurrentUser();
		request.setUpdateBy(user.getId());
		request.setUpdateTime(new Date());
		this.sysTrainService.updateById(request);
		return response();
	}
	
	/**
	 * 删除
	 */
	@PreAuthorize("hasAuthority('TRAIN_RECORD_MANAGE')")
	@DeleteMapping(value = "/{id}")
	@ApiOperation(value = "删除会议")
	@ApiResponse(code = 200, message = "OK", response = Result.class)
	public Result<Object> delete(@PathVariable Long id){
		SysTrain sysTrain = sysTrainService.selectById(id);
		if(sysTrain!=null){
			sysTrain.setDelFlag(true);
			this.sysTrainService.updateById(sysTrain);
		}
		return response();
	}
	
}
