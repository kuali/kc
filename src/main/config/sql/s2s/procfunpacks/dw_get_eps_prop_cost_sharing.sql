/**********************************************************************
This procedure selects all rows from OSP$EPS_PROP_COST_SHARING table
by a given proposal_number and version_number.
**********************************************************************/
create or replace procedure dw_get_eps_prop_cost_sharing
   (  AW_PROPOSAL_NUMBER IN OSP$EPS_PROP_COST_SHARING.PROPOSAL_NUMBER%TYPE,	
		AW_VERSION_NUMBER IN OSP$EPS_PROP_COST_SHARING.VERSION_NUMBER%TYPE,  
		cur_generic IN OUT result_sets.cur_generic) is 

begin

open cur_generic for

  SELECT *  
  FROM OSP$EPS_PROP_COST_SHARING
  WHERE 	PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER
	AND   VERSION_NUMBER = AW_VERSION_NUMBER
	order by FISCAL_YEAR asc;
                   
end;

/


