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

delimiter /

drop procedure if exists fix_questions
/

create procedure fix_questions()
begin
declare questionIdRef, notFound int default 0;
declare oldQuestions cursor for 
  select QUESTION_REF_ID from QUESTION q1 join 
    (select QUESTION_ID, max(SEQUENCE_NUMBER) as max_sequence_number from QUESTION group by QUESTION_ID) q2 
      on q1.QUESTION_ID = q2.QUESTION_ID 
    where SEQUENCE_NUMBER < max_sequence_number;
declare continue handler for not found set notFound = 1;

open oldQuestions;

fetch oldQuestions into questionIdRef;
while notFound = 0 do
  update QUESTION set SEQUENCE_STATUS = 'A' where QUESTION_REF_ID = questionIdRef;
  fetch oldQuestions into questionIdRef;
end while;

close oldQuestions;
end
/

call fix_questions
/

drop procedure if exists fix_questions
/

delimiter ;
