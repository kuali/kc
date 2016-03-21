--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

update valid_narr_forms set mandatory = 'N' where NARRATIVE_TYPE_CODE in ('146', '147');

update question set question_id = '143' where QUESTION = 'Select a Funding Mechanism';
update question set question_id = '144' where QUESTION = 'Have lobbying activities been conducted on behalf of this proposal? Disclosure of Lobbying Activities (GPG, Chapter II.C.1.e)';
