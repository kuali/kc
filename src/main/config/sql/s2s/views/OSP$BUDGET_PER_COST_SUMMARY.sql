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