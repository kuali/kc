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

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Prime_Administrative_Contact_Code', 'KC', SYS_GUID(), 1, 'CONFG', 0, 'FDP Prime Administrative Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD )
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Prime_Authorized_Official_Code', 'KC', SYS_GUID(), 1, 'CONFG', 0, 'FDP Prime Authorized Official Code', 'A')
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Prime_Financial_Contact_Code', 'KC', SYS_GUID(), 1, 'CONFG', 0, 'FDP Prime Financial Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Sub_Administrative_Contact_Code', 'KC', SYS_GUID(), 1, 'CONFG', 0, 'FDP Sub Administrative Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Sub_Authorized_Official_Code', 'KC', SYS_GUID(), 1, 'CONFG', 0, 'FDP Sub Authorized Official Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD )
VALUES( 'KC-SUBAWARD', 'Document', 'FDP_Sub_Financial_Contact_Code', 'KC', SYS_GUID(), 1, 'CONFG', 0, 'FDP Sub Financial Contact Code', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_FDP_Attachment_3B_Form_ID', 'KC', SYS_GUID(), 1, 'CONFG', 1, 'Subaward FDP Attachment 3B Form ID', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_FDP_Attachment_4_Form_ID', 'KC', SYS_GUID(), 1, 'CONFG', 1, 'Subaward FDP Attachment 4 Form ID', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_FDP_Attachment_5_Form_ID', 'KC', SYS_GUID(), 1, 'CONFG', 1, 'Subaward FDP Attachment 5 Form ID', 'A' )
/

INSERT INTO KRCR_PARM_T( NMSPC_CD, CMPNT_CD, PARM_NM, APPL_ID, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD ) 
VALUES( 'KC-SUBAWARD', 'Document', 'Subaward_Print_Attachment_Type_Inclusion', 'KC', SYS_GUID(), 1, 'CONFG', 1, 'Subaward Print Attachment Type Inclusion', 'A' )
/

UPDATE KRCR_PARM_T SET VAL = 35 WHERE PARM_NM = 'FDP_Prime_Administrative_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 37 WHERE PARM_NM = 'FDP_Prime_Authorized_Official_Code'
/

UPDATE KRCR_PARM_T SET VAL = 36 WHERE PARM_NM = 'FDP_Prime_Financial_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 31 WHERE PARM_NM = 'FDP_Sub_Administrative_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 34 WHERE PARM_NM = 'FDP_Sub_Authorized_Official_Code'
/

UPDATE KRCR_PARM_T SET VAL = 33 WHERE PARM_NM = 'FDP_Sub_Financial_Contact_Code'
/

UPDATE KRCR_PARM_T SET VAL = 'FDP_ATT_3B' WHERE PARM_NM = 'Subaward_FDP_Attachment_3B_Form_ID'
/

UPDATE KRCR_PARM_T SET VAL = 'FDP_ATT_4' WHERE PARM_NM = 'Subaward_FDP_Attachment_4_Form_ID'
/

UPDATE KRCR_PARM_T SET VAL = 0 WHERE PARM_NM = 'Subaward_FDP_Attachment_5_Form_ID'
/

UPDATE KRCR_PARM_T SET VAL = '1,2,3,5,6,7' WHERE PARM_NM = 'Subaward_Print_Attachment_Type_Inclusion'
/
