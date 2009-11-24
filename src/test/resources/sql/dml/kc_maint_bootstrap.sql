insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values ('1144', 'Manager', 'KC-SYS', 'This role represents a collection of all the KC module manager roles and has permission to initiate simple maintenance documents.', (select kim_typ_id from krim_typ_t where nm = 'Default' and nmspc_cd = 'KUALI'), 'Y', sysdate, sys_guid());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1068', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Administer Routing for Document'), 'KC-SYS', 'Administer Routing for Document', 'Allows users to open KC documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1110', '1068', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1069', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document'), 'KC-SYS', 'Blanket Approve Document', 'Allows access to the Blanket Approval button on KC Documents.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1111', '1069', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10111', '1144', '1069', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1070', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-SYS' AND T.NM = 'Initiate Document'), 'KC-SYS', 'Initiate Document', 'Authorizes the initiation of KC Documents.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1112', '1070', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1071', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Open Document'), 'KC-SYS', 'Open Document', 'Authorizes users to open KC Documents.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1113', '1071', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1072', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Save Document'), 'KC-SYS', 'Save Document', 'Authorizes user to save documents answering to the KC parent document Type.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1114', '1072', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type & Routing Node or State'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID()); 

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10112', '1144', '1072', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1073', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Delete Note / Attachment'), 'KC-SYS', 'Delete Note / Attachment', 'Authorizes users to delete notes and attachments created by any user on documents answering to the KC parent document type.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1115', '1073', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Document Type & Relationship to Note Author'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());
 
    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1116', '1073', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Document Type & Relationship to Note Author'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'createdBySelfOnly'), 'false', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10113', '1144', '1073', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1074', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Ad Hoc Review Document'), 'KC-SYS', 'Ad Hoc Review Document', 'Authorizes users to take the Approve action on KC documents Ad Hoc routed to them.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1117', '1074', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1118', '1074', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd'), 'A', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1075', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Ad Hoc Review Document'), 'KC-SYS', 'Ad Hoc Review Document', 'Authorizes users to take the FYI action on KC documents Ad Hoc routed to them.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1119', '1075', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1120', '1075', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd'), 'F', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1076', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Ad Hoc Review Document'), 'KC-SYS', 'Ad Hoc Review Document', 'Authorizes users to take the Acknowledge action on KC documents Ad Hoc routed to them.', 'Y', SYS_GUID());
 
    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1121', '1076', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1122', '1076', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd'), 'K', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1077', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-SYS' AND T.NM = 'Initiate Document'), 'KC-SYS', 'Initiate Document', 'Authorizes the initiation of KC Simple Maintenance documents.', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1123', '1077', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KcMaintenanceDocument', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10114', '1144', '1077', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1078', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Assign Role'), 'KC-SYS', 'Assign Role', 'Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for roles with a Module Code beginning with KRA.', 'Y', SYS_GUID());
--Need to decide how to include KRA* or KC*

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1124', '1078', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Role'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1125', '1078', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Role'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10115', '1144', '1078', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1079', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Grant Permission'), 'KC-SYS', 'Grant Permission', 'Authorizes users to modify the information on the Permissions tab of the Role Document for roles with a module code beginning with KRA.', 'Y', SYS_GUID());
--Need to decide how to include KRA* or KC*

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1126', '1079', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Permission'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1127', '1079', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Permission'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10116', '1144', '1079', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1080', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Grant Responsibility'), 'KC-SYS', 'Grant Responsibility', 'Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with a Module Code that begins with KFS.', 'Y', SYS_GUID());
--Need to decide how to include KRA* or KC*

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1128', '1080', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Responsibility'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1129', '1080', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Responsibility'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10117', '1144', '1080', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1081', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Maintain System Parameter'), 'KC-SYS', 'Maintain System Parameter', 'Authorizes users to initiate and edit the Parameter document for pameters with a module code beginning with KFS.', 'Y', SYS_GUID());
--Need to decide how to include KRA* or KC*

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1130', '1081', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1131', '1081', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10118', '1144', '1081', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1132', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Maintain System Parameter'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KR-SYS', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1133', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Maintain System Parameter'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KR*', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10119', '1144', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Maintain System Parameter'), 'Y', SYS_GUID());

