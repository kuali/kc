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

-- create the 'assign to IRB committee' permission
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID, NMSPC_CD, NM, DESC_TXT, PERM_TMPL_ID, ACTV_IND, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), 'KC-PROTOCOL', 'Assign IRB Committee', 
			'This permission allows the assignment (of a submission) to an IRB committee, and is meant to be granted to unit-hierarchy type admin roles for IRB protocols.',
			(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'), 'Y', 1, UUID())
/

		
-- grant it to the irb admin role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, 
			(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IRB Administrator' AND NMSPC_CD='KC-UNT'),
			(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), 'Y')
/

-- create the 'assign to IACUC committee' permission
INSERT INTO KRIM_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID, NMSPC_CD, NM, DESC_TXT, PERM_TMPL_ID, ACTV_IND, VER_NBR, OBJ_ID)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), 'KC-IACUC', 'Assign IACUC Committee', 
			'This permission allows the assignment (of a submission) to an IACUC committee, and is meant to be granted to unit-hierarchy type admin roles for IACUC protocols.',
			(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'), 'Y', 1, UUID())
/


-- grant it to the iacuc admin role
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), UUID(), 1, 
			(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator' AND NMSPC_CD='KC-UNT'),
			(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S), 'Y')
/

DELIMITER ;
