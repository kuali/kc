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

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES(NULL)
/

INSERT INTO krim_role_perm_t (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRIM_ROLE_PERM_ID_S)), UUID(), 1, (SELECT ROLE_ID from krim_role_t where ROLE_NM = 'Investigators' and NMSPC_CD = 'KC-PD'), (SELECT PERM_ID from krim_perm_t where NM = 'View Proposal' and NMSPC_CD = 'KC-PD'), 'Y')
/

DELIMITER ;
