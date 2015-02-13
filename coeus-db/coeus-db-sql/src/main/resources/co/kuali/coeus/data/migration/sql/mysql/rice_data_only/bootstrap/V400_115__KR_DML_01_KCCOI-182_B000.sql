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
INSERT INTO KRIM_ROLE_MBR_ID_S VALUES(NULL)
/
-- Insert the role-member data to create the association between the coi reporter role and the derived user role whose members are all principals. Thus all principals in the system effectively become coi reporters.
INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, LAST_UPDT_DT, OBJ_ID, VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KRIM_ROLE_MBR_ID_S), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-COIDISCLOSURE' AND ROLE_NM = 'COI Reporter'), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KUALI' AND ROLE_NM = 'User'), 'R', NOW(), UUID(), 1)
/
DELIMITER ;
