DELIMITER $$

DROP PROCEDURE IF EXISTS add_hr_api_dbo $$

-- Create the stored procedure to perform the migration
CREATE PROCEDURE add_hr_api_dbo()

BEGIN

  CREATE TABLE IF NOT EXISTS cx_hrapi_import_status (
    importId                VARCHAR(50) NOT NULL PRIMARY KEY,
    status                  VARCHAR(20) NOT NULL DEFAULT 'PROCESSING',
    startTime               BIGINT NOT NULL,
    endTime                 BIGINT DEFAULT -1,
    numRecords              INT DEFAULT 0,
    numProcessed            INT DEFAULT 0,
    detail                  TEXT
  );

  CREATE TABLE IF NOT EXISTS cx_hrapi_import_errors (
    errorId                 INT PRIMARY KEY AUTO_INCREMENT,
    importId                VARCHAR(50) NOT NULL,
    recordNum               INT,
    principalName           VARCHAR(100),
    exception               BLOB,

    CONSTRAINT FK_IMPORT FOREIGN KEY (importId)
    REFERENCES cx_hrapi_import_status(importId)
    ON DELETE CASCADE
  );

  CREATE TABLE IF NOT EXISTS cx_hrapi_import_persons (
    personId                VARCHAR(40) PRIMARY KEY NOT NULL,
    importId                VARCHAR(50) NOT NULL,
    recordStatus            VARCHAR(8) NOT NULL DEFAULT 'ADDED',

    CONSTRAINT FK_cx_hrapi_import_status FOREIGN KEY (importId)
    REFERENCES cx_hrapi_import_status(importId)
    ON DELETE CASCADE
  );

END $$

-- Execute the stored procedure
CALL add_hr_api_dbo() $$

-- Don't forget to drop the stored procedure when you're done!
DROP PROCEDURE IF EXISTS add_hr_api_dbo $$

DELIMITER ;
