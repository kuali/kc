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

DELETE FROM KRIM_ROLE_RSP_T WHERE ROLE_ID IN (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC ProtocolApprover') AND
RSP_ID IN (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview')
/


INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) VALUES 
(KRIM_ROLE_RSP_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUCApprover') , 
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview'), 'Y')
/

INSERT INTO krim_role_rsp_actn_t (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) values 
(KRIM_ROLE_RSP_ACTN_ID_BS_S.NEXTVAL, sys_guid(), '1', 'A', '1', 'F', '*', (SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 
'IACUCApprover') AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview')),'Y')
/



UPDATE KRIM_RSP_ATTR_DATA_T 
SET ATTR_VAL = 'false'
WHERE RSP_ID IN (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview') AND 
KIM_TYP_ID IN (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information') AND KIM_ATTR_DEFN_ID IN (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'required')
/

