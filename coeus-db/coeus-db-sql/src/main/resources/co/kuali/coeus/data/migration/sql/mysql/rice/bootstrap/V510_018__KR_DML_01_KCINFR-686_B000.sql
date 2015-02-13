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
update KRCR_PARM_T set PARM_DESC_TXT='The cfda cron job is set to run at 6am every morning as the default. For a detailed explanation on the cron trigger and to modify it, please view the documentation on cron triggers in the quartz scheduler documentation, here http://quartz-scheduler.org/documentation/quartz-1.x/tutorials/crontrigger' WHERE PARM_NM = 'CFDA_BATCH_JOB_CRON_EXPRESSION'
/

DELIMITER ;
