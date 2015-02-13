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

-- create new role responsible solely for requisitioner review approval

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'Subaward Requisitioner Review Approver', 'KC-SUBAWARD', 'Subaward requisitioner review approver Role', 
		(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Default'), 'Y', NOW())
/

-- map the requisitioner review approver role to the requisitioner review responsibility
INSERT INTO KRIM_ROLE_RSP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) 
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ID_S), UUID(), '1', (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S) , 
		(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview'), 'Y')
/

-- insert action for the above role-responsibility mapping
INSERT INTO KRIM_ROLE_RSP_ACTN_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) 
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ACTN_ID_S), UUID(), '1', 'A', '1', 'F', '*', (SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ID_S), 'Y')
/


-- delete the approve action mapped to the role-resp mapping of both roles: modify subaward and subaward requisitioner
DELETE FROM KRIM_ROLE_RSP_ACTN_T 
	WHERE ROLE_RSP_ID = 
		(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T 
			WHERE RSP_ID = 
				(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview' AND NMSPC_CD ='KC-SUBAWARD') 
	 		AND ROLE_ID = 
	 			(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Subaward'))
	AND ACTN_TYP_CD = 'A'
/

DELETE FROM KRIM_ROLE_RSP_ACTN_T 
	WHERE ROLE_RSP_ID = 
		(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T 
			WHERE RSP_ID = 
				(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview' AND NMSPC_CD ='KC-SUBAWARD') 
	 		AND ROLE_ID = 
	 			(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Requisitioner'))
	AND ACTN_TYP_CD = 'A'
/


-- now delete the role-resp mapping between both the roles and the requisitioner review responsibility
DELETE FROM KRIM_ROLE_RSP_T 
	WHERE RSP_ID = 
		(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview' AND NMSPC_CD ='KC-SUBAWARD') 
	AND ROLE_ID = 
		(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Subaward')
/

DELETE FROM KRIM_ROLE_RSP_T 
	WHERE RSP_ID = 
		(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'SubAwardRequisitionerReview' AND NMSPC_CD ='KC-SUBAWARD') 
	AND ROLE_ID = 
		(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Requisitioner')
/

-- make the two roles members of the dedicated requisitioner review approver role, so they will transitively regain the review responsibility (and its approve action) removed above
-- we need to do this because KEW seems to require approve action from each seperate role mapped to a route node's responsibility (AND semantics across roles)
-- since we want the OR semantics across all roles, so we are deploying only a single dedicated approver role directly mapped to the approve responsibility (with an action policy of "F")
-- other roles that need to be allowed to see and act on this approve action request should be made members of this approver role. 
INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S),  (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Subaward'), 'R', NOW(), UUID(), 1)
/

INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Requisitioner'), 'R', NOW(), UUID(), 1)
/

DELIMITER ;

