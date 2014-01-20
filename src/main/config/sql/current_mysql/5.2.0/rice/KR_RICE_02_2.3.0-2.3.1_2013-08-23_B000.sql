DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--


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

