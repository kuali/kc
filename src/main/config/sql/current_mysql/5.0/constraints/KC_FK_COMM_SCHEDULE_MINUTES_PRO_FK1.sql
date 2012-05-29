DELIMITER /
-- remove the constraint, so this table can be shared by irb & iacuc
ALTER TABLE COMM_SCHEDULE_MINUTES
	drop FOREIGN KEY COMM_SCHEDULE_MINUTES_PRO_FK1
/


DELIMITER ;
