/********************************************************************
This procedure selects from OSP$YNQ for a given question_type and for
status of active. An expression is also selected as a placeholder
for the answer.
********************************************************************/

CREATE OR REPLACE procedure dw_get_ynq_for_qtype
   ( AW_QUESTION_TYPE IN OSP$YNQ.QUESTION_TYPE%TYPE,
	  cur_generic IN OUT result_sets.cur_generic) is
begin
open cur_generic for
SELECT  QUESTION_ID,
	DESCRIPTION,
	NO_OF_ANSWERS,
	EXPLANATION_REQUIRED_FOR, --enh065
	' ' ANSWER,
	' ' ANSWER_2_CHOICES,
	' ' ANSWER_3_CHOICES,
	STATUS -- Added for Case # 2698 - for getting Question Status
FROM OSP$YNQ
WHERE QUESTION_TYPE = AW_QUESTION_TYPE;
   -- AND STATUS = 'A' ; Commented for Case # 2698 - for getting all the questions
   
end;
/

