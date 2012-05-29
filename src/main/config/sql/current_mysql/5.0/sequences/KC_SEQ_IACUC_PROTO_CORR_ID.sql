DELIMITER /
CREATE TABLE SEQ_IACUC_PROTO_CORR_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_IACUC_PROTO_CORR_ID auto_increment = 1
/
DELIMITER ;
