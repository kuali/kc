DELIMITER /
alter table EPS_PROP_PERSON drop primary key
/

alter table EPS_PROP_PERSON add primary key (PROPOSAL_NUMBER, PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON set PROP_PERSON_ROLE_ID = 'MPI' where PROP_PERSON_ROLE_ID = 'COI' and MULTIPLE_PI = 'Y'
/

alter table EPS_PROP_PERSON drop column MULTIPLE_PI
/

DELIMITER ;
