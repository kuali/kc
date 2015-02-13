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
INSERT INTO KRIM_RSP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_RSP_ID_S),(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Resolve Exception'),'KC-SYS','Rice Resolve Exception','Rice Document - Resolve Responsibility','Y',UUID(),1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),(SELECT (MAX(ID)) FROM KRIM_RSP_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-KEW' AND NM = 'Document Type (Responsibility)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),'RiceDocument',UUID(),1)
/
INSERT INTO KRIM_ROLE_RSP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_RSP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Application Administrator'),(SELECT (MAX(ID)) FROM KRIM_RSP_ID_S),'Y',UUID(),1)
/
INSERT INTO KRIM_RSP_ID_S VALUES(NULL)
/

INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_RSP_ID_S),(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Resolve Exception'),'KC-SYS','KC Resolve Exception','KC - Resolve Responsibility','Y',UUID(),1)
/
INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S),(SELECT (MAX(ID)) FROM KRIM_RSP_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-KEW' AND NM = 'Document Type (Responsibility)'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),'KC',UUID(),1)
/
INSERT INTO KRIM_ROLE_RSP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Application Administrator'),(SELECT (MAX(ID)) FROM KRIM_RSP_ID_S),'Y',UUID(),1)
/
DELIMITER ;
