DELIMITER /

alter table EPS_PROPOSAL_BUDGET_EXT drop column VER_NBR
/
alter table EPS_PROPOSAL_BUDGET_EXT drop column OBJ_ID
/
alter table EPS_PROPOSAL_BUDGET_EXT drop column UPDATE_TIMESTAMP
/
alter table EPS_PROPOSAL_BUDGET_EXT drop column UPDATE_USER
/

alter table EPS_PROPOSAL_BUDGET_EXT add column PROPOSAL_NUMBER varchar(12)
/

update EPS_PROPOSAL_BUDGET_EXT budget set PROPOSAL_NUMBER = 
	(select prop.PROPOSAL_NUMBER from EPS_PROPOSAL prop 
		left join BUDGET_DOCUMENT budgetDoc on prop.DOCUMENT_NUMBER = budgetDoc.PARENT_DOCUMENT_KEY 
	where budgetDoc.DOCUMENT_NUMBER = budget.DOCUMENT_NUMBER)
/

alter table EPS_PROPOSAL_BUDGET_EXT modify column PROPOSAL_NUMBER varchar(12) not null
/

DELIMITER ;