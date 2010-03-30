ALTER TABLE AWARD_BUDGET_EXT 
    ADD DOCUMENT_NUMBER NUMBER(10,0);
ALTER TABLE EPS_PROPOSAL_BUDGET_EXT 
    ADD DOCUMENT_NUMBER NUMBER(10,0);
    
update award_budget_ext a set document_number = (select document_number from budget b where b.budget_id=a.budget_id);
update EPS_PROPOSAL_BUDGET_EXT a set document_number = (select document_number from budget b where b.budget_id=a.budget_id);

commit;