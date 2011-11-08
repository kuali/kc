create or replace procedure dw_get_ynq_list
   ( as_question_type IN osp$ynq.question_type%TYPE,
     cur_ynq IN OUT result_sets.cur_generic) is 

begin
open cur_ynq for

    SELECT OSP$YNQ.QUESTION_ID,   
         OSP$YNQ.DESCRIPTION,   
         OSP$YNQ.NO_OF_ANSWERS,   
         OSP$YNQ.EXPLANATION_REQUIRED_FOR,   
         OSP$YNQ.DATE_REQUIRED_FOR
    FROM OSP$YNQ
	WHERE UPPER(OSP$YNQ.QUESTION_TYPE) = UPPER(as_question_type)
	  AND OSP$YNQ.STATUS = 'A' 
	 order by question_id asc;

end;

/



