ALTER TABLE FIN_ENTITY_REPORTER_UNITS 
ADD CONSTRAINT FK_FIN_ENTITY_UNITS_REPORTER 
FOREIGN KEY (FINANCIAL_ENTITY_REPORTER_ID) 
REFERENCES FINANCIAL_ENTITY_REPORTER (FINANCIAL_ENTITY_REPORTER_ID)
/
