create or replace procedure dw_get_proposal_ynq
   ( as_proposal_num IN OSP$EPS_PROP_YNQ.proposal_number%TYPE,
     cur_proposal_ynq IN OUT result_sets.cur_generic) is 

begin
open cur_proposal_ynq for
  SELECT * 
    FROM OSP$EPS_PROP_YNQ
   WHERE UPPER(OSP$EPS_PROP_YNQ.PROPOSAL_NUMBER) = UPPER(as_proposal_num);
end;

/



