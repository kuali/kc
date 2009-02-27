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

END;;
CREATE OR REPLACE PROCEDURE get_salary_summary
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
	   ai_version			IN osp$budget_details.version_number%TYPE,
	   ai_period  			IN osp$budget_per_details_for_edi.budget_period%TYPE,
     cur_summary   IN OUT result_sets.cur_generic) is

BEGIN
OPEN CUR_SUMMARY FOR

select
       start_date, end_date, fdesc as category, full_name as person_name, 
       percent_effort, percent_charged,  
       max(decode(rate_class_type, 'E', applied_rate, 0) ) || '%' eb_rate, 
       max(decode(rate_class_type, 'V', applied_rate, 0) ) || '%' vac_rate, 
       budget_category_code as category_code, max(salary_requested) as salary, 
       max(cost_sharing_amount) as cost_sharing_amount, 
       sum(calculated_cost) as fringe, 
       sum(calculated_cost_sharing) as fringe_cost_sharing, 
       PRINCIPAL_INVESTIGATOR,  
       gdesc as cost_element_desc 
from
(
select
       a.proposal_number, a.version_number, a.budget_period, a.line_item_number, a.budget_category_code, a.cost_element,
       f.description fdesc, g.description gdesc,
       b.person_number, b.person_id, p.full_name, b.percent_charged, b.percent_effort, 
       b.job_code, b.start_date, b.end_date, b.salary_requested, b.cost_sharing_amount, 
       c.rate_class_code, c.rate_type_code, d.rate_class_type, c.calculated_cost, c.calculated_cost_sharing, rb.applied_rate, 
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
     a.version_number = AI_VERSION  and 
     a.budget_period = ai_period 

MINUS

select
       a.proposal_number, a.version_number, a.budget_period, a.line_item_number, a.budget_category_code, a.cost_element,
       f.description fdesc, g.description gdesc,
       b.person_number, b.person_id, p.full_name, b.percent_charged, b.percent_effort, 
       b.job_code, b.start_date, b.end_date, b.salary_requested, b.cost_sharing_amount, 
       c.rate_class_code, c.rate_type_code, d.rate_class_type, c.calculated_cost, c.calculated_cost_sharing, rb.applied_rate, 
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
     a.version_number = AI_VERSION  and 
     a.budget_period = ai_period 
)

group by
      proposal_number, version_number, budget_period, line_item_number, budget_category_code, cost_element,
      fdesc, gdesc, person_number, person_id, full_name, job_code, principal_investigator, percent_charged, percent_effort, 
      start_date, end_date

order by       
      proposal_number, version_number, budget_period, line_item_number, person_number;

END;;
CREATE OR REPLACE VIEW OSP$BUDGET_PER_DETAILS_FOR_EDI AS
SELECT
  A.PROPOSAL_NUMBER, A.BUDGET_PERIOD, A.LINE_ITEM_NUMBER, A.PERSON_NUMBER, ROWNUM RATE_NUMBER, A.PERSON_ID, '000000' JOB_CODE,
  A.START_DATE, A.END_DATE, 'CC' PERIOD_TYPE, B.LINE_ITEM_DESCRIPTION, '0' SEQUENCE_NUMBER, A.SALARY_REQUESTED,
  A.PERCENT_EFFORT, A.PERCENT_CHARGED, '0.00' COST_SHARING_PERCENT, A.COST_SHARING_AMOUNT, A.UNDERRECOVERY_AMOUNT,
  B.ON_OFF_CAMPUS_FLAG, B.APPLY_IN_RATE_FLAG, B.BUDGET_JUSTIFICATION, B.UPDATE_TIMESTAMP, B.UPDATE_USER
FROM

