alter table EPS_PROP_COST_SHARING
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);
alter table BUDGET_PROJECT_INCOME
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);
alter table EPS_PROP_IDC_RATE
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);
