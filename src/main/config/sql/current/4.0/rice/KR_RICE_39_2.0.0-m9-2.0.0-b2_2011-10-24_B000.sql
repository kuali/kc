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

alter table krcr_cmpnt_t drop column cmpnt_set_id
/
create table krcr_drvd_cmpnt_t (
  nmspc_cd varchar2(20) not null,
  cmpnt_cd varchar2(100) not null,
  nm varchar2(255),
  cmpnt_set_id varchar2(40) not null,
  primary key (nmspc_cd, cmpnt_cd))
/
