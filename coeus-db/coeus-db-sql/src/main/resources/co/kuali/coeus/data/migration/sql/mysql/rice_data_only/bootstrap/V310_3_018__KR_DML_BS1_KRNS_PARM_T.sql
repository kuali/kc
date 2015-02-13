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
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','STIPEND_COST_ELEMENTS','CONFG','400315','STIPEND_COST_ELEMENTS used in Phs fellowship Supplemental s2s form','A',UUID())
/
insert into KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID)  values ('KC','KC-PD','Document','TUITION_COST_ELEMENTS','CONFG','422311','TUITION_COST_ELEMENTS used in Phs fellowship Supplemental s2s form','A',UUID())
/
update KRCR_PARM_T set PARM_NM = 'IRB_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL' where PARM_NM = 'IRB_DISPLAY_REVIEWER_NAME_TO_OTHERS'
/
update KRCR_PARM_T set PARM_NM = 'IRB_DISPLAY_REVIEWER_NAME_TO_PROTOCOL_PERSONNEL', PARM_DESC_TXT='Display Reviewer Name to Protocol Personnel' where PARM_NM='IRB_DISPLAY_REVIEWER_NAME_TO_OTHER_PROTOCOL_PERSONNEL'
/
delete from KRCR_PARM_T where PARM_NM = 'IRB_DISPLAY_REVIEWER_NAME_TO_PI'
/
commit
/
DELIMITER ;
