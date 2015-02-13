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
TRUNCATE TABLE KRIM_ENTITY_T
/
INSERT INTO KRIM_ENTITY_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1',STR_TO_DATE( '20081107094902', '%Y%m%d%H%i%s' ),'5B1B6B919CC96496E0404F8189D822F2',1)
/
INSERT INTO KRIM_ENTITY_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1100',STR_TO_DATE( '20081113140635', '%Y%m%d%H%i%s' ),'5B97C50B03946110E0404F8189D85213',1)
/
INSERT INTO KRIM_ENTITY_T (ACTV_IND,ENTITY_ID,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','1131',STR_TO_DATE( '20081113140640', '%Y%m%d%H%i%s' ),'5B97C50B03B36110E0404F8189D85213',1)
/
delimiter ;
