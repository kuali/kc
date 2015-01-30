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
TRUNCATE TABLE KREW_RULE_T
/
INSERT INTO KREW_RULE_T (ACTVN_DT,ACTV_IND,CUR_IND,DACTVN_DT,DLGN_IND,DOC_TYP_NM,FRC_ACTN,FRM_DT,NM,OBJ_ID,RULE_BASE_VAL_DESC,RULE_ID,RULE_TMPL_ID,RULE_VER_NBR,TMPL_RULE_IND,TO_DT,VER_NBR)
  VALUES (STR_TO_DATE( '20080801155902', '%Y%m%d%H%i%s' ),1,1,STR_TO_DATE( '21000101000000', '%Y%m%d%H%i%s' ),0,'SendNotificationRequest',1,STR_TO_DATE( '20080801155902', '%Y%m%d%H%i%s' ),'SendNotificationRequest.Reviewers','6166CBA1BBE9644DE0404F8189D86C09','Notification Request Reviewers',1044,1023,0,0,STR_TO_DATE( '21000101000000', '%Y%m%d%H%i%s' ),0)
/
delimiter ;
