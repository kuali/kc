DELIMITER /

CREATE TABLE SEQ_PERSON_TRAINING_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM
/
ALTER TABLE SEQ_PERSON_TRAINING_ID auto_increment = 1
/

DELIMITER ;
