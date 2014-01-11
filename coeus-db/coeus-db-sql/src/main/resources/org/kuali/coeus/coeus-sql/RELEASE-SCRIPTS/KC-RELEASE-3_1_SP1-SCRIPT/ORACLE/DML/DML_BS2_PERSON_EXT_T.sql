-- set default value for new field
UPDATE PERSON_EXT_T
SET CITIZENSHIP_TYPE_CODE = (SELECT CITIZENSHIP_TYPE_CODE FROM CITIZENSHIP_TYPE_T WHERE DESCRIPTION = 'US CITIZEN OR NONCITIZEN NATIONAL');

-- make new field a non-null field
ALTER TABLE "PERSON_EXT_T" MODIFY ( "CITIZENSHIP_TYPE_CODE" NUMBER(15,5) NOT NULL );