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

INSERT INTO KRCR_NMSPC_T (APPL_ID, NMSPC_CD, NM, ACTV_IND, OBJ_ID, VER_NBR)
  VALUES('RICE', 'KR-LOC', 'Kuali Location', 'Y', SYS_GUID(), 1)
/

UPDATE KRCR_PARM_T SET NMSPC_CD = 'KR-LOC' WHERE CMPNT_CD = 'All' AND PARM_NM = 'DEFAULT_COUNTRY'
/
