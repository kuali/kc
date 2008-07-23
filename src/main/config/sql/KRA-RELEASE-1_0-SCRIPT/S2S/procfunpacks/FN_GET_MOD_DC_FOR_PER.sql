create or replace FUNCTION          "FN_GET_MOD_DC_FOR_PER" (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
														ai_version_number osp$budget.version_number%type,
														 ai_budget_period osp$budget_periods.budget_period%type
                                          )
---------------------------------------------------
-- used for proposal printing
----------------------------------------------------

 return  number is



 li_direct number := 0;

begin


   select decode(sum(TOTAL_DIRECT_COST),null,0,sum(TOTAL_DIRECT_COST))
   into   li_direct
   from  osp$budget_modular
   where proposal_number = as_proposal_number
   and   version_number = ai_version_number
   and   budget_period = ai_budget_period;




	return li_direct;
end;
 
/