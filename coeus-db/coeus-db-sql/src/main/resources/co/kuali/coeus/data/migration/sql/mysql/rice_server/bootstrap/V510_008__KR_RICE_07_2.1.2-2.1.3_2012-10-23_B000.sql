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
