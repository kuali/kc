set serveroutput on size 1000000
declare
	CurSeqNextVal NUMBER;
	MaxTblVal NUMBER;
begin
begin
-- sequence: SEQ_ATTACHMENT_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_ATTACHMENT_ID';
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_ATTACHMENT_ID';
-- tables(columns): ATTACHMENT_FILE(FILE_ID)
	select max(FILE_ID) into MaxTblVal from ATTACHMENT_FILE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;

exception when others then null;
end;
begin
begin
-- sequence: SEQ_AWARD_AMOUNT_TRANS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_AMOUNT_TRANS_ID';
-- tables(columns): AWARD_AMOUNT_TRANSACTION(AWARD_AMOUNT_TRANSACTION_ID)
	select max(AWARD_AMOUNT_TRANSACTION_ID) into MaxTblVal from AWARD_AMOUNT_TRANSACTION;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_AMT_FNA_DSTRBTN_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_AMT_FNA_DSTRBTN_ID';
-- tables(columns): AWARD_AMT_FNA_DISTRIBUTION(AWARD_AMT_FNA_DISTRIBUTION_ID)
	select max(AWARD_AMT_FNA_DISTRIBUTION_ID) into MaxTblVal from AWARD_AMT_FNA_DISTRIBUTION;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_APPROVED_SUBAWARD_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_APPROVED_SUBAWARD_ID';
-- tables(columns): AWARD_APPROVED_SUBAWARDS(AWARD_APPROVED_SUBAWARD_ID)
	select max(AWARD_APPROVED_SUBAWARD_ID) into MaxTblVal from AWARD_APPROVED_SUBAWARDS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_ATTACHMENT_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_ATTACHMENT_ID';
-- tables(columns): AWARD_ATTACHMENT(AWARD_ATTACHMENT_ID)
	select max(AWARD_ATTACHMENT_ID) into MaxTblVal from AWARD_ATTACHMENT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_AWARD_CLOSEOUT
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_AWARD_CLOSEOUT';
-- tables(columns): AWARD_CLOSEOUT(AWARD_CLOSEOUT_ID)
	select max(AWARD_CLOSEOUT_ID) into MaxTblVal from AWARD_CLOSEOUT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_COMMENT_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_COMMENT_ID';
-- tables(columns): AWARD_COMMENT(AWARD_COMMENT_ID)
	select max(AWARD_COMMENT_ID) into MaxTblVal from AWARD_COMMENT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_COST_SHARE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_COST_SHARE_ID';
-- tables(columns): AWARD_COST_SHARE(AWARD_COST_SHARE_ID)
	select max(AWARD_COST_SHARE_ID) into MaxTblVal from AWARD_COST_SHARE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_CUSTOM_DATA_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_CUSTOM_DATA_ID';
-- tables(columns): AWARD_CUSTOM_DATA(AWARD_CUSTOM_DATA_ID)
	select max(AWARD_CUSTOM_DATA_ID) into MaxTblVal from AWARD_CUSTOM_DATA;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_EXEMPT_NUMBER_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_EXEMPT_NUMBER_ID';
-- tables(columns): AWARD_EXEMPT_NUMBER(AWARD_EXEMPT_NUMBER_ID)
	select max(AWARD_EXEMPT_NUMBER_ID) into MaxTblVal from AWARD_EXEMPT_NUMBER;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_REP_TERMS_RECNT_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_REP_TERMS_RECNT_ID';
-- tables(columns): AWARD_REP_TERMS_RECNT(AWARD_REP_TERMS_RECNT_ID)
	select max(AWARD_REP_TERMS_RECNT_ID) into MaxTblVal from AWARD_REP_TERMS_RECNT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_NOTEPAD_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_NOTEPAD_ID';
-- tables(columns): AWARD_NOTEPAD(AWARD_NOTEPAD_ID)
	select max(AWARD_NOTEPAD_ID) into MaxTblVal from AWARD_NOTEPAD;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_SCIENCE_KEYWORD_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_SCIENCE_KEYWORD_ID';
-- tables(columns): AWARD_SCIENCE_KEYWORD(AWARD_SCIENCE_KEYWORD_ID)
	select max(AWARD_SCIENCE_KEYWORD_ID) into MaxTblVal from AWARD_SCIENCE_KEYWORD;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_SPECIAL_REVIEW_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_SPECIAL_REVIEW_ID';
-- tables(columns): AWARD_SPECIAL_REVIEW(AWARD_SPECIAL_REVIEW_ID)
	select max(AWARD_SPECIAL_REVIEW_ID) into MaxTblVal from AWARD_SPECIAL_REVIEW;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_SPONSOR_TERM
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_SPONSOR_TERM';
-- tables(columns): AWARD_SPONSOR_TERM(AWARD_SPONSOR_TERM_ID)
	select max(AWARD_SPONSOR_TERM_ID) into MaxTblVal from AWARD_SPONSOR_TERM;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_SYNC_CHANGE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_SYNC_CHANGE_ID';
-- tables(columns): AWARD_SYNC_CHANGE(AWARD_SYNC_CHANGE_ID)
	select max(AWARD_SYNC_CHANGE_ID) into MaxTblVal from AWARD_SYNC_CHANGE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_SYNC_LOG_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_SYNC_LOG_ID';
-- tables(columns): AWARD_SYNC_LOG(AWARD_SYNC_LOG_ID)
	select max(AWARD_SYNC_LOG_ID) into MaxTblVal from AWARD_SYNC_LOG;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_SYNC_STATUS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_SYNC_STATUS_ID';
-- tables(columns): AWARD_SYNC_STATUS(AWARD_SYNC_STATUS_ID)
	select max(AWARD_SYNC_STATUS_ID) into MaxTblVal from AWARD_SYNC_STATUS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_TEMPLATE
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_TEMPLATE';
-- tables(columns): AWARD_TEMPLATE(AWARD_TEMPLATE_CODE)
--					AWARD_TEMPLATE_CONTACT(AWARD_TEMPLATE_CONTACT_ID)
--					AWARD_TEMPLATE_COMMENTS(AWARD_TEMPLATE_COMMENTS_ID)
--					AWARD_TEMPLATE_TERMS(AWARD_TEMPLATE_TERMS_ID)
--					AWARD_TEMPLATE_REPORT_TERMS(TEMPLATE_REPORT_TERMS_ID)
--					AWARD_TEMPL_REP_TERMS_RECNT(TEMPL_REP_TERMS_RECNT_ID)
	select max(TblVal) into MaxTblVal from (select max(AWARD_TEMPLATE_CODE) TblVal from AWARD_TEMPLATE union
	select max(AWARD_TEMPLATE_CONTACT_ID) from AWARD_TEMPLATE_CONTACT union
	select max(AWARD_TEMPLATE_COMMENTS_ID) from AWARD_TEMPLATE_COMMENTS union
	select max(AWARD_TEMPLATE_TERMS_ID) from AWARD_TEMPLATE_TERMS union
	select max(TEMPLATE_REPORT_TERMS_ID) from AWARD_TEMPLATE_REPORT_TERMS union
	select max(TEMPL_REP_TERMS_RECNT_ID) from AWARD_TEMPL_REP_TERMS_RECNT);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_AWARD_TRANS_SPONSOR_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_AWARD_TRANS_SPONSOR_ID';
