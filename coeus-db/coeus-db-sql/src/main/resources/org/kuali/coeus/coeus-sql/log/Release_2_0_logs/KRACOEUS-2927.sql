delete from proposal_log;
alter table proposal_log add create_timestamp date not null;
alter table proposal_log add create_user varchar2(60) not null;
