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
INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: Investigators', 'proposalRoleTypeService', 'Y', 'KC-PD')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, 'Investigators', 'KC-PD', 'Proposal Development Investigators Role', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Investigators'), 'Y', NOW())
/
INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: All Proposal Unit Administrators', 'proposalAllUnitAdministratorDerivedRoleTypeService', 'Y', 'KC-PD')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, 'All Unit Administrators', 'KC-PD', 'All Proposal Unit Administrators', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Derived Role: All Proposal Unit Administrators'), 'Y', NOW())
/
INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: All Past Approvers', 'pastApproversDerivedRoleTypeService', 'Y', 'KC-WKFLW')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- create new roles
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, 'All Past Approvers', 'KC-WKFLW', 'All Past Approvers', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role: All Past Approvers'), 'Y', NOW())
/
DELIMITER ;
