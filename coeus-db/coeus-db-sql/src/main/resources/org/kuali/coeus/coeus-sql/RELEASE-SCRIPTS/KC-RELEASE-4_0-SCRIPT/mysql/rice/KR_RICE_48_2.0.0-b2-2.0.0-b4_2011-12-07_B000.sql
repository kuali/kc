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
alter table krms_prop_t add column cmpnd_seq_no decimal(5,0) default null
/

update krms_prop_t, krms_cmpnd_prop_props_t set krms_prop_t.cmpnd_seq_no = krms_cmpnd_prop_props_t.seq_no
where krms_prop_t.prop_id = krms_cmpnd_prop_props_t.prop_id
/

alter table krms_cmpnd_prop_props_t drop seq_no
/
DELIMITER ;
