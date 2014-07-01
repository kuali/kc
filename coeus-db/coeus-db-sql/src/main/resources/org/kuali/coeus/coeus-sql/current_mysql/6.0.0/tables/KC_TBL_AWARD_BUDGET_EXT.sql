DELIMITER /

alter table AWARD_BUDGET_EXT add AWARD_ID decimal(22,0)
/

update AWARD_BUDGET_EXT budget set AWARD_ID = (select award.AWARD_ID from AWARD award left join BUDGET_DOCUMENT budgetdoc on award.document_number = budgetdoc.parent_document_key where budget.DOCUMENT_NUMBER = budgetdoc.DOCUMENT_NUMBER)
/

alter table AWARD_BUDGET_EXT modify AWARD_ID decimal(22,0) not null
/

DELIMITER ;
