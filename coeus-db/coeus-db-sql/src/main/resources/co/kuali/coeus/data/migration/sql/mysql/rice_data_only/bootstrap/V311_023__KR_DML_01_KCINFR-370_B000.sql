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
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT) VALUES (last_insert_id(), UUID(), 1,'Modify All Dev Proposals', 'KC-UNT', 'Modify All Dev Proposals',(SELECT kim_typ_id FROM krim_typ_t WHERE nm = 'Unit'), 'Y', NOW())
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='Modify ProposalDevelopmentDocument'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='View Proposal'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='Modify Narrative'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='View Narratives'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='Modify Budget'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='View Budget'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='Modify ProposalPermissions'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='Print Proposal'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) VALUES (last_insert_id(), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM ='Modify All Dev Proposals'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM ='Modify Proposal Rates'), 'Y')
/

DELIMITER ;
