alter table LOCATION_TYPE add (OBJ_ID varchar2(36));
update LOCATION_TYPE set OBJ_ID = SYS_GUID() where OBJ_ID IS NULL;
alter table LOCATION_TYPE modify OBJ_ID not null;
commit;