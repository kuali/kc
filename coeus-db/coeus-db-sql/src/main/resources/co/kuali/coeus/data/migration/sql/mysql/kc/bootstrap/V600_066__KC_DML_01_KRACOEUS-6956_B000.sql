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
UPDATE KC_KRMS_TERM_FUN_PARAM SET PARAM_TYPE='org.kuali.coeus.propdev.impl.core.DevelopmentProposal' WHERE PARAM_NAME='DevelopmentProposal'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET LOOKUP_ARGUMENT='org.kuali.coeus.common.framework.type.ActivityType' WHERE COLUMN_NAME ='ACTIVITY_TYPE_CODE'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET LOOKUP_ARGUMENT='org.kuali.coeus.common.framework.type.DeadlineType' WHERE COLUMN_NAME ='DEADLINE_DATE'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET LOOKUP_ARGUMENT='org.kuali.coeus.common.framework.type.ProposalType' WHERE COLUMN_NAME ='PROPOSAL_TYPE_CODE'
/

DELIMITER ;
