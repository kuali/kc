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
TRUNCATE TABLE KRIM_TYP_T
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','1','Default','KUALI','5ADF18B6D4827954E0404F8189D85002',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','10','Namespace or Component','KR-NS','5ADF18B6D4D77954E0404F8189D85002','namespaceOrComponentPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','11','Component Field','KR-NS','5ADF18B6D4DC7954E0404F8189D85002','componentFieldPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','12','Namespace or Action','KR-NS','5ADF18B6D4E37954E0404F8189D85002','namespaceOrActionPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','13','Button','KR-NS','5ADF18B6D4E77954E0404F8189D85002','buttonPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','14','Edit Mode & Document Type','KR-NS','5ADF18B6D4EA7954E0404F8189D85002','documentTypeAndEditModePermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','15','Batch Feed or Job','KR-NS','5ADF18B6D4ED7954E0404F8189D85002','batchFeedOrJobPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','16','Parameter','KR-NS','5ADF18B6D4F27954E0404F8189D85002','parameterPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','17','Campus','KR-NS','5ADF18B6D4F77954E0404F8189D85002','campusRoleService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','18','Role','KR-IDM','5ADF18B6D4F97954E0404F8189D85002','rolePermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','19','Permission','KR-IDM','5ADF18B6D4FD7954E0404F8189D85002','permissionPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','2','Derived Role: Principal','KR-IDM','5ADF18B6D4837954E0404F8189D85002','activePrincipalRoleTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','20','Responsibility','KR-IDM','5ADF18B6D5017954E0404F8189D85002','responsibilityPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','21','Group','KR-IDM','5ADF18B6D5057954E0404F8189D85002','groupPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','3','Document Type (Permission)','KR-SYS','5ADF18B6D4AC7954E0404F8189D85002','documentTypePermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','4','Action Request Type','KR-WKFLW','5ADF18B6D4B57954E0404F8189D85002','actionRequestTypePermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','42','Derived Role: Action Request','KR-WKFLW','5ADF18B6D53B7954E0404F8189D85002','actionRequestDerivedRoleTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','43','Derived Role: Route Log','KR-WKFLW','5ADF18B6D53C7954E0404F8189D85002','routeLogDerivedRoleTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','45','Derived Role: Permission (Edit Document)','KR-NS','5B6013B3AD131A9CE0404F8189D87094','documentEditorRoleTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','5','Ad Hoc Review','KR-WKFLW','5ADF18B6D4B87954E0404F8189D85002','adhocReviewPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','52','Document Type, Routing Node & Field(s)','KR-SYS','5C997D14EAC2FE40E0404F8189D87DC5','documentTypeAndNodeAndFieldsPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','54','Document Type (Responsibility)','KR-KEW','60432A73A6A29F65E0404F8189D86AA4','documentTypeResponsibilityTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','56','Document Type & Existing Records Only','KR-NS','603B735FA6B9FE21E0404F8189D8083B','documentTypeAndExistingRecordsOnlyPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','57','Component Section','KR-NS','603B735FA6BDFE21E0404F8189D8083B','componentSectionPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','59','Document Type & Relationship to Note Author','KR-NS','606763510FBB96D3E0404F8189D857A2','documentTypeAndRelationshipToNoteAuthorPermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','60','Derived Role: Permission (Open Document)','KR-NS','606763510FBC96D3E0404F8189D857A2','documentOpenerRoleTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','66','Derived Role: Permission (Initiate Document)','KR-SYS','67F145466E8A9160E0404F8189D86771','documentInitiatorRoleTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','67','Namespace','KR-NS','67F145466E8F9160E0404F8189D86771','namespacePermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','7','Document Type, Routing Node & Action Information','KR-WKFLW','5ADF18B6D4C07954E0404F8189D85002','reviewResponsibilityTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','8','Document Type & Routing Node or State','KR-SYS','5ADF18B6D4C67954E0404F8189D85002','documentTypeAndNodeOrStatePermissionTypeService',1)
/
INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y','9','Document Type & Attachment Type','KR-NS','5ADF18B6D4CD7954E0404F8189D85002','documentTypeAndAttachmentTypePermissionTypeService',1)
/
delimiter ;
