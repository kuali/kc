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

INSERT INTO KRIM_GRP_ID_S VALUES (null)
/
INSERT INTO KRIM_GRP_T (GRP_ID,KIM_TYP_ID,NMSPC_CD,GRP_NM,GRP_DESC,ACTV_IND,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_GRP_ID_S),(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),'KC-WKFLW','IACUCAdmin','IACUC Administrator','Y',NOW(),UUID(),1)
/

INSERT INTO KRIM_GRP_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_GRP_MBR_ID_S),(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'IACUCAdmin'),(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'),'P',NOW(),UUID(),1)
/
DELIMITER ;
