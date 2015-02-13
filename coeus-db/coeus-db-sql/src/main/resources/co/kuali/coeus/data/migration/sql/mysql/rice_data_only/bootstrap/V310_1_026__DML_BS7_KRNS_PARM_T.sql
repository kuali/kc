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

DELIMITER /

insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TUITION_OTHER_COST_ELEMENTS','CONFG','TUIOTHER','TUITION_OTHER_COST_ELEMENT used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS','CONFG','TUIPRESD','TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS','CONFG','TUIPREDD','TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TUITION_UNDERGRAD_COST_ELEMENTS','CONFG','TUIUNDGR','TUITION_UNDERGRAD_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TUITION_POSTDOC_DEG_COST_ELEMENTS','CONFG','TUIPDYDS','TUITION_POSTDOC_DEG_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TUITION_POSTDOC_NONDEG_COST_ELEMENTS','CONFG','TUIPDNDS','TUITION_POSTDOC_NONDEG_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','SUBCONTRACT_COST_ELEMENTS','CONFG','420620,420600','SUBCONTRACT_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TRAINING_REL_COST_ELEMENTS','CONFG','420144','TRAINING_REL_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TRAINEE_TRAVEL_COST_ELEMENTS','CONFG','420060','TRAINEE_TRAVEL_COST_ELEMENTS used in Phs training budget s2s form','A',UUID())
/
COMMIT
/

DELIMITER ;
