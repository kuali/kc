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
CREATE TABLE NEGOTIATION_ACTIVITY (
	NEGOTIATION_ACTIVITY_ID			DECIMAL(22) NOT NULL,
	NEGOTIATION_ID					DECIMAL(22) NOT NULL,
	LOCATION_ID						DECIMAL(22) NOT NULL,
	ACTIVITY_TYPE_ID				DECIMAL(22) NOT NULL,
	START_DATE						DATE NOT NULL,
	END_DATE						DATE,
	CREATE_DATE						DATE NOT NULL,
	FOLLOWUP_DATE					DATE,
	LAST_MODIFIED_USER				VARCHAR(60) NOT NULL,
	LAST_MODIFIED_DATE				DATE NOT NULL,
	DESCRIPTION						VARCHAR(2000),
	RESTRICTED						CHAR(1) NOT NULL,
	UPDATE_TIMESTAMP     			DATE NOT NULL,
	UPDATE_USER          			VARCHAR(60) NOT NULL,
	VER_NBR              			DECIMAL(8,0) NOT NULL,
	OBJ_ID               			VARCHAR(36) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/

ALTER TABLE NEGOTIATION_ACTIVITY
  ADD CONSTRAINT NEGOTIATION_ACTIVITY_PK1
  PRIMARY KEY (NEGOTIATION_ACTIVITY_ID)
/
DELIMITER ;
