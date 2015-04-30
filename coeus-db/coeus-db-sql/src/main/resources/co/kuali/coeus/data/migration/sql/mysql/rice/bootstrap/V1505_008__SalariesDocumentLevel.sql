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

INSERT INTO KRIM_ROLE_ID_S VALUES(NULL);

INSERT INTO KRIM_ROLE_T (ROLE_ID, ROLE_NM, NMSPC_CD, DESC_TXT, ACTV_IND, KIM_TYP_ID, OBJ_ID, VER_NBR, LAST_UPDT_DT)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_ID_S)), 'View Institutionally Maintained Salaries Document Level','KC-PD','View Institutionally Maintained Salaries','Y', (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Derived Role: Document Access' AND NMSPC_CD = 'KC-SYS'), UUID(), 1, NOW());

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL);

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'View Institutionally Maintained Salaries Document Level' AND NMSPC_CD = 'KC-PD'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'VIEW_INSTITUTIONAL_SALARIES' AND NMSPC_CD = 'KC-PD'), 'Y');

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL);

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)), UUID(), 1, (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'View Institutionally Maintained Salaries Document Level' AND NMSPC_CD = 'KC-PD'), (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Personnel Salaries' AND NMSPC_CD = 'KC-B'), 'Y');
