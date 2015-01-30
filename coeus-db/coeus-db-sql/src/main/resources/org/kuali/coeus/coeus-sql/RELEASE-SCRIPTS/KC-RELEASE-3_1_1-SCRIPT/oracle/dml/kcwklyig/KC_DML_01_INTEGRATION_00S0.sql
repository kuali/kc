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

UPDATE award_approved_foreign_travel SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE award_persons SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE award_unit_contacts SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE budget_per_det_rate_and_base SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE budget_personnel_details SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE budget_persons SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE comm_memberships SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE comm_schedule_attendance  SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE eps_prop_person SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE eps_prop_person_bio SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE kra_user SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE person_appointment SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE person_degree SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE person_ext_t SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE person_training SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE prop_role_template SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE proposal_persons SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE proposal_unit_contacts SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE proposal_unit_credit_split SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE proposal_units SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE protocol_attachment_personnel SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE protocol_persons SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE protocol_reviewers SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE protocol_units SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE protocol_vote_abstainees SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE protocol_vote_recused SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
UPDATE unit_administrator SET PERSON_ID = 'quickstart' WHERE PERSON_ID = '10000000001'
/
COMMIT
/
