DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
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

alter table krms_cntxt_vld_actn_t drop foreign key krms_cntxt_vld_actn_fk1
/
drop index krms_cntxt_vld_actn_ti1 on krms_cntxt_vld_actn_t
/
rename table krms_cntxt_vld_actn_s to krms_cntxt_vld_actn_typ_s
/
rename table krms_cntxt_vld_actn_t to krms_cntxt_vld_actn_typ_t
/
create index krms_cntxt_vld_actn_typ_ti1 on krms_cntxt_vld_actn_typ_t (cntxt_id)
/
alter table krms_cntxt_vld_actn_typ_t add constraint krms_cntxt_vld_actn_typ_fk1
  foreign key (cntxt_id) references krms_cntxt_t (cntxt_id)
/

alter table krms_cntxt_vld_agenda_t drop foreign key krms_cntxt_vld_agenda_fk1
/
drop index krms_cntxt_vld_agenda_ti1 on krms_cntxt_vld_agenda_t
/
rename table krms_cntxt_vld_agenda_s to krms_cntxt_vld_agenda_typ_s
/
rename table krms_cntxt_vld_agenda_t to krms_cntxt_vld_agenda_typ_t
/
create index krms_cntxt_vld_agenda_typ_ti1 on krms_cntxt_vld_agenda_typ_t (cntxt_id)
/
alter table krms_cntxt_vld_agenda_typ_t add constraint krms_cntxt_vld_agenda_typ_fk1
  foreign key (cntxt_id) references krms_cntxt_t (cntxt_id)
/

alter table krms_cntxt_vld_rule_t drop foreign key krms_cntxt_vld_rule_fk1
/
drop index krms_cntxt_vld_rule_ti1 on krms_cntxt_vld_rule_t
/
rename table krms_cntxt_vld_rule_s to krms_cntxt_vld_rule_typ_s
/
rename table krms_cntxt_vld_rule_t to krms_cntxt_vld_rule_typ_t
/
create index krms_cntxt_vld_rule_typ_ti1 on krms_cntxt_vld_rule_typ_t (cntxt_id)
/
alter table krms_cntxt_vld_rule_typ_t add constraint krms_cntxt_vld_rule_typ_fk1
  foreign key (cntxt_id) references krms_cntxt_t (cntxt_id)
/

alter table krms_cntxt_vld_rule_typ_t change rule_id rule_typ_id varchar(40) not null
/

update krms_agenda_t set typ_id = null where typ_id = 'T5'
/
update krms_rule_t set typ_id = null
/
delete from krms_cntxt_vld_rule_typ_t
/
DELIMITER ;
