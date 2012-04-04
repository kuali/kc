DELIMITER /
CREATE TABLE SEQ_RATE_CLASS_BASE_EXCL_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_RATE_CLASS_BASE_EXCL_ID auto_increment = 1
/
DELIMITER ;
