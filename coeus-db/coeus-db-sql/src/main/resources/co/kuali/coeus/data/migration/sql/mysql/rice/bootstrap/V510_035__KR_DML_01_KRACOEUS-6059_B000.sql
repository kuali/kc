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

-- Add the COI Approver role and assign various permissions

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), UUID(), 1, 'COI Approver', 'KC-COIDISCLOSURE', 'Role similar to COI Reviewer with some added limited admin functionality', 
		(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'), 'Y', NOW())
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Coi Disclosure Notes'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Coi Disclosure Attachments'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Coi Disclosure Notes'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Coi Disclosure Attachments'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Coi Disclosure'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Coi Viewer'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain Coi Reviewers'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Approve Coi Disclosure'), 'Y', UUID(), 1)
/

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Perform Coi Disclosure Actions'), 'Y', UUID(), 1)
/


-- Map the Coi Approver role to the CoiReview responsibility
INSERT INTO KRIM_ROLE_RSP_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) 
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ID_S), UUID(), '1', (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S) , (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview'), 'Y')
/

-- Insert action for the above role-responsibility mapping
INSERT INTO KRIM_ROLE_RSP_ACTN_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) 
	VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ACTN_ID_S), UUID(), '1', 'A', '1', 'F', '*', (SELECT (MAX(ID)) FROM KRIM_ROLE_RSP_ID_S), 'Y')
/


-- delete the approve action mapped to the coi admin and coi review role-resp mapping
DELETE FROM KRIM_ROLE_RSP_ACTN_T 
	WHERE ROLE_RSP_ID = 
		(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T 
			WHERE RSP_ID = 
				(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview' AND NMSPC_CD ='KC-WKFLW') 
	 		AND ROLE_ID = 
	 			(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Administrator'))
	AND ACTN_TYP_CD = 'A'
/

-- delete the coi admin and coi review role-resp mapping
DELETE FROM KRIM_ROLE_RSP_T 
	WHERE RSP_ID = 
		(SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'COIReview' AND NMSPC_CD ='KC-WKFLW') 
	AND ROLE_ID = 
		(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Administrator')
/

-- make the coi admin a member of the coi approver role, so it will transitively regain the coi review responsibility (and its approve action) removed above
-- we need to do this because KEW seems to require approve action from each seperate role mapped to a route node's responsibility (AND semantics across roles)
-- since we want the OR semantics across all roles, so deploying only a single approver role directly mapped to the approve responsibility (with an action policy of "F")
-- other roles that need to be allowed to see (and act on) this approve action request can simply become members of this approver role. 
INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'COI Administrator'), 'R', NOW(), UUID(), 1)
/

INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID,VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'unitNumber'), '000001', UUID(), 1)
/

INSERT INTO KRIM_ATTR_DATA_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID, VER_NBR) 
    VALUES ((SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_S), (SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'subunits'), 'Y', UUID(), 1)
/

DELIMITER ;
