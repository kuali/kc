set define off
DECLARE
  l_role_id VARCHAR2(40);
  l_default_type_id VARCHAR2(40);
  l_unit_type_id VARCHAR2(40);
  l_unit_hierarchy_type_id VARCHAR2(40);
  l_perm_id VARCHAR2(40);
  l_role_type_id VARCHAR2(40);
  l_unit_number_attr_id VARCHAR2(40);
  l_descend_attr_id VARCHAR2(40);
  l_qual_attr_id VARCHAR2(40);

  CURSOR RCUR IS SELECT * FROM KIM_ROLES_T;

BEGIN     
  SELECT KIM_TYP_ID INTO l_unit_type_id FROM KRIM_TYP_T T WHERE T.NM = 'Unit';
  SELECT KIM_TYP_ID INTO l_unit_hierarchy_type_id FROM KRIM_TYP_T T WHERE T.NM = 'UnitHierarchy';
  SELECT KIM_TYP_ID INTO l_default_type_id FROM KRIM_TYP_T T WHERE T.NM = 'Default';

  SELECT KIM_ATTR_DEFN_ID INTO l_unit_number_attr_id FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'kra.unitNumber';
  SELECT KIM_ATTR_DEFN_ID INTO l_descend_attr_id FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'kra.subunits';

  INSERT INTO KRIM_TYP_T (KIM_TYP_ID, NM, SRVC_NM, ACTV_IND, NMSPC_CD, OBJ_ID) VALUES (KRIM_TYP_ID_S.NEXTVAL, 'ProposalType', 'kimTypeService', 'Y', 'KC-SYS', SYS_GUID());
  INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL, OBJ_ID) VALUES (KRIM_ATTR_DEFN_ID_S.NEXTVAL, 'kra.proposal', 'Proposal Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes', '${application.url}', SYS_GUID());
  INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID) VALUES (KRIM_TYP_ATTR_ID_S.NEXTVAL, KRIM_TYP_ID_S.CURRVAL, KRIM_ATTR_DEFN_ID_S.CURRVAL, 'A', 'Y', SYS_GUID());

  INSERT INTO KRIM_TYP_T (KIM_TYP_ID, NM, SRVC_NM, ACTV_IND, NMSPC_CD, OBJ_ID) VALUES (KRIM_TYP_ID_S.NEXTVAL, 'ProtocolType', 'kimTypeService', 'Y', 'KC-SYS', SYS_GUID());
  INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL, OBJ_ID) VALUES (KRIM_ATTR_DEFN_ID_S.NEXTVAL, 'kra.protocol', 'Protocol Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes', '${application.url}', SYS_GUID());
  INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID) VALUES (KRIM_TYP_ATTR_ID_S.NEXTVAL, KRIM_TYP_ID_S.CURRVAL, KRIM_ATTR_DEFN_ID_S.CURRVAL, 'A', 'Y', SYS_GUID());

  INSERT INTO KRIM_TYP_T (KIM_TYP_ID, NM, SRVC_NM, ACTV_IND, NMSPC_CD, OBJ_ID) VALUES (KRIM_TYP_ID_S.NEXTVAL, 'CommitteeType', 'kimTypeService', 'Y', 'KC-SYS', SYS_GUID());
  INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL, OBJ_ID) VALUES (KRIM_ATTR_DEFN_ID_S.NEXTVAL, 'kra.committee', 'Committee Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes', '${application.url}', SYS_GUID());
  INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID) VALUES (KRIM_TYP_ATTR_ID_S.NEXTVAL, KRIM_TYP_ID_S.CURRVAL, KRIM_ATTR_DEFN_ID_S.CURRVAL, 'A', 'Y', SYS_GUID());

  INSERT INTO KRIM_TYP_T (KIM_TYP_ID, NM, SRVC_NM, ACTV_IND, NMSPC_CD, OBJ_ID) VALUES (KRIM_TYP_ID_S.NEXTVAL, 'AwardType', 'kimTypeService', 'Y', 'KC-SYS', SYS_GUID());
  INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL, OBJ_ID) VALUES (KRIM_ATTR_DEFN_ID_S.NEXTVAL, 'kra.award', 'Award Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes', '${application.url}', SYS_GUID());
  INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID) VALUES (KRIM_TYP_ATTR_ID_S.NEXTVAL, KRIM_TYP_ID_S.CURRVAL, KRIM_ATTR_DEFN_ID_S.CURRVAL, 'A', 'Y', SYS_GUID());

  INSERT INTO KRIM_TYP_T (KIM_TYP_ID, NM, SRVC_NM, ACTV_IND, NMSPC_CD, OBJ_ID) VALUES (KRIM_TYP_ID_S.NEXTVAL, 'TimeAndMoneyType', 'kimTypeService', 'Y', 'KC-SYS', SYS_GUID());
  INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL, OBJ_ID) VALUES (KRIM_ATTR_DEFN_ID_S.NEXTVAL, 'kra.timeandmoney', 'TimeAndMoney Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes', '${application.url}', SYS_GUID());
  INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID) VALUES (KRIM_TYP_ATTR_ID_S.NEXTVAL, KRIM_TYP_ID_S.CURRVAL, KRIM_ATTR_DEFN_ID_S.CURRVAL, 'A', 'Y', SYS_GUID());


  FOR REC IN RCUR

  LOOP
    SELECT (CASE 
           WHEN REC.DESCEND_FLAG = 'Y' THEN l_unit_hierarchy_type_id 
           WHEN REC.ROLE_TYPE_CODE IN ('O', 'D', 'A') THEN l_unit_hierarchy_type_id 
           ELSE l_unit_type_id
           END) INTO l_role_type_id FROM DUAL;
    
    DBMS_OUTPUT.PUT_LINE(RPAD(REC.NAME, 40) ||  'Role Migration starts here');
    
    INSERT INTO KRIM_ROLE_T (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID) 
    VALUES (KRIM_ROLE_ID_S.NEXTVAL, REC.NAME, DECODE(REC.ROLE_TYPE_CODE, 'D', 'KC-UNT', 'O', 'KC-ADM', 'P', 'KRA-PD', 'R', 'KC-PROTOCOL', 'A', 'KC-AWARD'), REC.DESCRIPTION, DECODE(REC.DESCEND_FLAG, 'Y', l_unit_hierarchy_type_id, 'N', l_unit_type_id), 'Y', SYSDATE, SYS_GUID());
    
    -- Taking care of Role-Permission mapping
    DECLARE 
            CURSOR RPCUR IS SELECT T.ROLE_ID, T.PERMISSION_ID, T1.NAME, T1.DESCRIPTION, T.ACTIVE_FLAG 
            FROM KIM_ROLES_PERMISSIONS_T T, KIM_PERMISSIONS_T T1 WHERE T.PERMISSION_ID = T1.ID AND T.ROLE_ID = REC.ID;
    BEGIN
           FOR RPREC IN RPCUR 
           LOOP
               BEGIN 
               SELECT T.PERM_ID into l_perm_id FROM KRIM_PERM_T T WHERE T.DESC_TXT = RPREC.DESCRIPTION;               
               INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, obj_id) VALUES 
               (KRIM_ROLE_PERM_ID_S.NEXTVAL, KRIM_ROLE_ID_S.CURRVAL, l_perm_id, RPREC.ACTIVE_FLAG, sys_guid());
               --DBMS_OUTPUT.PUT_LINE(RPAD(REC.NAME, 40) ||  RPAD(RPREC.NAME, 50));
               EXCEPTION 
                     WHEN NO_DATA_FOUND THEN  
                       IF(RPREC.NAME = 'MAINTAIN_IRB_CORRESP_TEMPLATE') THEN  
                              -- special case handling
                              -- where original Permission is becoming 3 new permissions because of the Permission Template
                             INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, obj_id) VALUES 
                              (KRIM_ROLE_PERM_ID_S.NEXTVAL, KRIM_ROLE_ID_S.CURRVAL, (select perm_id from krim_perm_t t where t.nm = 'Maintain Correspondence Generated'), RPREC.ACTIVE_FLAG, sys_guid()); 
                             INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, obj_id) VALUES 
                              (KRIM_ROLE_PERM_ID_S.NEXTVAL, KRIM_ROLE_ID_S.CURRVAL, (select perm_id from krim_perm_t t where t.nm = 'Maintain Correspondence Types'), RPREC.ACTIVE_FLAG, sys_guid()); 
                             INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, obj_id) VALUES 
                              (KRIM_ROLE_PERM_ID_S.NEXTVAL, KRIM_ROLE_ID_S.CURRVAL, (select perm_id from krim_perm_t t where t.nm = 'Maintain Correspondent Type'), RPREC.ACTIVE_FLAG, sys_guid()); 
                       END IF;              
                       DBMS_OUTPUT.PUT_LINE(RPAD(REC.NAME, 40) ||  RPAD(RPREC.NAME, 50) || '       ' || 'Awaiting Clarification');
               END;
               
           END LOOP;
    END;

    -- Taking care of Role-Member association and role qualification at Unit Hierarchy level
    DECLARE 
            CURSOR RMCUR IS SELECT T.PERSON_ID, T.UNIT_NUMBER, T.SUBUNITS, T.ACTIVE_FLAG 
            FROM UNIT_ACL T WHERE T.ROLE_ID = REC.ID;
    BEGIN
           FOR RMREC IN RMCUR 
           LOOP
               INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
               VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, KRIM_ROLE_ID_S.CURRVAL, RMREC.PERSON_ID, 'P', NULL, NULL, SYSDATE, SYS_GUID(), 1);

               INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
               VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_ROLE_MBR_ID_S.CURRVAL, l_role_type_id, l_unit_number_attr_id, RMREC.UNIT_NUMBER, SYS_GUID());
               
               IF(l_role_type_id = l_unit_hierarchy_type_id) THEN 
                     INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
                     VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_ROLE_MBR_ID_S.CURRVAL, l_role_type_id, l_descend_attr_id, RMREC.SUBUNITS, SYS_GUID());

               END IF;
               
           END LOOP;
    END;
    
    -- Taking care of Role-Member association and role qualification at the transactional document level
    DECLARE 
            CURSOR RMTCUR IS SELECT T.ID, T.ROLE_ID, T.PERSON_ID FROM KIM_ROLES_PERSONS_QUAL_T T WHERE T.ROLE_ID = REC.ID;
    BEGIN
           FOR RMTREC IN RMTCUR 
           LOOP
               INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
               VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, KRIM_ROLE_ID_S.CURRVAL, RMTREC.PERSON_ID, 'P', NULL, NULL, SYSDATE, SYS_GUID(), 1);

              DECLARE 
                      CURSOR RMQCUR IS SELECT T.ROLE_PERSON_ID, T.ATTRIBUTE_NAME, T.ATTRIBUTE_VALUE FROM KIM_PERSON_QUAL_ATTR_T T WHERE T.ROLE_PERSON_ID = RMTREC.ID;
              BEGIN
                     FOR RMQREC IN RMQCUR 
                     LOOP
                         SELECT KIM_ATTR_DEFN_ID INTO l_qual_attr_id FROM KRIM_ATTR_DEFN_T A WHERE A.NM = RMQREC.ATTRIBUTE_NAME;
                         INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
                         VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_ROLE_MBR_ID_S.CURRVAL,  decode (RMQREC.ATTRIBUTE_NAME, 'kra.unitNumber', l_role_type_id, (SELECT KIM_TYP_ID FROM KRIM_TYP_ATTR_T WHERE KIM_ATTR_DEFN_ID = l_qual_attr_id)), l_qual_attr_id, RMQREC.ATTRIBUTE_VALUE, SYS_GUID());
                     END LOOP;
              END;
           END LOOP;
    END;

    DBMS_OUTPUT.PUT_LINE(RPAD(REC.NAME, 40) ||  'Role Migration ends here');
  END LOOP;
  
    -- Need to uncomment if the Awards Permissions tab is going away completely. Else we don't need these updates
    -- Waiting for confirmation on this
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-ADM' WHERE ROLE_NM = 'Application Administrator' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-ADM' WHERE ROLE_NM = 'Award Modifier' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Award Viewer' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-ADM' WHERE ROLE_NM = 'Award Budget Admnistrator' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Award Budget Aggregator' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Award Budget Modifier' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Award Budget Viewer' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-ADM' WHERE ROLE_NM = 'Award Budget Approver' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Award Budget Maintainer' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Award Documents Viewer' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Award Documents Maintainer' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Departments Awards Viewer' AND NMSPC_CD = 'KC-AWARD';
	-- UPDATE KRIM_ROLE_T SET NMSPC_CD = 'KC-UNT' WHERE ROLE_NM = 'Template Viewer' AND NMSPC_CD = 'KC-AWARD';
	-- DELETE FROM KRIM_ROLE_T WHERE 'Award Unassigned' AND NMSPC_CD = 'KC-AWARD';
    COMMIT;
END;
