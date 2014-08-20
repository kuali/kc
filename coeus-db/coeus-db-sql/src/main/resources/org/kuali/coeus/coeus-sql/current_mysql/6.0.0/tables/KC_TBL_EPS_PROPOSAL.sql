delimiter /

alter table EPS_PROPOSAL add column FINAL_BUDGET_ID decimal(12)
/

update EPS_PROPOSAL proposal set proposal.FINAL_BUDGET_ID = 
(select max(budget.BUDGET_ID) from BUDGET budget 
left join BUDGET_DOCUMENT budgetDoc on budget.DOCUMENT_NUMBER = budgetDoc.PARENT_DOCUMENT_KEY 
where budget.FINAL_VERSION_FLAG = 'Y' and proposal.DOCUMENT_NUMBER = budgetDoc.PARENT_DOCUMENT_KEY)
/

delimiter ;
