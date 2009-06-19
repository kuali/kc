/********************************************************************
This procedure selects proposal_number and science_code from proposal_
science_code and description from science_code for a given proposal
number.
********************************************************************/

create or replace procedure dw_get_science_codes_for_prop
   ( AW_PROPOSAL_NUMBER IN OSP$EPS_PROP_SCIENCE_CODE.PROPOSAL_NUMBER%TYPE,
	  cur_generic IN OUT result_sets.cur_generic) is 

begin

open cur_generic for
  SELECT P.PROPOSAL_NUMBER,   
         P.SCIENCE_CODE,
			P.UPDATE_TIMESTAMP,
			P.UPDATE_USER,   
         S.DESCRIPTION  
    FROM OSP$EPS_PROP_SCIENCE_CODE P,   
         OSP$SCIENCE_CODE S
   WHERE P.SCIENCE_CODE = S.SCIENCE_CODE
	  AND P.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER ;
end;

/


