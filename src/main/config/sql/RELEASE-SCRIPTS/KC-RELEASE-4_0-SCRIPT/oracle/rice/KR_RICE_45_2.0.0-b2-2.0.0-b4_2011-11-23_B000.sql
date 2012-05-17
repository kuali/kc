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

-- give PeopleFlows friendlier names

update krms_typ_t set nm='Notify PeopleFlow' where typ_id = '1000'
/
update krms_typ_t set nm='Route to PeopleFlow' where typ_id = '1001'
/

-- remove constraint that is preventing compound props from persisting

alter table krms_cmpnd_prop_props_t modify seq_no NUMBER(5) null
/
