DELIMITER /

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
  DECLARE maxAttributeId INT;
  CREATE TABLE IF NOT EXISTS SEQ_CUSTOM_ATTRIBUTE (id bigint(19) not null auto_increment, primary key (id)) ENGINE MyISAM;
  select ifnull(max(ID),0) into maxAttributeId from CUSTOM_ATTRIBUTE;
  IF maxAttributeId = 0 THEN
    set @alter_seq := concat('ALTER TABLE SEQ_CUSTOM_ATTRIBUTE auto_increment = ', maxAttributeId+1); 
    prepare alter_seq_stmt from @alter_seq;
    execute alter_seq_stmt;
    deallocate prepare alter_seq_stmt;
  END IF;
END;
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
DELIMITER ;