-- tables(columns): AWARD_TRANSFERRING_SPONSOR(AWARD_TRANSFERRING_SPONSOR_ID)
	select max(AWARD_TRANSFERRING_SPONSOR_ID) into MaxTblVal from AWARD_TRANSFERRING_SPONSOR;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BATCH_CORRESPONDENCE
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BATCH_CORRESPONDENCE';
-- tables(columns): BATCH_CORRESPONDENCE_DETAIL(BATCH_CORRESPONDENCE_DETAIL_ID)
	select max(BATCH_CORRESPONDENCE_DETAIL_ID) into MaxTblVal from BATCH_CORRESPONDENCE_DETAIL;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BGT_PER_DET_RATE_BASE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BGT_PER_DET_RATE_BASE_ID';
-- tables(columns): BUDGET_PER_DET_RATE_AND_BASE(BGT_PER_DET_RATE_AND_BASE_ID)
	select max(BGT_PER_DET_RATE_AND_BASE_ID) into MaxTblVal from BUDGET_PER_DET_RATE_AND_BASE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BUDGET_DETAILS_CAL_AMTS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BUDGET_DETAILS_CAL_AMTS_ID';
-- tables(columns): BUDGET_DETAILS_CAL_AMTS(BUDGET_DETAILS_CAL_AMTS_ID)
	select max(BUDGET_DETAILS_CAL_AMTS_ID) into MaxTblVal from BUDGET_DETAILS_CAL_AMTS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BUDGET_DETAILS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BUDGET_DETAILS_ID';
-- tables(columns): BUDGET_DETAILS(BUDGET_DETAILS_ID)
	select max(BUDGET_DETAILS_ID) into MaxTblVal from BUDGET_DETAILS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BUDGET_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BUDGET_ID';
-- tables(columns): BUDGET(BUDGET_ID)
	select max(BUDGET_ID) into MaxTblVal from BUDGET;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BUDGET_PER_CAL_AMTS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BUDGET_PER_CAL_AMTS_ID';
-- tables(columns): BUDGET_PERSONNEL_CAL_AMTS(BUDGET_PERSONNEL_CAL_AMTS_ID)
	select max(BUDGET_PERSONNEL_CAL_AMTS_ID) into MaxTblVal from BUDGET_PERSONNEL_CAL_AMTS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BUDGET_PER_DET_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BUDGET_PER_DET_ID';
-- tables(columns): BUDGET_PERSONNEL_DETAILS(BUDGET_PERSONNEL_DETAILS_ID)
	select max(BUDGET_PERSONNEL_DETAILS_ID) into MaxTblVal from BUDGET_PERSONNEL_DETAILS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BUDGET_PERIOD_NUMBER
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BUDGET_PERIOD_NUMBER';
-- tables(columns): BUDGET_PERIODS(BUDGET_PERIOD_NUMBER)
	select max(BUDGET_PERIOD_NUMBER) into MaxTblVal from BUDGET_PERIODS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_BUDGET_RATE_AND_BASE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_BUDGET_RATE_AND_BASE_ID';
-- tables(columns): BUDGET_RATE_AND_BASE(BUDGET_RATE_AND_BASE_ID)
	select max(BUDGET_RATE_AND_BASE_ID) into MaxTblVal from BUDGET_RATE_AND_BASE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_COMM_RESEARCH_AREAS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_COMM_RESEARCH_AREAS_ID';
-- tables(columns): COMM_RESEARCH_AREAS(ID)
	select max(ID) into MaxTblVal from COMM_RESEARCH_AREAS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_COMM_SCHEDULE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_COMM_SCHEDULE_ID';
-- tables(columns): COMM_SCHEDULE(ID)
--                  COMM_SCHEDULE(SCHEDULE_ID)
	select max(TblVal) into MaxTblVal from (select max(ID) TblVal from COMM_SCHEDULE union
	select max(SCHEDULE_ID) from COMM_SCHEDULE);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_COMMITTEE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_COMMITTEE_ID';
-- tables(columns): COMMITTEE(ID)
--					COMM_MEMBERSHIPS(COMM_MEMBERSHIP_ID)
--					COMM_MEMBER_ROLES(COMM_MEMBER_ROLES_ID)
--					COMM_MEMBER_EXPERTISE(COMM_MEMBER_EXPERTISE_ID)
--					COMM_BATCH_CORRESP_DETAIL(COMM_BATCH_CORRESP_DETAIL_ID)
	select max(TblVal) into MaxTblVal from (select max(ID) TblVal from COMMITTEE union
	select max(COMM_MEMBERSHIP_ID) from COMM_MEMBERSHIPS union
	select max(COMM_MEMBER_ROLES_ID) from COMM_MEMBER_ROLES union
	select max(COMM_MEMBER_EXPERTISE_ID) from COMM_MEMBER_EXPERTISE union
	select max(COMM_BATCH_CORRESP_DETAIL_ID) from COMM_BATCH_CORRESP_DETAIL);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_CONG_DISTRICT_ID_KRA
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_CONG_DISTRICT_ID_KRA';
-- tables(columns): EPS_PROP_CONG_DISTRICT(CONG_DISTRICT_ID)
	select max(CONG_DISTRICT_ID) into MaxTblVal from EPS_PROP_CONG_DISTRICT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_CUSTOM_ATTRIBUTE
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_CUSTOM_ATTRIBUTE';
-- tables(columns): CUSTOM_ATTRIBUTE(ID)
--                  PERSON_TRAINING(PERSON_TRAINING_ID)
	select max(TblVal) into MaxTblVal from (select max(ID) TblVal from CUSTOM_ATTRIBUTE union
	select max(PERSON_TRAINING_ID) from PERSON_TRAINING);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_EPS_PROP_SPECIAL_REVIEW_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_EPS_PROP_SPECIAL_REVIEW_ID';
-- tables(columns): EPS_PROP_SPECIAL_REVIEW(PROPOSAL_SPECIAL_REVIEW_ID)
	select max(PROPOSAL_SPECIAL_REVIEW_ID) into MaxTblVal from EPS_PROP_SPECIAL_REVIEW;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_EPS_PROP_EXEMPT_NUMBER_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_EPS_PROP_EXEMPT_NUMBER_ID';
-- tables(columns): EPS_PROP_EXEMPT_NUMBER(PROPOSAL_EXEMPT_NUMBER_ID)
	select max(PROPOSAL_EXEMPT_NUMBER_ID) into MaxTblVal from EPS_PROP_EXEMPT_NUMBER;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_IP_COMMENT
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_IP_COMMENT';
-- tables(columns): PROPOSAL_COMMENTS(PROPOSAL_COMMENTS_ID)
	select max(PROPOSAL_COMMENTS_ID) into MaxTblVal from PROPOSAL_COMMENTS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_IP_REV_ACTIVITY_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_IP_REV_ACTIVITY_ID';
-- tables(columns): PROPOSAL_IP_REV_ACTIVITY(PROPOSAL_IP_REV_ACTIVITY_ID)
	select max(PROPOSAL_IP_REV_ACTIVITY_ID) into MaxTblVal from PROPOSAL_IP_REV_ACTIVITY;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_IP_REVIEW_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_IP_REVIEW_ID';
-- tables(columns): IP_REVIEW(IP_REVIEW_ID)
	select max(IP_REVIEW_ID) into MaxTblVal from IP_REVIEW;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_MEETING_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_MEETING_ID';
