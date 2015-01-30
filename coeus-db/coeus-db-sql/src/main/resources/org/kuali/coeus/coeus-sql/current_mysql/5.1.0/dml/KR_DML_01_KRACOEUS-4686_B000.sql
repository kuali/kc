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
INSERT INTO KRIM_PERM_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
VALUES (concat('KC',(SELECT (MAX(ID)) FROM KRIM_PERM_ID_BS_S)), UUID(), 1,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Recall Document'),
'KC-PD', 'Recall Document', 'Recall Proposal Development Document', 'Y')
/
INSERT INTO KRIM_ATTR_DATA_ID_BS_S VALUES(NULL)
/
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES (concat('KC',(SELECT (MAX(ID)) FROM KRIM_ATTR_DATA_ID_BS_S)), UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Recall Document' and NMSPC_CD = 'KC-PD'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM LIKE 'Document Type % Routing Node or State'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'),
'ProposalDevelopmentDocument')
/
INSERT INTO KRIM_ROLE_PERM_ID_BS_S VALUES(NULL)
/

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (concat('KC',(SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_BS_S)), UUID(), 1,
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Aggregator' AND NMSPC_CD = 'KC-PD'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Recall Document' AND NMSPC_CD = 'KC-PD'), 'Y')
/

DELIMITER ;
