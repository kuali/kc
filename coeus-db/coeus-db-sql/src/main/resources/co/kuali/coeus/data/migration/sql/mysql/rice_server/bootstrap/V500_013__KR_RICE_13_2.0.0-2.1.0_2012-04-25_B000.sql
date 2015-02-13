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

update krms_attr_defn_t set nm='actionTypeCode' where attr_defn_id='1004'
/
update krms_attr_defn_t set nm='actionMessage' where attr_defn_id='1005'
/
update krms_attr_defn_t set nm='ruleTypeCode' where attr_defn_id='1001'
/

delete from krms_typ_attr_t where ATTR_DEFN_ID = '1002'
/
delete from krms_typ_attr_t where ATTR_DEFN_ID = '1003'
/
delete from krms_attr_defn_t where ATTR_DEFN_ID = '1002'
/
delete from krms_attr_defn_t where ATTR_DEFN_ID = '1003'
/
DELIMITER ;
