DELIMITER /

ALTER TABLE PERSON_SIGNATURE_MODULE 
ADD CONSTRAINT UQ_PERSON_SIGN_MODULE 
UNIQUE (PERSON_SIGNATURE_ID, MODULE_CODE) 
/

DELIMITER ;
