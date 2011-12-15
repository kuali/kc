DELIMITER /

CREATE TABLE SEQ_FIN_ENTITY_UNIT_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_FIN_ENTITY_UNIT_ID auto_increment = 1
/

DELIMITER ;
