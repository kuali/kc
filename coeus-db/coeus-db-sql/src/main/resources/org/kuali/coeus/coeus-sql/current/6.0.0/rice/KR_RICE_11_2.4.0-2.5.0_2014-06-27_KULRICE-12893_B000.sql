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

DELETE FROM KRIM_ROLE_PERM_T
WHERE ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-RULE' AND ROLE_NM = 'Kuali Rules Management System Administrator')
AND PERM_ID = (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-RULE-TEST' AND NM = 'Maintain KRMS Agenda')
/

DELETE FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-RULE-TEST' AND NM = 'Maintain KRMS Agenda'
/
