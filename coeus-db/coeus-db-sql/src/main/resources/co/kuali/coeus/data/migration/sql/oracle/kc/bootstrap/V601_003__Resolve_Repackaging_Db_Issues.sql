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

update eps_prop_columns_to_alter set LOOKUP_ARGUMENT = 'org.kuali.coeus.common.framework.noo.NoticeOfOpportunity' where LOOKUP_ARGUMENT = 'org.kuali.kra.bo.NoticeOfOpportunity'
/
update eps_prop_columns_to_alter set LOOKUP_ARGUMENT = 'org.kuali.coeus.common.framework.rolodex.Rolodex' where LOOKUP_ARGUMENT = 'org.kuali.kra.bo.Rolodex'
/
update eps_prop_columns_to_alter set LOOKUP_ARGUMENT = 'org.kuali.coeus.common.framework.type.DeadlineType' where LOOKUP_ARGUMENT = 'org.kuali.kra.proposaldevelopment.bo.DeadlineType'
/
update question set LOOKUP_CLASS = 'org.kuali.coeus.common.framework.person.KcPerson' where LOOKUP_CLASS = 'org.kuali.kra.bo.KcPerson'
/
update question set LOOKUP_CLASS = 'org.kuali.coeus.propdev.impl.attachment.Narrative' where LOOKUP_CLASS = 'org.kuali.kra.proposaldevelopment.bo.Narrative'
/
update question set LOOKUP_CLASS = 'org.kuali.coeus.propdev.impl.attachment.NarrativeAttachment' where LOOKUP_CLASS = 'org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment'
/

