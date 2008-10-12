alter table unit_acl add UPDATE_TIMESTAMP  	DATE ;
alter table unit_acl add UPDATE_USER VARCHAR2(60) ;
update unit_acl set UPDATE_TIMESTAMP=sysdate , UPDATE_USER='quickstart';
ALTER TABLE unit_acl MODIFY ( UPDATE_TIMESTAMP NOT NULL);
ALTER TABLE unit_acl MODIFY ( UPDATE_USER NOT NULL);

 