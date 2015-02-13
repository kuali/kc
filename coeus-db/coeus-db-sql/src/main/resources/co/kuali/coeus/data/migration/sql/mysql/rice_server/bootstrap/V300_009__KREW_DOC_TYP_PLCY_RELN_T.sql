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
TRUNCATE TABLE KREW_DOC_TYP_PLCY_RELN_T
/
INSERT INTO KREW_DOC_TYP_PLCY_RELN_T (DOC_PLCY_NM,DOC_TYP_ID,OBJ_ID,PLCY_NM,VER_NBR)
  VALUES ('DEFAULT_APPROVE',2024,'61BA2DCF3BE658EEE0404F8189D80CAE',1,1)
/
INSERT INTO KREW_DOC_TYP_PLCY_RELN_T (DOC_PLCY_NM,DOC_TYP_ID,OBJ_ID,PLCY_NM,VER_NBR)
  VALUES ('DEFAULT_APPROVE',2680,'61BA2DCF3BE858EEE0404F8189D80CAE',1,2)
/
INSERT INTO KREW_DOC_TYP_PLCY_RELN_T (DOC_PLCY_NM,DOC_TYP_ID,OBJ_ID,PLCY_NM,VER_NBR)
  VALUES ('LOOK_FUTURE',2024,'61BA2DCF3BE758EEE0404F8189D80CAE',1,1)
/
INSERT INTO KREW_DOC_TYP_PLCY_RELN_T (DOC_PLCY_NM,DOC_TYP_ID,OBJ_ID,PLCY_NM,VER_NBR)
  VALUES ('LOOK_FUTURE',2680,'61BA2DCF3BE958EEE0404F8189D80CAE',1,2)
/
delimiter ;
