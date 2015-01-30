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

ALTER TABLE VALID_IACUC_PROTO_SUB_REV_TYPE 
ADD CONSTRAINT FK_AC_VALID_PROTO_SUB_REV_TYPE 
FOREIGN KEY (SUBMISSION_TYPE_CODE) 
REFERENCES IACUC_SUBMISSION_TYPE (SUBMISSION_TYPE_CODE)
/
ALTER TABLE VALID_IACUC_PROTO_SUB_REV_TYPE 
ADD CONSTRAINT FK2_AC_PROTO_SUB_REV_TYPE 
FOREIGN KEY (PROTOCOL_REVIEW_TYPE_CODE) 
REFERENCES IACUC_PROTO_REVIEW_TYPE (PROTOCOL_REVIEW_TYPE_CODE)
/
