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
TRUNCATE TABLE KRIM_AFLTN_TYP_T
/
INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','AFLT','d','N',STR_TO_DATE( '20081113140630', '%Y%m%d%H%i%s' ),'Affiliate','5B97C50B03736110E0404F8189D85213',1)
/
INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','FCLTY','b','Y',STR_TO_DATE( '20081113140630', '%Y%m%d%H%i%s' ),'Faculty','5B97C50B03746110E0404F8189D85213',1)
/
INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','STAFF','c','Y',STR_TO_DATE( '20081113140630', '%Y%m%d%H%i%s' ),'Staff','5B97C50B03756110E0404F8189D85213',1)
/
INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','STDNT','a','N',STR_TO_DATE( '20081113140630', '%Y%m%d%H%i%s' ),'Student','5B97C50B03766110E0404F8189D85213',1)
/
delimiter ;
