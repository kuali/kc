alter table EPS_PROP_CONG_DISTRICT add (OBJ_ID varchar(36));
update EPS_PROP_CONG_DISTRICT set OBJ_ID = UUID() where OBJ_ID IS NULL;
alter table EPS_PROP_CONG_DISTRICT modify OBJ_ID varchar(36) not null;
commit;