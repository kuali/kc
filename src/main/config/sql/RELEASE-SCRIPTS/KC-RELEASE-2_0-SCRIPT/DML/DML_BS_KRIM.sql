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
    l_cancel_doc_perm_tmpl_id VARCHAR2(40);
    l_route_doc_perm_tmpl_id VARCHAR2(40);
    l_maintain_parm_perm_id VARCHAR2(40);
    l_full_unmask_fld_perm_id VARCHAR2(40);
    l_initiate_doc_perm_id VARCHAR2(40);
    l_adhoc_review_doc_perm_id VARCHAR2(40);
    l_copy_doc_perm_id VARCHAR2(40);

    l_doc_type_id VARCHAR2(40); 
    l_doc_type_rte_node_id VARCHAR2(40); 
    l_doc_type_note_id VARCHAR2(40); 
    l_unit_hier_type_id VARCHAR2(40);
    l_unit_type_id VARCHAR2(40);
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
    SELECT PERM_TMPL_ID INTO l_cancel_doc_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Cancel Document';
    SELECT PERM_TMPL_ID INTO l_route_doc_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-WKFLW' AND T.NM = 'Route Document';

    SELECT PERM_TMPL_ID INTO l_assign_role_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Assign Role';
    SELECT PERM_TMPL_ID INTO l_grant_perm_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Grant Permission';
    SELECT PERM_TMPL_ID INTO l_grant_resp_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-IDM' AND T.NM = 'Grant Responsibility';
    SELECT PERM_TMPL_ID INTO l_maintain_parm_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KR-NS' AND T.NM = 'Maintain System Parameter';
    SELECT PERM_TMPL_ID INTO l_default_perm_tmpl_id FROM KRIM_PERM_TMPL_T T WHERE T.NMSPC_CD = 'KUALI' AND T.NM = 'Default';

    SELECT KIM_TYP_ID INTO l_doc_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)';
    SELECT KIM_TYP_ID INTO l_doc_type_rte_node_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type & Routing Node or State';
    SELECT KIM_TYP_ID INTO l_doc_type_note_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Document Type & Relationship to Note Author';
    SELECT KIM_TYP_ID INTO l_adhoc_review_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review';
    SELECT KIM_TYP_ID INTO l_unit_hier_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'UnitHierarchy';
    SELECT KIM_TYP_ID INTO l_unit_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Unit';
    SELECT KIM_TYP_ID INTO l_role_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Role';
    SELECT KIM_TYP_ID INTO l_perm_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Permission';
    SELECT KIM_TYP_ID INTO l_resp_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Responsibility'; 
    SELECT KIM_TYP_ID INTO l_parm_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'; 
    SELECT KIM_TYP_ID INTO l_comp_fld_type_id FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Component Field'; 
    
    SELECT KIM_ATTR_DEFN_ID INTO l_doc_type_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName';
    SELECT KIM_ATTR_DEFN_ID INTO l_created_by_self_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'createdBySelfOnly';
    SELECT KIM_ATTR_DEFN_ID INTO l_action_request_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd';
    SELECT KIM_ATTR_DEFN_ID INTO l_namespace_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode';
    SELECT KIM_ATTR_DEFN_ID INTO l_comp_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'componentName';
    SELECT KIM_ATTR_DEFN_ID INTO l_property_attr_id FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'propertyName';

    INSERT INTO KRIM_ROLE_PERM_T ( ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND )
	VALUES ( KRIM_ROLE_PERM_ID_S.NEXTVAL, '1DD197DD1EE24867A83B157DBB389FE7', 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'User' AND NMSPC_CD = 'KUALI'), 1088, 'Y');
	
	INSERT INTO KRIM_ROLE_PERM_T ( ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND )
	VALUES ( KRIM_ROLE_PERM_ID_S.NEXTVAL, 'D309EA64643F4799832AF1AFAE57B184', 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'User' AND NMSPC_CD = 'KUALI'), 1089, 'Y');
	
	INSERT INTO KRIM_ROLE_PERM_T ( ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND )
	VALUES ( KRIM_ROLE_PERM_ID_S.NEXTVAL, 'D68A7129791941BA9BC3555FFE424269', 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'User' AND NMSPC_CD = 'KUALI'), 1090, 'Y');
	
	-- KC Award Budget Permissions
    INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document') , 'KC-AB', 'Blanket Approve AwardBudgetDocument', 'Blanket Approve AwardBudgetDocument', 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document') , 'KC-AB', 'Submit AwardBudget', 'Submit award budget document', 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action') , 'KC-AB', 'Approve AwardBudget', 'Approve award budget document', 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action') , 'KC-AB', 'Post AwardBudget', 'Post award budget document', 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action') , 'KC-AB', 'Create AwardBudget', 'Create award budget document', 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action') , 'KC-AB', 'Maintain AwardBudgetRouting', 'Maintaining Award budget routing', 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section') , 'KC-AB', 'Modify AwardBudget', 'Modify Award Budget at unit level', 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_perm_t (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section') , 'KC-AB', 'View AwardBudget', 'View Award Budget at unit level', 'Y', SYS_GUID () ) ;

	-- KC Award Budget Permission Attribute Data
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit AwardBudget' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Approve AwardBudget' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Post AwardBudget' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain AwardBudgetRouting' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create AwardBudget' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify AwardBudget' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section' AND NMSPC_CD = 'KC-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Blanket Approve AwardBudgetDocument' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , 1, 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit AwardBudget' AND NMSPC_CD = 'KC-AB') , 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type (Permission)' AND NMSPC_CD = 'KR-SYS') , 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument') ;
	
	-- KC Award Budget Roles
	INSERT INTO krim_role_t (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID)
	VALUES(KRIM_ROLE_ID_S.NEXTVAL, 'Award Budget Administrator', 'KC-AB', 'Award Budget Administrator - the role grants permissions to manage any award budget at OSP level', 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T T1 WHERE T1.NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS') , 'Y', TO_DATE ('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS') , SYS_GUID () ) ;
	
	INSERT INTO krim_role_t (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID)
	VALUES(KRIM_ROLE_ID_S.NEXTVAL, 'Award Budget Maintainer', 'KC-AB', 'Maintain Award Budget - the role grants permissions to modify and submit award budget at departmental level', 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T T1 WHERE T1.NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS') , 'Y', TO_DATE ('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS') , SYS_GUID () ) ;
	
	INSERT INTO krim_role_t (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID)
	VALUES(KRIM_ROLE_ID_S.NEXTVAL, 'Award Budget Aggregator', 'KC-AB', 'Award Budget Aggregator - the role grants permissions to create and maintain award budget at department level', 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T T1 WHERE T1.NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS') , 'Y', TO_DATE ('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS') , SYS_GUID () ) ;
	
	INSERT INTO krim_role_t (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID)
	VALUES(KRIM_ROLE_ID_S.NEXTVAL, 'Award Budget Approver', 'KC-AB', 'Award Budget Approver - the role grants permissions to edit and approve award budget', 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T T1 WHERE T1.NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS') , 'Y', TO_DATE ('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS') , SYS_GUID () ) ;
	
	INSERT INTO krim_role_t (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID)
	VALUES(KRIM_ROLE_ID_S.NEXTVAL, 'Award Budget Modifier', 'KC-AB', 'Award Budget Modifier - the role grants permissions to modify or view award budget at departmental level', 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T T1 WHERE T1.NM = 'Unit' AND NMSPC_CD = 'KC-SYS') , 'Y', TO_DATE ('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS') , SYS_GUID () ) ;
	
	INSERT INTO krim_role_t (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID)
	VALUES(KRIM_ROLE_ID_S.NEXTVAL, 'Award Budget Viewer', 'KC-AB', 'Award Budget Viewer - the role grants permissions to view award budget at departmental level', 
	(SELECT KIM_TYP_ID FROM KRIM_TYP_T T1 WHERE T1.NM = 'Unit' AND NMSPC_CD = 'KC-SYS') , 'Y', TO_DATE ('2010-02-18 00:00:00', 'YYYY-MM-DD HH24:MI:SS') , SYS_GUID () ) ;

	-- KC Award Budget Administrator
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Administrator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Administrator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Post AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Administrator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Administrator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain AwardBudgetRouting' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Administrator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Administrator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	-- KC Award Budget Maintainer
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Maintainer') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Maintainer') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Maintainer') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	-- KC Award Budget Aggregator
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Aggregator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Aggregator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Aggregator') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	-- KC Award Budget Viewer
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Viewer') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	-- KC Award Budget Modifier
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Modifier') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;
	
	-- KC Award Budget Approver
	INSERT INTO krim_role_perm_t (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Approver') , 
	(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Approve AwardBudget' AND NMSPC_CD = 'KC-AB') , 'Y', SYS_GUID () ) ;

	-- KC Award Budget initial approval
	INSERT INTO KRIM_RSP_T (RSP_ID, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_RSP_ID_S.NEXTVAL, 1, 'KC-WKFLW', 'Award Budget InitialApproval', 'Award Budget Document - Initial Approval', 'Y', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'documentTypeName' AND T.NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'routeNodeName' AND T.NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetInitialApproval', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'required' AND T.NMSPC_CD = 'KR-WKFLW') , 'false', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'actionDetailsAtRoleMemberLevel' AND T.NMSPC_CD = 'KR-WKFLW') , 'false', SYS_GUID () ) ;
	
	INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, ROLE_ID, RSP_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_RSP_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Approver') , KRIM_RSP_ID_S.CURRVAL, 'Y', SYS_GUID () ) ;
	
	INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, ROLE_MBR_ID, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_RSP_ID, FRC_ACTN, OBJ_ID)
	VALUES(KRIM_ROLE_RSP_ACTN_ID_S.NEXTVAL, '*', 'A', 1, 'F', KRIM_ROLE_RSP_ID_S.CURRVAL, 'N', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_T (RSP_ID, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_RSP_ID_S.NEXTVAL, 1, 'KC-WKFLW', 'Award Budget OSPApproval', 'Award Budget Document - OSP Approval', 'Y', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'documentTypeName' AND T.NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetDocument', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'routeNodeName' AND T.NMSPC_CD = 'KR-WKFLW') , 'AwardBudgetOSPApproval', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'required' AND T.NMSPC_CD = 'KR-WKFLW') , 'false', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 
	(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'actionDetailsAtRoleMemberLevel' AND T.NMSPC_CD = 'KR-WKFLW') , 'false', SYS_GUID () ) ;
	
	INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, ROLE_ID, RSP_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_RSP_ID_S.NEXTVAL, 
	(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Approver') , KRIM_RSP_ID_S.CURRVAL, 'Y', SYS_GUID () ) ;
	
	INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, ROLE_MBR_ID, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_RSP_ID, FRC_ACTN, OBJ_ID)
	VALUES(KRIM_ROLE_RSP_ACTN_ID_S.NEXTVAL, '*', 'A', 1, 'F', KRIM_ROLE_RSP_ID_S.CURRVAL, 'N', SYS_GUID () ) ;

	-- KC Manager Role
    insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
    values (krim_role_id_s.nextval, 'Manager', 'KC-SYS', 'This role represents a collection of all the KC module manager roles and has permission to initiate simple maintenance documents.', (select kim_typ_id from krim_typ_t where nm = 'Default' and nmspc_cd = 'KUALI'), 'Y', sysdate, sys_guid());

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

	UPDATE KRIM_PERM_ATTR_DATA_T T SET T.PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document') 
	WHERE T.PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '% Tax Identification Number%') 
	AND ATTR_VAL = 'RiceDocument' ;
	
	-- add standard permissions for proposal log
	insert into krim_perm_t (perm_id, obj_id, ver_nbr, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind) 
	values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_init_doc_perm_tmpl_id, 'KC-IP', 'Create Proposal Log', 'Initiate a new Proposal Log', 'Y');
	
	insert into krim_perm_t (perm_id, obj_id, ver_nbr, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind) 
	values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_edit_doc_perm_tmpl_id, 'KC-IP', 'Edit Proposal Log', 'Edit a Proposal Log', 'Y');
	
	insert into krim_perm_t (perm_id, obj_id, ver_nbr, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind) 
	values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_save_doc_perm_tmpl_id, 'KC-IP', 'Save Proposal Log', 'Save a Proposal Log', 'Y');
	
	insert into krim_perm_t (perm_id, obj_id, ver_nbr, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind) 
	values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_route_doc_perm_tmpl_id, 'KC-IP', 'Submit Proposal Log', 'Submit a Proposal Log', 'Y');
	
	insert into krim_perm_t (perm_id, obj_id, ver_nbr, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind) 
	values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_open_doc_perm_tmpl_id, 'KC-IP', 'Open Proposal Log', 'Open a Proposal Log', 'Y');
	
	insert into krim_perm_t (perm_id, obj_id, ver_nbr, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind) 
	values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_cancel_doc_perm_tmpl_id, 'KC-IP', 'Cancel Proposal Log', 'Cancel a Proposal Log', 'Y');
	
	-- specify doc type qualifier for proposal log permissions
	insert into krim_perm_attr_data_t (attr_data_id, obj_id, ver_nbr, perm_id, kim_typ_id, kim_attr_defn_id, attr_val) 
	values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Proposal Log'), l_doc_type_id, l_doc_type_attr_id, 'ProposalLogMaintenanceDocument');
	
	insert into krim_perm_attr_data_t (attr_data_id, obj_id, ver_nbr, perm_id, kim_typ_id, kim_attr_defn_id, attr_val) 
	values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Proposal Log'), l_doc_type_id, l_doc_type_attr_id, 'ProposalLogMaintenanceDocument');
	
	insert into krim_perm_attr_data_t (attr_data_id, obj_id, ver_nbr, perm_id, kim_typ_id, kim_attr_defn_id, attr_val) 
	values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Proposal Log'), l_doc_type_id, l_doc_type_attr_id, 'ProposalLogMaintenanceDocument');
	
	insert into krim_perm_attr_data_t (attr_data_id, obj_id, ver_nbr, perm_id, kim_typ_id, kim_attr_defn_id, attr_val) 
	values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Proposal Log'), l_doc_type_id, l_doc_type_attr_id, 'ProposalLogMaintenanceDocument');
	
	insert into krim_perm_attr_data_t (attr_data_id, obj_id, ver_nbr, perm_id, kim_typ_id, kim_attr_defn_id, attr_val) 
	values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), l_doc_type_id, l_doc_type_attr_id, 'ProposalLogMaintenanceDocument');
	
	insert into krim_perm_attr_data_t (attr_data_id, obj_id, ver_nbr, perm_id, kim_typ_id, kim_attr_defn_id, attr_val) 
	values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Proposal Log'), l_doc_type_id, l_doc_type_attr_id, 'ProposalLogMaintenanceDocument');

	-- assign proposal log permissions to OSP Administrator role
	insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) 
	values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where role_nm = 'OSP Administrator' and actv_ind = 'Y'), (select PERM_ID from KRIM_PERM_T where NM='Create Proposal Log'), 'Y');
	
	insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) 
	values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where role_nm = 'OSP Administrator' and actv_ind = 'Y'), (select PERM_ID from KRIM_PERM_T where NM='Edit Proposal Log'), 'Y');
	
	insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) 
	values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where role_nm = 'OSP Administrator' and actv_ind = 'Y'), (select PERM_ID from KRIM_PERM_T where NM='Save Proposal Log'), 'Y');
	
	insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) 
	values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where role_nm = 'OSP Administrator' and actv_ind = 'Y'), (select PERM_ID from KRIM_PERM_T where NM='Submit Proposal Log'), 'Y');
	
	insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) 
	values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where role_nm = 'OSP Administrator' and actv_ind = 'Y'), (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), 'Y');
	
	insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) 
	values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select role_id from krim_role_t where role_nm = 'OSP Administrator' and actv_ind = 'Y'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Proposal Log'), 'Y');

	-- add a new type for Proposal Log Derived Role: PI
	insert into krim_typ_t (kim_typ_id, obj_id, ver_nbr, nm, srvc_nm, actv_ind, nmspc_cd) 
	values (KRIM_TYP_ID_S.nextval, sys_guid(), 1, 'Derived Role - Proposal Log PI', 'proposalLogPiDerivedRoleTypeService', 'Y', 'KC-IP');
	
	-- add new PI role based on new type
	insert into krim_role_t (role_id, obj_id, ver_nbr, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind) 
	values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Proposal Log PI', 'KC-IP', 'Derived role from PI on Proposal Log', KRIM_TYP_ID_S.currval, 'Y');
	
	-- assign Open Document permission to the new PI role
	insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind) 
	values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Log PI' and NMSPC_CD = 'KC-IP'), (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), 'Y');

	-- add standard permissions for institutional proposal
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_init_doc_perm_tmpl_id, 'KC-IP', 'Create Institutional Proposal', 'Initiate a new Institutional Proposal', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_edit_doc_perm_tmpl_id, 'KC-IP', 'Edit Institutional Proposal', 'Edit a Institutional Proposal', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_save_doc_perm_tmpl_id, 'KC-IP', 'Save Institutional Proposal', 'Save a Institutional Proposal', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_route_doc_perm_tmpl_id, 'KC-IP', 'Submit Institutional Proposal', 'Submit a Institutional Proposal', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_open_doc_perm_tmpl_id, 'KC-IP', 'Open Institutional Proposal', 'Open a Institutional Proposal', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_cancel_doc_perm_tmpl_id, 'KC-IP', 'Cancel Institutional Proposal', 'Cancel a Institutional Proposal', 'Y');
	
	-- specify doc type qualifier for institutional proposal permissions
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), l_doc_type_id, l_doc_type_attr_id, 'InstitutionalProposalDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Institutional Proposal'), l_doc_type_id, l_doc_type_attr_id, 'InstitutionalProposalDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Institutional Proposal'), l_doc_type_id, l_doc_type_attr_id, 'InstitutionalProposalDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), l_doc_type_id, l_doc_type_attr_id, 'InstitutionalProposalDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), l_doc_type_id, l_doc_type_attr_id, 'InstitutionalProposalDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Institutional Proposal'), l_doc_type_id, l_doc_type_attr_id, 'InstitutionalProposalDocument');
	
	-- add standard permissions for IP Review
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_init_doc_perm_tmpl_id, 'KC-IP', 'Create Intellectual Property Review', 'Initiate a new Intellectual Property Review', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_edit_doc_perm_tmpl_id, 'KC-IP', 'Edit Intellectual Property Review', 'Edit a Intellectual Property Review', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_save_doc_perm_tmpl_id, 'KC-IP', 'Save Intellectual Property Review', 'Save a Intellectual Property Review', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_route_doc_perm_tmpl_id, 'KC-IP', 'Submit Intellectual Property Review', 'Submit a Intellectual Property Review', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_open_doc_perm_tmpl_id, 'KC-IP', 'Open Intellectual Property Review', 'Open a Intellectual Property Review', 'Y');
	insert into krim_perm_t values (KRIM_PERM_ID_S.nextval, sys_guid(), 1, l_cancel_doc_perm_tmpl_id, 'KC-IP', 'Cancel Intellectual Property Review', 'Cancel a Intellectual Property Review', 'Y');
	
	-- specify doc type qualifier for IP Review permissions
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), l_doc_type_id, l_doc_type_attr_id, 'IntellectualPropertyReviewMaintenanceDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), l_doc_type_id, l_doc_type_attr_id, 'IntellectualPropertyReviewMaintenanceDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), l_doc_type_id, l_doc_type_attr_id, 'IntellectualPropertyReviewMaintenanceDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), l_doc_type_id, l_doc_type_attr_id, 'IntellectualPropertyReviewMaintenanceDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), l_doc_type_id, l_doc_type_attr_id, 'IntellectualPropertyReviewMaintenanceDocument');
	insert into krim_perm_attr_data_t values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), l_doc_type_id, l_doc_type_attr_id, 'IntellectualPropertyReviewMaintenanceDocument');
	
	-- create new role: Institutional Proposal Viewer
	insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
	values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Institutional Proposal Viewer', 'KC-IP', 'View Institutional Proposals', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');
	
	-- assign institutional proposal (view) & IP (view) permissions to View Institutional Proposal role
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), 'Y');
	
	-- create new role: Institutional Proposal Maintainer
	insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
	values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Institutional Proposal Maintainer', 'KC-IP', 'Maintain Institutional Proposals', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');
	
	-- assign institutional proposal (mod) & IP permissions (mod) to Modify Institutional Proposal role
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Edit Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Save Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Cancel Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), 'Y');
	
	-- create new role: IP Review Maintainer
	insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
	values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Intellectual Property Review Maintainer', 'KC-IP', 'Maintain Intellectual Property Review', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');
	
	-- assign institutional proposal (view) & IP (mod) permissions to IP Review Maintainer role
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, KRIM_ROLE_ID_S.currval, (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), 'Y');
	
	-- add a new type for Derived Role: Unit Administrator
	insert into krim_typ_t values (KRIM_TYP_ID_S.nextval, sys_guid(), 1, 'Derived Role - Unit Administrator', 'unitAdministratorDerivedRoleTypeService', 'Y', 'KC-IP');
	
	-- add new Unit Administrator role based on Unit Administrator type
	insert into krim_role_t (role_id, obj_id, ver_nbr, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind) 
	values (KRIM_ROLE_ID_S.nextval, sys_guid(), 1, 'Unit Administrator', 'KC-IP', 'Derived role based on Unit', KRIM_TYP_ID_S.currval, 'Y');
	
	-- assign Open Document permissions to the new Unit Administrator role
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Unit Administrator'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
	insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Unit Administrator'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');

	INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND, NMSPC_CD, OBJ_ID)
	VALUES(KRIM_PERM_TMPL_ID_S.NEXTVAL, 'Answer Questionnaire Permission', 'Answer Questionnaire', l_doc_type_id, 'Y', 'KC-IDM', SYS_GUID () ) ;
	
	INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID)
	VALUES(KRIM_PERM_ID_S.NEXTVAL, KRIM_PERM_TMPL_ID_S.CURRVAL, 'Answer Protocol Questionnaire', NULL, 'Y', 'KC-PROTOCOL', SYS_GUID () ) ;
	
	INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, (select role_id from krim_role_t where role_nm = 'IRB Administrator' and nmspc_cd = 'KC-UNT'), KRIM_PERM_ID_S.CURRVAL, 'Y', SYS_GUID () ) ;
	
	INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID () , '1', KRIM_PERM_ID_S.CURRVAL, l_doc_type_id, l_doc_type_attr_id, 'ProtocolDocument') ;
	
	---- Protocol Document / IRBReceipt - for future protocol w/f use 
	INSERT INTO KRIM_RSP_T (RSP_ID, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID)
	VALUES(KRIM_RSP_ID_S.NEXTVAL, 1, 'KC-WKFLW', 'IRB Receipt', 'Protocol Document - IRBReceipt', 'Y', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 
	(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'), 
	(select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD = 'KR-WKFLW' and nm = 'documentTypeName'), 'ProtocolDocument', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 
	(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'), 
	(select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD = 'KR-WKFLW' and nm = 'routeNodeName'), 'IRBReceipt', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 
	(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'), 
	(select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD = 'KR-WKFLW' and nm = 'required'), 'false', SYS_GUID () ) ;
	
	INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID)
	VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 
	(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'), 
	(select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD = 'KR-WKFLW' and nm = 'actionDetailsAtRoleMemberLevel'), 'false', SYS_GUID () ) ;
	
	INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, ROLE_ID, RSP_ID, ACTV_IND, OBJ_ID)
	VALUES(KRIM_ROLE_RSP_ID_S.NEXTVAL, KRIM_ROLE_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 'Y', SYS_GUID () ) ;
	
	INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, ROLE_MBR_ID, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_RSP_ID, FRC_ACTN, OBJ_ID)
	VALUES(KRIM_ROLE_RSP_ACTN_ID_S.NEXTVAL, '*', 'A', 1, 'F', KRIM_ROLE_RSP_ID_S.NEXTVAL, 'Y', SYS_GUID () ) ;
	
	--- update FORCE ACTION to true for all actions with resp  protocol IRBApprovers 
	UPDATE krim_role_rsp_actn_t ACTN 
	SET FRC_ACTN='Y' 
	WHERE EXISTS (SELECT 's' FROM KRIM_RSP_T RESP JOIN KRIM_ROLE_RSP_T ROLE_RESP ON RESP.RSP_ID = ROLE_RESP.RSP_ID JOIN KRIM_ROLE_T ROLE ON ROLE.ROLE_ID = ROLE_RESP.ROLE_ID 
              WHERE ACTN.ROLE_RSP_ID = ROLE_RESP.ROLE_RSP_ID
                    AND ROLE.ROLE_NM = 'IRBApprover') ;
		
	COMMIT;
