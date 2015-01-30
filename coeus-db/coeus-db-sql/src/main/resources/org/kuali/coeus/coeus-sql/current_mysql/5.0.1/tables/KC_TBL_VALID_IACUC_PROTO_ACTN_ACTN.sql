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
CREATE TABLE VALID_IACUC_PROTO_ACTN_ACTN ( 
		VALID_IACUC_PROTO_ACTN_ACTN_ID	DECIMAL(12,0) NOT NULL,
		PROTOCOL_ACTION_TYPE_CODE       VARCHAR(3) NOT NULL,
		ACTION_NUMBER                   DECIMAL(3,0) NOT NULL,
		FOLLOWUP_ACTION_CODE            VARCHAR(3) NOT NULL,
		USER_PROMPT_FLAG                VARCHAR(1) NOT NULL,
		USER_PROMPT                     VARCHAR(2000),
		COMM_DECISION_MOTION_TYPE_CODE  VARCHAR(3),
		UPDATE_TIMESTAMP                DATE NOT NULL,
		UPDATE_USER                     VARCHAR(60) NOT NULL,
		VER_NBR 						DECIMAL(8,0) DEFAULT 1 NOT NULL,
		OBJ_ID                          VARCHAR(36) NOT NULL
)
/

ALTER TABLE VALID_IACUC_PROTO_ACTN_ACTN
ADD CONSTRAINT PK_VALID_IACUC_ACTN_ACTN
PRIMARY KEY (VALID_IACUC_PROTO_ACTN_ACTN_ID)
/

DELIMITER ;
