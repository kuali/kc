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

INSERT INTO KRIM_TYP_ID_S VALUES (NULL)
/

INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES (CONCAT('KC', (SELECT MAX(ID) FROM KRIM_TYP_ID_S)), UUID(), 1, 'Derived Role: IACUC Correspondent', 'iacucCorrespondentDerivedRoleTypeService', 'Y', 'KC-IACUC')
/

INSERT INTO KRIM_ROLE_ID_S VALUES (NULL)
/

INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (CONCAT('KC', (SELECT MAX(ID) FROM KRIM_ROLE_ID_S)), UUID(), 1, 'IACUC Unit Correspondent', 'KC-IACUC', 'The IACUC Unit Correspondent role', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: IACUC Correspondent'), 'Y', NOW())
/

INSERT INTO KRIM_ROLE_ID_S VALUES (NULL)
/

INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
VALUES (CONCAT('KC', (SELECT MAX(ID) FROM KRIM_ROLE_ID_S)), UUID(), 1, 'IACUC Organization Correspondent', 'KC-IACUC', 'The IACUC Organization Correspondent role', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: IACUC Correspondent'), 'Y', NOW())
/

DELIMITER ;
