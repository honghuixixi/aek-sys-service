#1.新增档案模块
#模块id=11               
update sys_module set `enable`=true ,name='档案管理', url='archives.menu.list',custom_data='{"tenantType": [0,1], "defaultExist": false}',description='档案管理流程精细化管理' where id=11;
commit;
                  
#2.新增档案管理预设角色
#预设角色id=14
INSERT INTO `sys_role_preset` (`name`, `code`, `data_scope`, `descript`, `tenant_type`, `create_by`, `create_time`, `update_by`, `update_time`, `enable`, `del_flag`, `remark`) 
VALUES ('档案模块管理员', NULL, '1', '档案模块所有可用权限', '0,1', NULL, now(), NULL, now(), b'1', b'0', 'AEK,医疗机构所拥有');

#2.删除原来监管平台管理模块管理相应模块数据
delete from sys_role_permission where module_id=11;
delete from sys_tenant_module where module_id=11;
commit;


#3.调用存储过程，初始化机构与计量管理模块关系，参数=模块ID
call add_module_procedure(11);

#4.插入档案管理权限码
#菜单【档案管理】 ID=143
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '11', '档案管理', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【档案新增及编辑权限】 ID=144
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('143', '11', '档案新增及编辑', 'ARCHIVE_NEW_EDIT', b'0', NULL, NULL, now(), now(), b'1', '档案新增及编辑', NULL);

#菜单【档案查询】 ID=145
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('0', '11', '档案查询', NULL, b'1', NULL, NULL, now(), now(), b'1', NULL, NULL);
#权限【档案报表记录查看权限】 ID=146
INSERT INTO `sys_permission` (`parent_id`, `module_id`, `name`, `code`, `menu_flag`, `url`, `target`, `create_time`, `update_time`, `enable`, `description`, `custom_data`) 
VALUES ('145', '11', '档案报表记录查看', 'ARCHIVE_RECORD_VIEW', b'0', NULL, NULL, now(), now(), b'1', '档案报表记录查看', NULL);

#5.（1）给所有拥有该模块的机构角色赋予新增权限，默认不勾选，（2）给预设角色添加新增权限
#5.执行请求接口:调用之前先得插入预设角色 
#权限137-142

#143
call add_role_permission(11,143);
call add_role_permission_preset2(1,11,143);
call add_role_permission_preset2(14,11,143);

#144
call add_role_permission(11,144);
call add_role_permission_preset2(1,11,144);
call add_role_permission_preset2(14,11,144);

#145
call add_role_permission(11,145);
call add_role_permission_preset2(1,11,145);
call add_role_permission_preset2(14,11,145);

#146
call add_role_permission(11,146);
call add_role_permission_preset2(1,11,146);
call add_role_permission_preset2(14,11,146);

#6.调用存储过程，前提：先插入预设角色信息以及给预设角色分配权限，预设角色ID=14
call add_preset_role_procedure_01(14,'');

#7.给计量管理模块添加权限时，需给预设角色机构管理员及计量管理员角色赋予该权限且启用。
update sys_role_permission_preset set enable=true,del_flag=false where preset_role_id in(1,14) and module_id=11 and permission_id in(143,144,145,146);

#8.预设角色为机构管理员与计量管理员的角色权限，需启用新增权限
update sys_role_permission  set enable=true,del_flag=false where  module_id=11 and permission_id in(143,144,145,146) and role_id in (select id from sys_role where preset_id in (1,14));

#9.默认将保养管理赋予爱怡康机构
update sys_tenant_module set enable=true,del_flag=false where  module_id=11 and tenant_id=1;
commit;


