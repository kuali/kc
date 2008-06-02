CREATE OR REPLACE procedure get_budget_oh_rate_base
   ( as_proposal_number IN osp$budget_rate_base_for_edi.PROPOSAL_NUMBER%TYPE,
	  ai_period  			IN osp$budget_rate_base_for_edi.budget_period%TYPE,
     cur_summary 	IN OUT result_sets.cur_generic) is

begin
open cur_summary for
----------------------------------------------------------------------------------------------
---Here to_Number() function is used to avoid error while retreiving data fron Java.
--This is because the field length and precision are different for the fields involved in UNION
-----------------------------------------------------------------------------------------------
select min(start_date) start_date, max(end_date) end_date, applied_rate, round(sum(base_cost),0) base_cost, round(sum(calculated_cost),0) calculated_cost, ON_OFF_CAMPUS_FLAG, rate_class_desc
from (select pd.start_date, pd.end_date, pcal.applied_rate,
		 to_number(decode(pd.salary_requested, null, 0, pd.salary_requested)  +
		 decode(eb.amt, null, 0, eb.amt)  +
		 decode(vac.amt, null, 0, vac.amt)) base_cost,
		 to_number(pcal.calculated_cost) calculated_cost,
		 pd.ON_OFF_CAMPUS_FLAG,rc.DESCRIPTION rate_class_desc
	 from osp$budget_per_details_for_edi pd,
		OSP$BUDGET_PER_CAL_AMT_FOR_EDI pcal,
		osp$rate_class rc,
		(select pcal.person_number person_num,
			pcal.rate_number rate_num,
			pcal.line_item_number line_num,
			pcal.rate_class_code rate_class,
			pcal.rate_type_code  rate_type,
			pcal.calculated_cost amt
			from osp$budget_per_cal_amt_for_edi pcal,
			osp$rate_class rc
			where pcal.rate_class_code = rc.rate_class_code
			and rc.rate_class_type = 'E'
			and  pcal.proposal_number = as_proposal_number
			and pcal.budget_period = ai_period) eb,
		(select pcal.person_number person_num,
			pcal.rate_number rate_num,
			pcal.line_item_number line_num,
			pcal.rate_class_code rate_class,
			pcal.rate_type_code  rate_type,
			pcal.calculated_cost amt
			from osp$budget_per_cal_amt_for_edi pcal,
			osp$rate_class rc
			where pcal.rate_class_code = rc.rate_class_code
			and rc.rate_class_type = 'V'
			and  pcal.proposal_number = as_proposal_number
			and pcal.budget_period = ai_period) vac
     where pd.proposal_number = as_proposal_number and
		pd.budget_period = ai_period and
		pd.proposal_number = pcal.proposal_number and
		pd.budget_period = pcal.budget_period and
		pd.line_item_number = pcal.line_item_number and
		pd.person_number = pcal.person_number and
		pd.rate_number	= pcal.rate_number and
		pcal.apply_rate_flag = 'Y' and
		pcal.rate_class_code = rc.rate_class_code and
		rc.rate_class_type = 'O' and
		pd.person_number = eb.person_num (+) and
		pd.line_item_number = eb.line_num (+) and
		pd.rate_number = eb.rate_num (+) and
		pd.person_number = vac.person_num (+) and
		pd.line_item_number = vac.line_num (+) and
		pd.rate_number = vac.rate_num (+)
    union all
	select rb.start_date, rb.end_date, rb.applied_rate, to_number(rb.base_cost) base_cost, to_number(rb.calculated_cost) calculated_cost, rb.ON_OFF_CAMPUS_FLAG,rc.DESCRIPTION rate_class_desc
	from  OSP$BUDGET_RATE_BASE_FOR_EDI rb, osp$rate_class rc
	where rb.proposal_number = as_proposal_number and
		rb.budget_period = ai_period and
		rb.rate_class_code = rc.rate_class_code and
		rc.rate_class_type = 'O'
		order by 1, 2)
	GROUP BY ON_OFF_CAMPUS_FLAG, applied_rate, rate_class_desc ORDER BY start_date,end_date;

end;
/

