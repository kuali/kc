DELIMITER /
-- remove the constraint, so this table can be shared by irb & iacuc
ALTER TABLE COMM_SCHEDULE_MINUTES
	drop  FOREIGN KEY FK_COMM_SCHEDULE_MINUTES_FK4
/
DELIMITER ;
