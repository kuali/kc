 
CREATE OR REPLACE function getModularQuestion
    ( as_proposal_number in osp$budget.proposal_number%type,
      ai_version in osp$budget.version_number%type)
	  

return varchar2 is

ls_answer osp$budget.modular_budget_flag%type;
 


begin

	 select modular_budget_flag
	 into ls_answer
    from osp$budget
	 where proposal_number = as_proposal_number
    and version_number = ai_version ;
	
			  
	return ls_answer;
		
	exception
		when others then
			 return 'N';
	end;
	
 
/

