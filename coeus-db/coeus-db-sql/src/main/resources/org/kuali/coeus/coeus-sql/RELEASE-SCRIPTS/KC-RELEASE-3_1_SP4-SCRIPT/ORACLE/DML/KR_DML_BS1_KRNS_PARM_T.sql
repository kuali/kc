--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 The Kuali Foundation
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID)
  VALUES ('KC','KC-GEN','All','MULTI_CAMPUS_ENABLED','CONFG','N','Enables or disables Multi-Campus mode','A',SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,TXT,PARM_DESC_TXT,PARM_TYP_CD,CONS_CD,OBJ_ID,VER_NBR) 
  VALUES ('KC','KC-PROTOCOL','Document','protocolAttachmentDefaultSort','ATTP','Default sort for protocol attachments','CONFG','A',SYS_GUID(),'1');

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
  VALUES ('KC', 'KC-GEN', 'A', 'PERMANENT_RESIDENT_OF_US_PENDING', 1, 'CONFG', 4, 'Permanent Resident of U.S. Pending', 'A', SYS_GUID());

update KRNS_PARM_T set TXT='6' where PARM_NM='budgetPersonDefaultAppointmentType';

update krns_parm_t set txt='1,9' where parm_nm='scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardComment.commentTypeCode';

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID)
  VALUES ('KC', 'KC-GEN', 'A', 'ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES', 1, 'CONFG', 'Y', 'If Y then the proposal person citizenship type is used, if N then the kc extended attributes citizenship type is used', 'A', SYS_GUID());

delete from krns_parm_t where PARM_NM = 'AWARD_CREATE_ACCOUNT';

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) VALUES ('KC', 'KC-AWARD', 'Document', 'FIN_SYSTEM_INTEGRATION_ON', 1, 'CONFG', 'OFF', 'Parameter to set the financial system integration feature ON or OFF.', 'A', SYS_GUID());

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-UNT', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-ADM', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-T', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-AWARD', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-PROTOCOL', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-PD', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-WKFLW', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-SYS', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-AB', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);

INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID, VER_NBR) 
  VALUES ('KUALI', 'KC-IP', 'All', 'KIM_SHOW_BLANK_QUALIFIERS', 'CONFG', 'N', 'Whether to show the blank qualifiers in KIM Maintenance Screens', 'A', SYS_GUID(), 1);
  
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) 
  VALUES ('KC', 'KC-GEN', 'All', 'SPONSOR_HIERARCHY_FOR_PRINTING', 1, 'CONFG', 'Printing', 'The name of the Sponsor Hierarchy used for Sponsor Form selection.', 'A', SYS_GUID());

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) values ('KC', 'KC-AWARD', 'Document', 'CFDA_BATCH_JOB_CRON_EXPRESSION', 1, 'CONFG', '0 0 6 * * ?', 'Parameter to set the cron expression for the CFDA batch job', 'A', SYS_GUID());

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) values ('KC', 'KC-AWARD', 'Document', 'CFDA_GOV_URL', 1, 'CONFG', 'ftp://ftp.cfda.gov/programs', 'Url of the CFDA FTP site for the CFDA batch job', 'A', SYS_GUID());

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) values ('KC', 'KC-AWARD', 'Document', 'CFDA_BATCH_NOTIFICATION_RECIPIENT', 1, 'CONFG', '', 'Principal name of the person that should receive notifications when the CFDA batch job runs', 'A', SYS_GUID());

insert into krns_parm_t (APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, OBJ_ID) values('KC', 'KC-AWARD', 'Document', 'CFDA_BATCH_JOB_CRON_START_TIME', 1, 'CONFG', '', 'Start time of the CFDA job', 'A', SYS_GUID());  
  
insert into KRNS_PARM_T (APPL_NMSPC_CD,NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) values ('KC','KC-GEN','A','POST_DOCTORAL_COSTELEMENT','CONFG','400390','PostDoctoral CostElement', 'A',sys_guid());

update KRNS_PARM_T set TXT = '1' where parm_nm = 'scope.sync.PAYMENTS_AND_INVOICES_TAB.AwardComment.commentTypeCode' and nmspc_cd = 'KC-AWARD' and parm_dtl_typ_cd = 'Document';
