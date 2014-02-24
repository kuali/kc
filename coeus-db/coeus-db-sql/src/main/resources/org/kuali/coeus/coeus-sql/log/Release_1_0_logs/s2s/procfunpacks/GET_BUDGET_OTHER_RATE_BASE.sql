CREATE OR REPLACE procedure get_budget_other_rate_base
   ( as_proposal_number IN osp$budget_rate_base_for_edi.PROPOSAL_NUMBER%TYPE,
	  ai_period  			IN osp$budget_rate_base_for_edi.budget_period%TYPE,
     cur_summary 	IN OUT result_sets.cur_generic) is

begin
open cur_summary for
----------------------------------------------------------------------------------------------
---Here to_Number() function is used to avoid error while retreiving data fron Java.
--This is because the field length and precision are different for the fields involved in UNION
-----------------------------------------------------------------------------------------------
 select rate_class, rate_type, min(start_date) start_date, max(end_date) end_date, applied_rate, round(sum(salary_requested),0) salary_requested, round(sum(calculated_cost),0) calculated_cost,
			 ON_OFF_CAMPUS_FLAG from(
	select  rc.description rate_class, rt.description rate_type, pd.start_date, pd.end_date, pcal.applied_rate, to_Number(pd.salary_requested) salary_requested, to_Number(pcal.calculated_cost) calculated_cost,
			 pd.ON_OFF_CAMPUS_FLAG
	from osp$budget_per_details_for_edi pd,
			OSP$BUDGET_PER_CAL_AMT_FOR_EDI pcal,
			osp$rate_class rc,
			osp$rate_type rt
	where pd.proposal_number = as_proposal_number and
			pd.budget_period = ai_period and
			pd.proposal_number = pcal.proposal_number and
			pd.budget_period = pcal.budget_period and
			pd.line_item_number = pcal.line_item_number and
			pd.person_number = pcal.person_number and
			pd.rate_number	= pcal.rate_number and
			pcal.apply_rate_flag = 'Y' and
			pcal.rate_class_code = rc.rate_class_code and
			rc.rate_class_type = 'X' and
			pcal.rate_class_code = rt.rate_class_code and
			pcal.rate_type_code = rt.rate_type_code
	union all
	select rc.description rate_class, rt.description rate_type, rb.start_date, rb.end_date, rb.applied_rate, to_Number(rb.base_cost) salary_requested, to_Number(rb.calculated_cost) calculated_cost, rb.ON_OFF_CAMPUS_FLAG
	from  OSP$BUDGET_RATE_BASE_FOR_EDI rb, osp$rate_class rc, osp$rate_type rt
	where rb.proposal_number = as_proposal_number and
			rb.budget_period = ai_period and
			rb.rate_class_code = rc.rate_class_code and
			rc.rate_class_type = 'X' and
			rb.rate_class_code = rt.rate_class_code and
			rb.rate_type_code = rt.rate_type_code
	order by 1, 2, 3)
 GROUP BY rate_class, rate_type, ON_OFF_CAMPUS_FLAG, applied_rate ORDER BY rate_class, rate_type, start_date;

end;
/

