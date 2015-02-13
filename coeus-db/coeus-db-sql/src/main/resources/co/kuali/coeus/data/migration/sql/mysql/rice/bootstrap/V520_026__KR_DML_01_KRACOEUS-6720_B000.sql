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

UPDATE KRMS_FUNC_T SET DESC_TXT='Checks if the opportunity is already selected for the proposal' WHERE NM='proposalGrantsRule'
/
UPDATE KRMS_TERM_SPEC_T SET DESC_TXT='Is S2S Submission' WHERE nm=(select FUNC_ID from KRMS_FUNC_T where NM='proposalGrantsRule')
/
UPDATE KRMS_TERM_T SET DESC_TXT='Is S2S Submission' WHERE TERM_SPEC_ID=(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where DESC_TXT='Is S2S Submission')
/
commit
/

DELIMITER ;
