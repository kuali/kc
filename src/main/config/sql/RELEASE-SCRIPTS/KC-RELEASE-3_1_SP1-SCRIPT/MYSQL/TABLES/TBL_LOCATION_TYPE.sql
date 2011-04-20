alter table LOCATION_TYPE add (OBJ_ID varchar(36));
update LOCATION_TYPE set OBJ_ID = UUID() where OBJ_ID IS NULL;
alter table LOCATION_TYPE modify OBJ_ID varchar(36) not null;
commit;