DELIMITER /

CREATE TABLE SUBAWARD_REPORTS (	
 	SUBAWARD_REPORT_ID	    	DECIMAL(12,0) NOT NULL,
	SUBAWARD_ID		    		DECIMAL(12,0) NOT NULL,
    SUBAWARD_CODE               VARCHAR(20) NOT NULL, 
	SEQUENCE_NUMBER             DECIMAL(3,0) NOT NULL, 
	REPORT_TYPE_CODE            DECIMAL(3,0) NOT NULL,
    UPDATE_TIMESTAMP            DATE,
	UPDATE_USER                 VARCHAR(60), 
	VER_NBR                     DECIMAL(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID                      VARCHAR(36) NOT NULL)
/

ALTER TABLE SUBAWARD_REPORTS 
ADD CONSTRAINT SUBAWARD_REPORT_ID_PK 
PRIMARY KEY (SUBAWARD_REPORT_ID)
/

DELIMITER ;