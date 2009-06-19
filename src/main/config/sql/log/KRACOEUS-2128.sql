CREATE OR REPLACE PROCEDURE get_cum_salary_summary
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
     ai_version      IN osp$budget_details.version_number%TYPE,
     cur_summary   IN OUT result_sets.cur_generic) is

BEGIN
OPEN CUR_SUMMARY FOR

select
       start_date, end_date, category, person_name,
       percent_effort, percent_charged,
       eb_rate,
       vac_rate,
       category_code, salary,
       fringe,
       PRINCIPAL_INVESTIGATOR,
       cost_element_desc from ( 
select
       --proposal_number, version_number, budget_period, line_item_number,
       start_date, end_date, fdesc as category, full_name as person_name,
       percent_effort, percent_charged,
       max(decode(rate_class_type, 'E', applied_rate, 0) ) || '%' eb_rate,
       max(decode(rate_class_type, 'V', applied_rate, 0) ) || '%' vac_rate,
       budget_category_code as category_code, max(salary_requested) as salary,
       max(calculated_cost) as fringe,
       PRINCIPAL_INVESTIGATOR,
       gdesc as cost_element_desc 
from (       
  select
         proposal_number,
         version_number,
         budget_period,
         line_item_number,
         budget_category_code,
         cost_element,
         fdesc,
         gdesc,
         person_number,
         person_id,
         full_name,
         percent_charged,
         percent_effort,
         job_code,
         start_date,
         end_date,
         SALARY_REQUESTED, 
         cost_sharing_amount, 
         decode(rate_class_code,
                null,
                sRATE_CLASS_CODE,
                rate_class_code) rate_class_code,
         decode(rate_type_code, null, srate_type_code, rate_type_code) rate_type_code,
         decode(rate_class_type, null, srate_class_type, rate_class_type) rate_class_type,
         decode(calculated_cost,
                null,
                scalculated_cost,
                calculated_cost) calculated_cost,
         decode(calculated_cost_sharing,
                null,
                scalculated_cost_sharing,
                calculated_cost_sharing) calculated_cost_sharing,
         decode(applied_rate, null, sAPPLIED_RATE, applied_rate) applied_rate,
         PRINCIPAL_INVESTIGATOR 
    from (select A1.proposal_number proposal_number,
                 A1.version_number version_number,
                 A1.budget_period budget_period,
                 A1.line_item_number line_item_number,
                 A1.budget_category_code budget_category_code,
                 A1.cost_element cost_element,
                 A1.fdesc fdesc, 
                 A1.gdesc gdesc,
                 A1.person_number person_number,
                 A1.person_id person_id,
                 A1.full_name full_name,
                 A1.percent_charged percent_charged,
                 A1.percent_effort percent_effort,
                 A1.job_code job_code,
                 A1.start_date start_date,
                 A1.end_date end_date,
                 A1.SALARY_REQUESTED SALARY_REQUESTED,
                 a1.cost_sharing_amount cost_sharing_amount, 
                 A1.rate_class_code rate_class_code,
                 A1.rate_type_code rate_type_code,
                 a1.rate_class_type rate_class_type,
                 a1.calculated_cost calculated_cost,
                 A1.calculated_cost_sharing calculated_cost_sharing,
                 a1.applied_rate applied_rate,
                 a1.PRINCIPAL_INVESTIGATOR PRINCIPAL_INVESTIGATOR,
                 a2.RATE_CLASS_CODE sRATE_CLASS_CODE,
                 a2.RATE_TYPE_CODE sRATE_TYPE_CODE,
                 d.RATE_CLASS_TYPE sRATE_CLASS_TYPE,
                 rb.APPLIED_RATE sAPPLIED_RATE, 
                 a2.CALCULATED_COST sCALCULATED_COST, 
                 a2.CALCULATED_COST_SHARING sCALCULATED_COST_SHARING 
            from OSP$BUDGET_SALARY_SUMMARY   A1,
                 osp$budget_details_cal_amts a2,
                 osp$budget_rate_and_base    rb,
                 osp$rate_class              d,
                 osp$rate_type               e
           where a1.proposal_number = a2.PROPOSAL_NUMBER(+)
             AND A2.PROPOSAL_NUMBER = RB.PROPOSAL_NUMBER
             and a1.version_number = a2.VERSION_NUMBER(+)
             AND A2.VERSION_NUMBER = RB.VERSION_NUMBER
             and a1.budget_period = a2.BUDGET_PERIOD(+)
             AND A2.BUDGET_PERIOD = RB.BUDGET_PERIOD
             and a1.line_item_number = a2.LINE_ITEM_NUMBER(+)
             AND A2.LINE_ITEM_NUMBER = RB.LINE_ITEM_NUMBER
             AND A2.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND A2.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND RB.RATE_CLASS_CODE = D.RATE_CLASS_CODE
             AND D.RATE_CLASS_CODE = E.RATE_CLASS_CODE
             AND E.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND E.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND a1.proposal_number = as_proposal_number 
             And a1.version_number = ai_version 
             aND d.rate_class_type in ('E', 'V')
          
          MINUS
          
          select A1.proposal_number proposal_number,
                 A1.version_number version_number,
                 A1.budget_period budget_period,
                 A1.line_item_number line_item_number,
                 A1.budget_category_code budget_category_code,
                 A1.cost_element cost_element,
                 A1.fdesc fdesc, 
                 A1.gdesc gdesc,
                 A1.person_number person_number,
                 A1.person_id person_id,
                 A1.full_name full_name,
                 A1.percent_charged percent_charged,
                 A1.percent_effort percent_effort,
                 A1.job_code job_code,
                 A1.start_date start_date,
                 A1.end_date end_date,
                 A1.SALARY_REQUESTED SALARY_REQUESTED,
                 a1.cost_sharing_amount cost_sharing_amount, 
                 A1.rate_class_code rate_class_code,
                 A1.rate_type_code rate_type_code,
                 a1.rate_class_type rate_class_type,
                 a1.calculated_cost calculated_cost,
                 A1.calculated_cost_sharing calculated_cost_sharing,
                 a1.applied_rate applied_rate,
                 a1.PRINCIPAL_INVESTIGATOR PRINCIPAL_INVESTIGATOR,
                 a2.RATE_CLASS_CODE sRATE_CLASS_CODE,
                 a2.RATE_TYPE_CODE sRATE_TYPE_CODE,
                 d.RATE_CLASS_TYPE sRATE_CLASS_TYPE, 
                 rb.APPLIED_RATE sAPPLIED_RATE,
                 a2.CALCULATED_COST sCALCULATED_COST, 
                 a2.CALCULATED_COST_SHARING sCALCULATED_COST_SHARING 
          
            from OSP$BUDGET_SALARY_SUMMARY A1,
                 osp$budget_details_cal_amts a2,
                 osp$budget_rate_and_base rb,
                 osp$rate_class d,
                 osp$rate_type e,
                 (SELECT RATE_CLASS_CODE, RATE_TYPE_CODE
                    FROM osp$VALID_CALC_TYPES
                   WHERE RATE_CLASS_TYPE IN ('E', 'V')) R
           where a1.proposal_number = a2.PROPOSAL_NUMBER(+)
             AND A2.PROPOSAL_NUMBER = RB.PROPOSAL_NUMBER
             and a1.version_number = a2.VERSION_NUMBER(+)
             AND A2.VERSION_NUMBER = RB.VERSION_NUMBER
             and a1.budget_period = a2.BUDGET_PERIOD(+)
             AND A2.BUDGET_PERIOD = RB.BUDGET_PERIOD
             and a1.line_item_number = a2.LINE_ITEM_NUMBER(+)
             AND A2.LINE_ITEM_NUMBER = RB.LINE_ITEM_NUMBER
             AND A2.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND A2.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND RB.RATE_CLASS_CODE = D.RATE_CLASS_CODE
             AND D.RATE_CLASS_CODE = E.RATE_CLASS_CODE
             AND E.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND E.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND A2.rate_class_code = R.rate_class_code
             and A2.rate_type_code = R.rate_type_code
             AND a1.proposal_number = as_proposal_number 
             And a1.version_number = ai_version 
             AND d.rate_class_type in ('E', 'V'))
)

group by
      proposal_number, version_number, budget_period, line_item_number, budget_category_code, cost_element,
      fdesc, gdesc, person_number, person_id, full_name, job_code, principal_investigator, percent_charged, percent_effort,
      start_date, end_date
order by
      proposal_number, version_number, budget_period, line_item_number, person_number
       
) order by category, cost_element_desc, person_name, start_date;  
END;