--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

CREATE SEQUENCE  SUBAWARD_FFATA_REPORTING_ID_S
MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE;

CREATE TABLE SUBAWARD_FFATA_REPORTING (
  SUBAWARD_FFATA_REPORTING_ID number(12,0) PRIMARY KEY,
  SUBAWARD_ID number(12,0) NOT NULL,
  SUBAWARD_AMOUNT_INFO_ID number(12,0),
  OTHER_TRANS_DESC varchar2(100),
  DATE_SUBMITTED DATE NOT NULL,
  SUBMITTER_ID VARCHAR2(40) NOT NULL,
  COMMENTS CLOB,
  FILE_DATA_ID varchar2(36),
  FILE_NAME varchar2(150),
  MIME_TYPE varchar2(100),
  UPDATE_USER VARCHAR2(60) NOT NULL,
  UPDATE_TIMESTAMP DATE NOT NULL,
  VER_NBR number (8,0) DEFAULT 1 NOT NULL,
  OBJ_ID VARCHAR2(36) NOT NULL);


alter table SUBAWARD_FFATA_REPORTING add constraint FK1_SUBAWARD_FFATA_REPORTING foreign key (SUBAWARD_ID)
REFERENCES SUBAWARD (SUBAWARD_ID);

alter table SUBAWARD_FFATA_REPORTING add constraint FK2_SUBAWARD_FFATA_REPORTING foreign key (SUBAWARD_AMOUNT_INFO_ID)
REFERENCES SUBAWARD_AMOUNT_INFO (SUBAWARD_AMOUNT_INFO_ID);

alter table SUBAWARD_FFATA_REPORTING add constraint FK3_SUBAWARD_FFATA_REPORTING foreign key (FILE_DATA_ID)
REFERENCES FILE_DATA (ID);