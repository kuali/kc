-- committeewebtest needs this principal
INSERT INTO KRIM_ENTITY_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1141',sysdate,sys_guid(),1)
;

INSERT INTO KRIM_PRNCPL_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,PRNCPL_ID,PRNCPL_NM,VER_NBR)
  VALUES ('Y','1141',sysdate,sys_guid(),'aslusar','aslusar',1)
;

INSERT INTO KREN_RECIP_DELIV_T (CHNL,NM,RECIP_DELIV_ID,RECIP_ID,VER_NBR)
  VALUES ('KEW','mock',17,'aslusar',0)
;

insert into KRIM_ENTITY_ENT_TYP_T (OBJ_ID,VER_NBR,ENT_TYP_CD,ENTITY_ID,ACTV_IND,LAST_UPDT_DT)
 values(sys_guid(),1,'PERSON','1141','Y', sysdate)
;

INSERT INTO KRIM_ENTITY_EMAIL_T (ACTV_IND,DFLT_IND,EMAIL_ADDR,EMAIL_TYP_CD,ENTITY_EMAIL_ID,ENTITY_ID,ENT_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','Y','aslusar@cornell.edu','CAMPUS','1241','1141','PERSON',sysdate,sys_guid(),1)
;
INSERT INTO KRIM_ENTITY_NM_T (ACTV_IND,DFLT_IND,ENTITY_ID,ENTITY_NM_ID,FIRST_NM,LAST_NM,LAST_UPDT_DT,NM_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','Y','1141','1253','Andy ','Slusar',sysdate,'PFRD',sys_guid(),1)
;

--INSERT INTO KRIM_GRP_T (GRP_ID, OBJ_ID,VER_NBR,GRP_NM,NMSPC_CD,GRP_DESC,KIM_TYP_ID, ACTV_IND,LAST_UPDT_DT ) VALUES ( 
--  '2416',   SYS_GUID(), 1, 'OSP Superuser', 'KC-WKFLW', 'OSP SuperUser to allow Blanket Approval for Proposal documents', '', 'Y', SYSDATE);
--INSERT INTO KRIM_GRP_T (GRP_ID, OBJ_ID,VER_NBR,GRP_NM,NMSPC_CD,GRP_DESC,KIM_TYP_ID, ACTV_IND,LAST_UPDT_DT ) VALUES ( 
--  '2425',   SYS_GUID(),1,'ProposalAdmin', 'KC-WKFLW','Group to receive exception route notifications for Proposal Documents','','Y', SYSDATE);
--INSERT INTO KRIM_GRP_T (GRP_ID, OBJ_ID,VER_NBR,GRP_NM,NMSPC_CD,GRP_DESC,KIM_TYP_ID, ACTV_IND,LAST_UPDT_DT ) VALUES ( 
--  '2426',   SYS_GUID(),1,'KcAdmin', 'KC-WKFLW','General System Admin for Kuali Coeus App','','Y', SYSDATE);
--INSERT INTO KRIM_GRP_T (GRP_ID, OBJ_ID,VER_NBR,GRP_NM,NMSPC_CD,GRP_DESC,KIM_TYP_ID, ACTV_IND,LAST_UPDT_DT ) VALUES ( 
--  '2427',   SYS_GUID(),1,'IRBAdmin', 'KC-WKFLW','Admin Group responsible for receiving exception notifications, perform super-user functions and blanket approval for all IRB Documents','','Y', SYSDATE);
 
-- insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
--   VALUES ('1186','2416','aslusar','P', sysdate,sys_guid(),1)
-- ;
-- insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
-- VALUES ('1187','2425','aslusar','P', sysdate,sys_guid(),1)
-- ;

-- insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
--   VALUES ('1500','1','quickstart','P', sysdate,sys_guid(),1)
-- ;
-- insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
--   VALUES ('1501','2','quickstart','P', sysdate,sys_guid(),1)
--   ;
  
--  for questionnaire  

INSERT INTO KRIM_ENTITY_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1204',sysdate,sys_guid(),1)
;

INSERT INTO KRIM_PRNCPL_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,PRNCPL_ID,PRNCPL_NM,VER_NBR)
  VALUES ('Y','1204',sysdate,sys_guid(),'jtester','jtester',1)
;

insert into KRIM_ENTITY_ENT_TYP_T (OBJ_ID,VER_NBR,ENT_TYP_CD,ENTITY_ID,ACTV_IND,LAST_UPDT_DT)
 values(sys_guid(),1,'PERSON','1204','Y', sysdate)
;

  
--  for questionnaire  

INSERT INTO KRIM_ENTITY_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1204',sysdate,sys_guid(),1)
;

INSERT INTO KRIM_PRNCPL_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,PRNCPL_ID,PRNCPL_NM,VER_NBR)
  VALUES ('Y','1204',sysdate,sys_guid(),'jtester','jtester',1)
;

insert into KRIM_ENTITY_ENT_TYP_T (OBJ_ID,VER_NBR,ENT_TYP_CD,ENTITY_ID,ACTV_IND,LAST_UPDT_DT)
 values(sys_guid(),1,'PERSON','1204','Y', sysdate)
;

commit;