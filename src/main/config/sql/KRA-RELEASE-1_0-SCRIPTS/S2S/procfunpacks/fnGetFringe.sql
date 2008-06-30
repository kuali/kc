create or replace FUNCTION          "FNGETFRINGE" (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
																AI_VERSION_NUMBER osp$budget.version_number%type)
 return  NUMBER is
li_fringe	number;

BEGIN

li_fringe := 0;

 	select 	DECODE(sum(ROUND(osp$budget_details_cal_amts.calculated_cost )),NULL,0,
                   sum(ROUND(osp$budget_details_cal_amts.calculated_cost )) )
			into li_fringe
			from  	osp$budget_details_cal_amts,
						OSP$RATE_CLASS
			where 	osp$budget_details_cal_amts.proposal_number= as_proposal_number
			and   	osp$budget_details_cal_amts.version_number = ai_version_number
			and   	osp$budget_details_cal_amts.rate_class_code = osp$rate_class.rate_class_code
			and  	(( osp$RATE_CLASS.rate_class_TYPE = 'E' )
				   OR
					(osp$RATE_CLASS.rate_class_TYPE = 'V' ))

			;


li_fringe := ROUND(li_fringe);

return li_fringe;
end;
 
/