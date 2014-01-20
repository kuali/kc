INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, VER_NBR, OBJ_ID) 
VALUES (1210, 38, 'KC-SYS', 'Populate KC Groups', 'Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with namespaces beginning with KC.','Y',1,SYS_GUID()); 
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, VER_NBR, OBJ_ID) 
VALUES (1616, 1210, 21, 4, 'KC*', 1, SYS_GUID()); 
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, VER_NBR, OBJ_ID) 
VALUES (10493, 1139, 1210, 'Y', 1, SYS_GUID()); 
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, VER_NBR, OBJ_ID) 
VALUES (10494, 63, 1210, 'Y', 1, SYS_GUID()); 
COMMIT;