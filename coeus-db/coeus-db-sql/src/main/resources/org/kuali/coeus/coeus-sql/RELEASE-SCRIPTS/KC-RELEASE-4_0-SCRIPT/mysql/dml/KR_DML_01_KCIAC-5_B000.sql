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

DELIMITER /
INSERT INTO KRCR_NMSPC_T (ACTV_IND,NM,NMSPC_CD,APPL_ID,OBJ_ID,VER_NBR)
  VALUES ('Y','KC IACUC Protocol','KC-IACUC','KC',UUID(),1)
/
INSERT INTO KRCR_CMPNT_T (ACTV_IND,NM,NMSPC_CD,OBJ_ID,CMPNT_CD,VER_NBR)
  VALUES ('Y','Document','KC-IACUC',UUID(),'Document',1)
/

DELIMITER ;