-- tables(columns): COMM_SCHEDULE_MINUTES(COMM_SCHEDULE_MINUTES_ID)
--					COMM_SCHEDULE_ACT_ITEMS(COMM_SCHEDULE_ACT_ITEMS_ID)
--					COMM_SCHEDULE_MINUTE_DOC(COMM_SCHEDULE_MINUTE_DOC_ID)
--					SCHEDULE_AGENDA(SCHEDULE_AGENDA_ID)
--					PROTOCOL_VOTE_ABSTAINEES(PROTOCOL_VOTE_ABSTAINEES_ID)
--					PROTOCOL_VOTE_RECUSED(PROTOCOL_VOTE_RECUSED_ID)
--					COMM_SCHEDULE_ATTENDANCE(COMM_SCHEDULE_ATTENDANCE_ID)
	select max(TblVal) into MaxTblVal from (select max(COMM_SCHEDULE_MINUTES_ID) TblVal from COMM_SCHEDULE_MINUTES union
	select max(COMM_SCHEDULE_ACT_ITEMS_ID) from COMM_SCHEDULE_ACT_ITEMS union
	select max(COMM_SCHEDULE_MINUTE_DOC_ID) from COMM_SCHEDULE_MINUTE_DOC union
	select max(SCHEDULE_AGENDA_ID) from SCHEDULE_AGENDA union
	select max(PROTOCOL_VOTE_ABSTAINEES_ID) from PROTOCOL_VOTE_ABSTAINEES union
	select max(PROTOCOL_VOTE_RECUSED_ID) from PROTOCOL_VOTE_RECUSED union
	select max(COMM_SCHEDULE_ATTENDANCE_ID) from COMM_SCHEDULE_ATTENDANCE);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_MSG_OF_THE_DAY_ID_KRA
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_MSG_OF_THE_DAY_ID_KRA';
-- tables(columns): MSG_OF_THE_DAY(MSG_OF_THE_DAY_ID)
	select max(MSG_OF_THE_DAY_ID) into MaxTblVal from MSG_OF_THE_DAY;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PERSON_APPOINTMENT
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PERSON_APPOINTMENT';
-- tables(columns): PERSON_APPOINTMENT(APPOINTMENT_ID)
	select max(APPOINTMENT_ID) into MaxTblVal from PERSON_APPOINTMENT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PERSON_DEGREE
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PERSON_DEGREE';
-- tables(columns): PERSON_DEGREE(DEGREE_ID)
	select max(DEGREE_ID) into MaxTblVal from PERSON_DEGREE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROP_ROLE_TEMPLATE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROP_ROLE_TEMPLATE_ID';
-- tables(columns): PROP_ROLE_TEMPLATE(ID)
	select max(ID) into MaxTblVal from PROP_ROLE_TEMPLATE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROP_SCIENCE_KEYWORD_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROP_SCIENCE_KEYWORD_ID';
-- tables(columns): PROPOSAL_SCIENCE_KEYWORD(PROPOSAL_SCIENCE_KEYWORD_ID)
	select max(PROPOSAL_SCIENCE_KEYWORD_ID) into MaxTblVal from PROPOSAL_SCIENCE_KEYWORD;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_ADMIN_DETAILS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_ADMIN_DETAILS_ID';
-- tables(columns): PROPOSAL_ADMIN_DETAILS(PROPOSAL_ADMIN_DETAIL_ID)
	select max(PROPOSAL_ADMIN_DETAIL_ID) into MaxTblVal from PROPOSAL_ADMIN_DETAILS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_COST_SHARE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_COST_SHARE_ID';
-- tables(columns): PROPOSAL_COST_SHARING(PROPOSAL_COST_SHARING_ID)
	select max(PROPOSAL_COST_SHARING_ID) into MaxTblVal from PROPOSAL_COST_SHARING;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_CUSTOM_DATA_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_CUSTOM_DATA_ID';
-- tables(columns): PROPOSAL_CUSTOM_DATA(PROPOSAL_CUSTOM_DATA_ID)
	select max(PROPOSAL_CUSTOM_DATA_ID) into MaxTblVal from PROPOSAL_CUSTOM_DATA;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_EXEMPT_NUMBER_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_EXEMPT_NUMBER_ID';
-- tables(columns): PROPOSAL_EXEMPT_NUMBER(PROPOSAL_EXEMPT_NUMBER_ID)
	select max(PROPOSAL_EXEMPT_NUMBER_ID) into MaxTblVal from PROPOSAL_EXEMPT_NUMBER;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_IP_REVIEW_JOIN_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_IP_REVIEW_JOIN_ID';
-- tables(columns): PROPOSAL_IP_REVIEW_JOIN(PROPOSAL_IP_REVIEW_JOIN_ID)
	select max(PROPOSAL_IP_REVIEW_JOIN_ID) into MaxTblVal from PROPOSAL_IP_REVIEW_JOIN;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_NOTEPAD_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_NOTEPAD_ID';
-- tables(columns): PROPOSAL_NOTEPAD(PROPOSAL_NOTEPAD_ID)
	select max(PROPOSAL_NOTEPAD_ID) into MaxTblVal from PROPOSAL_NOTEPAD;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_NUMBER
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_NUMBER';
-- tables(columns): PROPOSAL_LOG(PROPOSAL_NUMBER)
	select max(PROPOSAL_NUMBER) into MaxTblVal from PROPOSAL_LOG;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_NUMBER_KRA
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_NUMBER_KRA';
-- tables(columns): EPS_PROPOSAL(PROPOSAL_NUMBER)
	select max(PROPOSAL_NUMBER) into MaxTblVal from EPS_PROPOSAL;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_PROPOSAL_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_PROPOSAL_ID';
-- tables(columns): PROPOSAL(PROPOSAL_ID)
	select max(PROPOSAL_ID) into MaxTblVal from PROPOSAL;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROPOSAL_SPECIAL_REVIEW_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROPOSAL_SPECIAL_REVIEW_ID';
-- tables(columns): PROPOSAL_SPECIAL_REVIEW(PROPOSAL_SPECIAL_REVIEW_ID)
	select max(PROPOSAL_SPECIAL_REVIEW_ID) into MaxTblVal from PROPOSAL_SPECIAL_REVIEW;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROTO_CORRESP_TEMPL
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROTO_CORRESP_TEMPL';
-- tables(columns): PROTO_CORRESP_TEMPL(PROTO_CORRESP_TEMPL_ID)
	select max(PROTO_CORRESP_TEMPL_ID) into MaxTblVal from PROTO_CORRESP_TEMPL;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROTO_NOTIFICATION_TEMPL
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROTO_NOTIFICATION_TEMPL';
-- tables(columns): PROTO_NOTIFICATION_TEMPL(NOTIFICATION_TEMPL_ID)
	select max(NOTIFICATION_TEMPL_ID) into MaxTblVal from PROTO_NOTIFICATION_TEMPL;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROTOCOL_EXEMPT_NUMBER_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROTOCOL_EXEMPT_NUMBER_ID';
-- tables(columns): PROTOCOL_EXEMPT_NUMBER(PROTOCOL_EXEMPT_NUMBER_ID)
	select max(PROTOCOL_EXEMPT_NUMBER_ID) into MaxTblVal from PROTOCOL_EXEMPT_NUMBER;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROTOCOL_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROTOCOL_ID';
