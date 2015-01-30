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

ALTER TABLE COMM_SCHEDULE_MINUTES
ADD CONSTRAINT COMM_SCH_MIN_IACPRO_FK
FOREIGN KEY (IACUC_PROTOCOL_ID_FK)
REFERENCES IACUC_PROTOCOL (PROTOCOL_ID)
/

ALTER TABLE COMM_SCHEDULE_MINUTES
ADD CONSTRAINT COMM_SCH_MIN_IACPRO_SUB_FK
FOREIGN KEY (IACUC_SUBMISSION_ID_FK)
REFERENCES IACUC_PROTOCOL_SUBMISSION (IACUC_PROTOCOL_SUBMISSION_ID)
/

ALTER TABLE COMM_SCHEDULE_MINUTES
ADD CONSTRAINT COMM_SCH_MIN_IACPRO_REV_FK
FOREIGN KEY (IACUC_REVIEWER_ID_FK)
REFERENCES IACUC_PROTOCOL_REVIEWERS (PROTOCOL_REVIEWER_ID)
/

ALTER TABLE COMM_SCHEDULE_MINUTES
ADD CONSTRAINT COMM_SCH_MIN_PROT_FK
FOREIGN KEY (PROTOCOL_ID_FK)
REFERENCES PROTOCOL (PROTOCOL_ID)
/

ALTER TABLE COMM_SCHEDULE_MINUTES
ADD CONSTRAINT COMM_SCH_MIN_PROT_SUB_FK
FOREIGN KEY (SUBMISSION_ID_FK)
REFERENCES PROTOCOL_SUBMISSION (SUBMISSION_ID)
/

ALTER TABLE COMM_SCHEDULE_MINUTES
ADD CONSTRAINT COMM_SCH_MIN_PROT_REV_FK
FOREIGN KEY (REVIEWER_ID_FK)
REFERENCES PROTOCOL_REVIEWERS (PROTOCOL_REVIEWER_ID)
/
