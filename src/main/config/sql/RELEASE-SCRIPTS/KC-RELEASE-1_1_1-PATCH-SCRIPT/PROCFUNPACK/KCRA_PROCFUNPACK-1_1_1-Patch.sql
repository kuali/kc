create or replace procedure get_indrl_bgt_summary_non_per
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
	  ai_version			IN osp$budget_details.version_number%TYPE,
	  ai_period  			IN osp$budget_per_details_for_edi.budget_period%TYPE,
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
	--bd.ON_OFF_CAMPUS_FLAG,
	round(sum(bd.line_item_cost),0)+round(sum(nvl(calamts.calculated_cost,0)),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce,
	osp$budget_details_cal_amts calamts,
	osp$rate_class rc
	where bd.proposal_number = as_proposal_number
	and bd.budget_period = ai_period
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	and bd.proposal_number = calamts.proposal_number
	and bd.version_number = calamts.version_number
	and bd.budget_period = calamts.budget_period
	and bd.line_item_number = calamts.line_item_number
	and calamts.rate_class_code = rc.rate_class_code
	and ( rc.rate_class_type in ('O', 'I', 'X') or 
		(rc.rate_class_type in ('E', 'V')  and (select count(1) from valid_calc_types vct where vct.dependent_rate_class_type = 'Y' and vct.rate_class_code = calamts.rate_class_code and vct.rate_type_code = calamts.rate_type_code) = 0) )

	group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	union
		  select  bc.description category,
			ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
			--bd.ON_OFF_CAMPUS_FLAG,
			round(sum(bd.line_item_cost),0) cost
			from osp$budget_details bd,
			osp$budget_category bc,
			osp$cost_element ce
			where bd.proposal_number = as_proposal_number
			and bd.budget_period = ai_period
			and bd.version_number = ai_version
			and bd.budget_category_code = bc.budget_category_code
			and ((bc.category_type <> 'P' ) or (bc.category_type is null))
			and bd.cost_element = ce.cost_element
			and bd.line_item_number not in
				(select line_item_number
				 from osp$budget_details_cal_amts calamts,osp$rate_class rc
				 where calamts.proposal_number = as_proposal_number
					   and calamts.version_number = ai_version
					   and calamts.budget_period = ai_period
					   and calamts.rate_class_code = rc.rate_class_code
				-- 	   and rc.rate_class_type='O'
              and ( rc.rate_class_type in ('O', 'I', 'X') or 
                   (rc.rate_class_type in ('E', 'V')  and (select count(1) from valid_calc_types vct where vct.dependent_rate_class_type = 'Y' and vct.rate_class_code = calamts.rate_class_code and vct.rate_type_code = calamts.rate_type_code) = 0) )
         )
			group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)

	union
			select  'Other Direct Costs' category,
			'Allocated Lab Expense' description,
			--bd.ON_OFF_CAMPUS_FLAG,
			round(sum(calamts.calculated_cost),0) cost
			from osp$budget_details bd,
			osp$budget_details_cal_amts calamts,
			osp$rate_class rc
			where bd.proposal_number = as_proposal_number
			and bd.budget_period = ai_period
			and bd.version_number = ai_version
			and bd.proposal_number = calamts.proposal_number
			and bd.version_number = calamts.version_number
			and bd.budget_period = calamts.budget_period
			and bd.line_item_number = calamts.line_item_number
			and calamts.rate_class_code = rc.rate_class_code
			and rc.rate_class_type in ('L') 

	  union
	      select 'Lab Allocation' category,
	      'Allocated Admin Support' description,
	      round(sum(calamts.calculated_cost),0) cost
	      from osp$budget_details bd,
	      osp$budget_details_cal_amts calamts,
	      osp$rate_class rc
	      where bd.proposal_number = as_proposal_number  
	      and bd.budget_period = ai_period 
	      and bd.version_number = ai_version  
	      and bd.proposal_number = calamts.proposal_number
	      and bd.version_number = calamts.version_number
	      and bd.budget_period = calamts.budget_period
	      and bd.line_item_number = calamts.line_item_number
	      and calamts.rate_class_code = rc.rate_class_code 
	      and (rc.rate_class_type = 'Y' or 
		      (  rc.rate_class_type in ('E', 'V') and 
			 (select count(1) from valid_calc_types vct where vct.dependent_rate_class_type = 'Y' and vct.rate_class_code = calamts.rate_class_code and vct.rate_type_code = calamts.rate_type_code) > 0
		       ) 
		   )  
      
	order by 1 ;
