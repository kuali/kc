CREATE OR REPLACE FUNCTION FN_GET_MOD_IDC_FOR_PER (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
														ai_version_number osp$budget.version_number%type,
														 ai_budget_period osp$budget_periods.budget_period%type
                                          )
---------------------------------------------------
-- used for proposal printing
----------------------------------------------------

 return  number is



 li_idc number := 0;

begin


   select decode(sum(funds_requested),null,0,sum(funds_requested))
   into   li_idc
   from  osp$budget_modular_idc
   where proposal_number = as_proposal_number
   and   version_number = ai_version_number
   and   budget_period = ai_budget_period;



	return li_idc;
end;
/

