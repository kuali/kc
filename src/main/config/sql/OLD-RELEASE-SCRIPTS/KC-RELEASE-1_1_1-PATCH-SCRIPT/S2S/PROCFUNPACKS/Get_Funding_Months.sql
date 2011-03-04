CREATE OR REPLACE PROCEDURE get_Funding_Months (
		AW_PROPOSAL_NUMBER  IN      OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE,
      AW_VERSION          IN      OSP$BUDGET.VERSION_NUMBER%TYPE,
  		AW_PERSONID         IN      OSP$PERSON.PERSON_ID%TYPE,
      CUR_GENERIC         IN OUT  RESULT_SETS.CUR_GENERIC )

IS

li_months  number;
ls_months varchar2(5);

BEGIN


OPEN CUR_GENERIC FOR

   SELECT TO_CHAR ( sum (.01 * PD.percent_effort * FN_GET_NUMBER_OF_MONTHS(pd.start_date,pd.end_date))
                  ) MONTHS,
            decode(period_type, 2, 'AP' , 4, 'SP', 'CC') period_type 
   FROM 		OSP$BUDGET_PERSONNEL_DETAILS PD, OSP$BUDGET B
	WHERE 	PD.proposal_number = AW_PROPOSAL_NUMBER
   AND      PD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
	and 		PD.VERSION_NUMBER = B.VERSION_NUMBER
   AND      B.VERSION_NUMBER = AW_VERSION
	and 		PD.budget_period = 1
	and 		PD.person_id = AW_PERSONID
   group by PERIOD_TYPE;
 END;
/