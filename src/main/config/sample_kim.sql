-- Create Proposal Administrator Role
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','Maintain all Proposals for a Unit','10001',STR_TO_DATE( '20110221161307', '%Y%m%d%H%i%s' ),'KC-PD',UUID(),'100000','Proposal Administrator',1);

-- Grant Proposal Permissions to Administrator Role

-- Submit Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='Submit ProposalDevelopmentDocument'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Administrator'),'100000',1);

-- Modify Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='Modify ProposalDevelopmentDocument'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Administrator'),'100001',1);

-- Create Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='Create ProposalDevelopmentDocument'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Administrator'),'100002',1);

-- Delete Proposal Development Document
INSERT INTO KRIM_ROLE_PERM_T (ACTV_IND,OBJ_ID,PERM_ID,ROLE_ID,ROLE_PERM_ID,VER_NBR)
  VALUES ('Y',UUID(), (select PERM_ID from KRIM_PERM_T where NM='Modify ProposalDevelopmentDocument'),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Delete Proposal'),'100003',1);

-- Several other permissions you could also grant - see slides

-- Assign Proposal Administrator Role
INSERT INTO KRIM_ROLE_MBR_T (LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,ROLE_ID,ROLE_MBR_ID,VER_NBR)
  VALUES (STR_TO_DATE( '20110221133440', '%Y%m%d%H%i%s' ),'10000','P',UUID(),
  (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Administrator'),'100000',1);

-- Qualify Role Assignment as required by UnitHierarchy Role Type (unit number and descend flag)

-- Unit Number
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
  VALUES ('100000', UUID(), 1, '100000', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'),
  (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM='unitNumber'), '000001');

-- Descend Flag
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
  VALUES ('100001', UUID(), 1, '100000', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'),
  (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM='subunits'), 'Y');
