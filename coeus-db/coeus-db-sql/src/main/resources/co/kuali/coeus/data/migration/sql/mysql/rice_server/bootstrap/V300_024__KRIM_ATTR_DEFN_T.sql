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

delimiter /
TRUNCATE TABLE KRIM_ATTR_DEFN_T
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','1','beanName','KR-SYS','5ADF18B6D4887954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','10','editMode','KR-NS','5ADF18B6D4917954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','11','parameterName','KR-NS','5ADF18B6D4927954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','12','campusCode','KR-NS','5ADF18B6D4937954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','13','documentTypeName','KR-WKFLW','5ADF18B6D4947954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','14','actionRequestCd','KR-WKFLW','5ADF18B6D4957954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','15','routeStatusCode','KR-WKFLW','5ADF18B6D4967954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','16','routeNodeName','KR-WKFLW','5ADF18B6D4977954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','18','roleName','KR-IDM','5ADF18B6D4997954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','19','permissionName','KR-IDM','5ADF18B6D49A7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','2','actionClass','KR-SYS','5ADF18B6D4897954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','20','responsibilityName','KR-IDM','5ADF18B6D49B7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','21','groupName','KR-IDM','5ADF18B6D49C7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','3','buttonName','KR-SYS','5ADF18B6D48A7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','4','namespaceCode','KR-NS','5ADF18B6D48B7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','40','required','KR-WKFLW','5C4970B2B2DF8277E0404F8189D80B30',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','41','actionDetailsAtRoleMemberLevel','KR-WKFLW','5C4970B2B2E08277E0404F8189D80B30',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','42','documentNumber','KR-NS','5C7D997640635002E0404F8189D86F11',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','44','sectionId','KR-NS','603B735FA6BCFE21E0404F8189D8083B',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','46','qualifierResolverProvidedIdentifier','KR-IDM','69FA55ACC2EE2598E0404F8189D86880',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','5','componentName','KR-NS','5ADF18B6D48C7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','6','propertyName','KR-NS','5ADF18B6D48D7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','7','existingRecordsOnly','KR-NS','603B735FA6BAFE21E0404F8189D8083B',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','8','createdBySelfOnly','KR-NS','5ADF18B6D48F7954E0404F8189D85002',1)
/
INSERT INTO KRIM_ATTR_DEFN_T (ACTV_IND,CMPNT_NM,KIM_ATTR_DEFN_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','org.kuali.rice.kim.bo.impl.KimAttributes','9','attachmentTypeCode','KR-NS','5ADF18B6D4907954E0404F8189D85002',1)
/
delimiter ;
