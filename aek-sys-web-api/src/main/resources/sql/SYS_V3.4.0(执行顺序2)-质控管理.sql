#1.新增质控模块
#模块id=15
INSERT INTO `sys_module` (`name`, `version_number`, `module_type`, `url`, `price_policy_id`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `custom_data`, `description`) 
                  VALUES ('质控管理', '1.0.0', '1', 'quality.menu.apply', NULL, '1', now(), '1', now(), b'1', b'0', 
                  '{\"tenantType\": [0, 1], \"defaultExist\": false}', '质控精细化管理');
                  
#2.新增质控管理预设角色
#预设角色id=15
INSERT INTO `sys_role_preset` (`name`, `code`, `data_scope`, `descript`, `tenant_type`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `remark`) 
VALUES ('质控模块管理员', NULL, '1', '质控模块所有可用权限', '0,1', NULL, now(), NULL, now(), b'1', b'0', 'AEK,医疗机构所拥有');

#3.调用存储过程，初始化机构与质控管理模块关系，参数=模块ID
call add_module_procedure(15);

#4.插入质控管理权限码
#菜单【质控填报】 ID=151
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '15', '质控填报', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【质控填报权限】 ID=152
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('151', '15', '质控填报管理', 'MD_APPLY_MANAGE', b'0', NULL, NULL, now(), now(), b'1', '包含质控填报暂存、提交、撤回等权限', NULL);

#菜单【质控审核】 ID=153
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '15', '质控审核', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【质控审核权限】 ID=154
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('153', '15', '质控审核管理', 'MD_CHECK_MANAGE', b'0', NULL, NULL, now(), now(), b'1', '包含审核所有业务，单据打印等', NULL);

#菜单【质控档案】 ID=155
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '15', '质控档案', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【质控档案管理权限】 ID=156
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('155', '15', '质控档案管理', 'MD_ARCHIVE_MANAGE', b'0', NULL, NULL, now(), now(), b'1', '包含本机构档案查看，单据打印等所有业务', NULL);

#菜单【质控模板】 ID=157
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '15', '质控模板', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【质控模板管理权限】 ID=158
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('157', '15', '质控模板管理', 'MD_TEMPLATE_MANAGE', b'0', NULL, NULL, now(), now(), b'1', '包含质控模板管理所有权限', NULL);

#5.（1）给所有拥有该模块的机构角色赋予新增权限，默认不勾选，（2）给预设角色添加新增权限
#5.执行请求接口:调用之前先得插入预设角色 
#权限151-158

#151
call add_role_permission(15,151);
call add_role_permission_preset2(1,15,151);
call add_role_permission_preset2(15,15,151);
#152
call add_role_permission(15,152);
call add_role_permission_preset2(1,15,152);
call add_role_permission_preset2(15,15,152);
#153
call add_role_permission(15,153);
call add_role_permission_preset2(1,15,153);
call add_role_permission_preset2(15,15,153);
#154
call add_role_permission(15,154);
call add_role_permission_preset2(1,15,154);
call add_role_permission_preset2(15,15,154);
#155
call add_role_permission(15,155);
call add_role_permission_preset2(1,15,155);
call add_role_permission_preset2(15,15,155);
#156
call add_role_permission(15,156);
call add_role_permission_preset2(1,15,156);
call add_role_permission_preset2(15,15,156);
#157
call add_role_permission(15,157);
call add_role_permission_preset2(1,15,157);
call add_role_permission_preset2(15,15,157);
#158
call add_role_permission(15,158);
call add_role_permission_preset2(1,15,158);
call add_role_permission_preset2(15,15,158);

#6.调用存储过程，前提：先插入预设角色信息以及给预设角色分配权限，预设角色ID=15
call add_preset_role_procedure_01(15,'');

#7.给质控管理模块添加权限时，需给预设角色机构管理员及质控管理员角色赋予该权限且启用。
select * from sys_role_permission_preset where preset_role_id in(1,15) and module_id=15 and permission_id in(151,152,153,154,155,156,157,158);

#8.预设角色为机构管理员与质控管理员的角色权限，需启用新增权限
select * from sys_role_permission where  module_id=15 and permission_id in(151,152,153,154,155,156,157,158) and role_id in (select id from sys_role where preset_id in (1,15));

#9.默认将质控管理赋予爱怡康机构
update sys_tenant_module set enable=true,del_flag=false where  module_id=15 and tenant_id=1;
commit;


