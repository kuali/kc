DELIMITER /

INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)
  VALUES ('KC','KC-GEN','All','MULTI_CAMPUS_ENABLED','CONFG','N','Enables or disables Multi-Campus mode','A',UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VAL,PARM_DESC_TXT,PARM_TYP_CD,EVAL_OPRTR_CD,OBJ_ID,VER_NBR) 
  VALUES ('KC','KC-PROTOCOL','Document','protocolAttachmentDefaultSort','ATTP','Default sort for protocol attachments','CONFG','A',UUID(),'1')
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
  VALUES ('KC', 'KC-GEN', 'A', 'PERMANENT_RESIDENT_OF_US_PENDING', 1, 'CONFG', 4, 'Permanent Resident of U.S. Pending', 'A', UUID())
/
update KRCR_PARM_T set VAL='6' where PARM_NM='budgetPersonDefaultAppointmentType'
/
update KRCR_PARM_T set VAL='1,9' where parm_nm='scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardComment.commentTypeCode'
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID)
  VALUES ('KC', 'KC-GEN', 'A', 'ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES', 1, 'CONFG', 'Y', 'If Y then the proposal person citizenship type is used, if N then the kc extended attributes citizenship type is used', 'A', UUID())
/
delete from KRCR_PARM_T where PARM_NM = 'AWARD_CREATE_ACCOUNT'
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID) VALUES ('KC', 'KC-AWARD', 'Document', 'FIN_SYSTEM_INTEGRATION_ON', 1, 'CONFG', 'OFF', 'Parameter to set the financial system integration feature ON or OFF.', 'A', UUID())
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-UNT', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-ADM', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-T', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-AWARD', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-PROTOCOL', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-PD', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-WKFLW', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-SYS', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-AB', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID, VER_NBR) VALUES ('KUALI', 'KC-IP', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', UUID(), 1)
/
INSERT INTO KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID) VALUES ('KC', 'KC-GEN', 'All', 'SPONSOR_HIERARCHY_FOR_PRINTING', 1, 'CONFG', 'Printing', 'The name of the Sponsor Hierarchy used for Sponsor Form selection.', 'A', UUID())
/
insert into KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID) values ('KC', 'KC-AWARD', 'Document', 'CFDA_BATCH_JOB_CRON_EXPRESSION', 1, 'CONFG', '0 0 6 * * ?', 'Parameter to set the cron expression for the CFDA batch job', 'A', UUID())
/
insert into KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID) values ('KC', 'KC-AWARD', 'Document', 'CFDA_GOV_URL', 1, 'CONFG', 'ftp://ftp.cfda.gov/programs', 'Url of the CFDA FTP site for the CFDA batch job', 'A', UUID())
/
insert into KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID) values ('KC', 'KC-AWARD', 'Document', 'CFDA_BATCH_NOTIFICATION_RECIPIENT', 1, 'CONFG', '', 'Principal name of the person that should receive notifications when the CFDA batch job runs', 'A', UUID())
/
insert into KRCR_PARM_T (APPL_ID, NMSPC_CD, CMPNT_CD, PARM_NM, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, OBJ_ID) values('KC', 'KC-AWARD', 'Document', 'CFDA_BATCH_JOB_CRON_START_TIME', 1, 'CONFG', '', 'Start time of the CFDA job', 'A', UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) values ('KC','KC-GEN','A','POST_DOCTORAL_COSTELEMENT','CONFG','400390','PostDoctoral CostElement', 'A',UUID())
/
update KRCR_PARM_T set VAL = '1' where parm_nm = 'scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardComment.commentTypeCode' and nmspc_cd = 'KC-AWARD' and CMPNT_CD = 'Document'
/

DELIMITER ;
