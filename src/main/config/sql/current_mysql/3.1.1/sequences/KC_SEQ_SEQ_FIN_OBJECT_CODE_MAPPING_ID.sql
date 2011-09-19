DELIMITER /

CREATE TABLE SEQ_FIN_OBJECT_CODE_MAPPING_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_FIN_OBJECT_CODE_MAPPING_ID auto_increment = 1
/

DELIMITER ;
