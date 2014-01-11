--Procedure to retrieve explanations of a yesNo question


create or replace procedure dw_get_ynq_explanation
   ( as_question_id in osp$ynq_explanation.question_id%TYPE,
     cur_ynq IN OUT result_sets.cur_generic) is 

begin
open cur_ynq for
SELECT *
    FROM OSP$YNQ_EXPLANATION
WHERE QUESTION_ID = as_question_id;
end;

/


