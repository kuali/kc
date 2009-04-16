CREATE OR REPLACE PROCEDURE GetNsfSrPersonnelEffort
 (AW_PROPOSAL_NUMBER IN OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE,
  AW_VERSION_NUMBER IN OSP$BUDGET.VERSION_NUMBER%TYPE,
  AW_BUDGET_PERIOD IN OSP$BUDGET_PERIODS.BUDGET_PERIOD%TYPE,
  AW_PERSONID      IN OSP$PERSON.PERSON_ID%TYPE,
 cur_generic IN OUT result_sets.cur_generic) is

begin

if aw_budget_period > 0 then
	open cur_generic for

      SELECT  period_type,  sum(salary_requested) SALARY_REQUESTED,
  --     sum(.01 * percent_effort *  round(months_between(PD.end_date,PD.start_date))) 	MONTHS
  --       sum(.01 * percent_effort *  round(months_between(PD.end_date,PD.start_date),1)) 	MONTHS 
           sum(.01 * PD.percent_effort * FN_GET_NUMBER_OF_MONTHS(pd.start_date,pd.end_date)) as MONTHS
		FROM 		OSP$BUDGET_PERSONNEL_DETAILS PD, OSP$BUDGET B
		WHERE 	PD.proposal_number = AW_PROPOSAL_NUMBER
      AND      PD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
		AND      B.VERSION_NUMBER = AW_VERSION_NUMBER
		and 		PD.VERSION_NUMBER = B.VERSION_NUMBER
		and 		PD.budget_period = AW_BUDGET_PERIOD
		and 		PD.person_id = AW_PERSONID
		group by period_type;
else
     open cur_generic for

      SELECT  period_type,  sum(salary_requested) SALARY_REQUESTED,
--       sum(.01 * percent_effort *  round(months_between(PD.end_date,PD.start_date))) 	MONTHS
--         sum(.01 * percent_effort *  round(months_between(PD.end_date,PD.start_date),1)) 	MONTHS     
           sum(.01 * PD.percent_effort * FN_GET_NUMBER_OF_MONTHS(pd.start_date,pd.end_date) ) as MONTHS
		FROM 		OSP$BUDGET_PERSONNEL_DETAILS PD, OSP$BUDGET B
		WHERE 	PD.proposal_number = AW_PROPOSAL_NUMBER
      AND		PD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
	   AND      B.VERSION_NUMBER = AW_VERSION_NUMBER
		and 		PD.VERSION_NUMBER = B.VERSION_NUMBER
		and 		PD.person_id = AW_PERSONID
		group by period_type;
end if;
	
END;
/






