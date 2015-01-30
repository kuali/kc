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
TRUNCATE TABLE KRIM_RSP_TMPL_T
/
INSERT INTO KRIM_RSP_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,RSP_TMPL_ID,VER_NBR)
  VALUES ('Y','7','Review','KR-WKFLW','5ADFE172441D6320E0404F8189D85169','1',1)
/
INSERT INTO KRIM_RSP_TMPL_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,RSP_TMPL_ID,VER_NBR)
  VALUES ('Y','54','Resolve Exception','KR-WKFLW','60432A73A6A49F65E0404F8189D86AA4','2',1)
/
delimiter ;
