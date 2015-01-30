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

CREATE TABLE SUBCONTRACT_EXP_CAT_DET (

	ID NUMBER(12) NOT NULL,
	AWARD_NUMBER VARCHAR2(12) NOT NULL,
	
	AMOUNT NUMBER(12,2),
	FISCAL_PERIOD DATE,
	
	IS_LARGE_BUSINESS VARCHAR2(1), 
	IS_SMALL_BUSINESS VARCHAR2(1),
	
	IS_WOMAN_OWNED VARCHAR2(1), 
	IS_8A_DISADVANTAGE VARCHAR2(1), 
	IS_HUB_ZONE VARCHAR2(1), 
	IS_VETERAN_OWNED VARCHAR2(1), 
	IS_SERVICE_DISABLED_VET_OWNED VARCHAR2(1), 
	IS_HISTORICAL_BLACK_COLLEGE VARCHAR2(1),
	
	UPDATE_TIMESTAMP DATE NOT NULL,
	UPDATE_USER VARCHAR2(60) NOT NULL,
 	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
 	OBJ_ID VARCHAR2(36) NOT NULL)
/

ALTER TABLE SUBCONTRACT_EXP_CAT_DET
ADD CONSTRAINT PK_SUBCONTRACT_EXP_CAT_DET
PRIMARY KEY (ID)
/
