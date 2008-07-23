create or replace function FnGetInventions (AS_PROPOSAL_NUMBER IN  OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE) 
return varchar2 is

ls_answer varchar2(2);

Begin

SELECT 	answer as INVENTIONS
INTO	   ls_answer
FROM 	   OSP$EPS_PROP_YNQ 
WHERE 	PROPOSAL_NUMBER = as_proposal_number
AND      OSP$EPS_PROP_YNQ.QUESTION_ID = '16';
   
return ls_answer;

EXCEPTION
		WHEN NO_DATA_FOUND THEN
			return 'X';

end;
/

