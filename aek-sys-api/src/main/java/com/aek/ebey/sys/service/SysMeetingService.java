package com.aek.ebey.sys.service;

import com.aek.ebey.sys.model.SysMeeting;
import com.aek.ebey.sys.model.query.MeetingQuery;
import com.baomidou.mybatisplus.plugins.Page;
import com.aek.common.core.base.BaseService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
public interface SysMeetingService extends BaseService<SysMeeting> {

	Page<SysMeeting> search(MeetingQuery query);
	
}
