create sequence SEQ_SPONSOR_CODE 
  INCREMENT BY 1
  START WITH 100000;
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, obj_id)
VALUES('KC', 'KC-GEN', 'All', 'AUTO_GENERATE_SPONSOR_CODE', 'CONFG', 'Y', 'Determines whether or not the sponsor code on new sponsors will be auto-generated. To change the auto-generation starting value, see the database sequence SEQ_SPONSOR_CODE.', 'A', sys_guid());