-- tables(columns): PROTOCOL(PROTOCOL_ID)
--                  PROTOCOL_UNITS(PROTOCOL_UNITS_ID)
--                  PROTOCOL_RISK_LEVELS(PROTOCOL_RISK_LEVELS_ID)
--                  PROTOCOL_VULNERABLE_SUB(PROTOCOL_VULNERABLE_SUB_ID)
--                  PROTOCOL_LOCATION(PROTOCOL_LOCATION_ID)
--                  PROTOCOL_FUNDING_SOURCE(PROTOCOL_FUNDING_SOURCE_ID)
--                  PROTOCOL_PERSONS(PROTOCOL_PERSON_ID)
--                  PROTOCOL_PERSON_ROLE_MAPPING(ROLE_MAPPING_ID)
--                  PROTOCOL_ATTACHMENT_TYPE_GROUP(TYPE_GROUP_ID)
--                  PROTOCOL_ATTACHMENT_PROTOCOL(PA_PROTOCOL_ID)
--                  PROTOCOL_ATTACHMENT_PERSONNEL(PA_PERSONNEL_ID)
--                  PROTOCOL_NOTEPAD(PROTOCOL_NOTEPAD_ID)
--                  PROTOCOL_ACTIONS(PROTOCOL_ACTION_ID)
--                  PROTOCOL_SUBMISSION(SUBMISSION_ID)
--                  PROTOCOL_CORRESPONDENCE(ID)
--                  PROTOCOL_EXEMPT_CHKLST(PROTOCOL_EXEMPT_CHKLST_ID)
--                  PROTOCOL_EXPIDITED_CHKLST(PROTOCOL_EXPEDITED_CHKLST_ID)
--                  PROTOCOL_REVIEWERS(PROTOCOL_REVIEWER_ID)
--                  PROTOCOL_ONLN_RVWS(PROTOCOL_ONLN_RVW_ID)
--                  PROTO_AMEND_RENEWAL(PROTO_AMEND_RENEWAL_ID)
--                  PROTO_AMEND_RENEW_MODULES(PROTO_AMEND_RENEW_MODULES_ID)
--                  PROTOCOL_SUBMISSION_DOC(SUBMISSION_DOC_ID)
	select max(TblVal) into MaxTblVal from (select max(PROTOCOL_ID) TblVal from PROTOCOL union
	select max(PROTOCOL_UNITS_ID) from PROTOCOL_UNITS union
	select max(PROTOCOL_RISK_LEVELS_ID) from PROTOCOL_RISK_LEVELS union
	select max(PROTOCOL_VULNERABLE_SUB_ID) from PROTOCOL_VULNERABLE_SUB union
	select max(PROTOCOL_LOCATION_ID) from PROTOCOL_LOCATION union
	select max(PROTOCOL_FUNDING_SOURCE_ID) from PROTOCOL_FUNDING_SOURCE union
	select max(PROTOCOL_PERSON_ID) from PROTOCOL_PERSONS union
	select max(ROLE_MAPPING_ID) from PROTOCOL_PERSON_ROLE_MAPPING union
	select max(TYPE_GROUP_ID) from PROTOCOL_ATTACHMENT_TYPE_GROUP union
	select max(PA_PROTOCOL_ID) from PROTOCOL_ATTACHMENT_PROTOCOL union
	select max(PA_PERSONNEL_ID) from PROTOCOL_ATTACHMENT_PERSONNEL union
	select max(PROTOCOL_NOTEPAD_ID) from PROTOCOL_NOTEPAD union
	select max(PROTOCOL_ACTION_ID) from PROTOCOL_ACTIONS union
	select max(SUBMISSION_ID) from PROTOCOL_SUBMISSION union
	select max(ID) from PROTOCOL_CORRESPONDENCE union
	select max(PROTOCOL_EXEMPT_CHKLST_ID) from PROTOCOL_EXEMPT_CHKLST union
	select max(PROTOCOL_EXPEDITED_CHKLST_ID) from PROTOCOL_EXPIDITED_CHKLST union
	select max(PROTOCOL_REVIEWER_ID) from PROTOCOL_REVIEWERS union
	select max(PROTOCOL_ONLN_RVW_ID) from PROTOCOL_ONLN_RVWS union
	select max(PROTO_AMEND_RENEWAL_ID) from PROTO_AMEND_RENEWAL union
	select max(PROTO_AMEND_RENEW_MODULES_ID) from PROTO_AMEND_RENEW_MODULES union
	select max(SUBMISSION_DOC_ID) from PROTOCOL_SUBMISSION_DOC);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROTOCOL_REFERENCES_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROTOCOL_REFERENCES_ID';
-- tables(columns): PROTOCOL_REFERENCES(PROTOCOL_REFERENCE_ID)
--                  PROTOCOL_REFERENCES(PROTOCOL_REFERENCE_NUMBER)
	select max(TblVal) into MaxTblVal from (select max(PROTOCOL_REFERENCE_ID) TblVal from PROTOCOL_REFERENCES union
	select max(PROTOCOL_REFERENCE_NUMBER) from PROTOCOL_REFERENCES);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROTOCOL_RESEARCH_AREAS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROTOCOL_RESEARCH_AREAS_ID';
-- tables(columns): PROTOCOL_RESEARCH_AREAS(ID)
	select max(ID) into MaxTblVal from PROTOCOL_RESEARCH_AREAS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_PROTOCOL_SPECIAL_REVIEW_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_PROTOCOL_SPECIAL_REVIEW_ID';
-- tables(columns): PROTOCOL_SPECIAL_REVIEW(PROTOCOL_SPECIAL_REVIEW_ID)
	select max(PROTOCOL_SPECIAL_REVIEW_ID) into MaxTblVal from PROTOCOL_SPECIAL_REVIEW;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_QUESTION_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_QUESTION_ID';
-- tables(columns): QUESTION(QUESTION_ID)
	select max(QUESTION_ID) into MaxTblVal from QUESTION;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_QUESTIONNAIRE_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_QUESTIONNAIRE_ID';
-- tables(columns): QUESTIONNAIRE(QUESTIONNAIRE_ID)
	select max(QUESTIONNAIRE_ID) into MaxTblVal from QUESTIONNAIRE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_QUESTIONNAIRE_REF_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_QUESTIONNAIRE_REF_ID';
-- tables(columns): QUESTION(QUESTION_REF_ID)
--                  QUESTION_EXPLANATION(QUESTION_EXPLANATION_ID)
--                  QUESTIONNAIRE_ANSWER_HEADER(QUESTIONNAIRE_ANSWER_HEADER_ID)
--                  QUESTIONNAIRE_ANSWER(QUESTIONNAIRE_ANSWER_ID)
--                  QUESTIONNAIRE(QUESTIONNAIRE_REF_ID)
--                  QUESTIONNAIRE_QUESTIONS(QUESTIONNAIRE_QUESTIONS_ID)
--                  QUESTIONNAIRE_USAGE(QUESTIONNAIRE_USAGE_ID)
	select max(TblVal) into MaxTblVal from (select max(QUESTION_REF_ID) TblVal from QUESTION union
	select max(QUESTION_EXPLANATION_ID) from QUESTION_EXPLANATION union
	select max(QUESTIONNAIRE_ANSWER_HEADER_ID) from QUESTIONNAIRE_ANSWER_HEADER union
	select max(QUESTIONNAIRE_ANSWER_ID) from QUESTIONNAIRE_ANSWER union
	select max(QUESTIONNAIRE_REF_ID) from QUESTIONNAIRE union
	select max(QUESTIONNAIRE_QUESTIONS_ID) from QUESTIONNAIRE_QUESTIONS union
	select max(QUESTIONNAIRE_USAGE_ID) from QUESTIONNAIRE_USAGE);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_S2S_APP_ATTACHMENT_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_S2S_APP_ATTACHMENT_ID';
