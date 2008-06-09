ALTER table eps_prop_person add (OPT_IN_UNIT_STATUS char(1) default 'Y' NOT NULL);
ALTER table eps_prop_person add (OPT_IN_CERTIFICATION_STATUS char(1) default 'Y' NOT NULL);
UPDATE EPS_PROP_PERSON_ROLE  set certification_required='O' where prop_person_role_id='KP';
UPDATE table EPS_PROP_PERSON_ROLE add (UNIT_DETAILS_REQUIRED char(1) default 'Y' NOT NULL);
UPDATE EPS_PROP_PERSON_ROLE  set UNIT_DETAILS_REQUIRED='O' where prop_person_role_id='KP';

