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

UPDATE KRIM_ROLE_T
SET ACTV_IND = 'N'
WHERE ROLE_NM = 'IACUCApprover'
/


UPDATE KRIM_ROLE_RSP_T
SET ACTV_IND = 'N'
WHERE role_id = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator')
AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUC Approve')
/

UPDATE KRIM_ROLE_RSP_T
SET ACTV_IND = 'N'
WHERE role_id = (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator')
AND RSP_ID = (SELECT RSP_ID FROM KRIM_RSP_T WHERE NM = 'IACUCReview')
/

DELIMITER ;
