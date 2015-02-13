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
TRUNCATE TABLE KRIM_ENTITY_NM_T
/
INSERT INTO KRIM_ENTITY_NM_T (ACTV_IND,DFLT_IND,ENTITY_ID,ENTITY_NM_ID,FIRST_NM,LAST_NM,LAST_UPDT_DT,NM_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','Y','1100','1200','admin','admin',STR_TO_DATE( '20081113140702', '%Y%m%d%H%i%s' ),'PRFR','5B97C50B042C6110E0404F8189D85213',1)
/
INSERT INTO KRIM_ENTITY_NM_T (ACTV_IND,DFLT_IND,ENTITY_ID,ENTITY_NM_ID,FIRST_NM,LAST_NM,LAST_UPDT_DT,NM_TYP_CD,OBJ_ID,VER_NBR)
  VALUES ('Y','Y','1131','1231','Notification','System',STR_TO_DATE( '20081113140707', '%Y%m%d%H%i%s' ),'PRFR','5B97C50B044B6110E0404F8189D85213',1)
/
delimiter ;