-- tables(columns): S2S_APP_ATTACHMENTS(S2S_APP_ATTACHMENT_ID)
	select max(S2S_APP_ATTACHMENT_ID) into MaxTblVal from S2S_APP_ATTACHMENTS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_SPONSOR_TERM
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_SPONSOR_TERM';
-- tables(columns): SPONSOR_TERM(SPONSOR_TERM_ID)
	select max(SPONSOR_TERM_ID) into MaxTblVal from SPONSOR_TERM;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_SUB_AWD_BGT_ATT_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_SUB_AWD_BGT_ATT_ID';
-- tables(columns): BUDGET_SUB_AWARD_ATT(SUB_AWARD_ATTACHMENT_ID)
	select max(SUB_AWARD_ATTACHMENT_ID) into MaxTblVal from BUDGET_SUB_AWARD_ATT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_TRAINING_STIPEND_RATES_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_TRAINING_STIPEND_RATES_ID';
-- tables(columns): TRAINING_STIPEND_RATES(TRAINING_STIPEND_RATES_ID)
	select max(TRAINING_STIPEND_RATES_ID) into MaxTblVal from TRAINING_STIPEND_RATES;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_TRANSACTION_DETAIL_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_TRANSACTION_DETAIL_ID';
-- tables(columns): TRANSACTION_DETAILS(TRANSACTION_DETAIL_ID)
	select max(TRANSACTION_DETAIL_ID) into MaxTblVal from TRANSACTION_DETAILS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_TRANSACTION_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_TRANSACTION_ID';
-- tables(columns): PENDING_TRANSACTIONS(TRANSACTION_ID)
	select max(TRANSACTION_ID) into MaxTblVal from PENDING_TRANSACTIONS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_UNRECOVERED_FNA_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_UNRECOVERED_FNA_ID';
-- tables(columns): PROPOSAL_IDC_RATE(PROPOSAL_IDC_RATE_ID)
	select max(PROPOSAL_IDC_RATE_ID) into MaxTblVal from PROPOSAL_IDC_RATE;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_VALID_SUBM_REVW_TYPE_QUAL
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_VALID_SUBM_REVW_TYPE_QUAL';
-- tables(columns): VALID_PROTO_SUB_REV_TYPE(VALID_PROTO_SUB_REV_TYPE_ID)
--                  VALID_PROTO_SUB_TYPE_QUAL(VALID_PROTO_SUB_TYPE_QUAL_ID)
	select max(TblVal) into MaxTblVal from (select max(VALID_PROTO_SUB_REV_TYPE_ID) TblVal from VALID_PROTO_SUB_REV_TYPE union
	select max(VALID_PROTO_SUB_TYPE_QUAL_ID) from VALID_PROTO_SUB_TYPE_QUAL);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQ_VERSION_HISTORY_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQ_VERSION_HISTORY_ID';
-- tables(columns): VERSION_HISTORY(VERSION_HISTORY_ID)
	select max(VERSION_HISTORY_ID) into MaxTblVal from VERSION_HISTORY;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQUENCE_AWARD_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQUENCE_AWARD_ID';
-- tables(columns): PROPOSAL_PERSONS(PROPOSAL_PERSON_ID)
--                  PROPOSAL_PERSON_UNITS(PROPOSAL_PERSON_UNIT_ID)
--					AWARD(AWARD_ID)
--					AWARD_PERSONS(AWARD_PERSON_ID)
--					AWARD_PERSON_CREDIT_SPLITS(AWARD_PERSON_CREDIT_SPLIT_ID)
--					AWARD_PERSON_UNITS(AWARD_PERSON_UNIT_ID)
--					AWARD_PERS_UNIT_CRED_SPLITS(APU_CREDIT_SPLIT_ID)
--					AWARD_APPROVED_EQUIPMENT(AWARD_APPROVED_EQUIPMENT_ID)
--					AWARD_APPROVED_FOREIGN_TRAVEL(AWARD_APPR_FORN_TRAVEL_ID)
--					AWARD_IDC_RATE(AWARD_IDC_RATE_ID)
--					AWARD_REPORT_TERMS(AWARD_REPORT_TERMS_ID)
--					AWARD_SPONSOR_CONTACTS(AWARD_SPONSOR_CONTACT_ID)
--					AWARD_UNIT_CONTACTS(AWARD_UNIT_CONTACT_ID)
--					AWARD_PAYMENT_SCHEDULE(AWARD_PAYMENT_SCHEDULE_ID)
--					AWARD_AMOUNT_INFO(AWARD_AMOUNT_INFO_ID)
--					AWARD_HIERARCHY(AWARD_HIERARCHY_ID)
--					AWARD_FUNDING_PROPOSALS(AWARD_FUNDING_PROPOSAL_ID)
	select max(TblVal) into MaxTblVal from (select max(PROPOSAL_PERSON_ID) TblVal from PROPOSAL_PERSONS union
	select max(PROPOSAL_PERSON_UNIT_ID) from PROPOSAL_PERSON_UNITS union
	select max(AWARD_ID) from AWARD union
	select max(AWARD_PERSON_ID) from AWARD_PERSONS union
	select max(AWARD_PERSON_CREDIT_SPLIT_ID) from AWARD_PERSON_CREDIT_SPLITS union
	select max(AWARD_PERSON_UNIT_ID) from AWARD_PERSON_UNITS union
	select max(APU_CREDIT_SPLIT_ID) from AWARD_PERS_UNIT_CRED_SPLITS union
	select max(AWARD_APPROVED_EQUIPMENT_ID) from AWARD_APPROVED_EQUIPMENT union
	select max(AWARD_APPR_FORN_TRAVEL_ID) from AWARD_APPROVED_FOREIGN_TRAVEL union
	select max(AWARD_IDC_RATE_ID) from AWARD_IDC_RATE union
	select max(AWARD_REPORT_TERMS_ID) from AWARD_REPORT_TERMS union
	select max(AWARD_SPONSOR_CONTACT_ID) from AWARD_SPONSOR_CONTACTS union
	select max(AWARD_UNIT_CONTACT_ID) from AWARD_UNIT_CONTACTS union
	select max(AWARD_PAYMENT_SCHEDULE_ID) from AWARD_PAYMENT_SCHEDULE union
	select max(AWARD_AMOUNT_INFO_ID) from AWARD_AMOUNT_INFO union
	select max(AWARD_HIERARCHY_ID) from AWARD_HIERARCHY union
	select max(AWARD_FUNDING_PROPOSAL_ID) from AWARD_FUNDING_PROPOSALS);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQUENCE_IPPCS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQUENCE_IPPCS_ID';
-- tables(columns): PROPOSAL_PER_CREDIT_SPLIT(PROPOSAL_PER_CREDIT_SPLIT_ID)
	select max(PROPOSAL_PER_CREDIT_SPLIT_ID) into MaxTblVal from PROPOSAL_PER_CREDIT_SPLIT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQUENCE_IPPUCS_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQUENCE_IPPUCS_ID';
