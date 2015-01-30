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
TRUNCATE TABLE KRIM_ADDR_TYP_T
/
INSERT INTO KRIM_ADDR_TYP_T (ACTV_IND,ADDR_TYP_CD,DISPLAY_SORT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','HM','b',STR_TO_DATE( '20081113140629', '%Y%m%d%H%i%s' ),'Home','5B97C50B03706110E0404F8189D85213',1)
/
INSERT INTO KRIM_ADDR_TYP_T (ACTV_IND,ADDR_TYP_CD,DISPLAY_SORT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','OTH','c',STR_TO_DATE( '20081113140629', '%Y%m%d%H%i%s' ),'Other','5B97C50B03716110E0404F8189D85213',1)
/
INSERT INTO KRIM_ADDR_TYP_T (ACTV_IND,ADDR_TYP_CD,DISPLAY_SORT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','WRK','a',STR_TO_DATE( '20081113140630', '%Y%m%d%H%i%s' ),'Work','5B97C50B03726110E0404F8189D85213',1)
/
delimiter ;
