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

UPDATE KRCR_PARM_T SET VAL = 'kcnotification@gmail.com' WHERE NMSPC_CD = 'KR-WKFLW' AND PARM_NM = 'EMAIL_NOTIFICATION_TEST_ADDRESS'
/
UPDATE KRCR_PARM_T SET VAL = 'Y' WHERE NMSPC_CD = 'KR-WKFLW' AND PARM_NM = 'SEND_EMAIL_NOTIFICATION_IND'
/
UPDATE KRCR_PARM_T SET VAL = 'kc.grants.gov@kuali.org' WHERE NMSPC_CD = 'KR-WKFLW' AND PARM_NM = 'FROM_ADDRESS'
/
