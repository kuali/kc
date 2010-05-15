alter table PROPOSAL_COST_SHARING modify SOURCE_ACCOUNT varchar2(32);
alter table PROPOSAL_IDC_RATE modify SOURCE_ACCOUNT varchar2(32);
alter table AWARD_IDC_RATE modify SOURCE_ACCOUNT varchar2(32);
alter table AWARD_IDC_RATE modify DESTINATION_ACCOUNT varchar2(32);
alter table AWARD_COST_SHARE modify SOURCE varchar2(32);
alter table AWARD_COST_SHARE modify DESTINATION varchar2(32);