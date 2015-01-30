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
INSERT INTO KRIM_GRP_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID,VER_NBR,OBJ_ID,GRP_ID,MBR_ID,MBR_TYP_CD,ACTV_FRM_DT,ACTV_TO_DT,LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_GRP_MBR_ID_S),1,UUID(),(SELECT GRP_ID FROM KRIM_GRP_T WHERE GRP_NM='KcAdmin' AND NMSPC_CD='KC-WKFLW'),(SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM='coiadmin'),'P',null,null,NOW())
/
DELIMITER ;
