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
TRUNCATE TABLE KRIM_ENTITY_EMAIL_T
/
INSERT INTO KRIM_ENTITY_EMAIL_T (ACTV_IND,DFLT_IND,EMAIL_ADDR,EMAIL_TYP_CD,ENTITY_EMAIL_ID,ENTITY_ID,ENT_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','Y','test@email.edu','WRK','1200','1100','PERSON',STR_TO_DATE( '20081113140655', '%Y%m%d%H%i%s' ),'5B97C50B04066110E0404F8189D85213',1)
/
INSERT INTO KRIM_ENTITY_EMAIL_T (ACTV_IND,DFLT_IND,EMAIL_ADDR,EMAIL_TYP_CD,ENTITY_EMAIL_ID,ENTITY_ID,ENT_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR)
  VALUES ('Y','Y','test@email.edu','WRK','1231','1131','PERSON',STR_TO_DATE( '20081113140700', '%Y%m%d%H%i%s' ),'5B97C50B04256110E0404F8189D85213',1)
/
delimiter ;
