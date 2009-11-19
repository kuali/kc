set define off
DECLARE
 l_role_name VARCHAR2(500);
 l_new_role_id VARCHAR2(40);
 l_role_type_id VARCHAR2(40);
 
  l_default_type_id VARCHAR2(40);
  l_unit_type_id VARCHAR2(40);
  l_unit_hierarchy_type_id VARCHAR2(40);
  l_unit_number_attr_id VARCHAR2(40);
  l_descend_attr_id VARCHAR2(40);
  
 CURSOR UCUR IS SELECT * FROM UNIT_ACL WHERE ACTIVE_FLAG = 'Y';
 -- SELECT ID, PERSON_ID, ROLE_ID, UNIT_NUMBER, SUBUNITS, ACTIVE_FLAG, VER_NBR, OBJ_ID FROM UNIT_ACL;
BEGIN

    SELECT KIM_TYP_ID INTO l_unit_type_id FROM KRIM_TYP_T T WHERE T.NM = 'Unit';
    SELECT KIM_TYP_ID INTO l_unit_hierarchy_type_id FROM KRIM_TYP_T T WHERE T.NM = 'UnitHierarchy';
    SELECT KIM_TYP_ID INTO l_default_type_id FROM KRIM_TYP_T T WHERE T.NM = 'Default';

    SELECT KIM_ATTR_DEFN_ID INTO l_unit_number_attr_id FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'unitNumber';
    SELECT KIM_ATTR_DEFN_ID INTO l_descend_attr_id FROM KRIM_ATTR_DEFN_T T WHERE T.NM = 'subunits';

    FOR REC IN UCUR   
    LOOP
        SELECT NAME INTO l_role_name FROM KIM_ROLES_T T WHERE T.ID = REC.ROLE_ID;
        SELECT ROLE_ID INTO l_new_role_id FROM KRIM_ROLE_T WHERE ROLE_NM = l_role_name;
        SELECT KIM_TYP_ID INTO l_role_type_id FROM KRIM_ROLE_T WHERE ROLE_ID = l_new_role_id;
        
        INSERT INTO KRIM_ROLE_MBR_T(ROLE_MBR_ID, ROLE_ID, MBR_ID,MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
        VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, l_new_role_id, REC.PERSON_ID, 'P', NULL, NULL, SYSDATE, SYS_GUID(), 1);
        
         INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
         VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_ROLE_MBR_ID_S.CURRVAL, l_role_type_id, l_unit_number_attr_id, REC.UNIT_NUMBER, SYS_GUID());
                 
         IF(l_role_type_id = l_unit_hierarchy_type_id) THEN 
               INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) 
               VALUES (KRIM_ATTR_DATA_ID_S.NEXTVAL, KRIM_ROLE_MBR_ID_S.CURRVAL, l_role_type_id, l_descend_attr_id, REC.SUBUNITS, SYS_GUID());

         END IF;
    END LOOP;

END;   