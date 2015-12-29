insert into protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('900', 'FYI in Progress', SYSDATE, 'kc', 1, sys_guid());
insert into protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('901', 'FYI Incorporated into Protocol', SYSDATE, 'kc', 1, sys_guid());
insert into coeus_sub_module (coeus_sub_module_id, module_code, sub_module_code, description, update_timestamp, update_user, ver_nbr, obj_id, require_unique_questionnaire)
  values ('900', '7', '900', 'FYI', SYSDATE, 'admin', 1, sys_guid(), 'N');

insert into iacuc_protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('900', 'FYI in Progress', SYSDATE, 'kc', 1, sys_guid());
insert into iacuc_protocol_status (protocol_status_code, description, update_timestamp, update_user, ver_nbr, obj_id)
  values ('901', 'FYI Incorporated into Protocol', SYSDATE, 'kc', 1, sys_guid());
insert into coeus_sub_module (coeus_sub_module_id, module_code, sub_module_code, description, update_timestamp, update_user, ver_nbr, obj_id, require_unique_questionnaire)
  values ('901', '9', '900', 'FYI', SYSDATE, 'admin', 1, sys_guid(), 'N');
