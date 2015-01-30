--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

DELIMITER /

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
