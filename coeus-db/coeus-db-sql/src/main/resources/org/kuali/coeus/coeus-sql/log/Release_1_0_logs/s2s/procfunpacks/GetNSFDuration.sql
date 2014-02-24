create or replace procedure GetNSFDuration (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
  cur_generic IN OUT result_sets.cur_generic) IS
																
BEGIN

OPEN cur_generic for
 SELECT round(months_between(requested_end_date_initial,requested_start_date_initial)) DURATION
    FROM 	OSP$EPS_PROPOSAL 
    WHERE 	OSP$EPS_PROPOSAL.PROPOSAL_NUMBER = as_proposal_number;
	
end;


/

