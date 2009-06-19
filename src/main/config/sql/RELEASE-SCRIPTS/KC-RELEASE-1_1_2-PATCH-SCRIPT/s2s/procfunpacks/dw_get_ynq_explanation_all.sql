--Procedure to retrieve explanations of all yesNo questions


create or replace procedure dw_get_ynq_explanation_all
   ( cur_ynq IN OUT result_sets.cur_generic) is 

begin
open cur_ynq for
SELECT *
    FROM OSP$YNQ_EXPLANATION;
end;

/


