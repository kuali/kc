/* All schedules in the past are being made 'available' */
update COMM_SCHEDULE set AVAILABLE_TO_REVIEWERS = 'Y' where SCHEDULED_DATE <= current_date;

commit;