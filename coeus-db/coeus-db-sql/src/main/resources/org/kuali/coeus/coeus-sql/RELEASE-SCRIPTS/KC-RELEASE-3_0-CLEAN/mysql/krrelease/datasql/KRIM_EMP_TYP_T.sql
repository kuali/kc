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
TRUNCATE TABLE KRIM_EMP_TYP_T
/
INSERT INTO KRIM_EMP_TYP_T (ACTV_IND,DISPLAY_SORT_CD,EMP_TYP_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','02','N',STR_TO_DATE( '20081113140632', '%Y%m%d%H%i%s' ),'Non-Professional','5B97C50B03826110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_TYP_T (ACTV_IND,DISPLAY_SORT_CD,EMP_TYP_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','99','O',STR_TO_DATE( '20081113140633', '%Y%m%d%H%i%s' ),'Other','5B97C50B03836110E0404F8189D85213',1)
/
INSERT INTO KRIM_EMP_TYP_T (ACTV_IND,DISPLAY_SORT_CD,EMP_TYP_CD,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','01','P',STR_TO_DATE( '20081113140633', '%Y%m%d%H%i%s' ),'Professional','5B97C50B03846110E0404F8189D85213',1)
/
delimiter ;
