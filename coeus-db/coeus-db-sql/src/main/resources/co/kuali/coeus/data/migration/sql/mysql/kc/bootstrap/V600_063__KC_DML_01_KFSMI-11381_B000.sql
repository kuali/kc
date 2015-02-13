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

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
	declare newReportCode int default 0;
	declare newFrequencyCode int default 0;
	declare newValidId int default 0;
	declare newValidFreqId int default 0;

	select max(cast(REPORT_CODE as signed))+1 into newReportCode from REPORT;
	insert into REPORT (REPORT_CODE, DESCRIPTION, FINAL_REPORT_FLAG, ACTIVE_FLAG, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newReportCode, 'KFS Invoicing', 'N', 'N', 1, NOW(), 'admin', UUID());
		
	select max(cast(FREQUENCY_CODE as signed))+1 into newFrequencyCode from FREQUENCY;
	insert into FREQUENCY (FREQUENCY_CODE, DESCRIPTION, NUMBER_OF_DAYS, NUMBER_OF_MONTHS, REPEAT_FLAG, ADVANCE_NUMBER_OF_DAYS, 
			ADVANCE_NUMBER_OF_MONTHS, ACTIVE_FLAG, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID) 
		values (newFrequencyCode, 'Letter of Credit', null, null, 'N', null, null, 'N', 1, NOW(), 'admin', UUID());
		
	select max(VALID_CLASS_REPORT_FREQ_ID)+1 into newValidId from VALID_CLASS_REPORT_FREQ;
	insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidId, '6', newReportCode, newFrequencyCode, 1, NOW(), 'admin', UUID());
	
	select max(VALID_FREQUENCY_BASE_ID)+1 into newValidFreqId from VALID_FREQUENCY_BASE;
	insert into VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID, FREQUENCY_CODE, FREQUENCY_BASE_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidFreqId, newFrequencyCode, '6', 1, NOW(), 'admin', UUID());
		
	select max(VALID_CLASS_REPORT_FREQ_ID)+1 into newValidId from VALID_CLASS_REPORT_FREQ;
	insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidId, '6', newReportCode, '7', 1, NOW(), 'admin', UUID());
		
	select max(VALID_CLASS_REPORT_FREQ_ID)+1 into newValidId from VALID_CLASS_REPORT_FREQ;
	insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidId, '6', newReportCode, '2', 1, NOW(), 'admin', UUID());
		
	select max(VALID_CLASS_REPORT_FREQ_ID)+1 into newValidId from VALID_CLASS_REPORT_FREQ;
	insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidId, '6', newReportCode, '14', 1, NOW(), 'admin', UUID());

	select max(VALID_CLASS_REPORT_FREQ_ID)+1 into newValidId from VALID_CLASS_REPORT_FREQ;
	insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidId, '6', newReportCode, '4', 1, NOW(), 'admin', UUID());
		
	select max(VALID_CLASS_REPORT_FREQ_ID)+1 into newValidId from VALID_CLASS_REPORT_FREQ;
	insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidId, '6', newReportCode, '3', 1, NOW(), 'admin', UUID());
		
	select max(VALID_CLASS_REPORT_FREQ_ID)+1 into newValidId from VALID_CLASS_REPORT_FREQ;
	insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
		values (newValidId, '6', newReportCode, '6', 1, NOW(), 'admin', UUID());
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/

DELIMITER ;
