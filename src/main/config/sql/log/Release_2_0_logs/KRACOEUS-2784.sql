ALTER TABLE KRIM_DLGN_MBR_T DROP CONSTRAINT KRIM_DLGN_MBR_TR1
/
INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES('683', sys_guid(), 1, '59', '334', 'Y')
/

-- this row doesn't do anything, but it is possible that it could cause a potential problem
delete from krim_perm_attr_data_t where perm_id = '163' AND kim_typ_id = '16' AND kim_attr_defn_id = '4' AND attr_val = 'KR-SYS'
/
-- next 3 statements create a Kim Group (Insert member) permission for groups with namespace of KUALI
Insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) VALUES('833', '5B4F09744953EF33E0404F8189D84F25', 1, '38', 'KR-SYS', Null, Null, 'Y')
/
Insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) VALUES('203', '5B4F09744A39EF33E0404F8189D84F25', 1, '833', '21', '4', 'KUALI')
/
Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES('838', '5C27A267EF6D7417E0404F8189D830AA', 1, '63', '833', 'Y')
/

-- next 3 statements create a Kim Roles permission for roles with namespace of KUALI
Insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) VALUES('834', '5B4F09744953EF33E0404F8189D84F26', 1, '35', 'KR-SYS', Null, Null, 'Y')
/
Insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) VALUES('204', '5B4F09744A39EF33E0404F8189D84F26', 1, '834', '18', '4', 'KUALI')
/
Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES('839', '5C27A267EF6D7417E0404F8189D830AB', 1, '63', '834', 'Y')
/

-- next 3 statements create a Kim Permission permission for permissions with namespace of KUALI
Insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) VALUES('835', '5B4F09744953EF33E0404F8189D84F27', 1, '36', 'KR-SYS', Null, Null, 'Y')
/
Insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) VALUES('205', '5B4F09744A39EF33E0404F8189D84F27', 1, '835', '19', '4', 'KUALI')
/
Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES('840', '5C27A267EF6D7417E0404F8189D830AC', 1, '63', '835', 'Y')
/

-- next 3 statements create a Kim Responsibility permission for responsibilities with namespace of KUALI
Insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) VALUES('836', '5B4F09744953EF33E0404F8189D84F28', 1, '37', 'KR-SYS', Null, Null, 'Y')
/
Insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) VALUES('206', '5B4F09744A39EF33E0404F8189D84F28', 1, '836', '20', '4', 'KUALI')
/
Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES('841', '5C27A267EF6D7417E0404F8189D830AD', 1, '63', '835', 'Y')
/
commit
/
ALTER TABLE KRIM_DLGN_MBR_T
    ADD CONSTRAINT KRIM_DLGN_MBR_TR2 FOREIGN KEY (DLGN_ID)
    REFERENCES KRIM_DLGN_T (DLGN_ID)
/
commit
/
Insert into KREW_DOC_TYP_T (DOC_TYP_ID,PARNT_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,ACTV_IND,CUR_IND,LBL,PREV_DOC_TYP_VER_NBR,DOC_HDR_ID,DOC_TYP_DESC,DOC_HDLR_URL,POST_PRCSR,JNDI_URL,ADV_DOC_SRCH_URL,VER_NBR,RTE_VER_NBR,NOTIFY_ADDR,SVC_NMSPC,EMAIL_XSL,BLNKT_APPR_PLCY,SEC_XML,BLNKT_APPR_GRP_ID,RPT_GRP_ID,GRP_ID,HELP_DEF_URL,OBJ_ID) values (2708,2681,'CampusMaintenanceDocument',0,1,1,'CampusMaintenanceDocument',null,null,null,null,null,null,null,1,'2',null,null,null,null,null,null,null,null,null,'616D94CA-D08D-D036-E77D-4B53DB34CD95');
/
Insert into KREW_DOC_TYP_T (DOC_TYP_ID,PARNT_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,ACTV_IND,CUR_IND,LBL,PREV_DOC_TYP_VER_NBR,DOC_HDR_ID,DOC_TYP_DESC,DOC_HDLR_URL,POST_PRCSR,JNDI_URL,ADV_DOC_SRCH_URL,VER_NBR,RTE_VER_NBR,NOTIFY_ADDR,SVC_NMSPC,EMAIL_XSL,BLNKT_APPR_PLCY,SEC_XML,BLNKT_APPR_GRP_ID,RPT_GRP_ID,GRP_ID,HELP_DEF_URL,OBJ_ID) values (2709,2681,'CampusTypeMaintenanceDocument',0,1,1,'CampusTypeMaintenanceDocument',null,null,null,null,null,null,null,1,'2',null,null,null,null,null,null,null,null,null,'DE0B8588-E459-C07A-87B8-6ACD693AE70C');
/
Insert into KREW_DOC_TYP_T (DOC_TYP_ID,PARNT_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,ACTV_IND,CUR_IND,LBL,PREV_DOC_TYP_VER_NBR,DOC_HDR_ID,DOC_TYP_DESC,DOC_HDLR_URL,POST_PRCSR,JNDI_URL,ADV_DOC_SRCH_URL,VER_NBR,RTE_VER_NBR,NOTIFY_ADDR,SVC_NMSPC,EMAIL_XSL,BLNKT_APPR_PLCY,SEC_XML,BLNKT_APPR_GRP_ID,RPT_GRP_ID,GRP_ID,HELP_DEF_URL,OBJ_ID) values (2710,2681,'CountryMaintenanceDocument',0,1,1,'CountryMaintenanceDocument',null,null,null,null,null,null,null,1,'2',null,null,null,null,null,null,null,null,null,'82EDB593-97BA-428E-C6E7-A7F3031CFAEB');
/
Insert into KREW_DOC_TYP_T (DOC_TYP_ID,PARNT_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,ACTV_IND,CUR_IND,LBL,PREV_DOC_TYP_VER_NBR,DOC_HDR_ID,DOC_TYP_DESC,DOC_HDLR_URL,POST_PRCSR,JNDI_URL,ADV_DOC_SRCH_URL,VER_NBR,RTE_VER_NBR,NOTIFY_ADDR,SVC_NMSPC,EMAIL_XSL,BLNKT_APPR_PLCY,SEC_XML,BLNKT_APPR_GRP_ID,RPT_GRP_ID,GRP_ID,HELP_DEF_URL,OBJ_ID) values (2711,2681,'CountyMaintenanceDocument',0,1,1,'CountyMaintenanceDocument',null,null,null,null,null,null,null,1,'2',null,null,null,null,null,null,null,null,null,'C972E260-5552-BB63-72E6-A514301B0326');
/
Insert into KREW_DOC_TYP_T (DOC_TYP_ID,PARNT_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,ACTV_IND,CUR_IND,LBL,PREV_DOC_TYP_VER_NBR,DOC_HDR_ID,DOC_TYP_DESC,DOC_HDLR_URL,POST_PRCSR,JNDI_URL,ADV_DOC_SRCH_URL,VER_NBR,RTE_VER_NBR,NOTIFY_ADDR,SVC_NMSPC,EMAIL_XSL,BLNKT_APPR_PLCY,SEC_XML,BLNKT_APPR_GRP_ID,RPT_GRP_ID,GRP_ID,HELP_DEF_URL,OBJ_ID) values (2712,2681,'PostalCodeMaintenanceDocument',0,1,1,'PostalCodeMaintenanceDocument',null,null,null,null,null,null,null,1,'2',null,null,null,null,null,null,null,null,null,'B79D1104-BC48-1597-AFBE-773EED31A110');
/
--Insert into KREW_DOC_TYP_T (DOC_TYP_ID,PARNT_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,ACTV_IND,CUR_IND,LBL,PREV_DOC_TYP_VER_NBR,DOC_HDR_ID,DOC_TYP_DESC,DOC_HDLR_URL,POST_PRCSR,JNDI_URL,ADV_DOC_SRCH_URL,VER_NBR,RTE_VER_NBR,NOTIFY_ADDR,SVC_NMSPC,EMAIL_XSL,BLNKT_APPR_PLCY,SEC_XML,BLNKT_APPR_GRP_ID,RPT_GRP_ID,GRP_ID,HELP_DEF_URL,OBJ_ID) values (2713,2681,'StateMaintenanceDocument',0,1,1,'StateMaintenanceDocument',null,null,null,null,null,null,null,1,'2',null,null,null,null,null,null,null,null,null,'EF2378F6-E770-D7BF-B7F1-C18881E3AFF0');
--/
commit
/
ALTER TABLE KRIM_PERSON_DOCUMENT_T
MODIFY TAX_ID VARCHAR2(100)
/
commit
/
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES ('378', '5B4F09744953EF33E0404F8189D84F29', '1', '1', 'KR-IDM', 'Override Entity Privacy Preferences', 'Allows a user to override entity privacy preferences', 'Y')
/
Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES('230', '5C27A267EF5C7417E0404F8189D830AA', 1, '63', '378', 'Y')
/
commit
/
UPDATE KREW_DOC_TYP_T SET SVC_NMSPC=NULL WHERE SVC_NMSPC='FooBar'
/
commit
/
DROP TABLE KRIM_PERM_RQRD_ATTR_T
/
DROP TABLE KRIM_RSP_RQRD_ATTR_T
/