-- tables(columns): PROPOSAL_PERS_UNIT_CRED_SPLITS(PPU_CREDIT_SPLIT_ID)
	select max(PPU_CREDIT_SPLIT_ID) into MaxTblVal from PROPOSAL_PERS_UNIT_CRED_SPLITS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQUENCE_NSF_CODES
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQUENCE_NSF_CODES';
-- tables(columns): NSF_CODES(NSF_SEQUENCE_NUMBER)
	select max(NSF_SEQUENCE_NUMBER) into MaxTblVal from NSF_CODES;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: SEQUENCE_PROPOSAL_ID
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'SEQUENCE_PROPOSAL_ID';
-- tables(columns): PROPOSAL_UNIT_CONTACTS(PROPOSAL_UNIT_CONTACT_ID)
	select max(PROPOSAL_UNIT_CONTACT_ID) into MaxTblVal from PROPOSAL_UNIT_CONTACTS;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

end;
/

set serveroutput on size 1000000
declare
	CurSeqNextVal NUMBER;
	MaxTblVal NUMBER;
begin
begin
-- sequence: KRSB_BAM_PARM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_BAM_PARM_S';
-- tables(columns): KRSB_BAM_PARM_T(BAM_PARM_ID)
	select max(BAM_PARM_ID) into MaxTblVal from KRSB_BAM_PARM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRSB_BAM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_BAM_S';
-- tables(columns): KRSB_BAM_T(BAM_ID)
	select max(BAM_ID) into MaxTblVal from KRSB_BAM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRSB_FLT_SVC_DEF_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_FLT_SVC_DEF_S';
-- tables(columns): KRSB_FLT_SVC_DEF_T(FLT_SVC_DEF_ID)
	select max(FLT_SVC_DEF_ID) into MaxTblVal from KRSB_FLT_SVC_DEF_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRSB_MSG_QUE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_MSG_QUE_S';
-- tables(columns): KRSB_MSG_QUE_T(MSG_QUE_ID)
	select max(MSG_QUE_ID) into MaxTblVal from KRSB_MSG_QUE_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRSB_SVC_DEF_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRSB_SVC_DEF_S';
-- tables(columns): KRSB_SVC_DEF_T(SVC_DEF_ID)
	select max(SVC_DEF_ID) into MaxTblVal from KRSB_SVC_DEF_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRNS_LOCK_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRNS_LOCK_S';
-- tables(columns): KRNS_PESSIMISTIC_LOCK_T(PESSIMISTIC_LOCK_ID)
	select max(PESSIMISTIC_LOCK_ID) into MaxTblVal from KRNS_PESSIMISTIC_LOCK_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRNS_MAINT_LOCK_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRNS_MAINT_LOCK_S';
-- tables(columns): KRNS_MAINT_LOCK_T(MAINT_LOCK_ID)
	select max(MAINT_LOCK_ID) into MaxTblVal from KRNS_MAINT_LOCK_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRNS_NTE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRNS_NTE_S';
-- tables(columns): KRNS_NTE_T(NTE_ID)
	select max(NTE_ID) into MaxTblVal from KRNS_NTE_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ATTR_DATA_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ATTR_DATA_ID_S';
