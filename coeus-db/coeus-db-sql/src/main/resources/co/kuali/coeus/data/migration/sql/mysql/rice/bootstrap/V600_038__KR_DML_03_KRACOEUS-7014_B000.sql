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
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES (concat('KC',(SELECT (MAX(ID)) FROM KRIM_PERM_ID_S)), UUID(), 1,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM='Perform Document Action'),
'KC-PD', 'NOTIFY_PROPOSAL_PERSONS', 'Allows user to send person ceritification notifications', 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (concat('KC',(SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)), UUID(), 1,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator' AND NMSPC_CD = 'KC-PD'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'NOTIFY_PROPOSAL_PERSONS' AND NMSPC_CD = 'KC-PD'), 'Y')
/
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (concat('KC',(SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)), UUID(), 1,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator Document Level' AND NMSPC_CD = 'KC-PD'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'NOTIFY_PROPOSAL_PERSONS' AND NMSPC_CD = 'KC-PD'), 'Y')
/
INSERT INTO KRCR_PARM_T (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
VALUES ('KC-PD', 'Document', 'notifyAggregatorWhenAllCertificationsComplete', UUID(), 1, 'CONFG', 'Y', 'Determines if the aggregator gets a notification when all certifications are complete', 'A', 'KC')
/
DELIMITER ;
