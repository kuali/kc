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

update questionnaire_questions set question_ref_id_fk = (select question_ref_id from question where question_id = 2 and sequence_number = 2) where question_number = 1 and question_ref_id_fk = (select question_ref_id from question where question_id = 2 and sequence_number = 1) and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_ref_id_fk = (select question_ref_id from question where question_id = 3 and sequence_number = 2) where question_number = 2 and question_ref_id_fk = (select question_ref_id from question where question_id = 3 and sequence_number = 1) and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_ref_id_fk = (select question_ref_id from question where question_id = 5 and sequence_number = 2) where question_number = 7 and question_ref_id_fk = (select question_ref_id from question where question_id = 5 and sequence_number = 1) and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_ref_id_fk = (select question_ref_id from question where question_id = 6 and sequence_number = 2) where question_number = 8 and question_ref_id_fk = (select question_ref_id from question where question_id = 6 and sequence_number = 1) and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set parent_question_number = 7 where question_number = 9 and parent_question_number = 8 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set condition_type = 4 where question_number = 2 and condition_type = 3 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_seq_number = 2 where question_number = 3 and question_seq_number = 1 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_seq_number = 3 where question_number = 7 and question_seq_number = 1 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_seq_number = 2 where question_number = 9 and question_seq_number = 1 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_seq_number = 4 where question_number = 10 and question_seq_number = 1 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_seq_number = 5 where question_number = 13 and question_seq_number = 1 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);
update questionnaire_questions set question_seq_number = 6 where question_number = 15 and question_seq_number = 1 and QUESTIONNAIRE_REF_ID_FK = (select QUESTIONNAIRE_REF_ID from questionnaire where QUESTIONNAIRE_ID = -1);