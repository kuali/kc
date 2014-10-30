DELIMITER /
alter table EPS_PROPOSAL add FINAL_BUDGET_ID DECIMAL(12)
/

update EPS_PROPOSAL proposal set proposal.FINAL_BUDGET_ID = 
(select max(budget.BUDGET_ID) from BUDGET budget 
	left join BUDGET_DOCUMENT budgetDoc on budget.DOCUMENT_NUMBER = budgetDoc.DOCUMENT_NUMBER 
where budget.FINAL_VERSION_FLAG = 'Y' and proposal.DOCUMENT_NUMBER = budgetDoc.PARENT_DOCUMENT_KEY)
/

alter table EPS_PROPOSAL add column HIERARCHY_LAST_BUDGET_ID DECIMAL(12)
/

update EPS_PROPOSAL proposal set proposal.HIERARCHY_LAST_BUDGET_ID =
(select max(budget.BUDGET_ID) from BUDGET budget 
	left join BUDGET_DOCUMENT budgetDoc on budget.DOCUMENT_NUMBER = budgetDoc.DOCUMENT_NUMBER 
where proposal.HIERARCHY_LAST_BUDGET_DOC_NBR = budgetDoc.DOCUMENT_NUMBER)
/

alter table EPS_PROPOSAL drop column HIERARCHY_LAST_BUDGET_DOC_NBR
/
 
DELIMITER ;
