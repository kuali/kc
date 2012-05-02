--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

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