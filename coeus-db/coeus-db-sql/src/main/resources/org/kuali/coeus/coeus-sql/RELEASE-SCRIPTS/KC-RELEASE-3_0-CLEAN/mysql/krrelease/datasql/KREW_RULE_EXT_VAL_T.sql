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
TRUNCATE TABLE KREW_RULE_EXT_VAL_T
/
INSERT INTO KREW_RULE_EXT_VAL_T (KEY_CD,RULE_EXT_ID,RULE_EXT_VAL_ID,VAL,VER_NBR)
  VALUES ('destination',1047,1048,'las vegas',1)
/
INSERT INTO KREW_RULE_EXT_VAL_T (KEY_CD,RULE_EXT_ID,RULE_EXT_VAL_ID,VAL,VER_NBR)
  VALUES ('campus',1104,1105,'IUB',1)
/
INSERT INTO KREW_RULE_EXT_VAL_T (KEY_CD,RULE_EXT_ID,RULE_EXT_VAL_ID,VAL,VER_NBR)
  VALUES ('campus',1107,1108,'IUPUI',1)
/
delimiter ;
