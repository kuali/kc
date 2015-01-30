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

create or replace view OSP$S2S_OPPORTUNITY as 
	select PROPOSAL_NUMBER,OPPORTUNITY_TITLE,COMPETETION_ID,OPENING_DATE,CLOSING_DATE,SCHEMA_URL,
	INSTRUCTION_URL,OPPORTUNITY_ID,OPPORTUNITY,CFDA_NUMBER,S2S_SUBMISSION_TYPE_CODE,
	REVISION_CODE,REVISION_OTHER_DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER
	from S2S_OPPORTUNITY;
