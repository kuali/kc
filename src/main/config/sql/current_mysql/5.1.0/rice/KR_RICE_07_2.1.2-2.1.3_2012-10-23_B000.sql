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
--    KULRICE-8302 - Backdoor Restriction via Permission
--

INSERT INTO KRIM_TYP_T(KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
VALUES('KR1001', uuid(), 1, 'Backdoor Restriction', 'backdoorRestrictionPermissionTypeService','Y','KR-SYS')
/
INSERT INTO KRIM_ATTR_DEFN_T(KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
VALUES('KR1000', uuid(), 1,'appCode','Application Code', 'Y', 'KR-SYS', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/
INSERT INTO KRIM_TYP_ATTR_T(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('KR1003', uuid(),1,'A',
    (select KIM_TYP_ID from KRIM_TYP_T where nm = 'Backdoor Restriction'),
    (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where nm = 'appCode'),'Y')
/
INSERT INTO KRIM_PERM_TMPL_T(PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
VALUES('KR1003', uuid(), 1, 'KR-SYS', 'Backdoor Restriction', 'Backdoor Restriction',
      (select KIM_TYP_ID from KRIM_TYP_T where nm = 'Backdoor Restriction'),'Y')
/
DELIMITER ;
