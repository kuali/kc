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

CREATE TABLE AWARD_REPORT_TRACKING  ( 
	AWARD_REPORT_TERM_ID        NUMBER(22) NULL,
	AWARD_NUMBER				VARCHAR2(12) NOT NULL,
	PI_PERSON_ID				VARCHAR2(40) NULL,
	PI_ROLODEX_ID				NUMBER(6,0) NULL,
	PI_NAME					    VARCHAR2(90) NOT NULL,
	LEAD_UNIT_NUMBER			VARCHAR2(8) NOT NULL,
	REPORT_CLASS_CODE			VARCHAR2(3) NOT NULL,
	REPORT_CODE				    VARCHAR2(3) NOT NULL,
	FREQUENCY_CODE			    VARCHAR2(3) NULL,
	FREQUENCY_BASE_CODE		    VARCHAR2(3) NULL,
	OSP_DISTRIBUTION_CODE       VARCHAR2(3) NULL,
	STATUS_CODE              	VARCHAR2(3) NULL,
	BASE_DATE					DATE NULL,
	DUE_DATE					DATE NULL,
	ACTIVITY_DATE				DATE NULL,
	OVERDUE					    NUMBER(6,0) NULL,
	COMMENTS					VARCHAR2(200) NULL,
	PREPARER_ID				    VARCHAR2(40) NULL,
	PREPARER_NAME				VARCHAR2(90) NULL,
	SPONSOR_CODE				VARCHAR2(6) NOT NULL,
	SPONSOR_AWARD_NUMBER		VARCHAR2(70) NULL,
	TITLE						VARCHAR2(200) NOT NULL,
	LAST_UPDATE_USER			VARCHAR2(60) NOT NULL,
	LAST_UPDATE_DATE			DATE NOT NULL,
	UPDATE_TIMESTAMP			DATE NOT NULL,
	UPDATE_USER				    VARCHAR2(60) NOT NULL,
	VER_NBR					    NUMBER(8,0) NOT NULL,
	OBJ_ID					    VARCHAR2(36) NOT NULL
)
/
