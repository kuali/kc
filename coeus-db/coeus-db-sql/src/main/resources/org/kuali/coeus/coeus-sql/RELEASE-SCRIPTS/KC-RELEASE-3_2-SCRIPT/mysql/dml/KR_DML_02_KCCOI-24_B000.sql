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
INSERT INTO KRNS_PARM_T (APPL_NMSPC_CD,CONS_CD,NMSPC_CD,OBJ_ID,PARM_DESC_TXT,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,VER_NBR)
  VALUES ('KC','A','KC-COIDISCLOSURE',UUID(),'Column header label for Coi FE conflict','Document','COI_DISCLOSURE_FE_CONFLICT_HEADER_LABEL','CONFG','Related',1)
/
DELIMITER ;
