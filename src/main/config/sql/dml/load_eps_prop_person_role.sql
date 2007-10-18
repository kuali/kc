#
# $Id: load_eps_prop_person_role.sql,v 1.1 2007-10-18 15:42:50 lprzybyl Exp $
#
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP_UPDATE_USER, VER_NBR, OBJ_ID) values ('PI', 'Principal Investigator', sysdate, 'kradev');
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP_UPDATE_USER, VER_NBR, OBJ_ID) values ('COI', 'Co-Investigator', sysdate, 'kradev');
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, UPDATE_TIMESTAMP_UPDATE_USER, VER_NBR, OBJ_ID) values ('KP', 'Key Person', sysdate, 'kradev');
