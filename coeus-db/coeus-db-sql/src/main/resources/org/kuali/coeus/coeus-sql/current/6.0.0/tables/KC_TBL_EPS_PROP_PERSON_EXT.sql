alter table EPS_PROP_PERSON_EXT drop primary key
/

alter table EPS_PROP_PERSON_EXT add primary key (PROPOSAL_NUMBER, PROP_PERSON_NUMBER)
/

alter table EPS_PROP_PERSON_EXT drop PROP_PERSON_ROLE_ID
/