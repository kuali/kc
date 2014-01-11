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