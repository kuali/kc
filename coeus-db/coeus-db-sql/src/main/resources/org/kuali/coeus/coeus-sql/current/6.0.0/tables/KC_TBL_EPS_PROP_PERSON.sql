alter table EPS_PROP_PERSON drop primary key
/

alter table EPS_PROP_PERSON add primary key (PROPOSAL_NUMBER, PROP_PERSON_NUMBER)
/