else
-- ** this is a non-LA department
	open cur_summary for
	select  bc.description category,
	ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
	--bd.ON_OFF_CAMPUS_FLAG,
	round(sum(bd.line_item_cost),0)+round(sum(nvl(calamts.calculated_cost,0)),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce,
	osp$budget_details_cal_amts calamts,
	osp$rate_class rc
	where bd.proposal_number = as_proposal_number
	and bd.budget_period = ai_period
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	and bd.proposal_number = calamts.proposal_number
	and bd.version_number = calamts.version_number
	and bd.budget_period = calamts.budget_period
	and bd.line_item_number = calamts.line_item_number
	and calamts.rate_class_code = rc.rate_class_code
	and rc.rate_class_type in ('O')
	group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	union
		  select  bc.description category,
			ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
			--bd.ON_OFF_CAMPUS_FLAG,
			round(sum(bd.line_item_cost),0) cost
			from osp$budget_details bd,
			osp$budget_category bc,
			osp$cost_element ce
			where bd.proposal_number = as_proposal_number
			and bd.budget_period = ai_period
			and bd.version_number = ai_version
			and bd.budget_category_code = bc.budget_category_code
			and ((bc.category_type <> 'P' ) or (bc.category_type is null))
			and bd.cost_element = ce.cost_element
			and bd.line_item_number not in
				(select line_item_number
				 from osp$budget_details_cal_amts calamts,osp$rate_class rc
				 where calamts.proposal_number = as_proposal_number
					   and calamts.version_number = ai_version
					   and calamts.budget_period = ai_period
					   and calamts.rate_class_code = rc.rate_class_code
				 	   and rc.rate_class_type='O')
			group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	order by 1;
end if;


end;
/

CREATE OR REPLACE FUNCTION FN_GET_ORGANIZATION_NAME (AS_ORGANIZATION_ID IN OSP$ORGANIZATION.ORGANIZATION_ID%TYPE)
RETURN VARCHAR2 IS 
ORG_NAME OSP$ORGANIZATION.ORGANIZATION_NAME%TYPE;

BEGIN
	SELECT T.ORGANIZATION_NAME INTO ORG_NAME FROM OSP$ORGANIZATION T 
	WHERE RTRIM(LTRIM(T.ORGANIZATION_ID)) = RTRIM(LTRIM(AS_ORGANIZATION_ID));

	RETURN ORG_NAME;

	EXCEPTION WHEN NO_DATA_FOUND THEN 
	RETURN ' ';

END;
/
create or replace procedure get_budget_summary_non_per
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
	  ai_version			IN osp$budget_details.version_number%TYPE,
	  ai_period  			IN osp$budget_per_details_for_edi.budget_period%TYPE,
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
	round(sum(bd.COST_SHARING_AMOUNT),0) cost_sharing_amount,
	round(sum(bd.line_item_cost),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce
	where bd.proposal_number = as_proposal_number
	and bd.budget_period = ai_period
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	union
			select  'Other Direct Costs' category,
			'Allocated Lab Expense' description,
			round(sum(calamts.CALCULATED_COST_SHARING),0) cost_sharing_amount,
			round(sum(calamts.calculated_cost),0) cost
			from osp$budget_details bd,
			osp$budget_details_cal_amts calamts,
			osp$rate_class rc
			where bd.proposal_number = as_proposal_number
			and bd.budget_period = ai_period
			and bd.version_number = ai_version
			and bd.proposal_number = calamts.proposal_number
			and bd.version_number = calamts.version_number
			and bd.budget_period = calamts.budget_period
			and bd.line_item_number = calamts.line_item_number
			and calamts.rate_class_code = rc.rate_class_code
			and rc.rate_class_type = 'L' 

   union
      select 'Lab Allocation' category,
      'Allocated Admin Support' description,
			round(sum(calamts.calculated_cost_sharing),0) cost_sharing_amount,
      round(sum(calamts.calculated_cost),0) cost
      from osp$budget_details bd,
      osp$budget_details_cal_amts calamts,
      osp$rate_class rc
      where bd.proposal_number = as_proposal_number
      and bd.budget_period = ai_period
      and bd.version_number = ai_version
      and bd.proposal_number = calamts.proposal_number
      and bd.version_number = calamts.version_number
      and bd.budget_period = calamts.budget_period
      and bd.line_item_number = calamts.line_item_number
      and calamts.rate_class_code = rc.rate_class_code
      and (rc.rate_class_type = 'Y' or
              (  rc.rate_class_type in ('E', 'V') and
                 (select count(1) from valid_calc_types vct where vct.dependent_rate_class_type = 'Y' and vct.rate_class_code = calamts.rate_class_code and vct.rate_type_code = calamts.rate_type_code) > 0
               )
           )
	
	order by 1 ;
else
-- ** this is a non-LA department
	open cur_summary for
	select  bc.description category,
	ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description) description,
	round(sum(bd.COST_SHARING_AMOUNT),0) cost_sharing_amount,
	round(sum(bd.line_item_cost),0) cost
	from osp$budget_details bd,
	osp$budget_category bc,
	osp$cost_element ce
	where bd.proposal_number = as_proposal_number
	and bd.budget_period = ai_period
	and bd.version_number = ai_version
	and bd.budget_category_code = bc.budget_category_code
	and ((bc.category_type <> 'P' ) or (bc.category_type is null))
	and bd.cost_element = ce.cost_element
	group by bc.description , ce.description || decode(bd.line_item_description, NULL, '', ' - ' || bd.line_item_description)
	order by 1 ;
end if;


end;
/

create or replace procedure get_budget_cum_summary_non_per
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
			and bd.version_number = ai_version
			and bd.proposal_number = calamts.proposal_number
			and bd.version_number = calamts.version_number
			and bd.budget_period = calamts.budget_period
			and bd.line_item_number = calamts.line_item_number
			and calamts.rate_class_code = rc.rate_class_code
			and rc.rate_class_type = 'L'
  union
      select 'Lab Allocation' category,
      'Allocated Admin Support' description,
      round(sum(calamts.calculated_cost),0) cost
      from osp$budget_details bd,
      osp$budget_details_cal_amts calamts,
      osp$rate_class rc
      where bd.proposal_number = as_proposal_number
      and bd.version_number = ai_version
      and bd.proposal_number = calamts.proposal_number
      and bd.version_number = calamts.version_number
      and bd.budget_period = calamts.budget_period
      and bd.line_item_number = calamts.line_item_number
      and calamts.rate_class_code = rc.rate_class_code
      and (rc.rate_class_type = 'Y' or
              (  rc.rate_class_type in ('E', 'V') and
                 (select count(1) from valid_calc_types vct where vct.dependent_rate_class_type = 'Y' and vct.rate_class_code = calamts.rate_class_code and vct.rate_type_code = calamts.rate_type_code) > 0
               )
           )
          
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
