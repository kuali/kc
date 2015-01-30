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

ALTER TABLE COMM_BATCH_CORRESP_DETAIL 
ADD CONSTRAINT UQ_COMM_BATCH_CORRESP_ID 
UNIQUE (COMM_BATCH_CORRESP_ID) 
/
COMMIT
/

ALTER TABLE
  COMM_BATCH_CORRESP_DETAIL 
DROP CONSTRAINT UQ_COMM_BATCH_CORRESP_DETAIL
/

ALTER TABLE
  COMM_BATCH_CORRESP_DETAIL 
MODIFY
  PROTOCOL_ACTION_ID NULL
/

ALTER TABLE
  COMM_BATCH_CORRESP_DETAIL 
ADD 
  IACUC_PROTOCOL_ACTION_ID NUMBER(12,0) NULL
/

ALTER TABLE
  COMM_BATCH_CORRESP_DETAIL 
MODIFY
  PROTOCOL_CORRESPONDENCE_ID NULL
/


ALTER TABLE
  COMM_BATCH_CORRESP_DETAIL 
ADD 
  IACUC_PROT_CORRESPONDENCE_ID NUMBER(12,0) NULL
/