-- KULRICE-3287

DELETE FROM KRNS_PARM_T where NMSPC_CD='KR-WKFLW' AND PARM_DTL_TYP_CD='All' AND PARM_NM='APPLICATION_CONTEXT'
/

-- KULRICE-3278

DECLARE

    ref_perm_id VARCHAR2(40);

BEGIN

    SELECT perm_id INTO ref_perm_id FROM krim_perm_attr_data_t WHERE attr_val = 'org.kuali.rice.kew.web.backdoor.AdministrationAction';
    DELETE FROM krim_perm_attr_data_t WHERE perm_id = ref_perm_id;
    DELETE FROM krim_role_perm_t WHERE perm_id = ref_perm_id;
    DELETE FROM krim_perm_t WHERE perm_id = ref_perm_id;

END;
/ 
commit
/
UPDATE KRNS_PARM_T SET TXT = 'N' WHERE NMSPC_CD = 'KR-NS' AND PARM_DTL_TYP_CD = 'All' AND PARM_NM = 'ENABLE_FIELD_LEVEL_HELP_IND'
/
-- KULRICE-3349: Add the doc handler URL to the Campus, Campus Type, Country, County, Postal Code, and State document types.
UPDATE KREW_DOC_TYP_T SET DOC_HDLR_URL='${kr.url}/maintenance.do?methodToCall=docHandler' WHERE DOC_TYP_NM='CampusMaintenanceDocument' OR DOC_TYP_NM='CampusTypeMaintenanceDocument' OR DOC_TYP_NM='CountryMaintenanceDocument' OR DOC_TYP_NM='CountyMaintenanceDocument' OR DOC_TYP_NM='PostalCodeMaintenanceDocument' OR DOC_TYP_NM='StateMaintenanceDocument'
/
--KULRICE-3283
ALTER TABLE KRNS_PARM_T ADD APPL_NMSPC_CD  varchar2(20) default 'KUALI' not null
/

DECLARE 
      constraint_name varchar2(500);
      sql_stm varchar2(2000);
BEGIN 
      SQL_STM := 'select distinct constraint_name from user_constraints where table_name = upper(:1) and constraint_type = :2';
      EXECUTE IMMEDIATE SQL_STM INTO CONSTRAINT_NAME USING 'krns_parm_t','P';
      
      SQL_STM := 'alter table krns_parm_t drop constraint '||CONSTRAINT_NAME||' cascade';
      EXECUTE IMMEDIATE SQL_STM;END;
/

--ALTER TABLE KRNS_PARM_T DROP CONSTRAINT KRNS_PARM_TP1
--/
ALTER TABLE KRNS_PARM_T ADD CONSTRAINT KRNS_PARM_TP1 PRIMARY KEY(NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM, APPL_NMSPC_CD)
/
commit
/
ALTER TABLE KREW_DOC_HDR_T MODIFY (APP_DOC_ID VARCHAR2(255))
/

-- KULRICE-3015 - Standardize length of document type name and lbl columns
ALTER TABLE KREW_ACTN_ITM_T MODIFY (DOC_TYP_NM VARCHAR2(64))
/
ALTER TABLE KREW_OUT_BOX_ITM_T MODIFY (DOC_TYP_NM VARCHAR2(64))
/
ALTER TABLE KREW_DOC_TYP_T MODIFY (DOC_TYP_NM VARCHAR2(64))
/
ALTER TABLE KREW_RULE_T MODIFY (DOC_TYP_NM VARCHAR2(64))
/
ALTER TABLE KREW_EDL_ASSCTN_T MODIFY (DOC_TYP_NM VARCHAR2(64))
/
ALTER TABLE KREW_EDL_DMP_T MODIFY (DOC_TYP_NM VARCHAR2(64))
/
ALTER TABLE KREW_DOC_TYP_T MODIFY (LBL VARCHAR2(128))
/
ALTER TABLE KREW_ACTN_ITM_T MODIFY (DOC_TYP_LBL VARCHAR2(128))
/
ALTER TABLE KREW_OUT_BOX_ITM_T MODIFY (DOC_TYP_LBL VARCHAR2(128))
/
commit
/
-- KULRICE-3376
ALTER TABLE KRIM_ENTITY_ADDR_T MODIFY
  (
    ADDR_LINE_1 VARCHAR2(45),
    ADDR_LINE_2 VARCHAR2(45),
    ADDR_LINE_3 VARCHAR2(45)
  )
