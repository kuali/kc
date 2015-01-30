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
-- KULRICE-10251: Maintain KRMS Agenda permission has confusing and unused permission detail
--

DELETE FROM krim_perm_attr_data_t
WHERE attr_val = 'KRMS_TEST' AND perm_id =
  (
    SELECT
      perm_id
    FROM krim_perm_t
    WHERE nm = 'Maintain KRMS Agenda' AND nmspc_cd = 'KR-RULE-TEST'
  )
/
DELIMITER ;

