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
INSERT INTO KRCR_NMSPC_T (APPL_ID,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR)
VALUES('KC','KC-SUBAWARD','Sub Awards','Y',UUID(),1)
/

INSERT INTO KRCR_CMPNT_T (CMPNT_CD,NMSPC_CD,NM,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ('Document','KC-SUBAWARD','Document','Y',UUID(),1)
/

UPDATE KRIM_PERM_T SET PERM_TMPL_ID = (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document') WHERE NMSPC_CD = 'KC-SUBAWARD'
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID, PERM_ID, ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'CREATE SUBAWARD'),'SubAwardDocument',UUID(),1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID, PERM_ID, ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'MODIFY SUBAWARD'),'SubAwardDocument',UUID(),1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID, PERM_ID, ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'VIEW SUBAWARD'),'SubAwardDocument',UUID(),1)
/
DELIMITER ;
