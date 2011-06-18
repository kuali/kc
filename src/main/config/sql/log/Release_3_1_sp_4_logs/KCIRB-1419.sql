--  Oracle commands
alter table protocol_onln_rvws add REVIEWER_APPROVED char(1) NOT NULL;
alter table protocol_onln_rvws add ADMIN_ACCEPTED char(1) NOT NULL;

update protocol_onln_rvws set REVIEWER_APPROVED = 'Y', ADMIN_ACCEPTED = 'Y';

--  MySQL commands
alter table kcbnd.protocol_onln_rvws add column REVIEWER_APPROVED char(1) not null;
alter table kcbnd.protocol_onln_rvws add column ADMIN_ACCEPTED char(1) not null;