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

-- TEMP CORRECTION FOR ISSUE COMING FROM KR_DML_01_KRACOEUS-5004_B000.sql
DELETE FROM KRIM_ROLE_PERM_T WHERE PERM_ID IS NULL AND ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Modifier' AND NMSPC_CD = 'KC-AWARD')
/

-- KULRICE-6481
ALTER TABLE KRIM_ROLE_PERM_T MODIFY ROLE_ID varchar(40) NOT NULL
/

ALTER TABLE KRIM_ROLE_PERM_T MODIFY PERM_ID varchar(40) NOT NULL
/
DELIMITER ;
