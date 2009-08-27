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
 insert into krim_grp_t (GRP_ID, OBJ_ID,VER_NBR,GRP_NM,NMSPC_CD,GRP_DESC,KIM_TYP_ID, ACTV_IND,LAST_UPDT_DT ) values ( 
  '2416',   '6881E3E6D98542DAE0404F8189D85E85',1,'OSP Superuser', 'KC','OSP SuperUser to allow Blanket Approval for Proposal documents','','Y', sysdate);
 insert into krim_grp_t (GRP_ID, OBJ_ID,VER_NBR,GRP_NM,NMSPC_CD,GRP_DESC,KIM_TYP_ID, ACTV_IND,LAST_UPDT_DT ) values ( 
  '2425',   '6881E3E6D98542DAE0404F8189D85E86',1,'OSP', 'KC','OSP Office for Proposal Review and Approval','','Y', sysdate);

insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
  VALUES ('1186','2416','aslusar','P', sysdate,sys_guid(),1)
;
insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
  VALUES ('1187','2425','aslusar','P', sysdate,sys_guid(),1)
;

insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
  VALUES ('1500','1','quickstart','P', sysdate,sys_guid(),1)
;
insert into KRIM_GRP_MBR_T(GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
  VALUES ('1501','2','quickstart','P', sysdate,sys_guid(),1)
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