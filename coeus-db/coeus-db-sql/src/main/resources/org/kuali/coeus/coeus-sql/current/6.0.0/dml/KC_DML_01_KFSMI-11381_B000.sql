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

insert into REPORT (REPORT_CODE, DESCRIPTION, FINAL_REPORT_FLAG, ACTIVE_FLAG, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(cast(REPORT_CODE as int))+1 from REPORT), 'KFS Invoicing', 'N', 'N', 1, SYSDATE, 'admin', SYS_GUID())
/

insert into FREQUENCY (FREQUENCY_CODE, DESCRIPTION, NUMBER_OF_DAYS, NUMBER_OF_MONTHS, REPEAT_FLAG, ADVANCE_NUMBER_OF_DAYS, 
		ADVANCE_NUMBER_OF_MONTHS, ACTIVE_FLAG, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID) 
	values ((select max(cast(FREQUENCY_CODE as int))+1 from FREQUENCY), 'Letter of Credit', null, null, 'N', null, null, 'N', 1, SYSDATE, 'admin', SYS_GUID())
/

insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_CLASS_REPORT_FREQ_ID)+1 from VALID_CLASS_REPORT_FREQ), '6', 
	(select max(REPORT_CODE) from REPORT), 
	(select max(FREQUENCY_CODE) from FREQUENCY), 1, SYSDATE, 'admin', SYS_GUID())
/

insert into VALID_FREQUENCY_BASE (VALID_FREQUENCY_BASE_ID, FREQUENCY_CODE, FREQUENCY_BASE_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_FREQUENCY_BASE_ID)+1 from VALID_FREQUENCY_BASE), (select max(FREQUENCY_CODE) from FREQUENCY), '6', 1, SYSDATE, 'admin', SYS_GUID())
/

insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_CLASS_REPORT_FREQ_ID)+1 from VALID_CLASS_REPORT_FREQ), '6', 
	(select max(REPORT_CODE) from REPORT), '7', 1, SYSDATE, 'admin', SYS_GUID())
/
	
insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_CLASS_REPORT_FREQ_ID)+1 from VALID_CLASS_REPORT_FREQ), '6', 
	(select max(REPORT_CODE) from REPORT), '2', 1, SYSDATE, 'admin', SYS_GUID())
/
	
insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_CLASS_REPORT_FREQ_ID)+1 from VALID_CLASS_REPORT_FREQ), '6', 
	(select max(REPORT_CODE) from REPORT), '14', 1, SYSDATE, 'admin', SYS_GUID())
/

insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_CLASS_REPORT_FREQ_ID)+1 from VALID_CLASS_REPORT_FREQ), '6', 
	(select max(REPORT_CODE) from REPORT), '4', 1, SYSDATE, 'admin', SYS_GUID())
/
	
insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_CLASS_REPORT_FREQ_ID)+1 from VALID_CLASS_REPORT_FREQ), '6', 
	(select max(REPORT_CODE) from REPORT), '3', 1, SYSDATE, 'admin', SYS_GUID())
/
	
insert into VALID_CLASS_REPORT_FREQ (VALID_CLASS_REPORT_FREQ_ID, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, VER_NBR, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID)
	values ((select max(VALID_CLASS_REPORT_FREQ_ID)+1 from VALID_CLASS_REPORT_FREQ), '6', 
	(select max(REPORT_CODE) from REPORT), '6', 1, SYSDATE, 'admin', SYS_GUID())
/

