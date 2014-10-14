DELIMITER /
CREATE TABLE SEQ_DOCUMENT_ACCESS_ID (
		id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_DOCUMENT_ACCESS_ID auto_increment = 1
/

DELIMITER ;