-- tables(columns): KRIM_PND_ROLE_MBR_ATTR_DATA_MT(ATTR_DATA_ID)
-- 					KRIM_PND_DLGN_MBR_ATTR_DATA_T(ATTR_DATA_ID)
-- 					KRIM_PND_GRP_ATTR_DATA_T(ATTR_DATA_ID)
	select max(TblVal) into MaxTblVal from (select max(ATTR_DATA_ID) TblVal from KRIM_PND_ROLE_MBR_ATTR_DATA_MT union
	select max(ATTR_DATA_ID) from KRIM_PND_DLGN_MBR_ATTR_DATA_T union
	select max(ATTR_DATA_ID) from KRIM_PND_GRP_ATTR_DATA_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_DLGN_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_DLGN_ID_S';
-- tables(columns): KRIM_PND_DLGN_T(DLGN_ID)
	select max(DLGN_ID) into MaxTblVal from KRIM_PND_DLGN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_DLGN_MBR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_DLGN_MBR_ID_S';
-- tables(columns): KRIM_PND_DLGN_MBR_T(DLGN_MBR_ID)
	select max(DLGN_MBR_ID) into MaxTblVal from KRIM_PND_DLGN_MBR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_ADDR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_ADDR_ID_S';
-- tables(columns): KRIM_PND_ADDR_MT(ENTITY_ADDR_ID)
	select max(ENTITY_ADDR_ID) into MaxTblVal from KRIM_PND_ADDR_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_AFLTN_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_AFLTN_ID_S';
-- tables(columns): KRIM_PND_AFLTN_MT(ENTITY_AFLTN_ID)
	select max(ENTITY_AFLTN_ID) into MaxTblVal from KRIM_PND_AFLTN_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_EMAIL_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_EMAIL_ID_S';
-- tables(columns): KRIM_PND_EMAIL_MT(ENTITY_EMAIL_ID)
	select max(ENTITY_EMAIL_ID) into MaxTblVal from KRIM_PND_EMAIL_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_EMP_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_EMP_ID_S';
-- tables(columns): KRIM_PND_EMP_INFO_MT(ENTITY_EMP_ID)
	select max(ENTITY_EMP_ID) into MaxTblVal from KRIM_PND_EMP_INFO_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_ETHNIC_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_ETHNIC_ID_S';
-- tables(columns): KRIM_ENTITY_ETHNIC_T(ID)
	select max(ID) into MaxTblVal from KRIM_ENTITY_ETHNIC_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_EXT_ID_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_EXT_ID_ID_S';
-- tables(columns): KRIM_ENTITY_EXT_ID_T(ENTITY_EXT_ID_ID)
	select max(ENTITY_EXT_ID_ID) into MaxTblVal from KRIM_ENTITY_EXT_ID_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_ID_S';
-- tables(columns): KRIM_PERSON_DOCUMENT_T(entity_id)
	select max(entity_id) into MaxTblVal from KRIM_PERSON_DOCUMENT_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_NM_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_NM_ID_S';
-- tables(columns): KRIM_PND_NM_MT(ENTITY_NM_ID)
	select max(ENTITY_NM_ID) into MaxTblVal from KRIM_PND_NM_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_PHONE_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_PHONE_ID_S';
-- tables(columns): KRIM_PND_PHONE_MT(ENTITY_PHONE_ID)
	select max(ENTITY_PHONE_ID) into MaxTblVal from KRIM_PND_PHONE_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_RESIDENCY_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_RESIDENCY_ID_S';
-- tables(columns): KRIM_ENTITY_RESIDENCY_T(ID)
	select max(ID) into MaxTblVal from KRIM_ENTITY_RESIDENCY_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ENTITY_VISA_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ENTITY_VISA_ID_S';
-- tables(columns): KRIM_ENTITY_VISA_T(ID)
	select max(ID) into MaxTblVal from KRIM_ENTITY_VISA_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_GRP_ATTR_DATA_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_GRP_ATTR_DATA_ID_S';
-- tables(columns): KRIM_GRP_ATTR_DATA_T(ATTR_DATA_ID)
	select max(ATTR_DATA_ID) into MaxTblVal from KRIM_GRP_ATTR_DATA_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_GRP_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_GRP_ID_S';
-- tables(columns): KRIM_GRP_DOCUMENT_T(grp_id)
-- 					KRIM_GRP_T(GRP_ID)
	select max(TblVal) into MaxTblVal from (select max(GRP_ID) TblVal from KRIM_GRP_DOCUMENT_T union
	select max(GRP_ID) from KRIM_GRP_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_GRP_MBR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_GRP_MBR_ID_S';
-- tables(columns): KRIM_PND_GRP_MBR_T(GRP_MBR_ID)
-- 					KRIM_GRP_MBR_T(GRP_MBR_ID)
-- 					KRIM_PND_GRP_PRNCPL_MT(GRP_MBR_ID)
	select max(TblVal) into MaxTblVal from (select max(GRP_MBR_ID) TblVal from KRIM_PND_GRP_MBR_T union
	select max(GRP_MBR_ID) from KRIM_GRP_MBR_T union
	select max(GRP_MBR_ID) from KRIM_PND_GRP_PRNCPL_MT);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_PRNCPL_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_PRNCPL_ID_S';
-- tables(columns): KRIM_PERSON_DOCUMENT_T(prncpl_id)
	select max(prncpl_id) into MaxTblVal from KRIM_PERSON_DOCUMENT_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_ID_S';
-- tables(columns): KRIM_ROLE_DOCUMENT_T(role_id)
	select max(role_id) into MaxTblVal from KRIM_ROLE_DOCUMENT_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_MBR_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_MBR_ID_S';
-- tables(columns): KRIM_ROLE_MBR_T(ROLE_MBR_ID)
-- 					KRIM_PND_ROLE_MBR_MT(ROLE_MBR_ID)
	select max(TblVal) into MaxTblVal from (select max(ROLE_MBR_ID) TblVal from KRIM_ROLE_MBR_T union
	select max(ROLE_MBR_ID) from KRIM_PND_ROLE_MBR_MT);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_PERM_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_PERM_ID_S';
-- tables(columns): KRIM_PND_ROLE_PERM_T(ROLE_PERM_ID)
	select max(ROLE_PERM_ID) into MaxTblVal from KRIM_PND_ROLE_PERM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_RSP_ACTN_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_RSP_ACTN_ID_S';
-- tables(columns): KRIM_PND_ROLE_RSP_ACTN_MT(ROLE_RSP_ACTN_ID)
	select max(ROLE_RSP_ACTN_ID) into MaxTblVal from KRIM_PND_ROLE_RSP_ACTN_MT;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KRIM_ROLE_RSP_ID_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KRIM_ROLE_RSP_ID_S';
-- tables(columns): KRIM_PND_ROLE_RSP_T(ROLE_RSP_ID)
	select max(ROLE_RSP_ID) into MaxTblVal from KRIM_PND_ROLE_RSP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_ACTN_ITM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_ACTN_ITM_S';
-- tables(columns): KREW_ACTN_ITM_T(ACTN_ITM_ID)
	select max(ACTN_ITM_ID) into MaxTblVal from KREW_ACTN_ITM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_ACTN_RQST_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_ACTN_RQST_S';
-- tables(columns): KREW_ACTN_RQST_T(ACTN_RQST_ID)
	select max(ACTN_RQST_ID) into MaxTblVal from KREW_ACTN_RQST_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_ACTN_TKN_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_ACTN_TKN_S';
-- tables(columns): KREW_ACTN_TKN_T(ACTN_TKN_ID)
	select max(ACTN_TKN_ID) into MaxTblVal from KREW_ACTN_TKN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_DOC_HDR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_HDR_S';
-- tables(columns): KREW_DOC_HDR_T(DOC_HDR_ID)
--					KREW_DOC_TYP_T(DOC_TYP_ID)
--					KREW_APP_DOC_STAT_TRAN_T(APP_DOC_STAT_TRAN_ID)
	select max(TblVal) into MaxTblVal from (select max(DOC_HDR_ID) TblVal from KREW_DOC_HDR_T union
	select max(DOC_TYP_ID) from KREW_DOC_TYP_T union
	select max(APP_DOC_STAT_TRAN_ID) from KREW_APP_DOC_STAT_TRAN_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_DOC_NTE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_NTE_S';
-- tables(columns): KREW_DOC_NTE_T(DOC_NTE_ID)
-- 					KREW_ATT_T(ATTACHMENT_ID)
	select max(TblVal) into MaxTblVal from (select max(DOC_NTE_ID) TblVal from KREW_DOC_NTE_T union
	select max(ATTACHMENT_ID) from KREW_ATT_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_DOC_TYP_ATTR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_TYP_ATTR_S';
-- tables(columns): KREW_DOC_TYP_ATTR_T(DOC_TYP_ATTRIB_ID)
	select max(DOC_TYP_ATTRIB_ID) into MaxTblVal from KREW_DOC_TYP_ATTR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_EDL_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_EDL_S';
-- tables(columns): KREW_STYLE_T(STYLE_ID)
--					KREW_EDL_DEF_T(EDOCLT_DEF_ID)
--					KREW_EDL_ASSCTN_T(EDOCLT_ASSOC_ID)
	select max(TblVal) into MaxTblVal from (select max(STYLE_ID) TblVal from KREW_STYLE_T union
	select max(EDOCLT_DEF_ID) from KREW_EDL_DEF_T union
	select max(EDOCLT_ASSOC_ID) from KREW_EDL_ASSCTN_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_HLP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_HLP_S';
-- tables(columns): KREW_HLP_T(HLP_ID)
	select max(HLP_ID) into MaxTblVal from KREW_HLP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_OUT_BOX_ITM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_OUT_BOX_ITM_S';
-- tables(columns): KREW_OUT_BOX_ITM_T(ACTN_ITM_ID)
	select max(ACTN_ITM_ID) into MaxTblVal from KREW_OUT_BOX_ITM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_RSP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RSP_S';
-- tables(columns): KREW_RULE_RSP_T(RULE_RSP_ID)
	select max(RULE_RSP_ID) into MaxTblVal from KREW_RULE_RSP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_RTE_NODE_CFG_PARM_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RTE_NODE_CFG_PARM_S';
-- tables(columns): KREW_RTE_NODE_CFG_PARM_T(RTE_NODE_CFG_PARM_ID)
	select max(RTE_NODE_CFG_PARM_ID) into MaxTblVal from KREW_RTE_NODE_CFG_PARM_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_RTE_NODE_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RTE_NODE_S';
-- tables(columns): KREW_RTE_NODE_T(RTE_NODE_ID)
--					KREW_RTE_BRCH_PROTO_T(RTE_BRCH_PROTO_ID)
--					KREW_RTE_NODE_INSTN_T(RTE_NODE_INSTN_ID)
--					KREW_RTE_BRCH_T(RTE_BRCH_ID)
--					KREW_RTE_BRCH_ST_T(RTE_BRCH_ST_ID)
--					KREW_RTE_NODE_INSTN_ST_T(RTE_NODE_INSTN_ST_ID)
--					KREW_DOC_TYP_PROC_T(DOC_TYP_PROC_ID)
	select max(TblVal) into MaxTblVal from (select max(RTE_NODE_ID) TblVal from KREW_RTE_NODE_T union
	select max(RTE_BRCH_PROTO_ID) from KREW_RTE_BRCH_PROTO_T union
	select max(RTE_NODE_INSTN_ID) from KREW_RTE_NODE_INSTN_T union
	select max(RTE_BRCH_ID) from KREW_RTE_BRCH_T union
	select max(RTE_BRCH_ST_ID) from KREW_RTE_BRCH_ST_T union
	select max(RTE_NODE_INSTN_ST_ID) from KREW_RTE_NODE_INSTN_ST_T union
	select max(DOC_TYP_PROC_ID) from KREW_DOC_TYP_PROC_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_RTE_TMPL_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RTE_TMPL_S';
-- tables(columns): KREW_RULE_TMPL_T(RULE_TMPL_ID)
-- 					KREW_RULE_ATTR_T(RULE_ATTR_ID)
--					KREW_RULE_TMPL_ATTR_T(RULE_TMPL_ATTR_ID)
--					KREW_RULE_T(RULE_ID)
--					KREW_DLGN_RSP_T(DLGN_RULE_ID)
--					KREW_RULE_EXT_T(RULE_EXT_ID)
--					KREW_RULE_EXT_VAL_T(RULE_EXT_VAL_ID)
	select max(TblVal) into MaxTblVal from (select max(RULE_TMPL_ID) TblVal from KREW_RULE_TMPL_T union
	select max(RULE_ATTR_ID) from KREW_RULE_ATTR_T union
	select max(RULE_TMPL_ATTR_ID) from KREW_RULE_TMPL_ATTR_T union
	select max(RULE_ID) from KREW_RULE_T union
	select max(DLGN_RULE_ID) from KREW_DLGN_RSP_T union
	select max(RULE_EXT_ID) from KREW_RULE_EXT_T union
	select max(RULE_EXT_VAL_ID) from KREW_RULE_EXT_VAL_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_RULE_EXPR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RULE_EXPR_S';
-- tables(columns): KREW_RULE_EXPR_T(RULE_EXPR_ID)
	select max(RULE_EXPR_ID) into MaxTblVal from KREW_RULE_EXPR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_RULE_TMPL_OPTN_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_RULE_TMPL_OPTN_S';
-- tables(columns): KREW_RULE_TMPL_OPTN_T(RULE_TMPL_OPTN_ID)
	select max(RULE_TMPL_OPTN_ID) into MaxTblVal from KREW_RULE_TMPL_OPTN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_SRCH_ATTR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_SRCH_ATTR_S';
-- tables(columns): KREW_DOC_HDR_EXT_T(DOC_HDR_EXT_ID)
--					KREW_DOC_HDR_EXT_FLT_T(DOC_HDR_EXT_FLT_ID)
--					KREW_DOC_HDR_EXT_LONG_T(DOC_HDR_EXT_LONG_ID)
--					KREW_DOC_HDR_EXT_DT_T(DOC_HDR_EXT_DT_ID)
	select max(TblVal) into MaxTblVal from (select max(DOC_HDR_EXT_ID) TblVal from KREW_DOC_HDR_EXT_T union
	select max(DOC_HDR_EXT_FLT_ID) from KREW_DOC_HDR_EXT_FLT_T union
	select max(DOC_HDR_EXT_LONG_ID) from KREW_DOC_HDR_EXT_LONG_T union
	select max(DOC_HDR_EXT_DT_ID) from KREW_DOC_HDR_EXT_DT_T);
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_EDL_FLD_DMP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_EDL_FLD_DMP_S';
-- tables(columns): KREW_EDL_FLD_DMP_T(EDL_FIELD_DMP_ID)
	select max(EDL_FIELD_DMP_ID) into MaxTblVal from KREW_EDL_FLD_DMP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREW_DOC_LNK_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREW_DOC_LNK_S';
-- tables(columns): KREW_DOC_LNK_T(DOC_LNK_ID)
	select max(DOC_LNK_ID) into MaxTblVal from KREW_DOC_LNK_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_CHNL_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_CHNL_S';
-- tables(columns): KREN_CHNL_T(CHNL_ID)
	select max(CHNL_ID) into MaxTblVal from KREN_CHNL_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_CHNL_SUBSCRP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_CHNL_SUBSCRP_S';
-- tables(columns): KREN_CHNL_SUBSCRP_T(CHNL_SUBSCRP_ID)
	select max(CHNL_SUBSCRP_ID) into MaxTblVal from KREN_CHNL_SUBSCRP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_CNTNT_TYP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_CNTNT_TYP_S';
-- tables(columns): KREN_CNTNT_TYP_T(CNTNT_TYP_ID)
	select max(CNTNT_TYP_ID) into MaxTblVal from KREN_CNTNT_TYP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_MSG_DELIV_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_MSG_DELIV_S';
-- tables(columns): KREN_MSG_DELIV_T(MSG_DELIV_ID)
	select max(MSG_DELIV_ID) into MaxTblVal from KREN_MSG_DELIV_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_MSG_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_MSG_S';
-- tables(columns): KREN_MSG_T(MSG_ID)
	select max(MSG_ID) into MaxTblVal from KREN_MSG_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_NTFCTN_MSG_DELIV_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_NTFCTN_MSG_DELIV_S';
-- tables(columns): KREN_NTFCTN_MSG_DELIV_T(NTFCTN_MSG_DELIV_ID)
	select max(NTFCTN_MSG_DELIV_ID) into MaxTblVal from KREN_NTFCTN_MSG_DELIV_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_NTFCTN_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_NTFCTN_S';
-- tables(columns): KREN_NTFCTN_T(NTFCTN_ID)
	select max(NTFCTN_ID) into MaxTblVal from KREN_NTFCTN_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_PRIO_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_PRIO_S';
-- tables(columns): KREN_PRIO_T(PRIO_ID)
	select max(PRIO_ID) into MaxTblVal from KREN_PRIO_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_PRODCR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_PRODCR_S';
-- tables(columns): KREN_PRODCR_T(PRODCR_ID)
	select max(PRODCR_ID) into MaxTblVal from KREN_PRODCR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_DELIV_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_DELIV_S';
-- tables(columns): KREN_RECIP_DELIV_T(RECIP_DELIV_ID)
	select max(RECIP_DELIV_ID) into MaxTblVal from KREN_RECIP_DELIV_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_LIST_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_LIST_S';
-- tables(columns): KREN_RECIP_LIST_T(RECIP_LIST_ID)
	select max(RECIP_LIST_ID) into MaxTblVal from KREN_RECIP_LIST_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_PREF_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_PREF_S';
-- tables(columns): KREN_RECIP_PREFS_T(RECIP_PREFS_ID)
	select max(RECIP_PREFS_ID) into MaxTblVal from KREN_RECIP_PREFS_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_RECIP_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RECIP_S';
-- tables(columns): KREN_RECIP_T(RECIP_ID)
	select max(RECIP_ID) into MaxTblVal from KREN_RECIP_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_RVWER_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_RVWER_S';
-- tables(columns): KREN_RVWER_T(RVWER_ID)
	select max(RVWER_ID) into MaxTblVal from KREN_RVWER_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

begin
-- sequence: KREN_SNDR_S
	select last_number into CurSeqNextVal from user_sequences where sequence_name = 'KREN_SNDR_S';
-- tables(columns): KREN_SNDR_T(SNDR_ID)
	select max(SNDR_ID) into MaxTblVal from KREN_SNDR_T;
  if MaxTblVal is not null and CurSeqNextVal < MaxTblVal then
    dbms_output.put_line('SeqNextVal: ' || CurSeqNextVal);
    dbms_output.put_line('MaxTblVal: ' || MaxTblVal);
  end if;
exception when others then null;
end;

end;
/