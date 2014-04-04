DELIMITER /

CREATE TABLE SEQ_VAL_PROTO_SUB_REV_TYP_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/

ALTER TABLE SEQ_VAL_PROTO_SUB_REV_TYP_ID auto_increment = 200
/

DELIMITER ;
