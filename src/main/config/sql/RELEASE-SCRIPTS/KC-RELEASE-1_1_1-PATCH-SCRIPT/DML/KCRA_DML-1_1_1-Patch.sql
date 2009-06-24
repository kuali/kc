-- *****************
--
-- SQL script to patch a release 1.1 DB to Patch release 1 DB
-- Created 6-AUG-2008 by Tyler Wilson
--
-- *****************

UPDATE unit SET parent_unit_number = null WHERE unit_number = '000001';
DELETE FROM unit WHERE unit_number = '000000';

DELETE FROM SH_PARM_DTL_TYP_T T WHERE T.SH_PARM_NMSPC_CD = 'KR-NS';
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KR-NS', 'Document', 'Document', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KR-NS', 'Lookup', 'Lookup', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KR-NS', 'All', 'All', 'Y');
COMMIT;

INSERT
INTO sh_parm_t(sh_parm_nmspc_cd,    sh_parm_dtl_typ_cd,    sh_parm_nm,    sh_parm_typ_cd,    sh_parm_txt,    sh_parm_desc,    sh_parm_cons_cd,    active_ind)
VALUES('KRA-B',    'D',    'budgetPersonDefaultPeriodType',    'CONFG',    '3',    'Default Period Type',    'A',    'Y');
insert 
into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','pessimisticLocking.cronExpression','CONFG','0 0 1 * * ?','The Cron Expression for Quartz to activate a clearing of old locks','A','Y');
insert 
into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) 
values ('KRA-PD','D','pessimisticLocking.timeout','CONFG','1440','The expiration timeout in minutes; expired locks are deleted','A','Y');
INSERT
INTO sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, obj_id, ver_nbr, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, wrkgrp_nm, active_ind)
VALUES('KRA-B', 'D', 'budgetCostSharingEnforcementFlag', '205246DF7F364A3083357960305030C5',1, 'CONFG', 'Y', 'Flag indicating if Cost Sharing allocation should be enforced', 'A', 'WorkflowAdmin', 'Y');
INSERT
INTO sh_parm_t (sh_parm_nmspc_cd, sh_parm_dtl_typ_cd, sh_parm_nm, obj_id, ver_nbr, sh_parm_typ_cd, sh_parm_txt, sh_parm_desc, sh_parm_cons_cd, wrkgrp_nm, active_ind)
VALUES('KRA-B', 'D', 'budgetUnrecoveredFandAEnforcementFlag', 'C9E4C6277E234C45BF0E1D041B2491CF',1, 'CONFG', 'Y', 'Flag indicating if Unrecovered F and A allocation should be enforced', 'A', 'WorkflowAdmin', 'Y');
INSERT INTO sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind)   
VALUES ('KRA-B','A','instituteRateClassTypes','CONFG','E;I;O;V;X','Manages a list of Institute rate class types.','A','Y');

INSERT INTO sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind)  
VALUES ('KRA-B','A','instituteLaRateClassTypes','CONFG','Y;L','Manages a list of Institute La rate class types.','A','Y');
INSERT INTO SH_PARM_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM, ACTIVE_IND) 
VALUES ('KRA-B', 'D', 'budgetCategoryType.personnel', 'CONFG', 'P', 'Personnel Budget Category Type', 'A', 'WorkflowAdmin', 'Y');


UPDATE sh_parm_t
SET sh_parm_txt = '7'
WHERE sh_parm_nm = 'budgetPersonDefaultAppointmentType';
update SH_PARM_TYP_T set ACTIVE_IND = 'Y' where ACTIVE_IND = '1';

update sh_parm_t t
   set t.sh_parm_txt = 'N'
 where t.sh_parm_nmspc_cd = 'KR-NS'
   AND T.SH_PARM_DTL_TYP_CD = 'Document'
   and t.sh_parm_nm = 'DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND';

UPDATE SH_PARM_T 
	SET SH_PARM_NM = 'budgetPersonnelDetailsHelpUrl', 
		SH_PARM_TXT = 'default.htm?turl=WordDocuments%2Fpersonneldetailtab.htm' 
	WHERE SH_PARM_NMSPC_CD = 'KRA-B' 
		AND SH_PARM_DTL_TYP_CD = 'D' 
		AND SH_PARM_NM = 'budgetPersonnelDetailsHelpUrl';

INSERT
INTO fp_doc_type_t(fdoc_typ_cd,    ver_nbr,    fdoc_grp_cd,    fdoc_nm,    fin_elim_elgbl_cd,    fdoc_typ_active_cd,    fdoc_rtng_rule_cd,    fdoc_autoaprv_days,    fdoc_balanced_cd,    trn_scrbbr_ofst_gen_ind)
VALUES('POSS',    1,    'KR',    'POST SUBMISSION STATUS CODE',    'N',    'Y',    'N',    0,    'N',    'N');
INSERT INTO FP_DOC_TYPE_T (FDOC_TYP_CD,FDOC_GRP_CD,FDOC_NM,FIN_ELIM_ELGBL_CD,FDOC_TYP_ACTIVE_CD,FDOC_RTNG_RULE_CD,FDOC_AUTOAPRV_DAYS,FDOC_BALANCED_CD,TRN_SCRBBR_OFST_GEN_IND) 
	VALUES ('VCEJ','KR','VALID COST ELEMENT JOB CODE','N','Y','N',0,'N','N');

INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES ('6', 'Deactivated', 'not selected for funding',SYSDATE,'KRADEV');
INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES ('7', 'Helds for Funds Availability', 'was not awarded as sponsor ran out of funds', SYSDATE, 'KRADEV');
INSERT INTO EPS_PROP_POST_SUB_STATUS (STATUS_CODE, DESCRIPTION, DEFINITION, UPDATE_TIMESTAMP, UPDATE_USER) VALUES ('8', 'Void', 'Used to mark unwanted,incorrect reports', SYSDATE, 'KRADEV');

insert into abstract_type (abstract_type_code,description,update_timestamp,update_user) values('16','Areas Affected',sysdate,'kradev');
insert into abstract_type (abstract_type_code,description,update_timestamp,update_user) values('17','Relevance',sysdate,'kradev');

UPDATE YNQ 
SET GROUP_NAME = 'General Y/N Questions'
WHERE GROUP_NAME IN ('COEUS', 'Grants.Gov');

insert into KIM_PERMISSIONS_T (ID, NAMESPACE_ID, NAME, DESCRIPTION) values (14, 2, 'ADD_PROPOSAL_VIEWER', 'Assign User to Proposal Viewer Role');

INSERT INTO KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID, ACTIVE_FLAG) VALUES (7, 5, 'Y');
INSERT INTO KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID, ACTIVE_FLAG) VALUES (7, 7, 'Y');
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID) values (2, 14);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID, PERMISSION_ID) values (7, 14);
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(8,3); 
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(8,5); 
insert into KIM_ROLES_PERMISSIONS_T (ROLE_ID,PERMISSION_ID) values(8,7); 

INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KR-NS', 'D', 'Document', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KR-NS', 'L', 'Lookup', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KR-NS', 'A', 'All', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KRA-M', 'D', 'Document', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KRA-M', 'L', 'Lookup', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KRA-M', 'A', 'All', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KRA-B', 'D', 'Document', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KRA-B', 'L', 'Lookup', 'Y');
INSERT INTO SH_PARM_DTL_TYP_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_DTL_TYP_NM, ACTIVE_IND) VALUES ('KRA-B', 'A', 'All', 'Y');

delete from  EN_RULE_RSP_T where RULE_RSP_NM='bhutchinson';

COMMIT;

delete from PROPOSAL_TYPE;
insert into PROPOSAL_TYPE (PROPOSAL_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('1','New',to_date('13-MAR-08','DD-MON-RR'),'KRADBA',1,'4851951BFFD5F56CE0404F8189D8621D');
insert into PROPOSAL_TYPE (PROPOSAL_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('2','Continuation',to_date('13-MAR-08','DD-MON-RR'),'KRADBA',1,'4851951BFFD6F56CE0404F8189D8621D');
insert into PROPOSAL_TYPE (PROPOSAL_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('3','Renewal',to_date('13-MAR-08','DD-MON-RR'),'KRADBA',1,'4851951BFFD7F56CE0404F8189D8621D');
insert into PROPOSAL_TYPE (PROPOSAL_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('4','Resubmission',to_date('13-MAR-08','DD-MON-RR'),'KRADBA',1,'4851951BFFD8F56CE0404F8189D8621D');
insert into PROPOSAL_TYPE (PROPOSAL_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('5','Revision',to_date('13-MAR-08','DD-MON-RR'),'KRADBA',1,'4851951BFFD9F56CE0404F8189D8621D');
insert into PROPOSAL_TYPE (PROPOSAL_TYPE_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('6','Task Order',to_date('13-MAR-08','DD-MON-RR'),'KRADBA',1,'4851951BFFDAF56CE0404F8189D8621D');
commit;

delete from osp$parameter where parameter='KRADEV_SCHEDULER_SERVICE_ENABLED';
insert into osp$parameter(parameter,effective_date,value,update_timestamp,update_user) 
	values('KRA_SCHEDULER_SERVICE_ENABLED',sysdate,'1',sysdate,substr(user,1,8));
	
commit;	

insert into school_code(school_code,description,update_timestamp,update_user) values ('1','DUNS',sysdate,'kradev');
insert into school_code(school_code,description,update_timestamp,update_user) values ('9','DUNS+4',sysdate,'kradev');
insert into school_code(school_code,description,update_timestamp,update_user) values ('10','DODACC',sysdate,'kradev');
insert into school_code(school_code,description,update_timestamp,update_user) values ('33','CAGE',sysdate,'kradev');

INSERT INTO FP_DOC_TYPE_T (FDOC_TYP_CD,FDOC_GRP_CD,FDOC_NM,FIN_ELIM_ELGBL_CD,FDOC_TYP_ACTIVE_CD,FDOC_RTNG_RULE_CD,FDOC_AUTOAPRV_DAYS,FDOC_BALANCED_CD,TRN_SCRBBR_OFST_GEN_IND) VALUES ('SCHC','KR','SCHOOL CODE','N','Y','N',0,'N','N');

commit;
