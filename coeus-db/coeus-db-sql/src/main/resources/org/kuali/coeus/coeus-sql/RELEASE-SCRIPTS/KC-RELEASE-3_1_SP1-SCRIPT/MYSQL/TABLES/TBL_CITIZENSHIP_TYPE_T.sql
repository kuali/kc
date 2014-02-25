-- create citizenship type table
CREATE TABLE CITIZENSHIP_TYPE_T  ( 
	CITIZENSHIP_TYPE_CODE	DECIMAL(15,1) NOT NULL,
	DESCRIPTION          	VARCHAR(40) NOT NULL,
	ACTIVE_FLAG          	CHAR(1) NOT NULL,
	UPDATE_TIMESTAMP     	DATE NOT NULL,
	UPDATE_USER          	VARCHAR(60) NOT NULL,
	VER_NBR              	DECIMAL(15,5) NOT NULL,
	OBJ_ID               	VARCHAR(36) NOT NULL 
	) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin;
	
-- add primary key
ALTER TABLE CITIZENSHIP_TYPE_T
	ADD ( CONSTRAINT CITIZEN_TYPE_PK1
	PRIMARY KEY (CITIZENSHIP_TYPE_CODE)
	 );
