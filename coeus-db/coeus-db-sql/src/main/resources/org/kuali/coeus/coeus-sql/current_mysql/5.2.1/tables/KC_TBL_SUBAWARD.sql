DELIMITER /

ALTER TABLE SUBAWARD ADD COST_TYPE DECIMAL(3,0)
/

ALTER TABLE SUBAWARD ADD DATE_OF_FULLY_EXECUTED DATE
/

ALTER TABLE SUBAWARD ADD REQUISITION_NUMBER VARCHAR(50)
/

DELIMITER ;