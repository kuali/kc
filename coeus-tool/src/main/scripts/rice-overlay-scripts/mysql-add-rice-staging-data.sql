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

/*Need staging data is unique to integration*/
use krdev;
update KRCR_PARM_T set VAL = 'ON' where parm_nm = 'FIN_SYSTEM_INTEGRATION_ON';
update KRCR_PARM_T set VAL = '2' where parm_nm = 'enable.award.FnA.validation';
-- update KRCR_PARM_T set VAL = 'http://test.kc.kuali.org/kc-wkly' where parm_nm = 'kuali.docHandler.url.prefix';
update KRCR_PARM_T set VAL = '0 0 0/2 * * ?' WHERE PARM_NM = 'CFDA_BATCH_JOB_CRON_EXPRESSION';
commit;
