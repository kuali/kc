DELIMITER /
CREATE TABLE SEQ_S2S_ERROR_ID (
		id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_S2S_ERROR_ID auto_increment = 1
/

DELIMITER ;
