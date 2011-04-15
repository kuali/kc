alter table EPS_PROP_CONG_DISTRICT add (OBJ_ID varchar2(36));
update EPS_PROP_CONG_DISTRICT set OBJ_ID = SYS_GUID() where OBJ_ID IS NULL;
alter table EPS_PROP_CONG_DISTRICT modify OBJ_ID not null;
commit;