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
TRUNCATE TABLE KRIM_EMP_STAT_T
/
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','01','A',STR_TO_DATE( '20081113140631', '%Y%m%d%H%i%s' ),'Active','5B97C50B037A6110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','99','D',STR_TO_DATE( '20081113140631', '%Y%m%d%H%i%s' ),'Deceased','5B97C50B037B6110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','04','L',STR_TO_DATE( '20081113140632', '%Y%m%d%H%i%s' ),'On Non-Pay Leave','5B97C50B037C6110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','03','N',STR_TO_DATE( '20081113140632', '%Y%m%d%H%i%s' ),'Status Not Yet Processed','5B97C50B037D6110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','02','P',STR_TO_DATE( '20081113140632', '%Y%m%d%H%i%s' ),'Processing','5B97C50B037E6110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','10','R',STR_TO_DATE( '20081113140632', '%Y%m%d%H%i%s' ),'Retired','5B97C50B037F6110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_STAT_T (ACTV_IND,DISPLAY_SORT_CD,EMP_STAT_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','97','T',STR_TO_DATE( '20081113140632', '%Y%m%d%H%i%s' ),'Terminated','5B97C50B03806110E0404F8189D85213',1)
/
delimiter ;
