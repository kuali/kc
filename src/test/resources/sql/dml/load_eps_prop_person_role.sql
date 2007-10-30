#
# $Id: load_eps_prop_person_role.sql,v 1.1 2007-10-30 20:52:18 shyu Exp $
#
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) values ('PI', 'Principal Investigator', sysdate, 'kradev');
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) values ('COI', 'Co-Investigator', sysdate, 'kradev');
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) values ('KP', 'Key Person', sysdate, 'kradev');
