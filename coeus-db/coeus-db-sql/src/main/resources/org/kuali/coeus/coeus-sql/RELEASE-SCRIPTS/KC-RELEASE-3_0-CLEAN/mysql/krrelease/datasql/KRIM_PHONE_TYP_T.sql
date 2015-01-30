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
TRUNCATE TABLE KRIM_PHONE_TYP_T
/
INSERT INTO KRIM_PHONE_TYP_T (ACTV_IND,DISPLAY_SORT_CD,LAST_UPDT_DT,OBJ_ID,PHONE_TYP_CD,PHONE_TYP_NM,VER_NBR)
  VALUES ('Y','b',STR_TO_DATE( '20081113140635', '%Y%m%d%H%i%s' ),'5B97C50B03906110E0404F8189D85213','HM','Home',1)
/
INSERT INTO KRIM_PHONE_TYP_T (ACTV_IND,DISPLAY_SORT_CD,LAST_UPDT_DT,OBJ_ID,PHONE_TYP_CD,PHONE_TYP_NM,VER_NBR)
  VALUES ('Y','c',STR_TO_DATE( '20081113140635', '%Y%m%d%H%i%s' ),'5B97C50B03916110E0404F8189D85213','MBL','Mobile',1)
/
INSERT INTO KRIM_PHONE_TYP_T (ACTV_IND,DISPLAY_SORT_CD,LAST_UPDT_DT,OBJ_ID,PHONE_TYP_CD,PHONE_TYP_NM,VER_NBR)
  VALUES ('Y','d',STR_TO_DATE( '20081113140635', '%Y%m%d%H%i%s' ),'5B97C50B03926110E0404F8189D85213','OTH','Other',1)
/
INSERT INTO KRIM_PHONE_TYP_T (ACTV_IND,DISPLAY_SORT_CD,LAST_UPDT_DT,OBJ_ID,PHONE_TYP_CD,PHONE_TYP_NM,VER_NBR)
  VALUES ('Y','a',STR_TO_DATE( '20081113140635', '%Y%m%d%H%i%s' ),'5B97C50B03936110E0404F8189D85213','WRK','Work',1)
/
delimiter ;
