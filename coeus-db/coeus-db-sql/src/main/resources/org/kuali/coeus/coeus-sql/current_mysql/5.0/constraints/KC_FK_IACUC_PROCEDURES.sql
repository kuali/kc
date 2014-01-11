DELIMITER /
ALTER TABLE IACUC_PROCEDURES 
ADD CONSTRAINT FK_IACUC_PROCEDURE_CATEGORY 
FOREIGN KEY (PROCEDURE_CATEGORY_CODE) 
REFERENCES IACUC_PROCEDURE_CATEGORY (PROCEDURE_CATEGORY_CODE)
/

DELIMITER ;
