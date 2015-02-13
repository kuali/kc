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

DELIMITER /
ALTER TABLE COMM_BATCH_CORRESP_DETAIL
ADD CONSTRAINT COMM_BAT_CORR_IAC_PROT_ACTN_FK
FOREIGN KEY (IACUC_PROTOCOL_ACTION_ID)
REFERENCES IACUC_PROTOCOL_ACTIONS (IACUC_PROTOCOL_ACTION_ID)
/

ALTER TABLE COMM_BATCH_CORRESP_DETAIL
ADD CONSTRAINT COMM_BAT_CORR_IAC_PROT_CORR_FK
FOREIGN KEY (IACUC_PROT_CORRESPONDENCE_ID)
REFERENCES IACUC_PROTO_CORRESPONDENCE (ID)
/

DELIMITER ;
