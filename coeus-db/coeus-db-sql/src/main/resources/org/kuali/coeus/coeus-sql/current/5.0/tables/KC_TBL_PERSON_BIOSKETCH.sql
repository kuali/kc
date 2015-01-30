--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

CREATE TABLE PERSON_BIOSKETCH (
    PERSON_BIOSKETCH_ID   NUMBER(8) NOT NULL,
    PERSON_ID             NUMBER(22) NOT NULL,
    DESCRIPTION           VARCHAR2(4000),
    FILE_NAME             VARCHAR2(300),
    CONTENT_TYPE          VARCHAR2(255),
    ATTACHMENT_FILE       BLOB,
    UPDATE_USER           VARCHAR2(60) NOT NULL,
    UPDATE_TIMESTAMP      DATE NOT NULL,
    OBJ_ID                VARCHAR2(36) NOT NULL,
    VER_NBR               NUMBER(8) DEFAULT 1 NOT NULL
)
/
ALTER TABLE PERSON_BIOSKETCH ADD CONSTRAINT PK_PERSON_BIOSKETCH
  PRIMARY KEY (PERSON_BIOSKETCH_ID)
/

DECLARE personId VARCHAR2(40);
        attachmentDescription VARCHAR2(4000);
        attachmentFileName VARCHAR2(300);
        attachmentContentType VARCHAR2(255);
        attachmentFile BLOB;
        updateUser VARCHAR2(60);
        updateTimestamp DATE;
        descriptionCount NUMBER;
        attachmentFileCount NUMBER;
        CURSOR cur IS SELECT * FROM PERSON_EXT_T;
BEGIN
    FOR rec IN cur 
    LOOP
        EXECUTE IMMEDIATE 'SELECT PERSON_ID FROM PERSON_EXT_T WHERE PERSON_ID = ' || rec.PERSON_ID INTO personId;
        EXECUTE IMMEDIATE 'SELECT BIOSKETCH_DESCRIPTION FROM PERSON_EXT_T WHERE PERSON_ID = ' || rec.PERSON_ID INTO attachmentDescription;
        EXECUTE IMMEDIATE 'SELECT BIOSKETCH_FILENAME FROM PERSON_EXT_T WHERE PERSON_ID = ' || rec.PERSON_ID INTO attachmentFileName;
        EXECUTE IMMEDIATE 'SELECT BIOSKETCH_FILE_CONTENT_TYPE FROM PERSON_EXT_T WHERE PERSON_ID = ' || rec.PERSON_ID INTO attachmentContentType;
        EXECUTE IMMEDIATE 'SELECT BIOSKETCH_FILE FROM PERSON_EXT_T WHERE PERSON_ID = ' || rec.PERSON_ID INTO attachmentFile;
        EXECUTE IMMEDIATE 'SELECT UPDATE_USER FROM PERSON_EXT_T WHERE PERSON_ID = ' || rec.PERSON_ID INTO updateUser;
        EXECUTE IMMEDIATE 'SELECT UPDATE_TIMESTAMP FROM PERSON_EXT_T WHERE PERSON_ID = ' || rec.PERSON_ID INTO updateTimestamp;
        
        EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM PERSON_EXT_T WHERE BIOSKETCH_DESCRIPTION IS NOT NULL AND PERSON_ID = ' || rec.PERSON_ID INTO descriptionCount;
        EXECUTE IMMEDIATE 'SELECT COUNT(*) FROM PERSON_EXT_T WHERE BIOSKETCH_FILENAME IS NOT NULL AND PERSON_ID = ' || rec.PERSON_ID INTO attachmentFileCount;
        
        IF descriptionCount > 0 OR attachmentFileCount > 0  THEN
            INSERT INTO PERSON_BIOSKETCH (PERSON_BIOSKETCH_ID,PERSON_ID,DESCRIPTION,FILE_NAME,CONTENT_TYPE,ATTACHMENT_FILE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) VALUES (SEQ_PERSON_BIOSKETCH_ID.NEXTVAL,personId,attachmentDescription,attachmentFileName,attachmentContentType,attachmentFile,updateUser,updateTimestamp,SYS_GUID(),1);
        END IF;
    END LOOP;
END;
/

ALTER TABLE PERSON_EXT_T DROP (BIOSKETCH_DESCRIPTION, BIOSKETCH_FILENAME, BIOSKETCH_FILE_CONTENT_TYPE, BIOSKETCH_FILE)
/
