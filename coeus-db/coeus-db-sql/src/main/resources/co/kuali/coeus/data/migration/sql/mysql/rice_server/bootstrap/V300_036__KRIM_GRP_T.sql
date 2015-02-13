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
TRUNCATE TABLE KRIM_GRP_T
/
INSERT INTO KRIM_GRP_T (ACTV_IND,GRP_DESC,GRP_ID,GRP_NM,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','WorkflowAdmin','1','WorkflowAdmin','1',STR_TO_DATE( '20081113140721', '%Y%m%d%H%i%s' ),'KR-WKFLW','5B97C50B04A16110E0404F8189D85213',1)
/
INSERT INTO KRIM_GRP_T (ACTV_IND,GRP_DESC,GRP_ID,GRP_NM,KIM_TYP_ID,LAST_UPDT_DT,NMSPC_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','Notification system admin group for automation.','2000','NotificationAdmin','1',STR_TO_DATE( '20081113140721', '%Y%m%d%H%i%s' ),'KR-WKFLW','5B97C50B04A26110E0404F8189D85213',1)
/
delimiter ;
