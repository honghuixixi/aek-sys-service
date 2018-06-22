package com.aek.ebey.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.common.core.serurity.model.AuthUser;
import com.aek.ebey.sys.model.SysTrain;
import com.aek.ebey.sys.model.query.TrainQuery;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author LiuHui
 * @since 2018-05-15
 */
public interface SysTrainMapper extends BaseMapper<SysTrain> {

	List<SysTrain> search(Page<SysTrain> page, @Param("q") TrainQuery query,@Param("user") AuthUser authUser);

}