/
commit
/
delete from krew_rule_tmpl_attr_t where rule_tmpl_attr_id=1080
/
update krew_rule_tmpl_attr_t set actv_ind=1 where rule_tmpl_attr_id=1027
/

-- IMPORTANT: at this point, run the following:
--
--      1) demo-server-dataset-cleanup.sql
--      2) demo-client-dataset-cleanup.sql
--
-- DO NOT RUN THESE SCRIPTS BEFORE THIS POINT!!!  The previous statements in this file need to be
-- executed first.

-- Disable constraints for the duration of this script
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R'
           AND status = 'ENABLED';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' DISABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/

-- the mysql way to disable constraints
-- SET foreign_key_checks = 0


-- ##############
-- # KEW Tables #
-- ##############

-- Document Types

delete from krew_doc_typ_t where doc_typ_nm like 'EDENSERVICE-DOCS%'
/
delete from krew_doc_typ_t where doc_typ_nm='RemoveReplaceUserDocument'
/
delete from krew_doc_typ_t where doc_typ_nm like 'KIM%'
/
delete from krew_doc_typ_t where doc_typ_nm like 'KualiOrganization%'
/
delete from krew_doc_typ_t where doc_typ_nm='RiceUserMaintenanceDocument'
/
delete from krew_doc_typ_t where doc_typ_nm='TravelTripReimbursement'
/

delete from krew_doc_typ_attr_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/
delete from krew_doc_typ_plcy_reln_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/
delete from krew_doc_typ_proc_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/

delete from krew_rte_node_cfg_parm_t where rte_node_id in (select rte_node_id from krew_rte_node_t where doc_typ_id not in (select doc_typ_id from krew_doc_typ_t))
/
delete from krew_rte_node_t where DOC_TYP_ID not in (select doc_typ_id from KREW_DOC_TYP_T)
/
delete from krew_rte_node_lnk_t where from_rte_node_id not in (select rte_node_id from krew_rte_node_t)
/
delete from krew_rte_brch_proto_t
/

-- Rules

delete from krew_rule_t where rule_id=1034
/
delete from krew_rule_t where rule_id=1035
/
delete from krew_rule_t where doc_typ_nm like 'KIM%'
/
update krew_rule_t set NM='SendNotificationRequest.Reviewers', RULE_BASE_VAL_DESC='Notification Request Reviewers' where rule_id=1044
/
update krew_rule_t set NM='TravelRequest.Destination.LasVegas', RULE_BASE_VAL_DESC='Destination - Las Vegas' where rule_id=1046
/
update krew_rule_t set NM='TravelRequest.Traveler', RULE_BASE_VAL_DESC='Travler Routing' where rule_id=1049
/
update krew_rule_t set NM='TravelRequest.Supervisor', RULE_BASE_VAL_DESC='Supervisor Routing' where rule_id=1049
/
update krew_rule_t set NM='TravelRequest.DeanDirector', RULE_BASE_VAL_DESC='Dean/Director Routing' where rule_id=1050
/
update krew_rule_t set NM='TravelRequest.FiscalOfficer', RULE_BASE_VAL_DESC='Fiscal Officer Routing' where rule_id=1051
/
update krew_rule_t set NM='eDoc.Example1Doctype.IUB' where rule_id=1103
/
update krew_rule_t set NM='eDoc.Example1Doctype.IUPUI' where rule_id=1106
/
delete from krew_rule_t where rule_id=1637
/
delete from krew_rule_t where rule_id=1640
/

-- update rule for Recipe Masters so that it points to a valid group id
update krew_rule_rsp_t set NM='9997' where rule_rsp_id='2064'
/

delete from krew_dlgn_rsp_t
/

delete from krew_rule_ext_t where rule_id not in (select rule_id from krew_rule_t)
/
delete from krew_rule_ext_val_t where rule_ext_id not in (select rule_ext_id from krew_rule_ext_t)
/
delete from krew_rule_rsp_t where rule_id not in (select rule_id from krew_rule_t)
/

-- Rule Attributes

update krew_rule_attr_t set lbl='Rule Routing Attribute', desc_txt='Rule Routing Attribute' where nm='RuleRoutingAttribute'
/
delete from krew_rule_attr_t where nm like 'RemoveReplace%'
/
delete from krew_rule_attr_t where cls_nm like 'edu.iu%'
/
delete from krew_rule_attr_t where nm like 'SIS%'
/
delete from krew_rule_attr_t where nm like 'Travel%' and nm != 'TravelAccountDocumentAccountNumberAttribute'
/
delete from krew_rule_attr_t where nm like 'Timesheet%'
/
delete from krew_rule_attr_t where nm like 'EPIC%'
/
delete from krew_rule_attr_t where nm like 'Hrms%'
/
delete from krew_rule_attr_t where nm like 'Iuf%'
/
delete from krew_rule_attr_t where nm like 'UGS%'
/
delete from krew_rule_attr_t where nm='DepartmentAttribute'
/
delete from krew_rule_attr_t where nm='DepartmentSearchAttribute'
/
delete from krew_rule_attr_t where nm='DepartmentSearchAttribute'
/
delete from krew_rule_attr_t where nm='SchoolAttribute'
/
delete from krew_rule_attr_t where nm='SchoolSearchAttribute'
/
delete from krew_rule_attr_t where nm='proposedDirectorAttribute'
/
delete from krew_rule_attr_t where nm='DirectorSearchAttribute'
/
delete from krew_rule_attr_t where nm='TeacherAttribute'
/                                                                                 
delete from krew_rule_attr_t where nm='TeacherSearchAttribute'
/
delete from krew_rule_attr_t where nm='facultyAdvisorAttribute'
/
delete from krew_rule_attr_t where nm='AdvisorSearchAttribute'
/                                                                          
delete from krew_rule_attr_t where nm='StudentProgramCodeAttribute'
/
delete from krew_rule_attr_t where nm='StudentProgramCodeSearchAttribute'
/
delete from krew_rule_attr_t where nm='CourseSubjectCodeAttribute'
/
delete from krew_rule_attr_t where nm='CourseSubjectCodeSearchAttribute'
/
delete from krew_rule_attr_t where nm='EmplidSearchAttribute'
/
delete from krew_rule_attr_t where nm='CashTransferAmountAttribute'
/

