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

update KRIM_ROLE_T set ROLE_ID = 'KR1001' where ROLE_NM = 'Complete Request Recipient' and NMSPC_CD = 'KR-WKFLW'
/
UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where ROLE_PERM_ID = 'KR1000'
/
UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where ROLE_PERM_ID = 'KR1001'
/

