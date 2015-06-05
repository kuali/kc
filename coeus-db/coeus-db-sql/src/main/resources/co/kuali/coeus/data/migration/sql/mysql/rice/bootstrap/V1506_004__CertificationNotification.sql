--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2015 Kuali, Inc.
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

INSERT INTO KRCR_PARM_T(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','keyPersonProjectRole',UUID(),1,'CONFG','Other Significant Contributor,Consultant,Subaward Investigator','Proposal Key person project roles which will decide KP needs to certify or not','A','KC');

INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) values
('KC-GEN','All','PROP_PERSON_COI_CERTIFY_QID',UUID(),1,'CONFG','10086,10087,10088','Comma delimited list of questionSeqIds for COI related certification questions','A','KC');

INSERT INTO KRCR_PARM_T (NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID) values
('KC-GEN','All','PROP_PERSON_COI_STATUS_FLAG',UUID(),1,'CONFG','N','Flag to turn on/off COI disclosure status on proposal development','A','KC');

INSERT INTO KRIM_TYP_ID_S VALUES(NULL);
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES(CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S)), UUID(), 1, 'Derived Role: Principle Investigator', 'proposalPersonDerivedRoleTypeService', 'Y', 'KC-PD');

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL);
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S)), UUID(), 1, 'Principle Investigator', 'KC-PD', 'Principle Investigator for PD', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Derived Role: Principle Investigator'), 'Y', NOW());

UPDATE KRIM_TYP_T SET SRVC_NM='{http://kc.kuali.org/core/v5_0}proposalPiTypeDerivedRoleTypeService' WHERE NM='Derived Role: Principle Investigator' AND
NMSPC_CD='KC-PD';

INSERT INTO KRIM_PERM_ID_S VALUES(NULL);
INSERT INTO KRIM_PERM_T(PERM_ID,OBJ_ID,VER_NBR,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND)
VALUES(CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_PERM_ID_S)),UUID(),1,NULL,'KC-PD','View Certification','For Only Viewing certification questionnaire','Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL);
INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID,OBJ_ID,VER_NBR,ROLE_ID,PERM_ID,ACTV_IND)
VALUES(CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)),UUID(),1,(select  ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Principle Investigator' AND NMSPC_CD='KC-PD'),(select PERM_ID from KRIM_PERM_T where nm = 'Certify'),'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL);
INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID,OBJ_ID,VER_NBR,ROLE_ID,PERM_ID,ACTV_IND)
VALUES(CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)),UUID(),1,(select  ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Principle Investigator' AND NMSPC_CD='KC-PD'),(select PERM_ID from KRIM_PERM_T where nm = 'View Certification'),'Y');

