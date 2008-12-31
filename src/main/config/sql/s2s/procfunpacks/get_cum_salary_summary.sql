CREATE OR REPLACE PROCEDURE GET_CUM_SALARY_SUMMARY
   ( AS_PROPOSAL_NUMBER IN OSP$BUDGET_PER_DETAILS_FOR_EDI.PROPOSAL_NUMBER%TYPE,
     AI_VERSION    IN OSP$BUDGET_DETAILS.VERSION_NUMBER%TYPE,
     CUR_SUMMARY   IN OUT RESULT_SETS.CUR_GENERIC) IS

BEGIN
OPEN CUR_SUMMARY FOR

select
       start_date, end_date, fdesc as category, full_name as person_name,
       max(decode(rate_class_type, 'E', applied_rate, 0) ) || '%' eb_rate, 
       max(decode(rate_class_type, 'V', applied_rate, 0) ) || '%' vac_rate, 
       budget_category_code as category_code, max(salary_requested) as salary, 
       sum(calculated_cost) as fringe, 
       PRINCIPAL_INVESTIGATOR,  
       gdesc as cost_element_desc 
from
(
select
       a.proposal_number, a.version_number, a.budget_period, a.line_item_number, a.budget_category_code, a.cost_element,
       f.description fdesc, g.description gdesc,
       b.person_number, b.person_id, p.full_name,
       b.job_code, b.start_date, b.end_date, b.salary_requested,
       c.rate_class_code, c.rate_type_code, d.rate_class_type, c.calculated_cost, rb.applied_rate, 
       DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4') PRINCIPAL_INVESTIGATOR 

from osp$budget_details a,
     osp$budget_personnel_details b,
     osp$person p,
     osp$budget_personnel_cal_amts c,
     osp$budget_rate_and_base rb,
     osp$rate_class d,
     osp$rate_type e,
     osp$budget_category f,
     osp$cost_element g, 
     osp$eps_prop_investigators inv 
where
     a.proposal_number = b.proposal_number and
     a.proposal_number = c.proposal_number and
     a.proposal_number = rb.proposal_number and
     a.version_number = b.version_number and
     a.version_number = c.version_number and
     a.version_number = rb.version_number and
     a.budget_period = b.budget_period and
     a.budget_period = c.budget_period and
     a.budget_period = rb.budget_period and
     a.line_item_number = b.line_item_number and
     a.line_item_number = c.line_item_number and
     a.line_item_number = rb.line_item_number (+) and
     c.rate_class_code = d.rate_class_code and
     c.rate_class_code = e.rate_class_code and
     c.rate_type_code = e.rate_type_code and
     c.rate_class_code = rb.rate_class_code and
     c.rate_type_code = rb.rate_type_code and
     b.person_id = p.person_id and 
     b.person_number = c.person_number and 
     a.budget_category_code = f.budget_category_code and
     a.budget_category_code = f.budget_category_code and
     a.cost_element = g.cost_element and
     d.rate_class_type in ('E', 'V') and 
     b.proposal_number = inv.proposal_number (+) and 
     b.person_id = inv.person_id (+) and 
     a.proposal_number = AS_PROPOSAL_NUMBER and
     a.version_number = AI_VERSION  

MINUS

select
       a.proposal_number, a.version_number, a.budget_period, a.line_item_number, a.budget_category_code, a.cost_element,
       f.description fdesc, g.description gdesc,
       b.person_number, b.person_id, p.full_name,
       b.job_code, b.start_date, b.end_date, b.salary_requested,
       c.rate_class_code, c.rate_type_code, d.rate_class_type, c.calculated_cost, rb.applied_rate, 
       DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4') PRINCIPAL_INVESTIGATOR 

from osp$budget_details a,
     osp$budget_personnel_details b,
     osp$person p,
     osp$budget_personnel_cal_amts c,
     osp$budget_rate_and_base rb,
     osp$rate_class d,
     osp$rate_type e,
     (SELECT RATE_CLASS_CODE, RATE_TYPE_CODE
     FROM osp$VALID_CALC_TYPES
     WHERE RATE_CLASS_TYPE IN ('E', 'V')) R,
     osp$budget_category f,
     osp$cost_element g, 
     osp$eps_prop_investigators inv
where
     a.proposal_number = b.proposal_number and
     a.proposal_number = c.proposal_number and
     a.proposal_number = rb.proposal_number and
     a.version_number = b.version_number and
     a.version_number = c.version_number and
     a.version_number = rb.version_number and
     a.budget_period = b.budget_period and
     a.budget_period = c.budget_period and
     a.budget_period = rb.budget_period and
     a.line_item_number = b.line_item_number and
     a.line_item_number = c.line_item_number and
     a.line_item_number = rb.line_item_number (+) and
     c.rate_class_code = d.rate_class_code and
     c.rate_class_code = e.rate_class_code and
     c.rate_type_code = e.rate_type_code and
     c.rate_class_code = rb.rate_class_code and
     c.rate_type_code = rb.rate_type_code and
     b.person_id = p.person_id and 
     b.person_number = c.person_number and 
     a.budget_category_code = f.budget_category_code and
     a.budget_category_code = f.budget_category_code and
     a.cost_element = g.cost_element and
     d.rate_class_type in ('E', 'V') and
     c.rate_class_code = R.rate_class_code and
     c.rate_type_code = R.rate_type_code and 
     b.proposal_number = inv.proposal_number (+) and 
     b.person_id = inv.person_id (+) and      
     a.proposal_number = AS_PROPOSAL_NUMBER and
     a.version_number = AI_VERSION  
)

group by
      proposal_number, version_number, budget_period, line_item_number, budget_category_code, cost_element,
      fdesc, gdesc, person_number, person_id, full_name, job_code, principal_investigator, start_date, end_date

order by       
      proposal_number, version_number, budget_period, line_item_number, person_number;

END;
/
