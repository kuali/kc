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
alter table AWARD add AWARD_SEQUENCE_STATUS VARCHAR(10) default 'PENDING' not null
/

update award aw
set award_sequence_status = (select v.version_status from version_history v
  where aw.award_number = v.seq_owner_version_name_value
	and aw.sequence_number = v.seq_owner_seq_number
  and v.seq_owner_class_name = 'org.kuali.kra.award.home.Award'
  and v.version_history_id in (select max(version_history_id) from version_history group by seq_owner_version_name_value, seq_owner_seq_number))
/
DELIMITER ;
