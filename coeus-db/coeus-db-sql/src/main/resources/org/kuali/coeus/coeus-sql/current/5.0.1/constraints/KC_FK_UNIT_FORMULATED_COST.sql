ALTER TABLE UNIT_FORMULATED_COST 
ADD CONSTRAINT FK_UNIT_FORMULATED_COST 
FOREIGN KEY (FORMULATED_TYPE_CODE) 
REFERENCES FORMULATED_TYPE (FORMULATED_TYPE_CODE)
/

ALTER TABLE UNIT_FORMULATED_COST 
ADD CONSTRAINT FK_UNIT_FORMULATED_COST_1
FOREIGN KEY (UNIT_NUMBER) 
REFERENCES UNIT (UNIT_NUMBER)
/
