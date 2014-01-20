alter table protocol_onln_rvws add column REVIEWER_APPROVED char(1);
alter table protocol_onln_rvws add column ADMIN_ACCEPTED char(1);
update protocol_onln_rvws set REVIEWER_APPROVED = 'Y', ADMIN_ACCEPTED = 'Y';
alter table protocol_onln_rvws modify REVIEWER_APPROVED char(1) not null;
alter table protocol_onln_rvws modify ADMIN_ACCEPTED char(1) not null;