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
INSERT INTO COI_DISCLOSURE_EVENT_TYPE
(EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER,
 VER_NBR, OBJ_ID, EXCLUDE_FROM_MASTER_DISCL, ACTIVE_FLAG,
 PROJECT_ID_LABEL, PROJECT_TITLE_LABEL,
 USE_SHRT_TXT_FLD_1, REQ_SHRT_TXT_FLD_1, SHRT_TXT_FLD_1_LABEL,
 USE_LNG_TXT_FLD_1, REQ_LNG_TXT_FLD_1, LNG_TXT_FLD_1_LABEL,
 USE_LNG_TXT_FLD_2, REQ_LNG_TXT_FLD_2, LNG_TXT_FLD_2_LABEL,
 USE_LNG_TXT_FLD_3, REQ_LNG_TXT_FLD_3, LNG_TXT_FLD_3_LABEL,
 USE_DATE_FLD_1, REQ_DATE_FLD_1, DATE_FLD_1_LABEL,
 USE_DATE_FLD_2, REQ_DATE_FLD_2, DATE_FLD_2_LABEL,
 USE_NMBR_FLD_1, REQ_NMBR_FLD_1, NMBR_FLD_1_LABEL)
 VALUES
 ('15', 'Travel Disclosure', NOW(), 'admin', 1, UUID(), 'N', 'Y',
  'Event Id', 'Event Title', 
  'Y', 'Y', 'Destination/Location of Travel',
  'Y', 'Y', 'Entity Name',
  'Y', 'Y', 'Travel Sponsor',
  'Y', 'Y', 'Purpose of Travel',
  'Y', 'Y', 'Start Date',
  'Y', 'Y', 'End Date',
  'Y', 'Y', 'Amount of reimbursement received')
/
DELIMITER ;
