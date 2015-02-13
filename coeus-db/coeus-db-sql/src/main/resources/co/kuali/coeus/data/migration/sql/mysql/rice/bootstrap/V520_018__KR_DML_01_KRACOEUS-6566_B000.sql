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

-- Create new permission to maitain person signature
-- Create new role and assign above permission to that role
-- Default - quickstart has permission to maintain signature

INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), UUID(), 1, 10, 'KC-SYS', 'Maintain Person Signature', 'Permission for managing the person signature maintenance document', 'Y')
/

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Modify Person Signature', 'KC-SYS', 'Role for maintaining person signature', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'View Edit Mode'), 'Y', NOW())
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Person Signature' AND NMSPC_CD = 'KC-SYS'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Person Signature' AND NMSPC_CD = 'KC-SYS'), 'Y')
/


DELIMITER ;
