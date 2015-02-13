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

CREATE TABLE VALID_PROTO_ACTION_ACTION  ( 
    VALID_PROTO_ACTION_ACTION_ID	DECIMAL(12,0) NOT NULL,
	PROTOCOL_ACTION_TYPE_CODE       VARCHAR(3) NOT NULL,
	ACTION_NUMBER                   DECIMAL(3,0) NOT NULL,
	FOLLOWUP_ACTION_CODE            VARCHAR(3) NOT NULL,
	USER_PROMPT_FLAG                VARCHAR(1) NOT NULL,
	USER_PROMPT                     VARCHAR(2000) NULL,
	UPDATE_TIMESTAMP                DATE NOT NULL,
	UPDATE_USER                     VARCHAR(60) NOT NULL,
    OBJ_ID                          VARCHAR(36) NOT NULL,
    COMM_DECISION_MOTION_TYPE_CODE  VARCHAR(3),
	CONSTRAINT VALID_PROTO_ACTION_ACTIONP1 PRIMARY KEY(VALID_PROTO_ACTION_ACTION_ID)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE VALID_PROTO_ACTION_ACTION
	ADD ( CONSTRAINT UQ_VALID_PROTO_ACTION_ACTION
	UNIQUE (PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE,COMM_DECISION_MOTION_TYPE_CODE) );
