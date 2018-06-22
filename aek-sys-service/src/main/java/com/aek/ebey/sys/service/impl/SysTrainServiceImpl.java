package com.aek.ebey.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.sys.mapper.SysTrainMapper;
import com.aek.ebey.sys.model.SysTrain;
import com.aek.ebey.sys.model.query.TrainQuery;
import com.aek.ebey.sys.service.SysTrainService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 机构培训表 服务实现类
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
@Service
@Transactional
public class SysTrainServiceImpl extends BaseServiceImpl<SysTrainMapper, SysTrain> implements SysTrainService {
	
	@Autowired
	private SysTrainMapper sysTrainMapper;

	@Override
	public Page<SysTrain> search(TrainQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<SysTrain> page=query.getPage();
		List<SysTrain> list=sysTrainMapper.search(page,query,authUser);
		page.setRecords(list);
		return page;
	}
	
}
