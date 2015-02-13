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
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
-- iacuc protocol approver
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
VALUES ('Y','This role exists primarily to grant implicit Cancel permission to IACUC Protocol Aggregators and Admins',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'IRBApprover-Nested'),NOW(),'KC-IACUC',UUID(),(SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S),'IACUC ProtocolApprover',1)
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
-- iacuc protocol unassigned
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
VALUES ('Y','IACUC Protocol Unassigned - no permissions',(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Unit'),NOW(),'KC-IACUC',UUID(),(SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S),'IACUC Protocol Unassigned',1)
/
DELIMITER ;
