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
UPDATE COMM_MEMBER_EXPERTISE
SET IACUC_RESEARCH_AREA_CODE = '000001'
WHERE COMM_MEMBERSHIP_ID_FK IN (SELECT COMM_MEMBERSHIP_ID FROM COMM_MEMBERSHIPS
WHERE COMMITTEE_ID_FK = (SELECT id FROM committee
WHERE committee_id = 'KC002' AND committee_name = 'KC IACUC 1'))
/

DELIMITER ;
