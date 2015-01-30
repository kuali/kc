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

TRUNCATE TABLE KRIM_ROLE_T DROP STORAGE
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from the users in the Principal table. This role gives users high-level permissions to interact with RICE documents and to login to KUALI.','2',TO_DATE( '20081104143710', 'YYYYMMDDHH24MISS' ),'KUALI','5ADF18B6D4847954E0404F8189D85002','1','User',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from users with that have received an action request for a given document.','42',TO_DATE( '20081114141017', 'YYYYMMDDHH24MISS' ),'KR-WKFLW','5BABFACC4F62A8EEE0404F8189D8770F','59','Approve Request Recipient',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from the initiator listed within the route log of a given document.','43',TO_DATE( '20081114141017', 'YYYYMMDDHH24MISS' ),'KR-WKFLW','5BABFACC4F63A8EEE0404F8189D8770F','60','Initiator',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from the initiator and action request recipients listed within the route log of a given document.','43',TO_DATE( '20081114141017', 'YYYYMMDDHH24MISS' ),'KR-WKFLW','5BABFACC4F64A8EEE0404F8189D8770F','61','Initiator or Reviewer',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role can take superuser actions and blanket approve RICE documents as well as being able to modify and assign permissions, responsibilities and roles belonging to the KR namespaces.','1',TO_DATE( '20081108115522', 'YYYYMMDDHH24MISS' ),'KR-SYS','5B31640F0105ADF1E0404F8189D84647','63','Technical Administrator',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from users with the Edit Document permission for a given document type.,','45',TO_DATE( '20081114141017', 'YYYYMMDDHH24MISS' ),'KR-NS','5BABFACC4F61A8EEE0404F8189D8770F','66','Document Editor',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from the user who took the Complete action on a given document.','43',TO_DATE( '20081114141017', 'YYYYMMDDHH24MISS' ),'KR-WKFLW','5BABFACC4F65A8EEE0404F8189D8770F','67','Router',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from users with the Open Document permission for a given document type.,','60',TO_DATE( '20090113192616', 'YYYYMMDDHH24MISS' ),'KR-NS','606763510FBF96D3E0404F8189D857A2','83','Document Opener',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from users with an acknowledge action request in the route log of a given document.','42',TO_DATE( '20090121130202', 'YYYYMMDDHH24MISS' ),'KR-WKFLW','6102F3FA08CE45CFE0404F8189D8317E','88','Acknowledge Request Recipient',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from users with an FYI action request in the route log of a given document.','42',TO_DATE( '20090121130203', 'YYYYMMDDHH24MISS' ),'KR-WKFLW','6102F3FA08CF45CFE0404F8189D8317E','89','FYI Request Recipient',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role represents the KR System User, that is the user ID RICE uses when it takes programmed actions.','1',TO_DATE( '20090806170249', 'YYYYMMDDHH24MISS' ),'KR-SYS','61815E6C62D0B647E0404F8189D873B3','90','System User',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from users with the Initiate Document permission for a given document type.','66',TO_DATE( '20090806170249', 'YYYYMMDDHH24MISS' ),'KR-SYS','67F145466E8B9160E0404F8189D86771','95','Document Initiator',1)
/
INSERT INTO KRIM_ROLE_T (ACTV_IND,DESC_TXT,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,ROLE_ID,ROLE_NM,VER_NBR)
  VALUES ('Y','This role derives its members from users with an Approval action request (that was not generated via the ad-hoc recipients tab) in the route log of a given document.','42',TO_DATE( '20090806170249', 'YYYYMMDDHH24MISS' ),'KR-WKFLW','67F145466EB09160E0404F8189D86771','97','Non-Ad Hoc Approve Request Recipient',1)
/
