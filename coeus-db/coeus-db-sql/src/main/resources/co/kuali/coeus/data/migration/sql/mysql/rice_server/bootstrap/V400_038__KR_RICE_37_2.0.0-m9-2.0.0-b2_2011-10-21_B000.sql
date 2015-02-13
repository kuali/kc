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

-- KULRICE-5348
alter table KREW_RULE_T change PREV_RULE_VER_NBR PREV_VER_RULE_ID varchar(40)
/

-- KULRICE-4589
UPDATE KRCR_PARM_T
SET PARM_NM='NOTIFY_GROUPS',
    PARM_DESC_TXT='Defines a group name (in the format "namespace:name") which contains members who should never receive "notifications" action requests from KEW.',
    EVAL_OPRTR_CD='D'
WHERE NMSPC_CD = 'KR-WKFLW'
  AND CMPNT_CD = 'Notification'
  AND PARM_NM = 'NOTIFY_EXCLUDED_USERS_IND'
/
DELIMITER ;
