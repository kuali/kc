CREATE OR REPLACE FUNCTION GetFundingMonths
 		(AW_PROPOSAL_NUMBER OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE,
       AW_VERSION OSP$BUDGET.VERSION_NUMBER%TYPE,
  		 AW_PERSONID   OSP$PERSON.PERSON_ID%TYPE) 
RETURN VARCHAR2 IS

li_months  number;
ls_months varchar2(5);

BEGIN

li_months := 0;

	SELECT   sum(.01 * PD.percent_effort *  round(months_between(PD.end_date,PD.start_date))) MONTHS        
	INTO     li_months
   FROM 		OSP$BUDGET_PERSONNEL_DETAILS PD, OSP$BUDGET B
	WHERE 	PD.proposal_number = AW_PROPOSAL_NUMBER
   AND      PD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
	and 		PD.VERSION_NUMBER = B.VERSION_NUMBER
   AND      B.VERSION_NUMBER = AW_VERSION
	and 		PD.budget_period = 1
	and 		PD.person_id = AW_PERSONID;

 
li_months := round(li_months, 2); 
 ls_months := to_char(li_months);
--ls_months := to_char(li_months,'99.99');

IF ls_months IS NOT NULL THEN
   return ls_months;
ELSE
  return '';
END IF;


END;
/