(
  SELECT X.PROPOSAL_NUMBER, X.VERSION_NUMBER, X.BUDGET_PERIOD, X.LINE_ITEM_NUMBER,
  X.START_DATE, X.END_DATE, X.PERSON_NUMBER, X.PERSON_ID,
  Z.PERCENT_EFFORT, Z.PERCENT_CHARGED,
  MAX(X.BASE_COST_SHARING) COST_SHARING_AMOUNT, MAX(X.SALARY_REQUESTED) SALARY_REQUESTED, MAX(X.UNDERRECOVERY_AMOUNT)  UNDERRECOVERY_AMOUNT
  FROM BUDGET_PER_DET_RATE_AND_BASE X,
       (SELECT RATE_CLASS_CODE, RATE_TYPE_CODE FROM VALID_CALC_TYPES WHERE RATE_CLASS_TYPE IN ('E', 'V')) Y ,
       BUDGET_PERSONNEL_DETAILS Z
  WHERE
       X.RATE_CLASS_CODE = Y.RATE_CLASS_CODE AND
       X.RATE_TYPE_CODE = Y.RATE_TYPE_CODE
       AND X.PROPOSAL_NUMBER = Z.PROPOSAL_NUMBER
       AND X.VERSION_NUMBER = Z.VERSION_NUMBER
       AND X.BUDGET_PERIOD = Z.BUDGET_PERIOD
       AND X.LINE_ITEM_NUMBER = Z.LINE_ITEM_NUMBER
       AND X.PERSON_NUMBER = Z.PERSON_NUMBER
       AND X.PERSON_ID = Z.PERSON_ID

 GROUP BY X.PROPOSAL_NUMBER,
          X.VERSION_NUMBER,
          X.BUDGET_PERIOD,
          X.LINE_ITEM_NUMBER,
          X.PERSON_NUMBER,
          X.PERSON_ID,
          Z.PERCENT_EFFORT,
          Z.PERCENT_CHARGED,
          X.START_DATE,
          X.END_DATE
  UNION

  SELECT N.PROPOSAL_NUMBER,
       N.VERSION_NUMBER,
       N.BUDGET_PERIOD,
       N.LINE_ITEM_NUMBER,
       N.START_DATE,
       N.END_DATE,
       N.PERSON_NUMBER,
       N.PERSON_ID,
       O.PERCENT_EFFORT,
       O.PERCENT_CHARGED,
       MAX(N.BASE_COST_SHARING) COST_SHARING_AMOUNT,
       MAX(N.SALARY_REQUESTED) SALARY_REQUESTED,
       MAX(N.UNDERRECOVERY_AMOUNT) UNDERRECOVERY_AMOUNT
  FROM BUDGET_PER_DET_RATE_AND_BASE N,
       (SELECT PROPOSAL_NUMBER,
               VERSION_NUMBER,
               BUDGET_PERIOD,
               LINE_ITEM_NUMBER,
               START_DATE,
               END_DATE,
               PERSON_NUMBER,
               PERSON_ID,
               RATE_CLASS_CODE,
               RATE_TYPE_CODE
          FROM BUDGET_PER_DET_RATE_AND_BASE X

        MINUS

        SELECT PROPOSAL_NUMBER,
               VERSION_NUMBER,
               BUDGET_PERIOD,
               LINE_ITEM_NUMBER,
               START_DATE,
               END_DATE,
               PERSON_NUMBER,
               PERSON_ID,
               X.RATE_CLASS_CODE,
               X.RATE_TYPE_CODE
          FROM BUDGET_PER_DET_RATE_AND_BASE X,
               (SELECT RATE_CLASS_CODE, RATE_TYPE_CODE
                  FROM VALID_CALC_TYPES
                 WHERE RATE_CLASS_TYPE IN ('E', 'V')) Y
         WHERE X.RATE_CLASS_CODE = Y.RATE_CLASS_CODE
           AND X.RATE_TYPE_CODE = Y.RATE_TYPE_CODE) M,  BUDGET_PERSONNEL_DETAILS O

 WHERE M.PROPOSAL_NUMBER = N.PROPOSAL_NUMBER
   AND M.VERSION_NUMBER = N.VERSION_NUMBER
   AND M.BUDGET_PERIOD = N.BUDGET_PERIOD
   AND M.LINE_ITEM_NUMBER = N.LINE_ITEM_NUMBER
   AND M.PERSON_NUMBER = N.PERSON_NUMBER
   AND M.PERSON_ID = N.PERSON_ID
   AND M.RATE_CLASS_CODE = N.RATE_CLASS_CODE
   AND M.RATE_TYPE_CODE = N.RATE_TYPE_CODE
   AND M.PROPOSAL_NUMBER = O.PROPOSAL_NUMBER
   AND M.VERSION_NUMBER = O.VERSION_NUMBER
   AND M.BUDGET_PERIOD = O.BUDGET_PERIOD
   AND M.LINE_ITEM_NUMBER = O.LINE_ITEM_NUMBER
   AND M.PERSON_NUMBER = O.PERSON_NUMBER
   AND M.PERSON_ID = O.PERSON_ID

 GROUP BY N.PROPOSAL_NUMBER,
          N.VERSION_NUMBER,
          N.BUDGET_PERIOD,
          N.LINE_ITEM_NUMBER,
          N.PERSON_NUMBER,
          N.PERSON_ID,
          O.PERCENT_EFFORT,
          O.PERCENT_CHARGED,
          N.START_DATE,
          N.END_DATE) A,
BUDGET_DETAILS B
WHERE
	B.PROPOSAL_NUMBER = A.PROPOSAL_NUMBER AND
	B.VERSION_NUMBER = A.VERSION_NUMBER AND
	B.BUDGET_PERIOD = A.BUDGET_PERIOD AND
	B.LINE_ITEM_NUMBER = A.LINE_ITEM_NUMBER;