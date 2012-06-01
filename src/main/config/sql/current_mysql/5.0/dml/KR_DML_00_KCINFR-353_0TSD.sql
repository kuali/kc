DELIMITER /
drop table if exists KRIM_ROLE_PERM_ID_S
/

create table KRIM_ROLE_PERM_ID_S (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/

alter table KRIM_ROLE_PERM_ID_S auto_increment = 10000
/
DELIMITER ;
