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
TRUNCATE TABLE KRIM_ENT_TYP_T
/
INSERT INTO KRIM_ENT_TYP_T (ACTV_IND,DISPLAY_SORT_CD,ENT_TYP_CD,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','01','PERSON','Person','5B97C50B03886110E0404F8189D85213',1)
/
INSERT INTO KRIM_ENT_TYP_T (ACTV_IND,DISPLAY_SORT_CD,ENT_TYP_CD,NM,OBJ_ID,VER_NBR)
  VALUES ('Y','02','SYSTEM','System','5B97C50B03896110E0404F8189D85213',1)
/
delimiter ;
