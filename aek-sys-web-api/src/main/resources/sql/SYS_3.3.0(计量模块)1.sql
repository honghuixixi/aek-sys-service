#1.新增计量模块
#模块id=9
#INSERT INTO `sys_module` (`name`, `version_number`, `module_type`, `url`, `price_policy_id`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `custom_data`, `description`) 
#                  VALUES ('计量管理', '1.0.0', '1', 'maintain.menu.plan', NULL, '1', now(), '1', now(), b'1', b'0', 
#                  '{\"tenantType\": [0, 1], \"defaultExist\": false}', '计量管理流程精细化管理');                 
update sys_module set `enable`=true ,name='计量管理', url='metering.menu.assets',custom_data='{"tenantType": [0,1], "defaultExist": false}',description='计量管理流程精细化管理' where id=9;
commit;
                  
#2.新增计量管理预设角色
#预设角色id=13
INSERT INTO `sys_role_preset` (`name`, `code`, `data_scope`, `descript`, `tenant_type`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `remark`) 
VALUES ('计量模块管理员', NULL, '1', '计量模块所有可用权限', '0,1', NULL, now(), NULL, now(), b'1', b'0', 'AEK,医疗机构所拥有');

#2.删除原来BI管理模块管理相应模块数据
delete from sys_role_permission where module_id=9;
delete from sys_tenant_module where module_id=9;
commit;


#3.调用存储过程，初始化机构与计量管理模块关系，参数=模块ID
call add_module_procedure(9);

#4.插入计量管理权限码
#菜单【计量台账】 ID=137
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '9', '计量台账', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【计量台账管理权限】 ID=138
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('137', '9', '计量台账查看、新增、导入、导出', 'MS_ASSETS_VIEW_NEW_IMPORT_EXPORT', b'0', NULL, NULL, now(), now(), b'1', '计量台账列表查看及台账新增导入导出等功能', NULL);

#菜单【计量检测】 ID=139
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '9', '计量检测', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【计量检测管理权限】 ID=140
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('139', '9', '计量检测编辑、打印', 'MS_CHECK_EDIT_PRINT', b'0', NULL, NULL, now(), now(), b'1', '计量检测数据编辑提交及报告单打印', NULL);

#菜单【计量档案】 ID=141
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '9', '计量档案', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【计量档案管理】 ID=142
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('141', '9', '计量记录查询、打印', 'MS_RECORD_VIEW_PRINT', b'0', NULL, NULL, now(), now(), b'1', '机构内计量记录查询及报告单打印', NULL);

#5.（1）给所有拥有该模块的机构角色赋予新增权限，默认不勾选，（2）给预设角色添加新增权限
#5.执行请求接口:调用之前先得插入预设角色 
#权限137-142

#137
call add_role_permission(9,137);
call add_role_permission_preset2(1,9,137);
call add_role_permission_preset2(13,9,137);

#138
call add_role_permission(9,138);
call add_role_permission_preset2(1,9,138);
call add_role_permission_preset2(13,9,138);

#139
call add_role_permission(9,139);
call add_role_permission_preset2(1,9,139);
call add_role_permission_preset2(13,9,139);

#140
call add_role_permission(9,140);
call add_role_permission_preset2(1,9,140);
call add_role_permission_preset2(13,9,140);

#141
call add_role_permission(9,141);
call add_role_permission_preset2(1,9,141);
call add_role_permission_preset2(13,9,141);

#142
call add_role_permission(9,142);
call add_role_permission_preset2(1,9,142);
call add_role_permission_preset2(13,9,142);

#6.调用存储过程，前提：先插入预设角色信息以及给预设角色分配权限，预设角色ID=13
call add_preset_role_procedure_01(13,'');

#7.给计量管理模块添加权限时，需给预设角色机构管理员及计量管理员角色赋予该权限且启用。
update sys_role_permission_preset set enable=true,del_flag=false where preset_role_id in(1,13) and module_id=9 and permission_id in(137,138,139,140,141,142);

#8.预设角色为机构管理员与计量管理员的角色权限，需启用新增权限
update sys_role_permission  set enable=true,del_flag=false where  module_id=9 and permission_id in(137,138,139,140,141,142) and role_id in (select id from sys_role where preset_id in (1,13));

#9.默认将保养管理赋予爱怡康机构
update sys_tenant_module set enable=true,del_flag=false where  module_id=9 and tenant_id=1;
commit;


