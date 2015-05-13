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

CREATE TABLE QUESTION_MULTI_CHOICE (
  ID DECIMAL(12,0),
  QUESTION_REF_ID_FK DECIMAL(12,0) NOT NULL,
  PROMPT VARCHAR(200) NOT NULL,
  DESCRIPTION VARCHAR(200) NOT NULL,
  UPDATE_TIMESTAMP DATE NOT NULL,
  UPDATE_USER VARCHAR(60) NOT NULL,
  VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
  OBJ_ID VARCHAR(36) NOT NULL);

ALTER TABLE QUESTION_MULTI_CHOICE
ADD CONSTRAINT PK_QUESTION_MULTI_CHOICE
PRIMARY KEY (ID);

ALTER TABLE QUESTION_MULTI_CHOICE
ADD CONSTRAINT FK_QUESTION_MULTI_CHOICE
FOREIGN KEY (QUESTION_REF_ID_FK)
REFERENCES QUESTION (QUESTION_REF_ID);

CREATE TABLE SEQ_QUESTION_MULTI_CHOICE_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;

ALTER TABLE SEQ_QUESTION_MULTI_CHOICE_ID auto_increment = 1;

INSERT INTO QUESTION_TYPES (QUESTION_TYPE_ID,QUESTION_TYPE_NAME,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) VALUES (100,'Multiple Choice',NOW(),'admin',1,UUID());
