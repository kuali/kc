delimiter /

alter table EPS_PROPOSAL add column FINAL_BUDGET_ID decimal(12)
/

update EPS_PROPOSAL proposal set proposal.FINAL_BUDGET_ID = 
(select budget.BUDGET_ID from BUDGET budget 
left join EPS_PROPOSAL_BUDGET_EXT propBudget on budget.BUDGET_ID = propBudget.BUDGET_ID 
where budget.FINAL_VERSION_FLAG = 'Y' and proposal.PROPOSAL_NUMBER = propBudget.PROPOSAL_NUMBER)
/


delimiter ;
