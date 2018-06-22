package com.aek.ebey.sys.mapper;

import org.apache.ibatis.annotations.Param;

import com.aek.common.core.base.BaseMapper;
import com.aek.ebey.sys.model.SysUserModule;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-05-27
 */
public interface SysUserModuleMapper extends BaseMapper<SysUserModule> {

	 SysUserModule checkHasModule(@Param("moduleId")Long moduleId,@Param("userId")Long userId);
}