-- Rule Templates

delete from krew_rule_tmpl_t where NM='RemoveReplaceWorkgroupTemplate'
/
delete from krew_rule_tmpl_t where NM='RemoveReplaceRuleTemplate'
/
delete from krew_rule_tmpl_t where NM='SubAccount'
/
delete from krew_rule_tmpl_t where NM like 'HRMS%'
/
delete from krew_rule_tmpl_t where NM='Fiscal & University Personnel Action Approval'
/
delete from krew_rule_tmpl_t where NM like 'EPIC%'
/
delete from krew_rule_tmpl_t where NM like 'UniversityGraduate%'
/
delete from krew_rule_tmpl_t where NM='KSBDuplicating-Route'
/
delete from krew_rule_tmpl_t where NM like 'E596%'
/
delete from krew_rule_tmpl_t where NM='SchoolofMusic-TeacherRouting'
/
delete from krew_rule_tmpl_t where NM='InternshipContract-advisorRouting'
/
delete from krew_rule_tmpl_t where NM='RegistrarsOffice-ProgramRouting'
/
delete from krew_rule_tmpl_t where NM='RegistrarsOffice-Routing'
/
delete from krew_rule_tmpl_t where NM like 'Timesheet%'
/
delete from krew_rule_tmpl_t where NM='SupervisorRuleTemplate'
/
delete from krew_rule_tmpl_t where NM like 'SIS%'
/
delete from krew_rule_tmpl_t where NM='KualiSimpleMaintenanceDocumentTemplate'
/
delete from krew_rule_tmpl_t where NM like 'Iuf%'
/
delete from krew_rule_tmpl_t where NM='TravelAccountDelegationTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelContentTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelSubAccountTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelAccountTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelChartOrgDollarRangeTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelSeparationTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelInternalTRMSTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelTravelerTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelSubFundTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelBudgetTemplate'
/
delete from krew_rule_tmpl_t where NM like 'SAV%'
/
delete from krew_rule_tmpl_t where NM='Fiscal and University Personnel Action Approval v2'
/
delete from krew_rule_tmpl_t where NM='AccountTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelArrangerAuthorizationTemplate'
/
delete from krew_rule_tmpl_t where NM='TravelContractsAndGrantsTemplate'
/
delete from krew_rule_tmpl_t where NM='ChartOrgTemplate'
/
delete from krew_rule_tmpl_t where NM='RegistrarsOffice-SubjectRouting'
/
delete from krew_rule_tmpl_t where NM='PayrollProcessorRuleTemplate'
/

delete from krew_rule_tmpl_attr_t where rule_attr_id not in (select rule_attr_id from krew_rule_attr_t)
/
delete from krew_rule_tmpl_attr_t where rule_tmpl_id not in (select rule_tmpl_id from krew_rule_tmpl_t)
/

-- Rule Template Options

delete from krew_rule_tmpl_optn_t
/

-- ##############
-- # KNS Tables #
-- ##############

delete from krns_campus_t where campus_cd='IX'
/

update krns_nmspc_t set appl_nmspc_cd='RICE' where nmspc_cd like 'KR%'
/

delete from krns_parm_t where parm_nm='nate'
/

-- ##############
-- # KEN Tables #
-- ##############

-- No KEN data needs to be fixed

-- ##############
-- # KIM Tables #
-- ##############

-- add missing attribute definition

