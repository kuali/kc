CREATE OR REPLACE procedure get_salary_summary_for_edi
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
	  ai_version			IN osp$budget_details.version_number%TYPE,
	  ai_period  			IN osp$budget_per_details_for_edi.budget_period%TYPE,
     cur_summary 	IN OUT result_sets.cur_generic) is

begin
open cur_summary for
/* select min(start_date) start_date, max(end_date) end_date, category, person_name, PERCENT_EFFORT, percent_charged,
 		eb_rate, vac_rate, category_code, round(sum(salary),0) salary,
 		round(sum(fringe),0) fringe, PRINCIPAL_INVESTIGATOR
	*/
select start_date, end_date, category, person_name, PERCENT_EFFORT, percent_charged,
 		eb_rate, vac_rate, category_code, salary,
 		fringe, PRINCIPAL_INVESTIGATOR

		from (
			 select pd.start_date, pd.end_date, DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG,
				'Y', '1', 'N', '2', NULL, '3', '4') PRINCIPAL_INVESTIGATOR,
	 			decode(decode(pd.person_id || pd.job_code, '999999999LA', 99, bd.budget_category_code) , 1, bc.description, 'Other Personnel') category,
				decode(pd.person_id || pd.job_code, '999999999LA', 'Allocated Admin Support',
				decode(bd.budget_category_code, 1, p.full_name, bc.description) ) person_name,
				sum(PERCENT_EFFORT)PERCENT_EFFORT, -- Added to fix the budget report bug
				sum(percent_charged)percent_charged, -- Added to fix the budget report bug
				sum(pd.salary_requested) Salary,
				to_char(decode(eb.rate, NULL, 0, eb.rate), '990.00') || '%' eb_rate,
				to_char(decode(vac.rate, NULL, 0, vac.rate), '990.00') || '%' vac_rate,
				decode(sum(eb.amt), NULL, 0, sum(eb.amt)) + decode(sum(vac.amt), NULL, 0, sum(vac.amt)) fringe,
				decode(pd.person_id || pd.job_code, '999999999LA', 99, bd.budget_category_code) category_code
			from osp$budget_per_details_for_edi pd,
					 osp$person p, osp$budget_details bd,
					 osp$budget_category bc,
					 osp$eps_prop_investigators inv,
					 (select pcal.person_number person_num,
						 pcal.rate_number rate_num,
						 pcal.line_item_number line_num,
						 pcal.rate_class_code rate_class,
						 pcal.rate_type_code  rate_type,
						 pcal.calculated_cost amt ,
						 pcal.applied_rate rate
							from osp$budget_per_cal_amt_for_edi pcal,
							osp$rate_class rc
							where pcal.rate_class_code = rc.rate_class_code
							and rc.rate_class_type = 'E'
							and  pcal.proposal_number = as_proposal_number
							and pcal.budget_period = ai_period
					 ) eb,
					(select pcal.person_number person_num,
						pcal.rate_number rate_num,
						pcal.line_item_number line_num,
						pcal.rate_class_code rate_class,
						pcal.rate_type_code  rate_type,
						pcal.calculated_cost amt ,
						pcal.applied_rate rate
						from osp$budget_per_cal_amt_for_edi pcal,
						osp$rate_class rc
						where pcal.rate_class_code = rc.rate_class_code
						and rc.rate_class_type = 'V'
						and  pcal.proposal_number = as_proposal_number
						and pcal.budget_period = ai_period
					) vac
			where pd.proposal_number = as_proposal_number
				and pd.budget_period = ai_period
				and bd.version_number = ai_version
				and pd.proposal_number = bd.proposal_number
				and pd.budget_period = bd.budget_period
				and pd.line_item_number = bd.line_item_number
				and bd.budget_category_code = bc.budget_category_code
				and pd.line_item_number = eb.line_num (+)
				and pd.person_number = eb.person_num (+)
				and pd.rate_number = eb.rate_num (+)
				and pd.line_item_number = vac.line_num (+)
				and pd.person_number = vac.person_num (+)
				and pd.rate_number = vac.rate_num (+)
				and pd.proposal_number = inv.proposal_number (+)
				and pd.person_id = inv.person_id (+)
				and pd.person_id = p.person_id
			group by pd.start_date, pd.end_date, DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4'),
				decode(decode(pd.person_id || pd.job_code, '999999999LA', 99, bd.budget_category_code) , 1, bc.description, 'Other Personnel'),
				decode(pd.person_id || pd.job_code, '999999999LA', 'Allocated Admin Support',
						decode(bd.budget_category_code, 1, p.full_name, bc.description)
				),
				PERCENT_EFFORT, percent_charged,eb.rate, vac.rate ,
				decode(pd.person_id || pd.job_code, '999999999LA', 99, bd.budget_category_code)
			order by decode(pd.person_id || pd.job_code, '999999999LA', 99, bd.budget_category_code),
				DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4'),
				decode(pd.person_id || pd.job_code, '999999999LA', 'Allocated Admin Support',
					decode(bd.budget_category_code, 1, p.full_name, bc.description)
				) ,
				PD.START_DATE
		)
/*group by category, category_code, PRINCIPAL_INVESTIGATOR, person_name, eb_rate, vac_rate*/

order by category_code, PRINCIPAL_INVESTIGATOR, person_name, start_date;

end;
/

