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
TRUNCATE TABLE KRNS_NTE_TYP_T
/
INSERT INTO KRNS_NTE_TYP_T (ACTV_IND,NTE_TYP_CD,OBJ_ID,TYP_DESC_TXT,VER_NBR)
  VALUES ('Y','BO','53680C68F5A9AD9BE0404F8189D80A6C','DOCUMENT BUSINESS OBJECT',1)
/
INSERT INTO KRNS_NTE_TYP_T (ACTV_IND,NTE_TYP_CD,OBJ_ID,TYP_DESC_TXT,VER_NBR)
  VALUES ('Y','DH','53680C68F5AAAD9BE0404F8189D80A6C','DOCUMENT HEADER',1)
/
delimiter ;
