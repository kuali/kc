DELIMITER /
CREATE TABLE IACUC_RESEARCH_AREAS   (
	RESEARCH_AREA_CODE VARCHAR(8) NOT NULL, 
	PARENT_RESEARCH_AREA_CODE VARCHAR(8) NOT NULL, 
	HAS_CHILDREN_FLAG VARCHAR(1), 
	DESCRIPTION VARCHAR(200), 
    ACTIVE_FLAG VARCHAR(1) DEFAULT 'Y' NOT NULL,
    UPDATE_TIMESTAMP       		DATE NOT NULL,
    UPDATE_USER            		VARCHAR(60) NOT NULL,
    VER_NBR 					DECIMAL(8,0) DEFAULT 1 NOT NULL,
	OBJ_ID 						VARCHAR(36) NOT NULL
)
/
  
ALTER TABLE IACUC_RESEARCH_AREAS 
ADD CONSTRAINT  PK_IACUC_RESEARCH_AREAS
PRIMARY KEY (RESEARCH_AREA_CODE)
/


DELIMITER ;
