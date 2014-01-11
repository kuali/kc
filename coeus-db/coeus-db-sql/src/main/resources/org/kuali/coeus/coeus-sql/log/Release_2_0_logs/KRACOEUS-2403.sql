create or replace view osp$eps_prop_key_persons
(proposal_number, person_id, person_name, project_role, faculty_flag, non_mit_person_flag, percentage_effort, update_timestamp, update_user)
as
select
       PROPOSAL_NUMBER, decode(PERSON_ID,null,to_char(ROLODEX_ID),PERSON_ID) PERSON_ID,
       FULL_NAME, PROJECT_ROLE, IS_FACULTY,decode(PERSON_ID,null,'N','Y') NON_MIT_PERSON_FLAG,
       decode(IS_OSC,'Y','999.00',PERCENTAGE_EFFORT) PERCENTAGE_EFFORT,
       UPDATE_TIMESTAMP, UPDATE_USER
       from EPS_PROP_PERSON WHERE PROP_PERSON_ROLE_ID='KP';
