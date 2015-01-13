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

alter table EPS_PROPOSAL_BUDGET_EXT add column STATUS_CODE char(1)
/

update EPS_PROPOSAL_BUDGET_EXT prop_budget set STATUS_CODE = (select BUDGET_STATUS from EPS_PROPOSAL prop where prop.PROPOSAL_NUMBER = prop_budget.PROPOSAL_NUMBER) where (select FINAL_VERSION_FLAG from BUDGET where prop_budget.BUDGET_ID = BUDGET.BUDGET_ID) = 'Y'
/

update EPS_PROPOSAL_BUDGET_EXT prop_budget set STATUS_CODE = '2' where STATUS_CODE is null
/

alter table EPS_PROPOSAL_BUDGET_EXT modify column STATUS_CODE char(1) not null
/

DELIMITER ;
