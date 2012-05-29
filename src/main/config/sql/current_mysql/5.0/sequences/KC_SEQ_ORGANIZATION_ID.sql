DELIMITER /
create table SEQ_ORGANIZATION_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
alter table SEQ_ORGANIZATION_ID auto_increment = 100000
/
DELIMITER ;
