alter table BUDGET_SUB_AWARDS
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);
