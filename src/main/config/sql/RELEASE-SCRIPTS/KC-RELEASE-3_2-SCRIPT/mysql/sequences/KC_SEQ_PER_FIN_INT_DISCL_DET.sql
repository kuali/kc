DELIMITER /

CREATE TABLE SEQ_PER_FIN_INT_DISCL_DET_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_PER_FIN_INT_DISCL_DET_ID auto_increment = 1
/

DELIMITER ;
