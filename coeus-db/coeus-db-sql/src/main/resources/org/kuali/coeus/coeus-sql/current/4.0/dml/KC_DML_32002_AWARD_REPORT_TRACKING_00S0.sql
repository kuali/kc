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

INSERT INTO AWARD_REPORT_TRACKING (AWARD_REPORT_TERM_ID, AWARD_NUMBER, PI_PERSON_ID, PI_ROLODEX_ID, PI_NAME, 
								   LEAD_UNIT_NUMBER, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, FREQUENCY_BASE_CODE, 
								   OSP_DISTRIBUTION_CODE, STATUS_CODE, BASE_DATE, DUE_DATE, ACTIVITY_DATE, OVERDUE, COMMENTS, 
								   PREPARER_ID, PREPARER_NAME, SPONSOR_CODE, SPONSOR_AWARD_NUMBER, TITLE, 
								   LAST_UPDATE_USER, LAST_UPDATE_DATE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
	VALUES ((select AWARD_REPORT_TERMS_ID from AWARD_REPORT_TERMS where AWARD_NUMBER = '001002-00001' 
													and SEQUENCE_NUMBER = '3' and REPORT_CLASS_CODE = '1' 
													and REPORT_CODE = '5' and FREQUENCY_CODE = '12' and FREQUENCY_BASE_CODE = '4'),		 
								  '001002-00001', '10000000002', null, 'Joe Tester', 
								  '000001', '1', '5', '12', '4', 
								  '2', '1', to_date('12/31/2015', 'mm/dd/yyyy'), to_date('3/30/2016', 'mm/dd/yyyy'), null, 0, null,
								  null, null, '000340', 'SD300120', 'Root Award for testing Sync Descendants',
								  'quickstart', sysdate, sysdate, 'admin', 0, sys_guid())
/
								  
INSERT INTO AWARD_REPORT_TRACKING (AWARD_REPORT_TERM_ID, AWARD_NUMBER, PI_PERSON_ID, PI_ROLODEX_ID, PI_NAME, 
								   LEAD_UNIT_NUMBER, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, FREQUENCY_BASE_CODE, 
								   OSP_DISTRIBUTION_CODE, STATUS_CODE, BASE_DATE, DUE_DATE, ACTIVITY_DATE, OVERDUE, COMMENTS, 
								   PREPARER_ID, PREPARER_NAME, SPONSOR_CODE, SPONSOR_AWARD_NUMBER, TITLE, 
								   LAST_UPDATE_USER, LAST_UPDATE_DATE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
	VALUES ((select AWARD_REPORT_TERMS_ID from AWARD_REPORT_TERMS where AWARD_NUMBER = '001002-00001' 
													and SEQUENCE_NUMBER = '3' and REPORT_CLASS_CODE = '1' 
													and REPORT_CODE = '33' and FREQUENCY_CODE = '7' and FREQUENCY_BASE_CODE = '3'),
								  '001002-00001', '10000000002', null, 'Joe Tester', 
								  '000001', '1', '33', '7', '3', 
								  '4', '1', null, null, null, 0, null,
								  null, null, '000340', 'SD300120', 'Root Award for testing Sync Descendants',
								  'quickstart', sysdate, sysdate, 'admin', 0, sys_guid())
/
								  
INSERT INTO AWARD_REPORT_TRACKING (AWARD_REPORT_TERM_ID, AWARD_NUMBER, PI_PERSON_ID, PI_ROLODEX_ID, PI_NAME, 
								   LEAD_UNIT_NUMBER, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, FREQUENCY_BASE_CODE, 
								   OSP_DISTRIBUTION_CODE, STATUS_CODE, BASE_DATE, DUE_DATE, ACTIVITY_DATE, OVERDUE, COMMENTS, 
								   PREPARER_ID, PREPARER_NAME, SPONSOR_CODE, SPONSOR_AWARD_NUMBER, TITLE, 
								   LAST_UPDATE_USER, LAST_UPDATE_DATE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
	VALUES ((select AWARD_REPORT_TERMS_ID from AWARD_REPORT_TERMS where AWARD_NUMBER = '001002-00001' 
													and SEQUENCE_NUMBER = '3' and REPORT_CLASS_CODE = '3' 
													and REPORT_CODE = '7' and FREQUENCY_CODE = '6' and FREQUENCY_BASE_CODE = '2'),
								  '001002-00001', '10000000002', null, 'Joe Tester', 
								  '000001', '3', '7', '6', '2', 
								  '4', '1', null, null, null, 0, null,
								  null, null, '000340', 'SD300120', 'Root Award for testing Sync Descendants',
								  'quickstart', sysdate, sysdate, 'admin', 0, sys_guid())
/
								  
INSERT INTO AWARD_REPORT_TRACKING ( AWARD_REPORT_TERM_ID, AWARD_NUMBER, PI_PERSON_ID, PI_ROLODEX_ID, PI_NAME, 
								   LEAD_UNIT_NUMBER, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, FREQUENCY_BASE_CODE, 
								   OSP_DISTRIBUTION_CODE, STATUS_CODE, BASE_DATE, DUE_DATE, ACTIVITY_DATE, OVERDUE, COMMENTS, 
								   PREPARER_ID, PREPARER_NAME, SPONSOR_CODE, SPONSOR_AWARD_NUMBER, TITLE, 
								   LAST_UPDATE_USER, LAST_UPDATE_DATE, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
	VALUES ((select AWARD_REPORT_TERMS_ID from AWARD_REPORT_TERMS where AWARD_NUMBER = '001002-00001' 
													and SEQUENCE_NUMBER = '3' and REPORT_CLASS_CODE = '3' 
													and REPORT_CODE = '36' and FREQUENCY_CODE = '30' and FREQUENCY_BASE_CODE = '3'),
								  '001002-00001', '10000000002', null, 'Joe Tester', 
								  '000001', '3', '36', '30', '3', 
								  '1', '1', null, null, null, 0, null,
								  null, null, '000340', 'SD300120', 'Root Award for testing Sync Descendants',
								  'quickstart', sysdate, sysdate, 'admin', 0, sys_guid())
/
