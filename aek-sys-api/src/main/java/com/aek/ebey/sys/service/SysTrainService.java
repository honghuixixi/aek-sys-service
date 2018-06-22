package com.aek.ebey.sys.service;

import com.aek.ebey.sys.model.SysTrain;
import com.aek.ebey.sys.model.query.TrainQuery;
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
public interface SysTrainService extends BaseService<SysTrain> {

	Page<SysTrain> search(TrainQuery query);
	
}
