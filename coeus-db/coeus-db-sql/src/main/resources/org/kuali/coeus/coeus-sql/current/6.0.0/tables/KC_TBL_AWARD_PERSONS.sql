alter table AWARD_PERSONS drop constraint FK_PROP_PERS_ROLE_AWD_PERSONS
/
update AWARD_PERSONS set CONTACT_ROLE_CODE = 'MPI' where CONTACT_ROLE_CODE = 'COI' and MULTIPLE_PI = 'Y'
/

alter table AWARD_PERSONS drop column MULTIPLE_PI
/
