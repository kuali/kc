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

CREATE OR REPLACE VIEW OSP$AWARD 
("MIT_AWARD_NUMBER", 
"SEQUENCE_NUMBER", 
"MODIFICATION_NUMBER", 
"SPONSOR_AWARD_NUMBER", 
"STATUS_CODE", 
"TEMPLATE_CODE", 
"AWARD_EXECUTION_DATE", 
"AWARD_EFFECTIVE_DATE", 
"BEGIN_DATE", 
"SPONSOR_CODE", 
"ACCOUNT_NUMBER", 
"APPRVD_EQUIPMENT_INDICATOR", 
"APPRVD_FOREIGN_TRIP_INDICATOR", 
"APPRVD_SUBCONTRACT_INDICATOR", 
"PAYMENT_SCHEDULE_INDICATOR", 
"IDC_INDICATOR", 
"TRANSFER_SPONSOR_INDICATOR", 
"COST_SHARING_INDICATOR", 
"UPDATE_TIMESTAMP", 
"UPDATE_USER", 
"SPECIAL_REVIEW_INDICATOR", 
"SCIENCE_CODE_INDICATOR", 
"NSF_CODE") AS 
  select
  AWARD_NUMBER,
  SEQUENCE_NUMBER,
  MODIFICATION_NUMBER,
  SPONSOR_AWARD_NUMBER,
  STATUS_CODE,
  TEMPLATE_CODE,
  AWARD_EXECUTION_DATE,
  AWARD_EFFECTIVE_DATE,
  BEGIN_DATE,
  SPONSOR_CODE,
  ACCOUNT_NUMBER,
  APPRVD_EQUIPMENT_INDICATOR,
  APPRVD_FOREIGN_TRIP_INDICATOR,
  APPRVD_SUBCONTRACT_INDICATOR,
  PAYMENT_SCHEDULE_INDICATOR,
  IDC_INDICATOR,
  TRANSFER_SPONSOR_INDICATOR,
  COST_SHARING_INDICATOR,
  UPDATE_TIMESTAMP,
  UPDATE_USER,
  SPECIAL_REVIEW_INDICATOR,
  SCIENCE_CODE_INDICATOR,
  NSF_CODE
from award;

