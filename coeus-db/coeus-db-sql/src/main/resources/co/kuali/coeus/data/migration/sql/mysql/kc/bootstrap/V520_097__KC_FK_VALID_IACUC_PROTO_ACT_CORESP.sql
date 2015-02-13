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

ALTER TABLE VALID_IACUC_PROTO_ACT_CORESP 
ADD CONSTRAINT FK_VALID_IACUC_PROT_ACT_CORS_1 
FOREIGN KEY (PROTO_CORRESP_TYPE_CODE) 
REFERENCES IACUC_PROTOCOL_CORRESP_TYPE (PROTO_CORRESP_TYPE_CODE)
/


ALTER TABLE VALID_IACUC_PROTO_ACT_CORESP 
ADD CONSTRAINT FK_VALID_IACUC_PROT_ACT_CORS_2 
FOREIGN KEY (PROTOCOL_ACTION_TYPE_CODE) 
REFERENCES IACUC_PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE)
/

DELIMITER ;
