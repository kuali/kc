/* Adding a boolean 'availability' column to schedules */
alter table COMM_SCHEDULE add AVAILABLE_TO_REVIEWERS varchar2(1) default 'N' not null; 		

/* All schedules in the past are being made 'available' */
update COMM_SCHEDULE set AVAILABLE_TO_REVIEWERS = 'Y' where SCHEDULED_DATE <= current_date;

commit;
