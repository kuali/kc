create or replace FUNCTION          "FN_GET_FUNDING_MONTHS" 
 		(AS_PROPOSAL_NUMBER OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE,
       AS_PERIOD_TYPE varchar2,
       ai_budget_period osp$budget_personnel_details.budget_period%type,
  		 AS_PERSONID   OSP$PERSON.PERSON_ID%TYPE)
RETURN VARCHAR2 IS

li_months   number;
ls_months   varchar2(5);
li_version  OSP$BUDGET.VERSION_NUMBER%TYPE;

BEGIN

-- get budget version

  BEGIN
	SELECT  version_number
	INTO    li_version
	FROM    OSP$BUDGET
	WHERE   OSP$BUDGET.proposal_number = as_proposal_number
	AND     FINAL_VERSION_FLAG = 'Y';

	EXCEPTION
		WHEN NO_DATA_FOUND then
			begin
				select max(version_number)
            into li_version
            fROM    OSP$BUDGET
	         WHERE   OSP$BUDGET.proposal_number = as_proposal_number;
          exception
            WHEN NO_DATA_FOUND then
						return '0';
         end;

 	END;


li_months := 0;
ls_months := 0;



	SELECT   sum(.01 * PD.percent_effort *  round(months_between(PD.end_date,PD.start_date))) MONTHS
	INTO     li_months
   FROM 		OSP$BUDGET_PERSONNEL_DETAILS PD, OSP$BUDGET B
	WHERE 	PD.proposal_number = AS_PROPOSAL_NUMBER
   AND      PD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
	and 		PD.VERSION_NUMBER = B.VERSION_NUMBER
   AND      B.VERSION_NUMBER = li_version
	and 		PD.person_id = AS_PERSONID
   and      period_type = AS_PERIOD_TYPE
   and      PD.BUDGET_PERIOD = ai_budget_period;


 li_months := round(li_months, 2);
 ls_months := to_char(li_months);

return ls_months;

END;
 
/