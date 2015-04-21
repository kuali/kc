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

create index idx_qrtz_ft_trig_name on kc_qrtz_fired_triggers(TRIGGER_NAME);
create index idx_qrtz_ft_trig_group on kc_qrtz_fired_triggers(TRIGGER_GROUP);
create index idx_qrtz_ft_trig_nm_gp on kc_qrtz_fired_triggers(TRIGGER_NAME,TRIGGER_GROUP);
create index idx_qrtz_ft_trig_volatile on kc_qrtz_fired_triggers(IS_VOLATILE);
create index idx_qrtz_ft_trig_inst_name on kc_qrtz_fired_triggers(INSTANCE_NAME);
create index idx_qrtz_ft_job_name on kc_qrtz_fired_triggers(JOB_NAME);
create index idx_qrtz_ft_job_group on kc_qrtz_fired_triggers(JOB_GROUP);
create index idx_qrtz_ft_job_stateful on kc_qrtz_fired_triggers(IS_STATEFUL);
create index idx_qrtz_ft_job_req_recovery on kc_qrtz_fired_triggers(REQUESTS_RECOVERY);
