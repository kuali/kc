------------------------------------------------------------------
-- procedure GetMrLA
-- this procedure replaces function fnGetMrLA
-- this is to print cents rather than rounding on nsf budget forms in proposal printing
-- created march 14, 2006
------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GetMrLA
 					(AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
					AI_VERSION_NUMBER OSP$BUDGET.VERSION_NUMBER%TYPE,
					AI_BUDGET_PERIOD OSP$BUDGET_PERIODS.BUDGET_PERIOD%TYPE,
               cur_generic IN OUT result_sets.cur_generic) IS 

li_cost	number;
 
BEGIN

open cur_generic for

 select   	sum(a.calculated_cost) as MRLA
	from 		osp$budget_details_cal_amts a, osp$budget_details d, osp$rate_class r
	where 	a.proposal_number = AS_PROPOSAL_NUMBER
	and 		a.budget_period = AI_BUDGET_PERIOD
	and 		a.version_number = AI_VERSION_NUMBER
	and 		a.rate_class_code = r.rate_class_code
	and      r.rate_class_type = 'Y'
	and 		a.proposal_number = d.proposal_number
	and 		a.budget_period = d.budget_period
	and	 	a.version_number = d.version_number
	and 		a.line_item_number = d.line_item_number;

END;
/
