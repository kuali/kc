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
CREATE TABLE NEGOTIATION  ( 
	NEGOTIATION_ID               	DECIMAL(22) NOT NULL,
	DOCUMENT_NUMBER              	VARCHAR(40) NULL,
	NEGOTATION_STATUS_ID         	DECIMAL(22) NOT NULL,
	NEGOTIATION_AGREEMENT_TYPE_ID	DECIMAL(22) NOT NULL,
	NEGOTIATION_ASSC_TYPE_ID     	DECIMAL(22) NOT NULL,
	NEGOTIATOR_PERSON_ID         	VARCHAR(40) NOT NULL,
	NEGOTIATOR_FULL_NAME			VARCHAR(90) NOT NULL,
	NEGOTIATION_START_DATE       	DATE NULL,
	NEGOTIATION_END_DATE         	DATE NULL,
	ANTICIPATED_AWARD_DATE       	DATE NULL,
	DOCUMENT_FOLDER              	VARCHAR(255) NULL,
	UPDATE_TIMESTAMP     	DATE NOT NULL,
	UPDATE_USER          	VARCHAR(60) NOT NULL,
	VER_NBR              	DECIMAL(8,0) NOT NULL,
	OBJ_ID               	VARCHAR(36) NOT NULL,
	ASSOCIATED_DOCUMENT_ID	VARCHAR(25),
	CONSTRAINT NEGOTIATION_PK1 PRIMARY KEY(NEGOTIATION_ID)
	
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
DELIMITER ;
