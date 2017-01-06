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

ALTER TABLE EPS_PROPOSAL ADD NSF_SEQUENCE_NUMBER_COPY NUMBER(12,0);

UPDATE EPS_PROPOSAL SET NSF_SEQUENCE_NUMBER_COPY = NSF_SEQUENCE_NUMBER;

ALTER TABLE EPS_PROPOSAL DROP COLUMN NSF_SEQUENCE_NUMBER;

ALTER TABLE EPS_PROPOSAL RENAME COLUMN NSF_SEQUENCE_NUMBER_COPY TO NSF_SEQUENCE_NUMBER;

ALTER TABLE EPS_PROPOSAL ADD CONSTRAINT FK_EPS_PROPOSAL_NSF
FOREIGN KEY (NSF_SEQUENCE_NUMBER)
REFERENCES NSF_CODES (NSF_SEQUENCE_NUMBER);

ALTER TABLE PROPOSAL ADD NSF_SEQUENCE_NUMBER_COPY NUMBER(12,0);

UPDATE PROPOSAL SET NSF_SEQUENCE_NUMBER_COPY = NSF_SEQUENCE_NUMBER;

ALTER TABLE PROPOSAL DROP COLUMN NSF_SEQUENCE_NUMBER;

ALTER TABLE PROPOSAL RENAME COLUMN NSF_SEQUENCE_NUMBER_COPY TO NSF_SEQUENCE_NUMBER;

ALTER TABLE PROPOSAL ADD CONSTRAINT FK_PROPOSAL_NSF
FOREIGN KEY (NSF_SEQUENCE_NUMBER)
REFERENCES NSF_CODES (NSF_SEQUENCE_NUMBER);

ALTER TABLE AWARD ADD NSF_SEQUENCE_NUMBER_COPY NUMBER(12,0);

UPDATE AWARD SET NSF_SEQUENCE_NUMBER_COPY = NSF_SEQUENCE_NUMBER;

ALTER TABLE AWARD DROP COLUMN NSF_SEQUENCE_NUMBER;

alter table AWARD RENAME COLUMN NSF_SEQUENCE_NUMBER_COPY TO NSF_SEQUENCE_NUMBER;

ALTER TABLE AWARD ADD CONSTRAINT FK_AWARD_NSF
FOREIGN KEY (NSF_SEQUENCE_NUMBER)
REFERENCES NSF_CODES (NSF_SEQUENCE_NUMBER);