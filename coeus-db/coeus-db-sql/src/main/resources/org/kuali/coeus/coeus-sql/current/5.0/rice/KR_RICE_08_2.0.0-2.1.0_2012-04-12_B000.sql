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

-- create a Kim Type wired to the documentRouterRoleTypeService permission-derived role service
INSERT INTO KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD) values ((select (max(to_number(KIM_TYP_ID)) + 1) from KRIM_TYP_T where KIM_TYP_ID is not NULL and REGEXP_LIKE(KIM_TYP_ID, '^[1-9][0-9]*$') and to_number(KIM_TYP_ID) < 10000), sys_guid(), 1, 'Derived Role: Permission (Route Document)', 'documentRouterRoleTypeService', 'Y', 'KR-WKFLW')
/
-- define the Route Document derived role
INSERT INTO KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND) values ((select (max(to_number(ROLE_ID)) + 1) from KRIM_ROLE_T where ROLE_ID is not NULL and REGEXP_LIKE(ROLE_ID, '^[1-9][0-9]*$') and to_number(ROLE_ID) < 10000), sys_guid(), 1, 'Document Router', 'KR-WKFLW', 'This role derives its members from users with the Route Document permission for a given document type.', (select KIM_TYP_ID from KRIM_TYP_T where NM = 'Derived Role: Permission (Route Document)' and NMSPC_CD = 'KR-WKFLW'), 'Y')
/
