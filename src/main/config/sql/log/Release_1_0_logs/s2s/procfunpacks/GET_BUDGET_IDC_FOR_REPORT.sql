CREATE OR REPLACE procedure get_budget_idc_for_report
   ( as_proposal_number IN osp$budget_details.PROPOSAL_NUMBER%TYPE,
	  ai_version			IN osp$budget_details.version_number%TYPE,
	  ai_period  			IN osp$budget_details.budget_period%TYPE,
     cur_summary 	IN OUT result_sets.cur_generic) is

begin
open cur_summary for
 select bd.on_off_campus_flag, round(sum(cal.calculated_cost),0) calculated_cost,
 round(sum(cal.CALCULATED_COST_SHARING),0) cost_sharing_amount
from osp$budget_details bd,
osp$budget_details_cal_amts cal,
osp$rate_class rc
where bd.proposal_number = as_proposal_number
and bd.version_number = ai_version
and bd.budget_period = ai_period
and bd.proposal_number = cal.proposal_number
and bd.version_number = cal.version_number
and bd.budget_period = cal.budget_period
and bd.line_item_number = cal.line_item_number
and cal.rate_class_code = rc.rate_class_code
and rc.rate_class_type = 'O'
group by bd.on_off_campus_flag;




end;
/
