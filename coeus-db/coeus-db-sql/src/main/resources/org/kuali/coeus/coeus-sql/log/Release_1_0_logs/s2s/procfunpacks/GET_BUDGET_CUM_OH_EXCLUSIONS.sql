CREATE OR REPLACE procedure get_budget_cum_oh_exclusions
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
	  ai_version			IN osp$budget_details.version_number%TYPE,
     cur_summary 	IN OUT result_sets.cur_generic) is

li_Count number;

begin

Select count(*)
into li_Count
from osp$eps_prop_la_rates
where proposal_number = as_proposal_number
and version_number = ai_Version;

IF li_Count > 0 THEN

	--This Proposal Gets LA

	open cur_summary for
		select '1' sort_id, 'Allocated Administrative Support' description, round(sum(pd.salary_requested),0) cost
	from osp$budget_per_details_for_edi pd
	where pd.proposal_number = as_proposal_number and
			pd.person_id = '999999999' and
			ltrim(rtrim(pd.job_code)) = 'LA'
	union
	select '2' sort_id, 'Employee Benefits on Allocated Administrative Support' description, round(sum(pcal.calculated_cost),0) cost
	from osp$budget_per_details_for_edi pd,
			osp$budget_per_cal_amt_for_edi pcal,
			osp$rate_class rc
	where pd.proposal_number = as_proposal_number and
			pd.person_id = '999999999' and
			ltrim(rtrim(pd.job_code)) = 'LA' and
			pd.proposal_number = pcal.proposal_number and
			pd.budget_period = pcal.budget_period and
			pd.line_item_number = pcal.line_item_number and
			pd.person_number	= pcal.person_number and
			pd.rate_number	= pcal.rate_number and
			pcal.rate_class_code = rc.rate_class_code and
			((rc.rate_class_type = 'E') OR (rc.rate_class_type = 'V'))
	union
	select  '3' sort_id, ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
	round(sum(bd.line_item_cost),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce
	where bd.proposal_number = as_proposal_number
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
--	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	and bd.line_item_number not in (
		select cal.line_item_number
		from osp$budget_details_cal_amts cal,
			osp$rate_class rc
		where cal.proposal_number = bd.proposal_number
		and cal.version_number = bd.version_number
		and cal.budget_period = bd.budget_period
		and cal.apply_rate_flag = 'Y'
		and cal.rate_class_code = rc.rate_class_code
		and rc.rate_class_type = 'O')
	group by  ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	union
	select '4' sort_id, 'Allocated Lab Expense' description, round(sum(calamts.calculated_cost),0) cost
	from osp$budget_details bd,
	osp$budget_details_cal_amts calamts,
	osp$budget_category bc,
	osp$rate_class rc
	where bd.proposal_number = as_proposal_number
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
	and bd.proposal_number = calamts.proposal_number
	and bd.version_number = calamts.version_number
	and bd.budget_period = calamts.budget_period
	and bd.line_item_number = calamts.line_item_number
	and calamts.rate_class_code = rc.rate_class_code
	and rc.rate_class_type = 'L' ;

ELSE
	--This Proposal Does not get LA

	open cur_summary for
	select  '1' sort_id, ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
	round(sum(bd.line_item_cost),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce
	where bd.proposal_number = as_proposal_number
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
--	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	and bd.line_item_number not in (
		select cal.line_item_number
		from osp$budget_details_cal_amts cal,
			osp$rate_class rc
		where cal.proposal_number = bd.proposal_number
		and cal.version_number = bd.version_number
		and cal.budget_period = bd.budget_period
		and cal.apply_rate_flag = 'Y'
		and cal.rate_class_code = rc.rate_class_code
		and rc.rate_class_type = 'O')
	group by  ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) ;
END IF;
end;
/

