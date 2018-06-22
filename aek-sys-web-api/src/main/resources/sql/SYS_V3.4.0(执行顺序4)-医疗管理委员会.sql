#1.新增医疗管理委员会模块
#模块id=17
INSERT INTO `sys_module` (`name`, `version_number`, `module_type`, `url`, `price_policy_id`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `custom_data`, `description`) 
                  VALUES ('医疗管理委员', '1.0.0', '1', 'meeting.menu.plan', NULL, '1', now(), '1', now(), b'1', b'0',
                  '{\"tenantType\": [0,1,2], \"defaultExist\": false}', '会议档案全纪录');
                  
#2.新增医疗管理委员预设角色
#预设角色id=17
INSERT INTO `sys_role_preset` (`name`, `code`, `data_scope`, `descript`, `tenant_type`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `remark`) 
VALUES ('医疗管理委员会模块管理员', NULL, '1', '包含医疗管理委员会所有可用权限', '0,1,2', NULL, now(), NULL, now(), b'1', b'0', 'AEK,医疗机构、监管机构所拥有');

#3.调用存储过程，初始化机构与医疗管理委员会模块关系，参数=模块ID
call add_module_procedure(17);

#4.插入医疗管理委员权限码
#菜单【记录管理】 ID=163
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '17', '记录管理', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【记录管理】 ID=164
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('163', '17', '记录管理(新增、查询、编辑、删除、打印等)', 'MEETING_RECORD_MANAGE', b'0', NULL, NULL, now(), now(), b'1', '包含新增记录的所有业务权限', NULL);

#菜单【档案管理】 ID=165
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '17', '档案管理', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【查看管理委员会档案】 ID=166
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('165', '17', '查看管理委员会档案', 'MEETING_ARCHIVE_VIEW', b'0', NULL, NULL, now(), now(), b'1', '包含档案内查看、打印等所有业务', NULL);

#5.（1）给所有拥有该模块的机构角色赋予新增权限，默认不勾选，（2）给预设角色添加新增权限
#5.执行请求接口:调用之前先得插入预设角色 
#权限163-166

#163
call add_role_permission(17,163);
call add_role_permission_preset2(1,17,163);
call add_role_permission_preset2(17,17,163);
#164
call add_role_permission(17,164);
call add_role_permission_preset2(1,17,164);
call add_role_permission_preset2(17,17,164);
#165
call add_role_permission(17,165);
call add_role_permission_preset2(1,17,165);
call add_role_permission_preset2(17,17,165);
#166
call add_role_permission(17,166);
call add_role_permission_preset2(1,17,166);
call add_role_permission_preset2(17,17,166);

#6.调用存储过程，前提：先插入预设角色信息以及给预设角色分配权限，预设角色ID=17
call add_preset_role_procedure_012(17,'');

#7.给医疗管理委员模块添加权限时，需给预设角色机构管理员及医疗管理委员员角色赋予该权限且启用。
select * from sys_role_permission_preset where preset_role_id in(1,17) and module_id=17 and permission_id in(163,164,165,166);

#8.预设角色为机构管理员与医疗管理委员员的角色权限，需启用新增权限
select * from sys_role_permission where  module_id=17 and permission_id in(163,164,165,166) and role_id in (select id from sys_role where preset_id in (1,17));

#9.默认将医疗管理委员赋予爱怡康机构
update sys_tenant_module set enable=true,del_flag=false where  module_id=17 and tenant_id=1;
commit;


