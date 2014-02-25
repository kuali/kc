  set define off;
  DECLARE 
    l_edit_doc_perm_tmpl_id VARCHAR2(40);
    l_init_doc_perm_tmpl_id VARCHAR2(40);
    l_save_doc_perm_tmpl_id VARCHAR2(40);
    l_admin_routing_perm_tmpl_id VARCHAR2(40);
    l_blanket_app_perm_tmpl_id VARCHAR2(40);
    l_open_doc_perm_tmpl_id VARCHAR2(40);
    l_delete_note_perm_tmpl_id VARCHAR2(40);
    l_adhoc_rev_doc_perm_tmpl_id VARCHAR2(40);                      
    l_assign_role_perm_tmpl_id VARCHAR2(40);                      
    l_grant_perm_perm_tmpl_id VARCHAR2(40);                      
    l_grant_resp_perm_tmpl_id VARCHAR2(40);  
    l_maintain_parm_perm_tmpl_id VARCHAR2(40);
    l_default_perm_tmpl_id VARCHAR2(40);
    l_maintain_parm_perm_id VARCHAR2(40);
    l_full_unmask_fld_perm_id VARCHAR2(40);
    l_initiate_doc_perm_id VARCHAR2(40);
    l_adhoc_review_doc_perm_id VARCHAR2(40);
    l_copy_doc_perm_id VARCHAR2(40);

    l_doc_type_id VARCHAR2(40); 
    l_doc_type_rte_node_id VARCHAR2(40); 
    l_doc_type_note_id VARCHAR2(40); 
    l_adhoc_review_type_id VARCHAR2(40);
    l_role_type_id VARCHAR2(40);
    l_perm_type_id VARCHAR2(40);
    l_resp_type_id VARCHAR2(40);
    l_parm_type_id VARCHAR2(40);
    l_comp_fld_type_id VARCHAR2(40);
    l_doc_type_attr_id VARCHAR2(40); 
    l_created_by_self_attr_id VARCHAR2(40); 
    l_action_request_attr_id VARCHAR2(40); 
    l_namespace_attr_id VARCHAR2(40); 
    l_comp_attr_id VARCHAR2(40); 
    l_property_attr_id VARCHAR2(40); 
 
    
  BEGIN

    SELECT PERM_TMPL_ID INTO l_edit_doc_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Edit Document';
    SELECT PERM_TMPL_ID INTO l_init_doc_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-SYS' AND T.NM = 'Initiate Document';
    SELECT PERM_TMPL_ID INTO l_save_doc_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Save Document';
    SELECT PERM_TMPL_ID INTO l_admin_routing_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Administer Routing for Document';
    SELECT PERM_TMPL_ID INTO l_blanket_app_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Blanket Approve Document';
    SELECT PERM_TMPL_ID INTO l_open_doc_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Open Document';
    SELECT PERM_TMPL_ID INTO l_delete_note_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Delete Note / Attachment';
    SELECT PERM_TMPL_ID INTO l_adhoc_rev_doc_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Ad Hoc Review Document';

    SELECT PERM_TMPL_ID INTO l_assign_role_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Assign Role';
    SELECT PERM_TMPL_ID INTO l_grant_perm_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Grant Permission';
    SELECT PERM_TMPL_ID INTO l_grant_resp_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Grant Responsibility';
    SELECT PERM_TMPL_ID INTO l_maintain_parm_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Maintain System Parameter';
    SELECT PERM_TMPL_ID INTO l_default_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KUALI' AND T.NM = 'Default';

    SELECT KIM_TYP_ID INTO l_doc_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)';
    SELECT KIM_TYP_ID INTO l_doc_type_rte_node_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type & Routing Node or State';
    SELECT KIM_TYP_ID INTO l_doc_type_note_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Document Type & Relationship to Note Author';
    SELECT KIM_TYP_ID INTO l_adhoc_review_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review';
    SELECT KIM_ATTR_DEFN_ID INTO l_doc_type_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName';
    SELECT KIM_ATTR_DEFN_ID INTO l_created_by_self_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'createdBySelfOnly';
    SELECT KIM_ATTR_DEFN_ID INTO l_action_request_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd';

    SELECT KIM_TYP_ID INTO l_role_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Role';
    SELECT KIM_TYP_ID INTO l_perm_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Permission';
    SELECT KIM_TYP_ID INTO l_resp_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Responsibility'; 
    SELECT KIM_TYP_ID INTO l_parm_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'; 
    SELECT KIM_TYP_ID INTO l_comp_fld_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Component Field'; 
    SELECT KIM_ATTR_DEFN_ID INTO l_namespace_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode';
    SELECT KIM_ATTR_DEFN_ID INTO l_comp_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'componentName';
    SELECT KIM_ATTR_DEFN_ID INTO l_property_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'propertyName';

    insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
    values (krim_role_id_s.nextval, 'Manager', 'KC-SYS', 'This role represents a collection of all the KC module manager roles and has permission to initiate simple maintenance documents.', (select kim_typ_id from krim_typ_t where nm = 'Default' and nmspc_cd = 'KUALI'), 'Y', sysdate, sys_guid());

    --insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    --values (krim_perm_id_s.nextval, l_edit_doc_perm_tmpl_id, 'KC-SYS', 'Edit Document', 'Edit Simple KC Maintenance Documents', 'Y', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_admin_routing_perm_tmpl_id, 'KC-SYS', 'Administer Routing for Document', 'Allows users to open KC documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).', 'Y', SYS_GUID());
    
    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id, 'KC', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_blanket_app_perm_tmpl_id, 'KC-SYS', 'Blanket Approve Document', 'Allows access to the Blanket Approval button on KC Documents.', 'Y', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id, 'KC', SYS_GUID());
    
    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_init_doc_perm_tmpl_id, 'KC-SYS', 'Initiate Document', 'Authorizes the initiation of KC Documents.', 'Y', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id, 'KC', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_open_doc_perm_tmpl_id, 'KC-SYS', 'Open Document', 'Authorizes users to open KC Documents.', 'Y', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id, 'KC', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_save_doc_perm_tmpl_id, 'KC-SYS', 'Save Document', 'Authorizes user to save documents answering to the KC parent document Type.', 'Y', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_rte_node_id, l_doc_type_attr_id, 'KC', SYS_GUID()); 

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_delete_note_perm_tmpl_id, 'KC-SYS', 'Delete Note / Attachment', 'Authorizes users to delete notes and attachments created by any user on documents answering to the KC parent document type.', 'Y', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_note_id, l_doc_type_attr_id, 'KC', SYS_GUID());
 
    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_note_id, l_created_by_self_attr_id, 'false', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_adhoc_rev_doc_perm_tmpl_id, 'KC-SYS', 'Ad Hoc Review Document', 'Authorizes users to take the Approve action on KC documents Ad Hoc routed to them.', 'Y', SYS_GUID());
    
    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_adhoc_review_type_id, l_doc_type_attr_id, 'KC', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_adhoc_review_type_id, l_action_request_attr_id, 'A', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_adhoc_rev_doc_perm_tmpl_id, 'KC-SYS', 'Ad Hoc Review Document', 'Authorizes users to take the FYI action on KC documents Ad Hoc routed to them.', 'Y', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_adhoc_review_type_id, l_doc_type_attr_id, 'KC', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_adhoc_review_type_id, l_action_request_attr_id, 'F', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_adhoc_rev_doc_perm_tmpl_id, 'KC-SYS', 'Ad Hoc Review Document', 'Authorizes users to take the Acknowledge action on KC documents Ad Hoc routed to them.', 'Y', SYS_GUID());
 
    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_adhoc_review_type_id, l_doc_type_attr_id, 'KC', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_adhoc_review_type_id, l_action_request_attr_id, 'K', SYS_GUID());
    
    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_init_doc_perm_tmpl_id, 'KC-SYS', 'Initiate Document', 'Authorizes the initiation of KC Simple Maintenance documents.', 'Y', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id, 'KcMaintenanceDocument', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_assign_role_perm_tmpl_id, 'KC-SYS', 'Assign Role', 'Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for roles with a Module Code beginning with KRA.', 'Y', SYS_GUID());
    --Need to decide how to include KRA* or KC*

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_role_type_id, l_namespace_attr_id, 'KRA*', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_role_type_id, l_namespace_attr_id, 'KC*', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_grant_perm_perm_tmpl_id, 'KC-SYS', 'Grant Permission', 'Authorizes users to modify the information on the Permissions tab of the Role Document for roles with a module code beginning with KRA.', 'Y', SYS_GUID());
    --Need to decide how to include KRA* or KC*

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_perm_type_id, l_namespace_attr_id, 'KRA*', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_perm_type_id, l_namespace_attr_id, 'KC*', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_grant_resp_perm_tmpl_id, 'KC-SYS', 'Grant Responsibility', 'Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with a Module Code that begins with KFS.', 'Y', SYS_GUID());
    --Need to decide how to include KRA* or KC*

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_resp_type_id, l_namespace_attr_id, 'KRA*', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_resp_type_id, l_namespace_attr_id, 'KC*', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_maintain_parm_perm_tmpl_id, 'KC-SYS', 'Maintain System Parameter', 'Authorizes users to initiate and edit the Parameter document for pameters with a module code beginning with KFS.', 'Y', SYS_GUID());
    --Need to decide how to include KRA* or KC*

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_parm_type_id, l_namespace_attr_id, 'KRA*', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_parm_type_id, l_namespace_attr_id, 'KC*', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    SELECT PERM_ID INTO l_maintain_parm_perm_id FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Maintain System Parameter';

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, l_maintain_parm_perm_id, l_parm_type_id, l_namespace_attr_id, 'KR-SYS', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, l_maintain_parm_perm_id, l_parm_type_id, l_namespace_attr_id, 'KR*', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, l_maintain_parm_perm_id, 'Y', SYS_GUID());

    UPDATE KRIM_PERM_T T SET T.DESC_TXT = 'Authorizes users to view the password field on the Person document and inquriy.' WHERE T.NM = 'Full Unmask Field' AND T.NMSPC_CD = 'KR-SYS' AND ROWNUM = 1;
    SELECT PERM_ID INTO l_full_unmask_fld_perm_id FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '%password field%';

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, l_full_unmask_fld_perm_id, l_comp_fld_type_id, l_comp_attr_id, 'KimPrincipalImpl', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, l_full_unmask_fld_perm_id, l_comp_fld_type_id, l_property_attr_id, 'password', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, l_full_unmask_fld_perm_id, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_default_perm_tmpl_id, 'KR-WKFLW', 'View Other Action List', 'Authorizes users to access other user''s action lists via the Help Desk Action List Login.', 'Y', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_default_perm_tmpl_id, 'KR-WKFLW', 'Unrestricted Document Search', 'Allows power users to bypass the security associated with certain document types to limit the result set.', 'Y', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, krim_perm_id_s.currval, 'Y', SYS_GUID());

    SELECT PERM_ID INTO l_full_unmask_fld_perm_id FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '% Tax Identification Number%';

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, l_full_unmask_fld_perm_id, l_comp_fld_type_id, l_comp_attr_id, 'IdentityManagementPersonDocument', SYS_GUID());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, l_full_unmask_fld_perm_id, l_comp_fld_type_id, l_property_attr_id, 'taxId', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, l_full_unmask_fld_perm_id, 'Y', SYS_GUID());

    insert into krim_role_mbr_t (role_mbr_id, role_id, mbr_id, mbr_typ_cd, actv_frm_dt, actv_to_dt, last_updt_dt, ver_nbr, obj_id) 
    values (krim_role_mbr_id_s.nextval, krim_role_id_s.currval, (select grp_id from krim_grp_t where grp_nm = 'KcAdmin' and nmspc_cd = 'KC-WKFLW'), 'G', null, null, sysdate, 1, sys_guid());

    SELECT PERM_ID INTO l_initiate_doc_perm_id FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document';

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id)  
    values (krim_attr_data_id_s.nextval, l_initiate_doc_perm_id, l_doc_type_id, l_doc_type_attr_id, 'RiceDocument', SYS_GUID());

    insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) 
    values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, l_initiate_doc_perm_id, 'Y', SYS_GUID());

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_blanket_app_perm_tmpl_id, 'KRA-PD', 'Blanket Approve ProposalDevelopmentDocument', 'Blanket Approve ProposalDevelopmentDocument', 'Y', sys_guid());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id , 'ProposalDevelopmentDocument', sys_guid() );

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_blanket_app_perm_tmpl_id, 'KC-PROTOCOL', 'Blanket Approve ProtocolDocument', 'Blanket Approve ProtocolDocument', 'Y', sys_guid());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id , 'ProtocolDocument', sys_guid() );

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
    values (krim_perm_id_s.nextval, l_blanket_app_perm_tmpl_id, 'KC-PROTOCOL', 'Blanket Approve CommitteeDocument', 'Blanket Approve CommitteeDocument', 'Y', sys_guid());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id , 'CommitteeDocument', sys_guid() );

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
    values (krim_perm_id_s.nextval, l_blanket_app_perm_tmpl_id, 'KC-AWARD', 'Blanket Approve AwardDocument', 'Blanket Approve AwardDocument', 'Y', sys_guid());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id , 'AwardDocument', sys_guid() );

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
    values (krim_perm_id_s.nextval, l_blanket_app_perm_tmpl_id, 'KC-AWARD', 'Blanket Approve AwardBudgetDocument', 'Blanket Approve AwardBudgetDocument', 'Y', sys_guid());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id , 'AwardBudgetDocument', sys_guid() );

    insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
    values (krim_perm_id_s.nextval, l_blanket_app_perm_tmpl_id, 'KC-AWARD', 'Blanket Approve TimeAndMoneyDocument', 'Blanket Approve TimeAndMoneyDocument', 'Y', sys_guid());

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, l_doc_type_id, l_doc_type_attr_id , 'TimeAndMoneyDocument', sys_guid() );
  
    SELECT PERM_ID INTO l_adhoc_review_doc_perm_id FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Ad Hoc Review Document';
    UPDATE KRIM_PERM_T T SET NMSPC_CD = 'KUALI', DESC_TXT = 'Authorizes users to take Approve, Acknowledge or FYI action on KUALI documents Ad Hoc routed to them.' WHERE PERM_ID = l_adhoc_review_doc_perm_id;
    delete from krim_perm_attr_data_t where perm_id = l_adhoc_review_doc_perm_id;

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, l_adhoc_review_doc_perm_id, l_adhoc_review_type_id, l_doc_type_attr_id , 'KualiDocument', sys_guid() );

    SELECT PERM_ID INTO l_copy_doc_perm_id FROM KRIM_PERM_T T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Copy Document';
    UPDATE KRIM_PERM_T T SET NMSPC_CD = 'KUALI', DESC_TXT = 'Allows access to the Copy button on KC Documents.' WHERE PERM_ID = l_copy_doc_perm_id;
    delete from krim_perm_attr_data_t where perm_id = l_copy_doc_perm_id;

    insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
    values(krim_attr_data_id_s.nextval, l_copy_doc_perm_id, l_doc_type_id, l_doc_type_attr_id , 'KualiDocument', sys_guid() );

    COMMIT;
END;
/