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
--     KULRICE-8300	& KULRICE-7799
--

-- NOTE NOTE -  This is the first time that the master database will have KRxxx as the IDs on some of it's tables.
-- This SQL accounts for that and should be error free.

INSERT INTO KRIM_TYP_T(KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
  VALUES('KR1000', uuid(), 1, 'Document Type, Route Node, and Route Status',
    'documentTypeAndNodeAndRouteStatusPermissionTypeService', 'Y', 'KR-SYS')
/
INSERT INTO KRIM_TYP_ATTR_T(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('KR1000',  uuid(), 1, 'a',
  (select KIM_TYP_ID from KRIM_TYP_T where NM = 'Document Type, Route Node, and Route Status' and NMSPC_CD = 'KR-SYS'),
  (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM = 'documentTypeName' and NMSPC_CD = 'KR-WKFLW'), 'Y')
/
INSERT INTO KRIM_TYP_ATTR_T(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('KR1001', uuid(), 1, 'b',
  (select KIM_TYP_ID from KRIM_TYP_T where NM = 'Document Type, Route Node, and Route Status' and NMSPC_CD = 'KR-SYS'),
  (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM = 'routeNodeName' and NMSPC_CD = 'KR-WKFLW'), 'Y')
/
INSERT INTO KRIM_TYP_ATTR_T(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('KR1002', uuid(), 1, 'c',
  (select KIM_TYP_ID from KRIM_TYP_T where NM = 'Document Type, Route Node, and Route Status' and NMSPC_CD = 'KR-SYS'),
  (select KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM = 'routeStatusCode' and NMSPC_CD = 'KR-WKFLW'), 'Y')
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('KR1000', 'Y',
  (SELECT KIM_TYP_ID FROM KRIM_TYP_T where NM = 'Document Type, Route Node, and Route Status' and SRVC_NM = 'documentTypeAndNodeAndRouteStatusPermissionTypeService'),
  'Super User Approve Single Action Request', 'KR-WKFLW', uuid(), 1)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('KR1001', 'Y',
  (SELECT KIM_TYP_ID FROM KRIM_TYP_T where NM = 'Document Type, Route Node, and Route Status' and SRVC_NM = 'documentTypeAndNodeAndRouteStatusPermissionTypeService'),
  'Super User Approve Document', 'KR-WKFLW', uuid(), 1)
/
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('KR1002', 'Y',
  (SELECT KIM_TYP_ID FROM KRIM_TYP_T where NM = 'Document Type, Route Node, and Route Status' and SRVC_NM = 'documentTypeAndNodeAndRouteStatusPermissionTypeService'),
  'Super User Disapprove Document', 'KR-WKFLW', uuid(), 1)
/
DELIMITER ;
