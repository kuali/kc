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
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: Active Committee Member On Iacuc Protocol', 'activeCommitteeMemberOnIacucProtocolDerivedRoleTypeService', 'Y', 'KC-IACUC')
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Active Committee Member On Iacuc Protocol', 'KC-IACUC', 'Role members are derived from active committee members on the protocol.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IACUC' AND NM = 'View IACUC Protocol'),'Y')
/
INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: Protocol Personnel','iacucProtocolPersonnelDerivedRoleTypeService','Y','KC-IACUC')
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'PI', 'KC-IACUC', 'The Protocol Principal Investigator role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'COI', 'KC-IACUC', 'The Protocol Co-Investigator role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'SP', 'KC-IACUC', 'The Protocol Study Personnel role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'CRC', 'KC-IACUC', 'The Protocol Correspondent CRC role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'CA', 'KC-IACUC', 'The Protocol Correspondent Administrator role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_TYP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,OBJ_ID,VER_NBR,NM,SRVC_NM,ACTV_IND,NMSPC_CD)
VALUES( (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), UUID(), 1, 'Derived Role: Protocol Affiliate Type','iacucProtocolAffiliateTypeDerivedRoleTypeService','Y','KC-IACUC')
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Faculty', 'KC-IACUC', 'The Faculty Affiliate role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Non-Faculty', 'KC-IACUC', 'The Non-Faculty Affiliate role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Affiliate', 'KC-IACUC', 'The Protocol Affiliate role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Student Investigator', 'KC-IACUC', 'The Protocol Student Investigator role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Faculty Supervisor', 'KC-IACUC', 'The Protocol Faculty Supervisor role.', (SELECT (MAX(ID)) FROM KRIM_TYP_ID_S), 'Y', NOW())
/
DELIMITER ;
