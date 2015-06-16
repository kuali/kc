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

CREATE TABLE SEQ_PROPOSAL_ATTACHMENT_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;

ALTER TABLE SEQ_PROPOSAL_ATTACHMENT_ID auto_increment = 1;

CREATE TABLE PROPOSAL_ATTACHMENT_TYPE(
ATTACHMENT_TYPE_CODE DECIMAL(3,0) NOT NULL,
DESCRIPTION VARCHAR(200) NOT NULL,
ALLOW_MULTIPLE VARCHAR(1) NOT NULL,
UPDATE_TIMESTAMP DATETIME NOT NULL,
UPDATE_USER VARCHAR(60) NOT NULL,
VER_NBR DECIMAL (8,0),
OBJ_ID VARCHAR(36));

ALTER TABLE PROPOSAL_ATTACHMENT_TYPE
ADD CONSTRAINT PK_ATTACHMENT_TYPE_CODE
PRIMARY KEY (ATTACHMENT_TYPE_CODE);

CREATE TABLE PROPOSAL_ATTACHMENTS(
PROPOSAL_ATTACHMENTS_ID DECIMAL(12,0),
PROPOSAL_ID DECIMAL(12,0),
PROPOSAL_NUMBER VARCHAR(8) NOT NULL,
SEQUENCE_NUMBER DECIMAL(4,0) NOT NULL,
ATTACHMENT_NUMBER DECIMAL (2,0) NOT NULL,
ATTACHMENT_TITLE VARCHAR(150),
ATTACHMENT_TYPE_CODE DECIMAL(3,0),
FILE_NAME VARCHAR(2000),
CONTENT_TYPE VARCHAR(255),
COMMENTS VARCHAR(300),
VER_NBR DECIMAL(8,0),
OBJ_ID VARCHAR(36),
UPDATE_USER VARCHAR(60) NOT NULL,
UPDATE_TIMESTAMP DATETIME NOT NULL,
LAST_UPDATE_USER VARCHAR(60) NOT NULL,
LAST_UPDATE_TIMESTAMP DATETIME NOT NULL,
DOCUMENT_STATUS_CODE CHAR(1),
FILE_DATA_ID VARCHAR(36));

ALTER TABLE PROPOSAL_ATTACHMENTS
ADD CONSTRAINT PK_PROPOSAL_ATTACHMENTS
PRIMARY KEY (PROPOSAL_ATTACHMENTS_ID);

ALTER TABLE PROPOSAL_ATTACHMENTS
ADD CONSTRAINT UK_PROPOSAL_ATTACHMENTS
UNIQUE (PROPOSAL_NUMBER, ATTACHMENT_NUMBER);

ALTER TABLE PROPOSAL_ATTACHMENTS
ADD CONSTRAINT FK_PROPOSAL_ATTACHMENTS
FOREIGN KEY (PROPOSAL_ID)
REFERENCES PROPOSAL (PROPOSAL_ID);

ALTER TABLE PROPOSAL_ATTACHMENTS
ADD CONSTRAINT FK_ATTACHMENT_TYPE_CODE
FOREIGN KEY (ATTACHMENT_TYPE_CODE)
REFERENCES PROPOSAL_ATTACHMENT_TYPE (ATTACHMENT_TYPE_CODE);

ALTER TABLE PROPOSAL_ATTACHMENTS
ADD CONSTRAINT FK_FILE_DATA_ID
FOREIGN KEY (FILE_DATA_ID)
REFERENCES FILE_DATA (ID);
