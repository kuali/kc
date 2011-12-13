DELIMITER /

CREATE TABLE SEQ_ENTITY_NUMBER_S (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_ENTITY_NUMBER_S auto_increment = 1000000000
/

DELIMITER ;
