alter table s2s_submission_type
add sort_id number(2);

update  s2s_submission_type
set sort_id=1 where s2s_submission_type_code='2';

update  s2s_submission_type
set sort_id=2 where s2s_submission_type_code='1';

update  s2s_submission_type
set sort_id=3 where s2s_submission_type_code='3';