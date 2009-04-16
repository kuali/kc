
--NSF extension for printing
create or replace procedure GetH4LobbyQuestion
   ( AW_PROPOSAL_NUMBER IN OSP$EPS_PROP_ABSTRACT.PROPOSAL_NUMBER%TYPE, 
     cur_generic IN OUT result_sets.cur_generic) is 

begin 

OPEN CUR_GENERIC FOR
	 SELECT 	count(*) COUNT
    FROM 	OSP$EPS_PROP_PERS_YNQ 
    WHERE 	OSP$EPS_PROP_PERS_YNQ.PROPOSAL_NUMBER = aw_proposal_number
	 AND     OSP$EPS_PROP_PERS_YNQ.QUESTION_ID = 'H4'
    AND     OSP$EPS_PROP_PERS_YNQ.ANSWER = 'Y';
end;

/



