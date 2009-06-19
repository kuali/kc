create or replace view osp$budget_per_cost_summary as
select        DISTINCT
               a.proposal_number,
               a.version_number,
               a.budget_period,
               a.line_item_number,
               a.budget_category_code,
               a.cost_element,
               f.description fdesc,
               g.description gdesc,
               b.person_number,
               b.person_id,
               p.full_name,
               b.percent_charged,
               b.percent_effort,
               b.job_code,
               decode(b.start_date, null, a.START_DATE, b.START_DATE) as START_DATE,
               decode(b.end_date, null, a.END_DATE, b.END_DATE) as END_DATE,
               decode(b.SALARY_REQUESTED,
                      null,
                      a.LINE_ITEM_cost,
                      b.SALARY_REQUESTED) as SALARY_REQUESTED,
               c.rate_class_code,
               c.rate_type_code,
               d.rate_class_type,
               c.calculated_cost,
               DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4') PRINCIPAL_INVESTIGATOR
          from osp$budget_details            a,
               osp$budget_personnel_details  b,
               osp$person                    p,
               osp$budget_rate_and_base      rb,
               osp$budget_personnel_cal_amts c,
               osp$rate_class                d,
               osp$rate_type                 e,
               osp$budget_category           f,
               osp$cost_element              g,
               osp$eps_prop_investigators inv
         where a.proposal_number = b.proposal_number(+)
           and b.PROPOSAL_NUMBER = rb.PROPOSAL_NUMBER(+)
           and rb.PROPOSAL_NUMBER = c.PROPOSAL_NUMBER(+)
           and a.version_number = b.version_number(+)
           and b.version_number = rb.version_number(+)
           and rb.VERSION_NUMBER = c.VERSION_NUMBER(+)
           and a.budget_period = b.budget_period(+)
           and b.budget_period = rb.budget_period(+)
           and rb.BUDGET_PERIOD = c.BUDGET_PERIOD(+)
           and a.line_item_number = b.line_item_number(+)
           and b.line_item_number = rb.line_item_number(+)
           and rb.LINE_ITEM_NUMBER = c.LINE_ITEM_NUMBER(+)
           and b.person_id = p.person_id(+)
           and (b.PERSON_NUMBER is null or b.PERSON_NUMBER = c.PERSON_NUMBER)
           and rb.RATE_CLASS_CODE = c.RATE_CLASS_CODE(+)
           and c.RATE_CLASS_CODE = d.RATE_CLASS_CODE(+)
           and c.RATE_CLASS_CODE = e.RATE_CLASS_CODE(+)
           and c.RATE_TYPE_CODE = e.RATE_TYPE_CODE(+)
           and rb.RATE_TYPE_CODE = c.RATE_TYPE_CODE(+)
           and a.budget_category_code = f.budget_category_code
           and a.budget_category_code = g.budget_category_code
           and a.cost_element = g.cost_element
           and b.proposal_number = inv.proposal_number (+)
           and b.person_id = inv.person_id (+)
           and upper(f.CATEGORY_TYPE) = 'P' 
           and ( d.rate_class_type in ('O', 'I', 'X') or 
                 (d.rate_class_type in ('E', 'V')  and (select count(1) from valid_calc_types vct where vct.dependent_rate_class_type = 'Y' and vct.rate_class_code = c.rate_class_code and vct.rate_type_code = c.rate_type_code) = 0) )
ORDER by  proposal_number,
          version_number,
          budget_period,
          line_item_number,
          budget_category_code,
          cost_element,
          fdesc,
          gdesc,
          PERSON_NUMBER,
          person_id,
          full_name,
          percent_charged,
          percent_effort,
          job_code,
          START_DATE,
          END_DATE,
          SALARY_REQUESTED,
          rate_class_code,
          rate_type_code,
          rate_class_type,
          calculated_cost;

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