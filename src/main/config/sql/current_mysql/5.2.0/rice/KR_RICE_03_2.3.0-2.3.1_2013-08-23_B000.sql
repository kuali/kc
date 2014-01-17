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
-- KULRICE-9142: Modify the existing Recall permission to apply to RiceDocument
--

UPDATE krim_perm_attr_data_t SET attr_val='RiceDocument'
WHERE attr_val = '*' AND perm_id =
  (
    SELECT perm_id FROM krim_perm_t WHERE nm='Recall Document' AND nmspc_cd='KR-WKFLW'
  )
/
DELIMITER ;
