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

DELIMITER ;

drop procedure if exists p;

delimiter //
create procedure p ()
begin
  declare l_new_seq INT;

  select ifnull(max(cast(QUESTION_ID as SIGNED)),'1') + 1 into l_new_seq from QUESTION;
  set @create_seq := 'CREATE TABLE IF NOT EXISTS SEQ_QUESTION_ID (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE SEQ_QUESTION_ID auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
  select ifnull(max(cast(QUESTIONNAIRE_ID as SIGNED)),'1') + 1 into l_new_seq from QUESTIONNAIRE;
  set @create_seq := 'CREATE TABLE IF NOT EXISTS SEQ_QUESTIONNAIRE_ID (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE SEQ_QUESTIONNAIRE_ID auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;

  select ifnull(max(cast(ID as SIGNED)),'1') + 1 into l_new_seq from 
    (select max(cast(QUESTION_REF_ID as SIGNED)) as ID from QUESTION 
      union select max(cast(QUESTION_EXPLANATION_ID as SIGNED)) as ID from QUESTION_EXPLANATION
      union select max(cast(QUESTIONNAIRE_ANSWER_HEADER_ID as SIGNED)) as ID from QUESTIONNAIRE_ANSWER_HEADER
      union select max(cast(QUESTIONNAIRE_ANSWER_ID as SIGNED)) as ID from QUESTIONNAIRE_ANSWER
      union select max(cast(QUESTIONNAIRE_REF_ID as SIGNED)) as ID from QUESTIONNAIRE
      union select max(cast(QUESTIONNAIRE_QUESTIONS_ID as SIGNED)) as ID from QUESTIONNAIRE_QUESTIONS
      union select max(cast(QUESTIONNAIRE_USAGE_ID as SIGNED)) as ID from QUESTIONNAIRE_USAGE) as t1;
  set @create_seq := 'CREATE TABLE IF NOT EXISTS SEQ_QUESTIONNAIRE_REF_ID (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM';
  prepare create_seq_stmt from @create_seq;
  execute create_seq_stmt;
  deallocate prepare create_seq_stmt;
  set @alter_seq := concat('ALTER TABLE SEQ_QUESTIONNAIRE_REF_ID auto_increment = ', l_new_seq);
  prepare alter_seq_stmt from @alter_seq;
  execute alter_seq_stmt;
  deallocate prepare alter_seq_stmt;
  
end //
delimiter ;

call p ();

drop procedure if exists p;
