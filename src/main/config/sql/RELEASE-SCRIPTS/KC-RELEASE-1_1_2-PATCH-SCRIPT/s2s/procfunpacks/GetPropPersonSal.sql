CREATE OR REPLACE PROCEDURE GetPropPersonSal
 (AW_PROPOSAL_NUMBER IN OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE,
  AW_VERSION_NUMBER IN OSP$BUDGET.VERSION_NUMBER%TYPE,
  AW_PERSONID      IN OSP$PERSON.PERSON_ID%TYPE,
 cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for

     SELECT   decode(sum(BD.salary_requested),null,0,sum(BD.salary_requested)) SALARY_REQUESTED,
              decode(sum(.01 * BD.percent_effort *  round(months_between(BD.end_date,BD.start_date),2)),null,0,
                     sum(.01 * BD.percent_effort *  round(months_between(BD.end_date,BD.start_date),2)) ) 	MONTHS        
		FROM 		OSP$BUDGET_PERSONNEL_DETAILS BD , OSP$BUDGET B
		WHERE 	BD.proposal_number = AW_PROPOSAL_NUMBER
      AND      BD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
		and 		BD.VERSION_NUMBER = B.VERSION_NUMBER
      AND      B.VERSION_NUMBER = AW_VERSION_NUMBER
		and 		BD.person_id = AW_PERSONID ;

END;
/




