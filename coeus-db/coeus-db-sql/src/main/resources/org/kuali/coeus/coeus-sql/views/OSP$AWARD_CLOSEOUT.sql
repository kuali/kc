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

CREATE OR REPLACE VIEW OSP$AWARD_CLOSEOUT AS
	SELECT A.AWARD_NUMBER MIT_AWARD_NUMBER, 
	A.SEQUENCE_NUMBER SEQUENCE_NUMBER, 
	(SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '1') FINAL_INV_SUBMISSION_DATE,
	(SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '4') FINAL_TECH_SUBMISSION_DATE,	
	(SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '3') FINAL_PATENT_SUBMISSION_DATE,
	(SELECT C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '2') FINAL_PROP_SUBMISSION_DATE,
	B.ARCHIVE_LOCATION ARCHIVE_LOCATION, 
	B.CLOSEOUT_DATE CLOSEOUT_DATE, 
	A.UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	A.UPDATE_USER UPDATE_USER
	FROM AWARD_CLOSEOUT A, AWARD B WHERE A.AWARD_NUMBER = B.AWARD_NUMBER AND A.SEQUENCE_NUMBER = B.SEQUENCE_NUMBER;
