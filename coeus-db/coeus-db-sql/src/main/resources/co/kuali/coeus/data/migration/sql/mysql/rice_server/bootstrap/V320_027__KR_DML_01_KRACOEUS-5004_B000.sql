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
-- add a new permission for modifying award report tracking
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), UUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Edit Document' and NMSPC_CD = 'KR-NS'),
'KC-AWARD', 'Modify Award Report Tracking', 'Modify the report tracking records of an award at any time.', 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- associate the new permission with the Award Modifier role.
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Modifier' AND NMSPC_CD = 'KC-AWARD'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Award Report Tracking' AND NMSPC_CD = 'KC-AWARD'), 'Y')
/
INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
-- Create a new role just for maintaining award report tracking
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Maintain Award Report Tracking', 'KC-AWARD', 'Role to maintain award report tracking records.',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default' and NMSPC_CD = 'KUALI'), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
-- associate the new role and permission
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Maintain Award Report Tracking' AND NMSPC_CD = 'KC-AWARD'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Modify Award Report Tracking' AND NMSPC_CD = 'KC-AWARD'), 'Y')
/
DELIMITER ;
