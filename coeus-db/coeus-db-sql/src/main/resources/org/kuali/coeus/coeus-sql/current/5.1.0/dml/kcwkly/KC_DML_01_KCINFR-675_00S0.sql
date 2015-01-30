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

--Demo data for icr type code
insert into FIN_IDC_TYPE_CODE (FIN_IDC_CODE, RATE_TYPE_CODE, OBJ_ID, VER_NBR, RATE_CLASS_CODE) values (22, 1, sys_guid(), 1, 1)
/
insert into FIN_IDC_TYPE_CODE (FIN_IDC_CODE, RATE_TYPE_CODE, OBJ_ID, VER_NBR, RATE_CLASS_CODE) values (11, 1, sys_guid(), 1, 2)
/
insert into FIN_IDC_TYPE_CODE (FIN_IDC_CODE, RATE_TYPE_CODE, OBJ_ID, VER_NBR, RATE_CLASS_CODE) values (22, 1, sys_guid(), 1, 13)
/
-- Valid rates
update VALID_RATES set ICR_RATE_CODE = '000' where VALID_RATES_ID < 11
/
update VALID_RATES set ICR_RATE_CODE = '018' where VALID_RATES_ID > 11 and VALID_RATES_ID <= 21
/
update VALID_RATES set ICR_RATE_CODE = '031' where VALID_RATES_ID > 21 and VALID_RATES_ID <= 31
/
update VALID_RATES set ICR_RATE_CODE = '056' where VALID_RATES_ID > 31 and VALID_RATES_ID <= 41
/
update VALID_RATES set ICR_RATE_CODE = '093' where VALID_RATES_ID > 41 and VALID_RATES_ID <= 51
/
update VALID_RATES set ICR_RATE_CODE = '041' where VALID_RATES_ID > 51 and VALID_RATES_ID <= 61
/
update VALID_RATES set ICR_RATE_CODE = '068' where VALID_RATES_ID > 61
/
delete from KRNS_DOC_HDR_T where FDOC_DESC = '*****PLACEHOLDER*****'
/