INSERT INTO krim_attr_defn_t(KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL)
  VALUES('46', '69FA55ACC2EE2598E0404F8189D86880', 1, 'qualifierResolverProvidedIdentifier', NULL, 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes', '${application.url}')
/

-- remove a bad employee status code

delete from krim_emp_stat_t where NM='Retired 2'
/

-- get rid of all the weird email addresses

update krim_entity_email_t set email_addr='test@email.edu'
/

-- delete 'TestRouteAGroup123'

delete from krim_grp_mbr_t where grp_id='2036'
/
delete from krim_grp_t where grp_id='2036'
/

-- delete 'TestAGroupRoutenum1'

delete from krim_grp_mbr_t where grp_id='2060'
/
delete from krim_grp_t where grp_id='2060'
/

-- delete 'CreatinAGroup4'

delete from krim_grp_mbr_t where grp_id='2122'
/
delete from krim_grp_t where grp_id='2122'
/

-- delete 'Testify!'

delete from krim_grp_mbr_t where grp_id='2180'
/
delete from krim_grp_t where grp_id='2180'
/

-- delete 'TestAGroup123'

delete from krim_grp_mbr_t where grp_id='2260'
/
delete from krim_grp_t where grp_id='2260'
/

-- update all of the names on our permissions

update krim_perm_t p set p.nm=(select pt.nm from krim_perm_tmpl_t pt where p.perm_tmpl_id=pt.perm_tmpl_id) where nm is null
/

-- Fix id of perm 169 (Save Document) and update it's namespace code from KUALI to KR-SYS, make it's id match the identical permission in the KFS db

update krim_perm_t set perm_id='290', nmspc_cd='KR-SYS', desc_txt='Users who can save RICE documents' where perm_id='169'
/
update krim_perm_attr_data_t set perm_id='290' where perm_id='169'
/
update krim_role_perm_t set perm_id='290' where perm_id='169'
/

-- setup "Modify Entity" permission, assign to KR-SYS Technical Administrators

INSERT INTO krim_perm_t(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
  VALUES('307', '638DD46953F9BCD5E0404F8189D86240', 1, '1', 'KR-IDM', 'Modify Entity', 'Users who can modify entity records in Kuali Identity Management.', 'Y')
/
INSERT INTO krim_role_perm_t(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
  VALUES('850', '70086A2DF17C62E4E0404F8189D863CD', 1, '63', '307', 'Y')
/

-- setup "Full Unmask Field" permission

INSERT INTO krim_perm_t(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
  VALUES('306', '6314CC58CF58B7B5E0404F8189D84439', 1, '27', 'KR-SYS', 'Full Unmask Field', 'Authorizes users to view the entire Tax Identification Number on the Person document and inquiry.', 'Y')
/
INSERT INTO krim_perm_attr_data_t(ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
  VALUES('431', '6314CC58CF59B7B5E0404F8189D84439', 1, '306', '11', '5', 'IdentityManagementPersonDocument')
/
INSERT INTO krim_perm_attr_data_t(ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
  VALUES('432', '6314CC58CF5AB7B5E0404F8189D84439', 1, '306', '11', '6', 'taxId')
/
-- assign to KR-SYS Technical Administrator
INSERT INTO krim_role_perm_t(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
  VALUES('578', '6314CC58CF5BB7B5E0404F8189D84439', 1, '63', '306', 'Y')
/

-- modify perm 827 ("Use Screen" for the Ingester) so that it has the same id as the corresponding perm in the kfs database (265)

update krim_perm_t set perm_id='265' where perm_id='827'
/
update krim_perm_attr_data_t set perm_id='265' where perm_id='827'
/
update krim_role_perm_t set perm_id='265' where perm_id='827'
/

-- remove extraneous permission attributes from "Use Screen" permissions

delete from krim_perm_attr_data_t where kim_attr_defn_id='4' and perm_id in ('140','141','142','143','144','145')
/

-- remove an extraneous permission for delete note/attachment

delete from krim_perm_t where perm_id='262'
/
delete from krim_perm_attr_data_t where perm_id='262'
/
delete from krim_role_perm_t where perm_id='262'
/

-- update descriptions on various permissions

update krim_perm_t set desc_txt='Authorizes users to login to the Kuali portal.' where perm_id='174'
/
update krim_perm_t set desc_txt='Administer Pessimistic Locking' where perm_id='289'
/
update krim_perm_t set desc_txt='Authorizes users to access other users action lists via the Help Desk Action List Login.' where perm_id='298'
/	
update krim_perm_t set desc_txt='Users who can perform a document search with no criteria or result limits.' where perm_id='299'
/	
update krim_perm_t set desc_txt='Allows a user to override entity privacy preferences' where perm_id='378'
/
update krim_perm_t set desc_txt='Authorizes the initiation of RICE Documents.' where perm_id='149'
/
update krim_perm_t set desc_txt='Authorizes users to cancel a document prior to it being submitted for routing.' where perm_id='167'
/
update krim_perm_t set desc_txt='Allows users to edit Kuali documents that are in ENROUTE status.' where perm_id='180'
/
update krim_perm_t set desc_txt='Allows users to edit Kuali documents that are in ENROUTE status.' where perm_id='181'
/
update krim_perm_t set desc_txt='Authorizes users to copy RICE Documents.' where perm_id='156'
/
update krim_perm_t set desc_txt='Allow users to access Kuali RICE lookups.' where perm_id='162'
/	
update krim_perm_t set desc_txt='Allows users to access Kuali RICE inquiries.' where perm_id='161'
/
update krim_perm_t set desc_txt='Authorizes users to view the entire Tax Identification Number on the Payee ACH document and Inquiry.' where perm_id='183'
/	
update krim_perm_t set desc_txt='Allows users to access the Document Operation screen.' where perm_id='140'
/	
update krim_perm_t set desc_txt='Allows users to access the Java Security Management screen.' where perm_id='141'
/
update krim_perm_t set desc_txt='Allows users to access the Message Queue screen.' where perm_id='142'
/
update krim_perm_t set desc_txt='Allows users to access the Service Registry screen.' where perm_id='143'
/
update krim_perm_t set desc_txt='Allows users to access the Thread Pool screen.' where perm_id='144'
/
update krim_perm_t set desc_txt='Allows users to access the Quartz Queue screen.' where perm_id='145'
/
update krim_perm_t set desc_txt='Allows users to access all RICE screens.' where perm_id='166'
/	
update krim_perm_t set desc_txt='Allows users to open RICE Documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).' where perm_id='147'
/
update krim_perm_t set desc_txt='Allows users to access and run Batch Jobs associated with KR modules via the Schedule link.' where perm_id='164'
/
update krim_perm_t set desc_txt='Authorizes to initiate and edit the Parameter document for parameters with a module code beginning with KR.' where perm_id='163'
/
update krim_perm_t set desc_txt='Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for Roles with a Module Code beginning with KR.' where perm_id='150'
/
update krim_perm_t set desc_txt='Authorizes users to modify the information on the Permissions tab of the Role Document for roles with a module code beginning with KR.' where perm_id='151'
/
update krim_perm_t set desc_txt='Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with a Module Code that begins with KR.' where perm_id='152'
/	
update krim_perm_t set desc_txt='Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with namespaces beginning with KR.' where perm_id='155'
/	
update krim_perm_t set desc_txt='Allows access to the Blanket Approval button on RICE Documents.' where perm_id='148'
/	
update krim_perm_t set desc_txt='Authorizes users to open RICE Documents.' where perm_id='165'
/	
update krim_perm_t set desc_txt='Users who can add notes and attachments to any document answering to the Kuali Document parent document type.' where perm_id='259'
/	
update krim_perm_t set desc_txt='Authorizes users to view notes and attachments on documents answering to the KualiDocument parent document type.' where perm_id='261'
/	
update krim_perm_t set desc_txt='Authorizes users to delete notes and attachments created by any user on documents answering to the RICE Document parent document type.' where perm_id='264'
/	
update krim_perm_t set desc_txt='Authorizes users to send FYI ad hoc requests for Kuali Documents' where perm_id='332'
/
update krim_perm_t set desc_txt='Authorizes users to send Acknowledge ad hoc requests for Kuali Documents' where perm_id='333'
/
update krim_perm_t set desc_txt='Authorizes users to send Approve ad hoc requests for Kuali Documents' where perm_id='334'
/
update krim_perm_t set desc_txt='Authorizes users to submit a document for routing.' where perm_id='168'
/
update krim_perm_t set desc_txt='Authorizes users to take the Approve action on documents routed to them.' where perm_id='170'
/	
update krim_perm_t set desc_txt='Authorizes users to take the FYI action on documents routed to them.' where perm_id='172'
/	
update krim_perm_t set desc_txt='Authorizes users to take the Acknowledge action on documents routed to them.' where perm_id='173'
/	
update krim_perm_t set desc_txt='Allows a user to receive ad hoc requests for RICE Documents.' where perm_id='146'
/

update krim_perm_t set desc_txt='Allow users to access the Rule Template lookup.' where perm_id='701'
/
update krim_perm_t set desc_txt='Allow users to access the Stylesheet lookup.' where perm_id='702'
/
update krim_perm_t set desc_txt='Allow users to access the eDocLite lookup.' where perm_id='703'
/
update krim_perm_t set desc_txt='Allow users to access the Rule Attribute lookup.' where perm_id='707'
/
update krim_perm_t set desc_txt='Allow users to access the Pessimistic Lock lookup.' where perm_id='714'
/
update krim_perm_t set desc_txt='Allow users to access the Parameter Component lookup.' where perm_id='719'
/
update krim_perm_t set desc_txt='Allow users to access the Namespace lookup.' where perm_id='720'
/
update krim_perm_t set desc_txt='Allow users to access the Parameter Type lookup.' where perm_id='721'
/

update krim_perm_t set desc_txt='Allow users to access the Rule Template inquiry.' where perm_id='801'
/
update krim_perm_t set desc_txt='Allow users to access the Stylesheet inquiry.' where perm_id='802'
/
update krim_perm_t set desc_txt='Allow users to access the eDocLite inquiry.' where perm_id='803'
/
update krim_perm_t set desc_txt='Allow users to access the Rule Attribute inquiry.' where perm_id='807'
/
update krim_perm_t set desc_txt='Allow users to access the Pessimistic Lock inquiry.' where perm_id='814'
/
update krim_perm_t set desc_txt='Allow users to access the Parameter Component inquiry.' where perm_id='819'
/
update krim_perm_t set desc_txt='Allow users to access the Namespace inquiry.' where perm_id='820'
/
update krim_perm_t set desc_txt='Allow users to access the Parameter Type inquiry.' where perm_id='821'
/

update krim_perm_t set desc_txt='Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with the KUALI namespace.' where perm_id='833'
/
update krim_perm_t set desc_txt='Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for Roles with the KUALI namespace.' where perm_id='834'
/
update krim_perm_t set desc_txt='Authorizes users to modify the information on the Permissions tab of the Role Document for roles with the KUALI namespace.' where perm_id='835'
/
update krim_perm_t set desc_txt='Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with the KUALI namespace.' where perm_id='836'
/

-- Role Data

-- update role descriptions

update krim_role_t
set desc_txt = 'This role derives its members from users with the Edit Document permission for a given document type.,'
where nmspc_cd = 'KR-NS'
and role_nm = 'Document Editor'
/
update krim_role_t
set desc_txt = 'This role derives its members from users with the Open Document permission for a given document type.,'
where nmspc_cd = 'KR-NS'
and role_nm = 'Document Opener'
/
update krim_role_t
set desc_txt = 'This role can take superuser actions and blanket approve RICE documents as well as being able to modify and assign permissions, responsibilities and roles belonging to the KR namespaces.'
where nmspc_cd = 'KR-SYS'
and role_nm = 'Technical Administrator'
/
update krim_role_t
set desc_txt = 'This role represents the KR System User, that is the user ID RICE uses when it takes programmed actions.'
where nmspc_cd = 'KR-SYS'
and role_nm = 'System User'
/
update krim_role_t
set desc_txt = 'This role derives its members from users with the Initiate Document permission for a given document type.'
where nmspc_cd = 'KR-SYS'
and role_nm = 'Document Initiator'
/
update krim_role_t
set desc_txt = 'This role derives its members from users with that have received an action request for a given document.'
where nmspc_cd = 'KR-WKFLW'
and role_nm = 'Approve Request Recipient'
/
update krim_role_t
set desc_txt = 'This role derives its members from the initiator listed within the route log of a given document.'
where nmspc_cd = 'KR-WKFLW'
and role_nm = 'Initiator'
/
update krim_role_t
set desc_txt = 'This role derives its members from the initiator and action request recipients listed within the route log of a given document.'
where nmspc_cd = 'KR-WKFLW'
and role_nm = 'Initiator or Reviewer'
/
update krim_role_t
set desc_txt = 'This role derives its members from the user who took the Complete action on a given document.'
where nmspc_cd = 'KR-WKFLW'
and role_nm = 'Router'
/
update krim_role_t
set desc_txt = 'This role derives its members from users with an acknowledge action request in the route log of a given document.'
where nmspc_cd = 'KR-WKFLW'
and role_nm = 'Acknowledge Request Recipient'
/
update krim_role_t
set desc_txt = 'This role derives its members from users with an FYI action request in the route log of a given document.'
where nmspc_cd = 'KR-WKFLW'
and role_nm = 'FYI Request Recipient'
/
update krim_role_t
set desc_txt = 'This role derives its members from users with an Approval action request (that was not generated via the ad-hoc recipients tab) in the route log of a given document.'
where nmspc_cd = 'KR-WKFLW'
and role_nm = 'Non-Ad Hoc Approve Request Recipient'
/
update krim_role_t
set desc_txt = 'This role derives its members from the users in the Principal table. This role gives users high-level permissions to interact with RICE documents and to login to KUALI.'
where nmspc_cd = 'KUALI'
and role_nm = 'User'
/

-- remove the "Rice" role, we will create a new "System User" role later in the script

delete from krim_role_t where role_id='62'
/
delete from krim_role_mbr_t where role_id='62'
/
delete from krim_role_perm_t where role_id='62'
/

-- create missing "System User" role

INSERT INTO krim_role_t(ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES('90', '61815E6C62D0B647E0404F8189D873B3', 1, 'System User', 'KR-SYS', 'This role represents the KR System User, that is the user ID RICE uses when it takes programmed actions.', '1', 'Y', NULL)
/
INSERT INTO krim_role_mbr_t(ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
  VALUES('1282', 1, '5B4B421E43857717E0404F8189D821F7', '90', '1', 'P', NULL, NULL, NULL)
/
INSERT INTO krim_role_perm_t(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
  VALUES('552', '61815E6C62D3B647E0404F8189D873B3', 1, '90', '290', 'Y')
/

-- create missing "Document Initiator" role

INSERT INTO krim_role_t(ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES('95', '67F145466E8B9160E0404F8189D86771', 1, 'Document Initiator', 'KR-SYS', 'This role derives its members from users with the Initiate Document permission for a given document type.', '66', 'Y', NULL)
/
INSERT INTO krim_role_perm_t(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
  VALUES('250', '70086A2DF17D62E4E0404F8189D863CD', 1, '95', '156', 'Y')
/

-- create missing "Non-Ad Hoc Approve Request Recipient" role, no permissions assigned here

INSERT INTO krim_role_t(ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
  VALUES('97', '67F145466EB09160E0404F8189D86771', 1, 'Non-Ad Hoc Approve Request Recipient', 'KR-WKFLW', 'This role derives its members from users with an Approval action request (that was not generated via the ad-hoc recipients tab) in the route log of a given document.', '42', 'Y', NULL)
/
INSERT INTO krim_role_perm_t(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
  VALUES('251', '70086A2DF17E62E4E0404F8189D863CD', 1, '97', '181', 'Y')
/


-- Kim Types

-- update missing service names

update krim_typ_t set srvc_nm='permissionPermissionTypeService' where kim_typ_id='19'
/
update krim_typ_t set srvc_nm='responsibilityPermissionTypeService' where kim_typ_id='20'
/

-- add missing Rice kim types

INSERT INTO krim_typ_t(KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES('66', '67F145466E8A9160E0404F8189D86771', 1, 'Derived Role: Permission (Initiate Document)', 'documentInitiatorRoleTypeService', 'Y', 'KR-SYS')
/
INSERT INTO krim_typ_t(KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES('67', '67F145466E8F9160E0404F8189D86771', 1, 'Namespace', 'namespacePermissionTypeService', 'Y', 'KR-NS')
/

-- fix missing kim type attributes

INSERT INTO krim_typ_attr_t(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('111', '67F145466E909160E0404F8189D86771', 1, 'a', '67', '4', 'Y')
/
INSERT INTO krim_typ_attr_t(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('112', '67F145466E959160E0404F8189D86771', 1, 'b', '14', '13', 'Y')
/
INSERT INTO krim_typ_attr_t(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('28', '5ADF18B6D4F87954E0404F8189D85002', 1, 'a', '17', '12', 'Y')
/
INSERT INTO krim_typ_attr_t(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('95', '5C997D14EAC3FE40E0404F8189D87DC5', 1, 'a', '52', '13', 'Y')
/
INSERT INTO krim_typ_attr_t(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('96', '5C997D14EAC4FE40E0404F8189D87DC5', 1, 'b', '52', '16', 'Y')
/
INSERT INTO krim_typ_attr_t(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('97', '5C997D14EAC5FE40E0404F8189D87DC5', 1, 'c', '52', '6', 'Y')
/



-- Re-enable constraints
DECLARE 
   CURSOR constraint_cursor IS 
      SELECT table_name, constraint_name 
         FROM user_constraints 
         WHERE constraint_type = 'R'
           AND status <> 'ENABLED'
           AND constraint_name <> 'KRIM_TYP_ATTRIBUTE_TR1';
BEGIN 
   FOR r IN constraint_cursor LOOP
      execute immediate 'ALTER TABLE '||r.table_name||' ENABLE CONSTRAINT '||r.constraint_name; 
   END LOOP; 
END;
/

-- the mysql way to re-enable constraints
-- SET foreign_key_checks = 1
commit
/
UPDATE KREW_DOC_TYP_T SET DOC_HDLR_URL='${kr.url}/maintenance.do?methodToCall=docHandler' WHERE DOC_TYP_NM='RoutingRuleDocument' AND CUR_IND=1
/

UPDATE KREW_DOC_TYP_T SET DOC_HDLR_URL='${kr.url}/maintenance.do?methodToCall=docHandler' WHERE DOC_TYP_NM='RoutingRuleDelegationMaintenanceDocument' AND CUR_IND=1
/
commit
/
INSERT INTO KRNS_PARM_T(NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD)
    VALUES('KR-NS', 'Document', 'ALLOW_ENROUTE_BLANKET_APPROVE_WITHOUT_APPROVAL_REQUEST_IND', sys_guid(), 1, 'CONFG', 'N', 'Controls whether the nervous system will show the blanket approve button to a user who is authorized for blanket approval but is neither the initiator of the particular document nor the recipient of an active, pending, approve action request.', 'A')
/ 
commit
/
update kren_recip_deliv_t set recip_id = 'testuser1' where recip_id = 'TestUser1'
/
update kren_recip_deliv_t set recip_id = 'testuser2' where recip_id = 'TestUser2'
/
update kren_recip_deliv_t set recip_id = 'testuser4' where recip_id = 'TestUser4'
/
update kren_recip_deliv_t set recip_id = 'testuser5' where recip_id = 'TestUser5'
/
update kren_recip_deliv_t set recip_id = 'testuser6' where recip_id = 'TestUser6'
/
update kren_recip_list_t set recip_id = 'testuser1' where recip_id = 'TestUser1'
/
update kren_recip_list_t set recip_id = 'testuser3' where recip_id = 'TestUser3'
/
update kren_rvwer_t set prncpl_id = 'testuser3' where prncpl_id = 'TestUser3'
/
update kren_chnl_subscrp_t set prncpl_id = 'testuser4' where prncpl_id = 'TestUser4'
/

-- KULRICE-3449
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT) VALUES('1207', 1, '6798B3E6C3C49827AE62E5F7A275A1A3', '2000', 'admin', 'P', Null, Null) 
/
commit
/
update krim_perm_t set desc_txt = 'Allows users to access the XML Ingester screen.' where perm_id=265 and nmspc_cd='KR-WKFLW' and NM='Use Screen'
/
update krim_perm_t set desc_txt = 'Allows users to access the Document Operation screen.' where perm_id=140 and nmspc_cd='KR-WKFLW' and NM='Use Screen'
/
commit
/
Alter table KREW_DOC_TYP_PROC_T modify INIT_RTE_NODE_ID NUMBER null
/
commit
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'DocumentType' AND parm_nm = 'DOCUMENT_TYPE_SEARCH_INSTRUCTION'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'GlobalReviewer' AND parm_nm = 'REPLACE_INSTRUCTION'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Note' AND parm_nm = 'NOTE_CREATE_NEW_INSTRUCTION'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Rule' AND parm_nm = 'RULE_CREATE_NEW_INSTRUCTION'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Rule' AND parm_nm = 'RULE_LOCKING_ON_IND'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Rule' AND parm_nm = 'RULE_SEARCH_INSTRUCTION'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'RuleTemplate' AND parm_nm = 'RULE_TEMPLATE_CREATE_NEW_INSTRUCTION'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'RuleTemplate' AND parm_nm = 'RULE_TEMPLATE_SEARCH_INSTRUCTION'
/
delete from krns_parm_t where nmspc_cd = 'KR-NS' AND PARM_DTL_TYP_CD = 'Document' AND parm_nm = 'PESSIMISTIC_LOCK_ADMIN_GROUP'
/
delete from krns_parm_t where nmspc_cd = 'KR-NS' AND PARM_DTL_TYP_CD = 'Document' AND parm_nm = 'EXCEPTION_GROUP'
/
delete from krns_parm_t where nmspc_cd = 'KR-NS' AND PARM_DTL_TYP_CD = 'Document' AND parm_nm = 'SUPERVISOR_GROUP'
/
delete from krns_parm_t where nmspc_cd = 'KR-NS' AND PARM_DTL_TYP_CD = 'Batch' AND parm_nm = 'SCHEDULE_ADMIN_GROUP'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Backdoor' AND parm_nm = 'TARGET_FRAME_NAME'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'ActionList' AND parm_nm = 'HELP_DESK_NAME_GROUP'
/
delete from krns_parm_t where nmspc_cd = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Rule' AND parm_nm = 'ROUTE_LOG_POPUP_IND'
/
commit
/
UPDATE krew_doc_typ_t SET doc_hdlr_url = '${ken.url}/DetailView.form' WHERE doc_typ_nm = 'KualiNotification';
UPDATE krew_doc_typ_t SET doc_hdlr_url = '${ken.url}/AdministerNotificationRequest.form' WHERE doc_typ_nm = 'SendNotificationRequest';
/
commit
/
Delete from KRNS_PARM_DTL_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'RuleService'
/
Delete from KRNS_PARM_DTL_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Workgroup'
/
Delete from KRNS_PARM_DTL_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'DocumentSearch'
/
-- Following lines commented out because these records should already be in the master rice database
-- These were needed to run against the KFS rice database since they were missing.
--Insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'Backdoor', 'F7E44233C2C440FFB1A399548951160A', 1, 'Backdoor', 'Y')
--/
--Insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'ActionList', '1821D8BAB21E498F9FB1ECCA25C37F9B', 1, 'Action List', 'Y')
--/
--Insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'EDocLite', '51DD5B9FACDD4EDAA9CA8D53A82FCCCA', 1, 'eDocLite', 'Y')
--/
--Insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'Feature', 'BBD9976498A4441F904013004F3D70B3', 1, 'Feature', 'Y')
--/
--Insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'Mailer', '5DB9D1433E214325BE380C82762A223B', 1, 'Mailer', 'Y')
--/
--Insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'QuickLinks', '3E26DA76458A46D68CBAF209DA036157', 1, 'Quick Link', 'Y')
/
Insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'Notification', 'D04AFB1812E34723ABEB64986AC61DC9', 1, 'Notification', 'Y')
/

UPDATE KRNS_PARM_T SET PARM_DTL_TYP_CD = 'DocSearchCriteriaDTO' where NMSPC_CD = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'DocumentSearch'
/
UPDATE KRNS_PARM_T SET PARM_DTL_TYP_CD = 'Notification' where NMSPC_CD = 'KR-WKFLW' AND PARM_DTL_TYP_CD = 'Workgroup'
/
commit
/
CREATE TABLE KRIM_ENTITY_ETHNIC_T
(
      ID VARCHAR2(40),
      ENTITY_ID VARCHAR2(40),
      ETHNCTY_CD VARCHAR2(40),
      SUB_ETHNCTY_CD VARCHAR2(40),
      VER_NBR NUMBER(8) default 1 NOT NULL,
      OBJ_ID VARCHAR2(36) NOT NULL,

      CONSTRAINT KRIM_ENTITY_ETHNIC_TC0 UNIQUE (OBJ_ID)
)
/
ALTER TABLE KRIM_ENTITY_ETHNIC_T
    ADD CONSTRAINT KRIM_ENTITY_ETHNIC_TP1
PRIMARY KEY (ID)
/
CREATE SEQUENCE KRIM_ENTITY_ETHNIC_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- create residency table
CREATE TABLE KRIM_ENTITY_RESIDENCY_T
(
      ID VARCHAR2(40),
      ENTITY_ID VARCHAR2(40),
      DETERMINATION_METHOD VARCHAR2(40),
      IN_STATE VARCHAR2(40),
      VER_NBR NUMBER(8) default 1 NOT NULL,
      OBJ_ID VARCHAR2(36) NOT NULL,

      CONSTRAINT KRIM_ENTITY_RESIDENCY_TC0 UNIQUE (OBJ_ID)
)
/
ALTER TABLE KRIM_ENTITY_RESIDENCY_T
    ADD CONSTRAINT KRIM_ENTITY_RESIDENCY_TP1
PRIMARY KEY (ID)
/
CREATE SEQUENCE KRIM_ENTITY_RESIDENCY_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- add visa table
CREATE TABLE KRIM_ENTITY_VISA_T
(
      ID VARCHAR2(40),
      ENTITY_ID VARCHAR2(40),
      VISA_TYPE_KEY VARCHAR2(40),
      VISA_ENTRY VARCHAR2(40),
      VISA_ID VARCHAR2(40),
      VER_NBR NUMBER(8) default 1 NOT NULL,
      OBJ_ID VARCHAR2(36) NOT NULL,

      CONSTRAINT KRIM_ENTITY_VISA_TC0 UNIQUE (OBJ_ID)
)
/
ALTER TABLE KRIM_ENTITY_VISA_T
    ADD CONSTRAINT KRIM_ENTITY_VISA_TP1
PRIMARY KEY (ID)
/
CREATE SEQUENCE KRIM_ENTITY_VISA_ID_S INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/

-- insert ethnicity values into new ethinicity table from bio table (copy ethnicityCode and entityId values... double check sequence usage)
INSERT INTO KRIM_ENTITY_ETHNIC_T ( ID, OBJ_ID, ENTITY_ID, ETHNCTY_CD )
    SELECT KRIM_ENTITY_ETHNIC_ID_S.NEXTVAL, SYS_GUID(), bio.ENTITY_ID, bio.ETHNCTY_CD
        FROM KRIM_ENTITY_BIO_T bio
/

-- alter bio table to add new fields
ALTER TABLE KRIM_ENTITY_BIO_T ADD DECEASED_DT DATE
/
ALTER TABLE KRIM_ENTITY_BIO_T ADD MARITAL_STATUS VARCHAR2(40)
/
ALTER TABLE KRIM_ENTITY_BIO_T ADD PRIM_LANG_CD VARCHAR2(40)
/
ALTER TABLE KRIM_ENTITY_BIO_T ADD SEC_LANG_CD VARCHAR2(40)
/
ALTER TABLE KRIM_ENTITY_BIO_T ADD BIRTH_CNTRY_CD VARCHAR2(2)
/
ALTER TABLE KRIM_ENTITY_BIO_T ADD BIRTH_STATE_CD VARCHAR2(2)
/
ALTER TABLE KRIM_ENTITY_BIO_T ADD BIRTH_CITY VARCHAR2(30)
/
ALTER TABLE KRIM_ENTITY_BIO_T ADD GEO_ORIGIN VARCHAR2(100)
/

-- drop ethnicity from bio table
ALTER TABLE KRIM_ENTITY_BIO_T DROP COLUMN ETHNCTY_CD
/
commit
/
ALTER TABLE KRIM_ROLE_DOCUMENT_T ADD DESC_TXT VARCHAR(4000) 
/
commit
/

