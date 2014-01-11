-- Moving data around so the column is empty when changed --
-- ALTER TABLE CITIZENSHIP_TYPE_T MODIFY CITIZENSHIP_TYPE_CODE NUMBER(15,0); --
ALTER TABLE CITIZENSHIP_TYPE_T 
  DROP PRIMARY KEY CASCADE;
ALTER TABLE CITIZENSHIP_TYPE_T 
  ADD (CITIZENSHIP_TYPE_CODE_TEMP NUMBER(15,0));
UPDATE CITIZENSHIP_TYPE_T
  SET CITIZENSHIP_TYPE_CODE_TEMP = CITIZENSHIP_TYPE_CODE;
ALTER TABLE CITIZENSHIP_TYPE_T
  DROP COLUMN CITIZENSHIP_TYPE_CODE;
ALTER TABLE CITIZENSHIP_TYPE_T 
  ADD (CITIZENSHIP_TYPE_CODE NUMBER(15,0));
UPDATE CITIZENSHIP_TYPE_T
  SET CITIZENSHIP_TYPE_CODE = CITIZENSHIP_TYPE_CODE_TEMP;
ALTER TABLE CITIZENSHIP_TYPE_T
  DROP COLUMN CITIZENSHIP_TYPE_CODE_TEMP;
ALTER TABLE CITIZENSHIP_TYPE_T
  ADD (CONSTRAINT CITIZEN_TYPE_PK1 PRIMARY KEY (CITIZENSHIP_TYPE_CODE));
ALTER TABLE PERSON_EXT_T
  ADD (CONSTRAINT PERSON_EXT_FK1 FOREIGN KEY(CITIZENSHIP_TYPE_CODE)
  REFERENCES CITIZENSHIP_TYPE_T (CITIZENSHIP_TYPE_CODE));