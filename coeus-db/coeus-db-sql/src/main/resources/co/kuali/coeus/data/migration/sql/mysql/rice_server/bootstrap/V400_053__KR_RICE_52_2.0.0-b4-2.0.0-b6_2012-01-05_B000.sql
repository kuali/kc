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
