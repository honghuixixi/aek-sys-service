#1.新增保养模块
#模块id=14
INSERT INTO `sys_module` (`name`, `version_number`, `module_type`, `url`, `price_policy_id`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `custom_data`, `description`) 
                  VALUES ('保养管理', '1.0.0', '1', 'maintain.menu.plan', NULL, '1', now(), '1', now(), b'1', b'0', 
                  '{\"tenantType\": [0, 1], \"defaultExist\": false}', '保养流程精细化管理');
                  
#2.新增保养管理预设角色
#预设角色id=12
INSERT INTO `sys_role_preset` (`name`, `code`, `data_scope`, `descript`, `tenant_type`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `remark`) 
VALUES ('保养模块管理员', NULL, '1', '保养模块所有可用权限', '0,1', NULL, now(), NULL, now(), b'1', b'0', 'AEK,医疗机构所拥有');

#3.调用存储过程，初始化机构与保养管理模块关系，参数=模块ID
call add_module_procedure(14);

#4.插入保养管理权限码
#菜单【保养计划】 ID=129
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '14', '保养计划', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【保养计划管理权限】 ID=130
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('129', '14', '保养计划管理', 'MT_PLAN_MANAGE', b'0', NULL, NULL, now(), now(), b'1', NULL, NULL);

#菜单【保养实施】 ID=131
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '14', '保养实施', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【保养实施管理权限】 ID=132
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('131', '14', '保养实施管理', 'MT_PLAN_IMPLEMENT_MANAGE', b'0', NULL, NULL, now(), now(), b'1', NULL, NULL);

#菜单【保养模板】 ID=133
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '14', '保养模板', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【保养模板管理权限】 ID=134
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('133', '14', '保养模板管理', 'MT_TEMPLATE_MANAGE', b'0', NULL, NULL, now(), now(), b'1', NULL, NULL);

#菜单【保养报告查询】 ID=135
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '14', '保养报告查询', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【保养报告查询权限】 ID=136
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('135', '14', '保养报告查询', 'MT_REPORT_VIEW', b'0', NULL, NULL, now(), now(), b'1', NULL, NULL);

#5.（1）给所有拥有该模块的机构角色赋予新增权限，默认不勾选，（2）给预设角色添加新增权限
#5.执行请求接口:调用之前先得插入预设角色 
#权限129-136

#129
call add_role_permission(14,129);
call add_role_permission_preset2(1,14,129);
call add_role_permission_preset2(12,14,129);
#130
call add_role_permission(14,130);
call add_role_permission_preset2(1,14,130);
call add_role_permission_preset2(12,14,130);
#131
call add_role_permission(14,131);
call add_role_permission_preset2(1,14,131);
call add_role_permission_preset2(12,14,131);
#132
call add_role_permission(14,132);
call add_role_permission_preset2(1,14,132);
call add_role_permission_preset2(12,14,132);
#133
call add_role_permission(14,133);
call add_role_permission_preset2(1,14,133);
call add_role_permission_preset2(12,14,133);
#134
call add_role_permission(14,134);
call add_role_permission_preset2(1,14,134);
call add_role_permission_preset2(12,14,134);
#135
call add_role_permission(14,135);
call add_role_permission_preset2(1,14,135);
call add_role_permission_preset2(12,14,135);
#136
call add_role_permission(14,136);
call add_role_permission_preset2(1,14,136);
call add_role_permission_preset2(12,14,136);

#6.调用存储过程，前提：先插入预设角色信息以及给预设角色分配权限，预设角色ID=12
call add_preset_role_procedure_01(12,'');

#7.给保养管理模块添加权限时，需给预设角色机构管理员及巡检管理员角色赋予该权限且启用。
select * from sys_role_permission_preset where preset_role_id in(1,12) and module_id=14 and permission_id in(129,130,131,132,133,134,135,136) ;

#8.预设角色为机构管理员与维修管理员的角色权限，需启用新增权限
select * from sys_role_permission where  module_id=14 and permission_id in(129,130,131,132,133,134,135,136) and role_id in (select id from sys_role where preset_id in (1,12));

#9.默认将保养管理赋予爱怡康机构
update sys_tenant_module set enable=true,del_flag=false where  module_id=14 and tenant_id=1;
commit;


