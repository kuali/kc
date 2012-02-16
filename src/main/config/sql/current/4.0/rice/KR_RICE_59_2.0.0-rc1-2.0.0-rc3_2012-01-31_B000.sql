--
-- Copyright 2005-2012 The Kuali Foundation
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

-- TEMP CORRECTION FOR ISSUE COMING FROM KR_DML_01_KRACOEUS-5004_B000.sql
DELETE FROM KRIM_ROLE_PERM_T WHERE PERM_ID IS NULL AND ROLE_ID = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Modifier' AND NMSPC_CD = 'KC-AWARD')
/

-- KULRICE-6481
ALTER TABLE KRIM_ROLE_PERM_T MODIFY (ROLE_ID NOT NULL)
/
ALTER TABLE KRIM_ROLE_PERM_T MODIFY (PERM_ID NOT NULL)
/