DELIMITER /

drop procedure if exists resolveMultipleSequenceTables
/

create procedure resolveMultipleSequenceTables() 
begin
declare seqExists int default 0;
select count(*) into seqExists from information_schema.tables where table_schema = DATABASE() and table_name = 'COMM_SCHEDULE_ATTACHMENT_ID';
	
if (seqExists > 0) then
select max(id)+1 into @newMax from COMM_SCHEDULE_ATTACHMENT_ID;
prepare stmt from 'alter table SEQ_COMM_SCHED_ATTACH_ID AUTO_INCREMENT = ?';
execute stmt using @newMax;
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
