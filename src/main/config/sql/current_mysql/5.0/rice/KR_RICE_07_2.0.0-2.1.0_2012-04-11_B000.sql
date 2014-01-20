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

-- insert Recall permission for initiators
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM) values ((select PERM_ID from (select (max(cast(PERM_ID as decimal)) + 1) as PERM_ID from KRIM_PERM_T where PERM_ID is not NULL and PERM_ID REGEXP '^[1-9][0-9]*$' and cast(PERM_ID as decimal) < 10000) as tmptable), uuid(), 1, (select PERM_TMPL_ID from KRIM_PERM_TMPL_T where NMSPC_CD = 'KR-WKFLW' and NM = 'Recall Document'), 'KR-WKFLW', 'Recall Document')
/
-- define document type wildcard for permission
INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) values ((select ATTR_DATA_ID from (select (max(cast(ATTR_DATA_ID as decimal)) + 1) as ATTR_DATA_ID from KRIM_PERM_ATTR_DATA_T where ATTR_DATA_ID is not NULL and ATTR_DATA_ID REGEXP '^[1-9][0-9]*$' and cast(ATTR_DATA_ID as decimal) < 10000) as tmptable), uuid(), 1, (select PERM_ID from KRIM_PERM_T where NMSPC_CD = 'KR-WKFLW' and NM='Recall Document'), (select KIM_TYP_ID from KRIM_PERM_TMPL_T where NMSPC_CD = 'KR-WKFLW' and NM = 'Recall Document'), (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NMSPC_CD = 'KR-WKFLW' and NM = 'documentTypeName'), '*')
/
-- associate Recall permission with initiator derived role
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) values ((select ROLE_PERM_ID from (select (max(cast(ROLE_PERM_ID as decimal)) + 1) as ROLE_PERM_ID from KRIM_ROLE_PERM_T where ROLE_PERM_ID is not NULL and ROLE_PERM_ID REGEXP '^[1-9][0-9]*$' and cast(ROLE_PERM_ID as decimal) < 10000) as tmptable), uuid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Initiator' and NMSPC_CD = 'KR-WKFLW'), (select PERM_ID from KRIM_PERM_T where NMSPC_CD = 'KR-WKFLW' and NM='Recall Document'), 'Y')
/
DELIMITER ;
