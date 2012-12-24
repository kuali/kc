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

--
--    KULRICE-8415 - Large roles cannot be opened or edited in KIM
--

alter table KRIM_TYP_ATTR_T ADD constraint KRIM_TYP_ATTR_TC1 unique (SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
/


--
--    KULRICE-4559 - Add Type as a qualifying value for Assign Group permissions
--


INSERT INTO KRIM_ATTR_DEFN_T(KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
  VALUES('KR1001', SYS_GUID(), 1, 'kimTypeName', 'Kim Type Name', 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/

INSERT INTO KRIM_TYP_ATTR_T(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('KR1004',  SYS_GUID(), 1, 'b',
  (SELECT KIM_TYP_ID from KRIM_TYP_T where NM = 'Group' and SRVC_NM = 'groupPermissionTypeService'),
  (SELECT KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM = 'kimTypeName' and NMSPC_CD = 'KR-IDM'), 'Y')
/