END;
/
-- Statements are order dependent, do not reorder.
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND, NMSPC_CD, OBJ_ID)
VALUES (KRIM_PERM_TMPL_ID_S.nextVal, 'Question Permission', 'Modify/View Question', 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 'Y', 'KC-IDM', SYS_GUID());


INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID)
VALUES (KRIM_PERM_ID_S.nextVal, KRIM_PERM_TMPL_ID_S.currVal, 'Modify Question', 'Modify Question', 'Y', 'KC-QUESTIONNAIRE', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID(), '1', KRIM_PERM_ID_S.currVal, 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionMaintenanceDocument') ;

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, 
(select role_id from KRIM_ROLE_T where NMSPC_CD = 'KC-UNT' and role_nm = 'IRB Administrator'), KRIM_PERM_ID_S.currVal, 'Y', SYS_GUID());


INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID)
VALUES (KRIM_PERM_ID_S.nextVal, KRIM_PERM_TMPL_ID_S.currVal, 'View Question', 'View Question', 'Y', 'KC-QUESTIONNAIRE', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID(), '1', KRIM_PERM_ID_S.currVal, 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionMaintenanceDocument') ;

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID)
VALUES (KRIM_ROLE_PERM_ID_S.nextval, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'), KRIM_PERM_ID_S.currVal, 'Y', SYS_GUID());

-- Questionnaire Permission 7 statments
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES (KRIM_PERM_TMPL_ID_S.nextVal, 'Questionnaire Permission', 'Modify/View Questionnaire', 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 'Y', 'KC-IDM', SYS_GUID());

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES (KRIM_PERM_ID_S.nextVal, KRIM_PERM_TMPL_ID_S.currVal, 'Modify Questionnaire', null, 'Y', 'KC-QUESTIONNAIRE', SYS_GUID());

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID) 
VALUES (KRIM_ROLE_PERM_ID_S.nextval, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'), KRIM_PERM_ID_S.currVal, 'Y', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID(), '1', KRIM_PERM_ID_S.currVal, 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionnaireMaintenanceDocument') ;


INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES (KRIM_PERM_ID_S.nextVal, KRIM_PERM_TMPL_ID_S.currVal, 'View Questionnaire', null, 'Y', 'KC-QUESTIONNAIRE', SYS_GUID());

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID) 
VALUES (KRIM_ROLE_PERM_ID_S.nextval, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'), KRIM_PERM_ID_S.currVal, 'Y', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID(), '1', KRIM_PERM_ID_S.currVal, 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionnaireMaintenanceDocument') ;

INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES (KRIM_PERM_TMPL_ID_S.nextVal, 'Answer Questionnaire Permission', 'Answer Questionnaire', 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 'Y', 'KC-IDM', SYS_GUID());

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES (KRIM_PERM_ID_S.nextVal, KRIM_PERM_TMPL_ID_S.currVal, 'Answer Protocol Questionnaire', null, 'Y', 'KC-PROTOCOL', SYS_GUID());

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID) 
VALUES (KRIM_ROLE_PERM_ID_S.nextval, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'), KRIM_PERM_ID_S.currVal, 'Y', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID(), '1', KRIM_PERM_ID_S.currVal, 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument') ;

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES (KRIM_PERM_ID_S.nextVal, 
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'), 'Modify Correspondence Template', null, 'Y', 'KC-PROTOCOL', SYS_GUID());

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID) 
VALUES (KRIM_ROLE_PERM_ID_S.nextval, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'), KRIM_PERM_ID_S.currVal, 'Y', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID(), '1', KRIM_PERM_ID_S.currVal, 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolCorrespondenceTemplateMaintenanceDocument') ;

