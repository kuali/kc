DELIMITER /
create table SEQ_ROLODEX_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
alter table SEQ_ROLODEX_ID auto_increment = 100000
/
DELIMITER ;
