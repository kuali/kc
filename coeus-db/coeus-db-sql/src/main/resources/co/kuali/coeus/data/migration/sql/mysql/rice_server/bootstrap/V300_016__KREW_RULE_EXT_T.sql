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
TRUNCATE TABLE KREW_RULE_EXT_T
/
INSERT INTO KREW_RULE_EXT_T (RULE_EXT_ID,RULE_ID,RULE_TMPL_ATTR_ID,VER_NBR)
  VALUES (1045,1044,1024,1)
/
INSERT INTO KREW_RULE_EXT_T (RULE_EXT_ID,RULE_ID,RULE_TMPL_ATTR_ID,VER_NBR)
  VALUES (1047,1046,1027,1)
/
INSERT INTO KREW_RULE_EXT_T (RULE_EXT_ID,RULE_ID,RULE_TMPL_ATTR_ID,VER_NBR)
  VALUES (1104,1103,1102,1)
/
INSERT INTO KREW_RULE_EXT_T (RULE_EXT_ID,RULE_ID,RULE_TMPL_ATTR_ID,VER_NBR)
  VALUES (1107,1106,1102,1)
/
delimiter ;
