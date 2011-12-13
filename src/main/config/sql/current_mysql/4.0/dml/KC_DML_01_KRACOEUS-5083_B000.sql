DELIMITER /

DROP PROCEDURE IF EXISTS p
/
CREATE PROCEDURE p ()
BEGIN
    DECLARE max_id INT;
    DECLARE max_sq INT;
    SELECT IFNULL(MAX(PROPOSAL_PERSON_ID),0) INTO max_id FROM PROPOSAL_PERSONS;
    SELECT IFNULL(MAX(PROPOSAL_PERSON_UNIT_ID),0) INTO max_sq FROM PROPOSAL_PERSON_UNITS;
    SELECT GREATEST (max_id, max_sq) INTO max_id;
    
    SELECT IFNULL(MAX(ID),0) INTO max_sq FROM SEQ_PROPOSAL_PROPOSAL_ID;
    IF max_sq < max_id THEN 
        SET @alter_proposal_proposal_id_seq := CONCAT('ALTER TABLE SEQ_PROPOSAL_PROPOSAL_ID auto_increment = ', max_id + 1);
        PREPARE alter_proposal_proposal_id_seq_stmt FROM @alter_proposal_proposal_id_seq;
        EXECUTE alter_proposal_proposal_id_seq_stmt;
        DEALLOCATE PREPARE alter_proposal_proposal_id_seq_stmt;
    END IF;
END
/
CALL p ()
/
DROP PROCEDURE IF EXISTS p
/
DELIMITER ;
