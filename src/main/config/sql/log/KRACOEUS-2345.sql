delete from deadline_type where length(deadline_type_code) > 1;
create table chlength as select deadline_type_code, description, update_timestamp, update_user, ver_nbr, obj_id from deadline_type;
delete from deadline_type;
alter table deadline_type modify deadline_type_code char(1);
insert into deadline_type (deadline_type_code, description, update_timestamp, update_user, ver_nbr, obj_id)
    select deadline_type_code, description, update_timestamp, update_user, ver_nbr, obj_id from chlength;
drop table chlength;
commit;
