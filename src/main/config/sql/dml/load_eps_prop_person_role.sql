#
# $Id: load_eps_prop_person_role.sql,v 1.3 2007-12-17 18:39:18 rmancher Exp $
#
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, CERTIFICATION_REQUIRED, CERTIFICATION_REQUIREDUPDATE_TIMESTAMP, UPDATE_USER) values ('PI', 'Principal Investigator', 'Y', 'sysdate, 'kradev');
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, CERTIFICATION_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('COI', 'Co-Investigator', 'Y', sysdate, 'kradev');
insert into EPS_PROP_PERSON_ROLE (PROP_PERSON_ROLE_ID, DESCRIPTION, CERTIFICATION_REQUIRED, UPDATE_TIMESTAMP, UPDATE_USER) values ('KP', 'Key Person', 'N', sysdate, 'kradev');