-- Correspondence Permissions
INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES (KRIM_PERM_ID_S.nextVal, 
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'), 'View Correspondence Template', null, 'Y', 'KC-PROTOCOL', SYS_GUID());

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID) 
VALUES (KRIM_ROLE_PERM_ID_S.nextval, 
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'), KRIM_PERM_ID_S.currVal, 'Y', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES(KRIM_ATTR_DATA_ID_S.NEXTVAL, SYS_GUID(), '1', KRIM_PERM_ID_S.currVal, 
(SELECT KIM_TYP_T FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolCorrespondenceTemplateMaintenanceDocument') ;

--Proposal Hierarchy Permission 2 statements
INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, OBJ_ID)
VALUES(KRIM_PERM_ID_S.NEXTVAL, 
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'), 'KRA-PD', 'Maintain ProposalHierarchy', 'Create, modify and synchronize ProposalHierarchies', SYS_GUID() ) ;

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, OBJ_ID)
VALUES(KRIM_ROLE_PERM_ID_S.NEXTVAL, 
(select role_id from krim_role_t where role_nm = 'Aggregator' and nmspc_cd = 'KRA-PD'), KRIM_PERM_ID_S.CURVAL, SYS_GUID() ) ;

-- Print Proposal Permission 1 statement
UPDATE krim_perm_t 
SET NM='Print Proposal' 
WHERE NM = 'PRINT_PROPOSAL';

UPDATE krim_grp_t 
SET GRP_NM='ProposalAdmin' 
WHERE GRP_NM = 'Proposal Admin'
      AND NMSPC_CD = 'KC-WKFLW';

UPDATE krim_rsp_attr_data_t 
SET ATTR_VAL='OSPInitial' 
WHERE ATTR_VAL = 'OspInitial';

UPDATE krim_role_t T 
SET T.ROLE_NM='PI' 
WHERE ROLE_NM = 'PrimaryInvestigator'
      AND NMSPC_CD = 'KC-WKFLW';

UPDATE krim_role_t T 
SET T.ROLE_NM='COI' 
WHERE ROLE_NM = 'CoInvestigator'
      AND NMSPC_CD = 'KC-WKFLW';

UPDATE krim_role_t T 
SET T.ROLE_NM='KP' 
WHERE ROLE_NM = 'KeyPerson'
      AND NMSPC_CD = 'KC-WKFLW';
      
insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind)
 values (krim_role_perm_id_s.nextval, sys_guid(), '1',
   (select role_id from krim_role_t t where t.role_nm = 'User' and t.nmspc_cd = 'KUALI'),
   (select perm_id from krim_perm_t u where u.nm = 'Open Document' and u.nmspc_cd = 'KC-SYS'),
   'Y');

   -- KRIM_ROLE_T  
UPDATE KRIM_ROLE_T 
SET NMSPC_CD='KC-PD' 
WHERE NMSPC_CD = 'KRA-PD';

-- KRIM_PERM_T  
UPDATE KRIM_PERM_T 
SET NMSPC_CD='KC-PD' 
WHERE NMSPC_CD = 'KRA-PD';

COMMIT;