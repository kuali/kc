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
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
-- Create the modify and view permissions
INSERT INTO KRIM_PERM_T (PERM_ID,PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'), 'KC-IACUC', 'Modify IACUC Batch Correspondence Detail', 'Modify Iacuc Batch Correspondence Detail', 'Y', UUID(), 1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,PERM_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S), (SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IacucBatchCorrespondenceDetailMaintenanceDocument', UUID(), 1)
/
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/


INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND, OBJ_ID, VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'), 'KC-IACUC', 'View IACUC Batch Correspondence Detail', 'View Iacuc Batch Correspondence Detail', 'Y', UUID(), 1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S), (SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IacucBatchCorrespondenceDetailMaintenanceDocument', UUID(), 1)
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/


-- Assign modify permssion to admin and view permission to reviewer
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, VER_NBR, OBJ_ID) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator' AND NMSPC_CD='KC-UNT'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify IACUC Batch Correspondence Detail' AND NMSPC_CD='KC-IACUC'), 'Y', 1, UUID())
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID) 
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Reviewer' AND NMSPC_CD='KC-UNT'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View IACUC Batch Correspondence Detail' AND NMSPC_CD='KC-IACUC'), 'Y', 1, UUID())
/


DELIMITER ;
