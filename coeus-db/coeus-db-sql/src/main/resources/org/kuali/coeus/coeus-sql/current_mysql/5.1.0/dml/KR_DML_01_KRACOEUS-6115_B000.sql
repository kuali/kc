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

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'CORRESPONDENCE_SIGNATURE_TYPE', UUID(), 1, 'CONFG', 'S', 
	'Define correspondence signature type. Allowed values are N-No signature required,  D-Always use Default signature and S-Always use signed in user signature.', 
	'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-IACUC', 'Document', 'CORRESPONDENCE_SIGNATURE_TAG', UUID(), 1, 'CONFG', 'Best Regards', 
	'Specify signature tag - a location where to place the signature. Application will identify the location based on this key and place the signature below. Multiple tags should be delimited by semi colon', 
	'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PROTOCOL', 'Document', 'CORRESPONDENCE_SIGNATURE_TYPE', UUID(), 1, 'CONFG', 'S', 
	'Define correspondence signature type. Allowed values are N-No signature required,  D-Always use Default signature and S-Always use signed in user signature.', 
	'A', 'KC')
/

INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PROTOCOL', 'Document', 'CORRESPONDENCE_SIGNATURE_TAG', UUID(), 1, 'CONFG', 'cc:', 
	'Specify signature tag - a location where to place the signature. Application will identify the location based on this key and place the signature below. Multiple tags should be delimited by semi colon', 
	'A', 'KC')
/
DELIMITER ;
