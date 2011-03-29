-- Make sure this is included and working in MySQL.  It is a constraint that was ok in Oracle but needed some changes to the tables (included above) to work in MySQL --
ALTER TABLE PERSON_EXT_T 
  ADD (CONSTRAINT PERSON_EXT_FK1 FOREIGN KEY(CITIZENSHIP_TYPE_CODE)
  REFERENCES CITIZENSHIP_TYPE_T (CITIZENSHIP_TYPE_CODE));