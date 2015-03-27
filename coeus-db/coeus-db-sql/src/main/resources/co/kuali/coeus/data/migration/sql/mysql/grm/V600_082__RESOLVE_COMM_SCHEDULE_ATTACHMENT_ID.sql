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

drop procedure if exists resolveMultipleSequenceTables
/

create procedure resolveMultipleSequenceTables() 
begin
declare seqExists int default 0;
declare records int default 0;
select count(*) into seqExists from information_schema.tables where table_schema = DATABASE() and table_name = 'COMM_SCHEDULE_ATTACHMENT_ID';

if (seqExists > 0) then
select count(*) into records from COMM_SCHEDULE_ATTACHMENT_ID;
if (records > 0) then
select max(id)+1 into @newMax from COMM_SCHEDULE_ATTACHMENT_ID;
select concat('alter table SEQ_COMM_SCHED_ATTACH_ID AUTO_INCREMENT = ', @newMax) into @fixStmt from dual;
prepare stmt from @fixStmt;
execute stmt;
end if;
end if;

end
/

call resolveMultipleSequenceTables()
/

drop procedure resolveMultipleSequenceTables
/

drop table if exists COMM_SCHEDULE_ATTACHMENT_ID
/

DELIMITER ;
