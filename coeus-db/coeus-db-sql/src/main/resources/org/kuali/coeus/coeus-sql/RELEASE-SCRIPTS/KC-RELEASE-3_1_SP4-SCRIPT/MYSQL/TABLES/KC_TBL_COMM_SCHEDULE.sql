/* Adding a boolean 'availability' column to schedules */
alter table COMM_SCHEDULE add AVAILABLE_TO_REVIEWERS varchar(1) default 'N' not null;  