UPDATE KRIM_PERM_T T SET T.DESC_TXT = 'Authorizes users to view the password field on the Person document and inquriy.' WHERE T.NM = 'Full Unmask Field' AND T.NMSPC_CD = 'KR-SYS' AND ROWNUM = 1;

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1134', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '%password field%'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Component Field'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'componentName'), 'KimPrincipalImpl', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1135', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '%password field%'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Component Field'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'propertyName'), 'password', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10120', '1144', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '%password field%'), 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1082', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KUALI' AND T.NM = 'Default'), 'KR-WKFLW', 'View Other Action List', 'Authorizes users to access other user''s action lists via the Help Desk Action List Login.', 'Y', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10121', '1144', '1082', 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1083', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KUALI' AND T.NM = 'Default'), 'KR-WKFLW', 'Unrestricted Document Search', 'Allows power users to bypass the security associated with certain document types to limit the result set.', 'Y', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10122', '1144', '1083', 'Y', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1136', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '% Tax Identification Number%'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Component Field'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'componentName'), 'IdentityManagementPersonDocument', SYS_GUID());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1137', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '% Tax Identification Number%'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Component Field'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'propertyName'), 'taxId', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10123', '1144', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '% Tax Identification Number%'), 'Y', SYS_GUID());

insert into krim_role_mbr_t (role_mbr_id, role_id, mbr_id, mbr_typ_cd, actv_frm_dt, actv_to_dt, last_updt_dt, ver_nbr, obj_id) 
values (krim_role_mbr_id_s.nextval, '1144', (select grp_id from krim_grp_t where grp_nm = 'KcAdmin' and nmspc_cd = 'KC-WKFLW'), 'G', null, null, sysdate, 1, sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
values ('1138', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '% Tax Identification Number%'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'RiceDocument', SYS_GUID());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
values ('10124', '1144', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'), 'Y', SYS_GUID());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1084', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document'), 'KRA-PD', 'Blanket Approve ProposalDevelopmentDocument', 'Blanket Approve ProposalDevelopmentDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1139', '1084', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'ProposalDevelopmentDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1085', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document'), 'KC-PROTOCOL', 'Blanket Approve ProtocolDocument', 'Blanket Approve ProtocolDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1140', '1085', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'ProtocolDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values ('1086', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document'), 'KC-PROTOCOL', 'Blanket Approve CommitteeDocument', 'Blanket Approve CommitteeDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1141', '1086', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'CommitteeDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values ('1087', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document'), 'KC-AWARD', 'Blanket Approve AwardDocument', 'Blanket Approve AwardDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1142', '1087', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'AwardDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values ('1088', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document'), 'KC-AWARD', 'Blanket Approve AwardBudgetDocument', 'Blanket Approve AwardBudgetDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1143', '1088', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'AwardBudgetDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values ('1089', (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document'), 'KC-AWARD', 'Blanket Approve TimeAndMoneyDocument', 'Blanket Approve TimeAndMoneyDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1144', '1089', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'TimeAndMoneyDocument', sys_guid() );
  
    UPDATE KRIM_PERM_T T SET NMSPC_CD = 'KUALI', DESC_TXT = 'Authorizes users to take Approve, Acknowledge or FYI action on KUALI documents Ad Hoc routed to them.' WHERE PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Ad Hoc Review Document');
delete from krim_perm_attr_data_t where perm_id = (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Ad Hoc Review Document');

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1145', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Ad Hoc Review Document'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'KualiDocument', sys_guid() );

UPDATE KRIM_PERM_T T SET NMSPC_CD = 'KUALI', DESC_TXT = 'Allows access to the Copy button on KC Documents.' WHERE PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Copy Document');
delete from krim_perm_attr_data_t where perm_id = (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Copy Document');

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values('1146', (SELECT PERM_ID FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Copy Document'), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName') , 'KualiDocument', sys_guid() );