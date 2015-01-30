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

create or replace view OSP$ROLODEX as
 select ROLODEX_ID, LAST_NAME, FIRST_NAME, MIDDLE_NAME, SUFFIX, PREFIX, TITLE, ORGANIZATION,         
 ADDRESS_LINE_1, ADDRESS_LINE_2, ADDRESS_LINE_3, FAX_NUMBER, EMAIL_ADDRESS, CITY,                 
 COUNTY, STATE, POSTAL_CODE, COMMENTS,  PHONE_NUMBER, COUNTRY_CODE, SPONSOR_CODE,         
 OWNED_BY_UNIT, SPONSOR_ADDRESS_FLAG, DELETE_FLAG, CREATE_USER, UPDATE_TIMESTAMP, UPDATE_USER           
 from ROLODEX;
