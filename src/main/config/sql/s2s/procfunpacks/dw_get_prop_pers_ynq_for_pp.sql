/********************************************************************
This procedure selects * from OSP$EPS_PROP_PERS_YNQ for a given
proposal number and person_id combination.
********************************************************************/

create or replace procedure dw_get_prop_pers_ynq_for_pp
   ( AW_PROPOSAL_NUMBER IN OSP$EPS_PROP_PERS_YNQ.PROPOSAL_NUMBER%TYPE,
     AW_PERSON_ID IN OSP$EPS_PROP_PERS_YNQ.PERSON_ID%TYPE,
	  cur_generic IN OUT result_sets.cur_generic) is 

begin

open cur_generic for

  SELECT PROPOSAL_NUMBER,
         PERSON_ID,
         QUESTION_ID,
         ANSWER,
         UPDATE_TIMESTAMP,
         UPDATE_USER
    FROM OSP$EPS_PROP_PERS_YNQ
   WHERE PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER
	  AND PERSON_ID = AW_PERSON_ID ;

end;

/




