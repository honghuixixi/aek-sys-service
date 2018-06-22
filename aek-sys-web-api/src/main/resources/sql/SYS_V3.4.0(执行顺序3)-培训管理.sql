#1.新增培训模块
#模块id=16
INSERT INTO `sys_module` (`name`, `version_number`, `module_type`, `url`, `price_policy_id`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `custom_data`, `description`) 
                  VALUES ('培训管理', '1.0.0', '1', 'training.menu.plan', NULL, '1', now(), '1', now(), b'1', b'0', 
                  '{\"tenantType\": [0,1,2], \"defaultExist\": false}', '轻松管理培训档案');
                  
#2.新增培训管理预设角色
#预设角色id=16
INSERT INTO `sys_role_preset` (`name`, `code`, `data_scope`, `descript`, `tenant_type`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `remark`) 
VALUES ('培训管理模块管理员', NULL, '1', '包含培训管理所有可用权限', '0,1,2', NULL, now(), NULL, now(), b'1', b'0', 'AEK,医疗机构、监管机构所拥有');

#3.调用存储过程，初始化机构与培训管理模块关系，参数=模块ID
call add_module_procedure(16);

#4.插入培训管理权限码
#菜单【培训记录】 ID=159
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '16', '培训记录', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【培训记录】 ID=160
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('159', '16', '培训记录管理', 'TRAIN_RECORD_MANAGE', b'0', NULL, NULL, now(), now(), b'1', '包含新增培训的所有内容', NULL);

#菜单【培训档案】 ID=161
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '16', '培训档案', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【质控审核权限】 ID=162
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('161', '16', '培训档案管理', 'TRAIN_ARCHIVE_MANAGE', b'0', NULL, NULL, now(), now(), b'1', '包含培训查询，查看详情等', NULL);

#5.（1）给所有拥有该模块的机构角色赋予新增权限，默认不勾选，（2）给预设角色添加新增权限
#5.执行请求接口:调用之前先得插入预设角色 
#权限159-162

#159
call add_role_permission(16,159);
call add_role_permission_preset2(1,16,159);
call add_role_permission_preset2(16,16,159);
#160
call add_role_permission(16,160);
call add_role_permission_preset2(1,16,160);
call add_role_permission_preset2(16,16,160);
#161
call add_role_permission(16,161);
call add_role_permission_preset2(1,16,161);
call add_role_permission_preset2(16,16,161);
#162
call add_role_permission(16,162);
call add_role_permission_preset2(1,16,162);
call add_role_permission_preset2(16,16,162);

#6.调用存储过程，前提：先插入预设角色信息以及给预设角色分配权限，预设角色ID=16
call add_preset_role_procedure_012(16,'');

#7.给培训管理模块添加权限时，需给预设角色机构管理员及培训管理员角色赋予该权限且启用。
select * from sys_role_permission_preset where preset_role_id in(1,16) and module_id=16 and permission_id in(159,160,161,162);

#8.预设角色为机构管理员与培训管理员的角色权限，需启用新增权限
select * from sys_role_permission where  module_id=16 and permission_id in(159,160,161,162) and role_id in (select id from sys_role where preset_id in (1,16));

#9.默认将培训管理赋予爱怡康机构
update sys_tenant_module set enable=true,del_flag=false where  module_id=16 and tenant_id=1;
commit;


