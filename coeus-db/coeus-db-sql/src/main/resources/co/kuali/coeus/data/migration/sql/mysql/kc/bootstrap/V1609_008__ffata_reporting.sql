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

CREATE TABLE SUBAWARD_FFATA_REPORTING_ID_S (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;

ALTER TABLE SUBAWARD_FFATA_REPORTING_ID_S auto_increment = 1;

CREATE TABLE SUBAWARD_FFATA_REPORTING (
  SUBAWARD_FFATA_REPORTING_ID decimal(12,0) PRIMARY KEY,
  SUBAWARD_ID decimal(12,0) NOT NULL,
  SUBAWARD_AMOUNT_INFO_ID decimal(12,0),
  OTHER_TRANS_DESC varchar(100),
  DATE_SUBMITTED DATETIME NOT NULL,
  SUBMITTER_ID VARCHAR(40) NOT NULL,
  COMMENTS LONGTEXT,
  FILE_DATA_ID varchar(36),
  FILE_NAME varchar(150),
  MIME_TYPE varchar(100),
  UPDATE_USER VARCHAR(60) NOT NULL,
  UPDATE_TIMESTAMP DATETIME NOT NULL,
  VER_NBR DECIMAL (8,0) NOT NULL DEFAULT 1,
  OBJ_ID VARCHAR(36) NOT NULL) character set utf8 collate utf8_bin;


alter table SUBAWARD_FFATA_REPORTING add constraint FK1_SUBAWARD_FFATA_REPORTING foreign key (SUBAWARD_ID)
REFERENCES SUBAWARD (SUBAWARD_ID);

alter table SUBAWARD_FFATA_REPORTING add constraint FK2_SUBAWARD_FFATA_REPORTING foreign key (SUBAWARD_AMOUNT_INFO_ID)
REFERENCES SUBAWARD_AMOUNT_INFO (SUBAWARD_AMOUNT_INFO_ID);

alter table SUBAWARD_FFATA_REPORTING add constraint FK3_SUBAWARD_FFATA_REPORTING foreign key (FILE_DATA_ID)
REFERENCES FILE_DATA (ID);