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
TRUNCATE TABLE KRNS_PARM_TYP_T
/
INSERT INTO KRNS_PARM_TYP_T (ACTV_IND,NM,OBJ_ID,PARM_TYP_CD,VER_NBR)
  VALUES ('Y','Authorization','53680C68F593AD9BE0404F8189D80A6C','AUTH',1)
/
INSERT INTO KRNS_PARM_TYP_T (ACTV_IND,NM,OBJ_ID,PARM_TYP_CD,VER_NBR)
  VALUES ('Y','Config','53680C68F591AD9BE0404F8189D80A6C','CONFG',1)
/
INSERT INTO KRNS_PARM_TYP_T (ACTV_IND,NM,OBJ_ID,PARM_TYP_CD,VER_NBR)
  VALUES ('Y','Help','53680C68F594AD9BE0404F8189D80A6C','HELP',1)
/
INSERT INTO KRNS_PARM_TYP_T (ACTV_IND,NM,OBJ_ID,PARM_TYP_CD,VER_NBR)
  VALUES ('Y','Document Validation','53680C68F592AD9BE0404F8189D80A6C','VALID',1)
/
delimiter ;
