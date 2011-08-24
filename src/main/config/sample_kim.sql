-- Create Proposal Administrator Role
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','Maintain all Proposals for a Unit','FILLMEIN',STR_TO_DATE( '20110221161307', '%Y%m%d%H%i%s' ),'FILLMEIN',UUID(),'100000','Proposal Administrator',1);

-- Grant Proposal Permissions to Administrator Role

-- Submit Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='FILLMEIN'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='FILLMEIN'),'100000',1);

-- Modify Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='FILLMEIN'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='FILLMEIN'),'100001',1);

-- Create Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='FILLMEIN'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='FILLMEIN'),'100002',1);

-- Delete Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='FILLMEIN'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='FILLMEIN'),'100003',1);

-- Several other permissions you could also grant - see slides

-- Assign Proposal Administrator Role
INSERT INTO KRIM_ROLE_MBR_T (LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (STR_TO_DATE( '20110221133440', '%Y%m%d%H%i%s' ),'FILLMEIN','FILLMEIN',UUID(),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='FILLMEIN'),'100000',1);

-- Qualify Role Assignment as required by UnitHierarchy Role Type (unit number and descend flag)

-- Assign role at unit number 000001
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
  VALUES ('100000', UUID(), 1, 'FILLMEIN', (select KIM_TYP_ID from KRIM_TYP_T where NM='FILLMEIN'),
  (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM='FILLMEIN'), 'FILLMEIN');

-- Specify Descend Flag is True
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
  VALUES ('100001', UUID(), 1, 'FILLMEIN', (select KIM_TYP_ID from KRIM_TYP_T where NM='FILLMEIN'),
  (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM='FILLMEIN'), 'FILLMEIN');
