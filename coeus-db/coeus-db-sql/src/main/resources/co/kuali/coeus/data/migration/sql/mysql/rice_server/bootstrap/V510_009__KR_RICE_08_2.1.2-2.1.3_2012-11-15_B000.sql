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
--    KULRICE-8415 - Large roles cannot be opened or edited in KIM
--

alter table KRIM_TYP_ATTR_T ADD constraint KRIM_TYP_ATTR_TC1 unique (SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
/
--
--    KULRICE-4559 - Add Type as a qualifying value for Assign Group permissions
--

INSERT INTO KRIM_ATTR_DEFN_T(KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
  VALUES('KR1001',uuid(), 1, 'kimTypeName', 'Kim Type Name', 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes')
/
INSERT INTO KRIM_TYP_ATTR_T(KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
  VALUES('KR1004',  uuid(), 1, 'b',
  (SELECT KIM_TYP_ID from KRIM_TYP_T where NM = 'Group' and SRVC_NM = 'groupPermissionTypeService'),
  (SELECT KIM_ATTR_DEFN_ID from KRIM_ATTR_DEFN_T where NM = 'kimTypeName' and NMSPC_CD = 'KR-IDM'), 'Y')
/
DELIMITER ;
  
