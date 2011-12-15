DELIMITER /

CREATE TABLE SEQ_COI_DISCL_NUMBER (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_COI_DISCL_NUMBER auto_increment = 1000000000
/

DELIMITER ;
