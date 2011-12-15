DELIMITER /

CREATE TABLE IF NOT EXISTS SEQ_DISCLOSURE_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_DISCLOSURE_ID auto_increment = 1
/

DELIMITER ;
