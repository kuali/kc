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

TRUNCATE TABLE KRIM_GRP_MBR_T DROP STORAGE
/
INSERT INTO KRIM_GRP_MBR_T (GRP_ID,GRP_MBR_ID,LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('2000','1122',TO_DATE( '20081208124957', 'YYYYMMDDHH24MISS' ),'notsysadm','P','5B97C50B04CB6110E0404F8189D85213',1)
/
INSERT INTO KRIM_GRP_MBR_T (GRP_ID,GRP_MBR_ID,LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('1','1123',TO_DATE( '20081208124957', 'YYYYMMDDHH24MISS' ),'admin','P','5B97C50B04CC6110E0404F8189D85213',1)
/
INSERT INTO KRIM_GRP_MBR_T (GRP_ID,GRP_MBR_ID,LAST_UPDT_DT,MBR_ID,MBR_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('1','1124',TO_DATE( '20081208124957', 'YYYYMMDDHH24MISS' ),'notsys','P','5B97C50B04CD6110E0404F8189D85213',1)
/
