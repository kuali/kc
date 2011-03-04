CREATE OR REPLACE procedure get_budget_cum_summary_non_per
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
	  ai_version			IN osp$budget_details.version_number%TYPE,
     cur_summary 	IN OUT result_sets.cur_generic) is

ls_Owned_by_unit 			osp$eps_proposal.owned_by_unit%type;
li_Count						number;

begin

/****************************************************************************
Allocated Lab Expenses should not print if owned by unit is a non-LA unit
****************************************************************************/
begin
	select owned_by_unit
	into ls_Owned_by_unit
	from osp$eps_proposal
	where proposal_number = as_proposal_number;
exception
	when others then
		ls_Owned_by_unit := '';
end;

select count(unit_number)
into li_count
from osp$institute_la_rates
where upper(ltrim(rtrim(unit_number))) = upper(ltrim(rtrim(ls_Owned_by_unit)));

if li_count > 0 then
	open cur_summary for
	 select  bc.description category,
	ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
	round(sum(bd.line_item_cost),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce
	where bd.proposal_number = as_proposal_number
	--and bd.budget_period = ai_period
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	union
			select  'Other Direct Costs' category,
			'Allocated Lab Expense' description,
			round(sum(calamts.calculated_cost),0) cost
			from osp$budget_details bd,
			osp$budget_details_cal_amts calamts,
			osp$rate_class rc
			where bd.proposal_number = as_proposal_number
		--	and bd.budget_period = ai_period
			and bd.version_number = ai_version
			and bd.proposal_number = calamts.proposal_number
			and bd.version_number = calamts.version_number
			and bd.budget_period = calamts.budget_period
			and bd.line_item_number = calamts.line_item_number
			and calamts.rate_class_code = rc.rate_class_code
			and rc.rate_class_type = 'L'
	order by 1 ;
else
-- ** this is a non-LA department
	open cur_summary for
	select  bc.description category,
	ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
	round(sum(bd.line_item_cost),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce
	where bd.proposal_number = as_proposal_number
	--and bd.budget_period = ai_period
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	order by 1 ;
end if;


end;
/

