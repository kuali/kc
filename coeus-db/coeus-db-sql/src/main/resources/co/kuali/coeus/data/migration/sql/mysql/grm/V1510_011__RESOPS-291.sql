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
	
update KRMS_PROP_PARM_T set PARM_VAL = DATE_FORMAT(NOW(), '%m/%d/%Y %H:%i:%s') 
	where PROP_ID = (select PROP_ID from KRMS_RULE_T where NMSPC_CD = 'KC-PD' and NM = 'Proposal Development Standard Workflow')
		and SEQ_NO = 1 and PARM_TYP_CD = 'C';
		
update KRMS_AGENDA_T set ACTV = 'Y' where CNTXT_ID = 'KC-PD-CONTEXT' and NM = 'Proposal Development Standard Workflow';
