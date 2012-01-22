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


-- correct fields in krms test data
update krms_prop_t set cmpnd_op_cd = '&' where cmpnd_op_cd = 'a'
/
update krms_cmpnd_prop_props_t set seq_no = '2' where prop_id = 'P421C'
/
update krms_cmpnd_prop_props_t set seq_no = '3' where prop_id = 'P421D'
/
update krms_cmpnd_prop_props_t set seq_no = '3' where prop_id = 'P502C'
/

-- move seq_no column from krms_cmpnd_prop_props_t pivot table to krms_prop_t table.
alter table krms_prop_t add (cmpnd_seq_no decimal(5,0) default null)
/
update krms_prop_t set krms_prop_t.cmpnd_seq_no = (select seq_no from krms_cmpnd_prop_props_t where prop_id = krms_prop_t.prop_id)
/
alter table krms_cmpnd_prop_props_t drop (seq_no)
/
