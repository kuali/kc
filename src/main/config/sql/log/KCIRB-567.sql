       ---- Protocol Document / IRBReceipt - for future protocol w/f use
INSERT INTO KRIM_RSP_T (RSP_ID, RSP_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID) 
       VALUES (KRIM_RSP_ID_S.NEXTVAL, 1, 'KC-WKFLW', 'IRB Receipt', 'Protocol Document - IRBReceipt', 'Y', SYS_GUID());
    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
       VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 13, 'ProtocolDocument', SYS_GUID());
    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
       VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 16, 'IRBReceipt', SYS_GUID());
    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
       VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 40, 'false', SYS_GUID());
    INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
       VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 7, 41, 'false', SYS_GUID());
    INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, ROLE_ID, RSP_ID, ACTV_IND, OBJ_ID) 
       VALUES (KRIM_ROLE_RSP_ID_S.NEXTVAL, KRIM_ROLE_ID_S.NEXTVAL, KRIM_RSP_ID_S.CURRVAL, 'Y', SYS_GUID());
    INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, ROLE_MBR_ID, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_RSP_ID, FRC_ACTN, OBJ_ID) 
       VALUES (KRIM_ROLE_RSP_ACTN_ID_S.NEXTVAL, '*', 'A', 1, 'F', KRIM_ROLE_RSP_ID_S.NEXTVAL, 'Y', SYS_GUID());


    --- update FORCE ACTION to true for all actions with resp  protocol IRBApprovers
update krim_role_rsp_actn_t actn set frc_actn = 'Y'
where exists 
(select 's' from krim_rsp_t resp 
	join krim_role_rsp_t 	role_resp on resp.rsp_id = role_resp.rsp_id 
	join krim_role_t 	role      on role.role_id     = role_resp.role_id 
	where actn.role_rsp_id = role_resp.role_rsp_id and role.role_nm = 'IRBApprover');

/

