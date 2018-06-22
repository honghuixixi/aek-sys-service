package com.aek.ebey.sys.mapper;

import com.aek.common.core.base.BaseMapper;
import com.aek.ebey.sys.model.SysRole;
import com.aek.ebey.sys.model.query.RoleQuery;
import com.aek.ebey.sys.model.vo.SysRoleVo;
import com.aek.ebey.sys.model.vo.SysTenantRoleVo;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author aek
 * @since 2017-05-06
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

	List<SysRoleVo> selectByUserIdAndTenantId(Map<String,Object> map);
	
	List<SysTenantRoleVo> selectAllRoleByUserIdAndTenantId(@Param("userId") Long userId, @Param("tenantId") Long tenantId,@Param("parentIds") String parentIds);
	
	Boolean isOrgAdmin(@Param("userId") Long userId, @Param("tenantId") Long tenantId);
	
	List<Long> getOrgModuleIds(@Param("userId") Long userId, @Param("tenantId") Long tenantId);
	
	List<Long> getOrgModuleByRole(@Param("userId") Long userId, @Param("tenantId") Long tenantId);

	/**
	 * 角色分页查询
	 * @param page
	 * @param query
	 * @return
	 */
	List<SysRole> getSysRoleByPage(@Param("page") Page<SysRole> page, @Param("query") RoleQuery query);
}
