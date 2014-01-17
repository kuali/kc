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
-- KULRICE-9034: KR-KRAD - RESULTS_LIMIT parameter should be added and the code should be changed to use it
--

INSERT INTO krcr_cmpnt_t (nmspc_cd, cmpnt_cd, obj_id, ver_nbr, nm, actv_ind)
  VALUES ('KR-KRAD', 'Lookup', uuid(), 1, 'Lookup', 'Y')
/
INSERT INTO KRCR_PARM_T
  (NMSPC_CD, CMPNT_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, VAL, PARM_DESC_TXT, EVAL_OPRTR_CD, APPL_ID)
  VALUES ('KR-KRAD', 'Lookup', 'RESULTS_LIMIT', uuid(), 1, 'CONFG', '200',
          'Maximum number of results returned in a look-up query.', 'A', 'KUALI')
/
DELIMITER ;