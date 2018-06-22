package com.aek.ebey.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek.common.core.base.BaseServiceImpl;
import com.aek.common.core.serurity.WebSecurityUtils;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.sys.mapper.SysMeetingMapper;
import com.aek.ebey.sys.model.SysMeeting;
import com.aek.ebey.sys.model.query.MeetingQuery;
import com.aek.ebey.sys.service.SysMeetingService;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 机构会议记录表 服务实现类
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
@Service
@Transactional
public class SysMeetingServiceImpl extends BaseServiceImpl<SysMeetingMapper, SysMeeting> implements SysMeetingService {

	
	@Autowired
	private SysMeetingMapper sysMeetingMapper;
	
	@Override
	public Page<SysMeeting> search(MeetingQuery query) {
		AuthUser authUser = WebSecurityUtils.getCurrentUser();
		Page<SysMeeting> page=query.getPage();
		List<SysMeeting> list=sysMeetingMapper.search(page,query,authUser);
		page.setRecords(list);
		return page;
	}
	
}
