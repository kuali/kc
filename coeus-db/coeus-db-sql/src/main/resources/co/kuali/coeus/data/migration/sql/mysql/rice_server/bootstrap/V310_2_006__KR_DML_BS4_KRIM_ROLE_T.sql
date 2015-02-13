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

-- create new role: Time And Money Viewer
INSERT INTO KRIM_ROLE_ID_S VALUES (NULL);
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ((SELECT MAX(ID) FROM KRIM_ROLE_ID_S), uuid(), 1, 'Time And Money Viewer', 'KC-T', 'View Time And Money Document', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

-- create new role: Time And Money Modifier
INSERT INTO KRIM_ROLE_ID_S VALUES (NULL);
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ((SELECT MAX(ID) FROM KRIM_ROLE_ID_S), uuid(), 1, 'Time And Money Modifier', 'KC-T', 'Modify Time And Money Document', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

COMMIT;
