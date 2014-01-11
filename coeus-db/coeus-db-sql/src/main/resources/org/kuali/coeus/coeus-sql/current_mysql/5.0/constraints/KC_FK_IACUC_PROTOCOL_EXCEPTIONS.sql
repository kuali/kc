DELIMITER /
ALTER TABLE IACUC_PROTOCOL_EXCEPTIONS 
ADD CONSTRAINT FK_IACUC_PROTO_EXCEPTION_CAT 
FOREIGN KEY (EXCEPTION_CATEGORY_CODE) 
REFERENCES IACUC_EXCEPTION_CATEGORY (EXCEPTION_CATEGORY_CODE)
/

ALTER TABLE IACUC_PROTOCOL_EXCEPTIONS 
ADD CONSTRAINT FK_IACUC_EXPCEPTION_SPECIES 
FOREIGN KEY (SPECIES_CODE) 
REFERENCES IACUC_SPECIES (SPECIES_CODE)
/

DELIMITER ;
