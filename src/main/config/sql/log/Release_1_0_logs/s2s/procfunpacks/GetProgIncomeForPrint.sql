 CREATE or REPLACE PROCEDURE getProgIncomeForPrint ( 
       AS_PROPOSAL_NUMBER IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE, 
       AI_VERSION_NUMBER IN OSP$BUDGET.VERSION_NUMBER%TYPE,     
       AI_BUDGET_PERIOD IN OSP$BUDGET_PROJECT_INCOME.BUDGET_PERIOD%TYPE,   
           cur_generic IN OUT result_sets.cur_generic) IS 

liAmount number;
source OSP$BUDGET_PROJECT_INCOME.description%type;

BEGIN 

OPEN CUR_GENERIC FOR
    SELECT amount,
			  description
    FROM   OSP$BUDGET_PROJECT_INCOME
	 WHERE  PROPOSAL_NUMBER   = AS_PROPOSAL_NUMBER
	 AND	  VERSION_NUMBER = AI_VERSION_NUMBER
	 AND    BUDGET_PERIOD = AI_BUDGET_PERIOD;
    
	
 EXCEPTION
    WHEN OTHERS THEN
       liAmount := 0;
       source := 'Unknown';


end;

/

