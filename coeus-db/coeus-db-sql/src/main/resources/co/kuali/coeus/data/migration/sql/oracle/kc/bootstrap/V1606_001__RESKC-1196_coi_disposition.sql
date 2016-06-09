--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
    VALUES (KRIM_PERM_ID_S.NEXTVAL),(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),'KC-SYS','View COI Disclosure Disposition','View COI Disclosure Disposition','Y',SYS_GUID(),1);

INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PD','Document','ENABLE_DISCLOSURE_DISPOSITION_STATUS_FROM_COI_MODULE',SYS_GUID(),1,'CONFG','N','When enabled, this parameter displays the disclosure disposition status from the COI module. This is different from PROP_PERSON_COI_STATUS_FLAG and both should not be enabled at the same time.','A','KC');


INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-AWARD','Document','ENABLE_DISCLOSURE_DISPOSITION_STATUS_FROM_COI_MODULE',SYS_GUID(),1,'CONFG','N','When enabled, this parameter displays the disclosure disposition status from the COI module.','A','KC');


INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-IP','Document','ENABLE_DISCLOSURE_DISPOSITION_STATUS_FROM_COI_MODULE',SYS_GUID(),1,'CONFG','N','When enabled, this parameter displays the disclosure disposition status from the COI module.','A','KC');

INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-PROTOCOL','Document','ENABLE_DISCLOSURE_DISPOSITION_STATUS_FROM_COI_MODULE',SYS_GUID(),1,'CONFG','N','When enabled, this parameter displays the disclosure disposition status from the COI module.','A','KC');

INSERT INTO krcr_parm_t(NMSPC_CD,CMPNT_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,APPL_ID)
VALUES ('KC-IACUC','Document','ENABLE_DISCLOSURE_DISPOSITION_STATUS_FROM_COI_MODULE',SYS_GUID(),1,'CONFG','N','When enabled, this parameter displays the disclosure disposition status from the COI module.','A','KC');
