#1.巡检管理添加巡检验收菜单权限

#菜单【巡检验收】 ID=147
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '6', '巡检验收', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【巡检验收管理】 ID=148
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('147', '6', '巡检验收管理', 'QC_CHECK_MANAGE', b'0', NULL, NULL, now(), now(), b'1', NULL, NULL);

#147
call add_role_permission(6,147);
call add_role_permission_preset2(1,6,147);
call add_role_permission_preset2(4,6,147);
#148
call add_role_permission(6,148);
call add_role_permission_preset2(1,6,148);
call add_role_permission_preset2(4,6,148);

#给巡检管理模块添加权限时，需给预设角色机构管理员及巡检管理员角色赋予该权限且启用。
select * from sys_role_permission_preset where preset_role_id in(1,4) and module_id=6 and permission_id in(147,148);

#预设角色为机构管理员与巡检管理员的角色权限，需启用新增权限
select * from sys_role_permission where  module_id=6 and permission_id in(147,148) and role_id in (select id from sys_role where preset_id in (1,4));


#2.PM管理添加巡检验收菜单权限

#菜单【PM验收】 ID=149
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '7', 'PM验收', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【巡检验收管理】 ID=150
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('149', '7', 'PM验收管理', 'PM_CHECK_MANAGE', b'0', NULL, NULL, now(), now(), b'1', NULL, NULL);

#149
call add_role_permission(7,149);
call add_role_permission_preset2(1,7,149);
call add_role_permission_preset2(9,7,149);
#150
call add_role_permission(7,150);
call add_role_permission_preset2(1,7,150);
call add_role_permission_preset2(9,7,150);

#给PM管理模块添加权限时，需给预设角色机构管理员及PM管理员角色赋予该权限且启用。
select * from sys_role_permission_preset where preset_role_id in(1,9) and module_id=7 and permission_id in(149,150);

#预设角色为机构管理员与PM管理员的角色权限，需启用新增权限
select * from sys_role_permission where  module_id=7 and permission_id in(149,150) and role_id in (select id from sys_role where preset_id in (1,9));






