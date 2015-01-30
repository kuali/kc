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

INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
   VALUES ('KC','KC-GEN','All','SYSTEM_NOTIFICATION_PRODUCER_ID',1,'CONFG','1000','Code corresponding to System Notification Producer','A',sys_guid())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
   VALUES ('KC','KC-GEN','All','NORMAL_NOTIFICATION_PRIORITY_ID',1,'CONFG','1','Code corresponding to Normal Notification Priority','A',sys_guid())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
   VALUES ('KC','KC-GEN','All','SIMPLE_NOTIFICATION_CONTENT_TYPE_ID',1,'CONFG','1','Code corresponding to Simple Notification Type','A',sys_guid())
/
INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,CMPNT_CD,PARM_NM,VER_NBR,PARM_TYP_CD,VAL,PARM_DESC_TXT,EVAL_OPRTR_CD,OBJ_ID) 
   VALUES ('KC','KC-GEN','All','KC_NOTIFICATION_CHANNEL_ID',1,'CONFG','1000','Code corresponding to KC Notification Channel','A',sys_guid())
/
