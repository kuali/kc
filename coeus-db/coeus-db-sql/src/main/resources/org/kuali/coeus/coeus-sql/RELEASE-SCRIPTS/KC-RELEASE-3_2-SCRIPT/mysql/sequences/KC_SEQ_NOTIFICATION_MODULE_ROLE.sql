DELIMITER /

CREATE TABLE SEQ_NTFCTN_MODULE_ROLE_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_NTFCTN_MODULE_ROLE_ID auto_increment = 10000
/

DELIMITER ;
