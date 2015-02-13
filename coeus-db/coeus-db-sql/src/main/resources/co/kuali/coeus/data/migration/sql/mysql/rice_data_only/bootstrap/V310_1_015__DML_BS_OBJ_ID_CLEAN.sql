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
--
--

UPDATE KRIM_TYP_T SET OBJ_ID = UUID() WHERE OBJ_ID = 'A8488117C37040B2A56531BCBB7DCD9B'
/
UPDATE KRIM_PERM_T SET OBJ_ID = UUID() WHERE OBJ_ID = '3F0FE9D4-83BC-7CC6-5292-49E6F23FA6F0'
/
UPDATE KRIM_PERM_ATTR_DATA_T SET OBJ_ID = UUID() WHERE OBJ_ID = '77151AD4B1F69985E0404F8189D80E8B'
/
UPDATE KRIM_ROLE_MBR_ATTR_DATA_T SET OBJ_ID = UUID() WHERE OBJ_ID = '5D0BD1CB88BF4EF794A08CA6E892F500'
/
UPDATE KRIM_ROLE_PERM_T SET OBJ_ID = UUID() WHERE OBJ_ID = '1C0D53B5D8EC4928A672DA52C2DB75F4'
/
UPDATE KRIM_ROLE_PERM_T SET OBJ_ID = UUID() WHERE OBJ_ID = 'D28CF8A4-0951-DE4E-4350-D29F8FEFE053'
/
UPDATE KRIM_GRP_MBR_T SET OBJ_ID = UUID() WHERE OBJ_ID = '77151AD4B1E59985E0404F8189D80E8B'
/
UPDATE KRCR_PARM_T SET OBJ_ID = UUID() WHERE OBJ_ID = 'd6173017-d9d4-4309-b69c-75c111efb04b'
/
COMMIT
/
DELIMITER ;
