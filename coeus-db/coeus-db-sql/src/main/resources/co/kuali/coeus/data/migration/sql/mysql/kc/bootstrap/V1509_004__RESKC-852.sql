DELIMITER /

DROP PROCEDURE IF EXISTS proto_checklists
/
CREATE PROCEDURE proto_checklists()
BEGIN
  DECLARE NO_MORE_PROTOCOLS, NO_MORE_SUBMISSIONS, NO_MORE_EXPEDITES, NO_MORE_EXEMPTS BOOLEAN DEFAULT FALSE;    
  DECLARE v_protocol_number varchar(20);
  
  DECLARE v_submission_id decimal(12,0);
  DECLARE v_sub_protocol_id decimal(12,0);
  DECLARE v_sub_protocol_number varchar(20);
  DECLARE v_sub_sequence_number decimal(12,0);
  DECLARE v_submission_number decimal(12,0);
  DECLARE v_review_type_code varchar(3);
  
  DECLARE v_checklist_id decimal(12,0);
   
  DECLARE v_checklist_code varchar(4);
  DECLARE v_update_user varchar(60);
  DECLARE v_update_timestamp datetime;

  DECLARE v_record_count decimal(12,0);
  
  DECLARE PROTOCOL_CUR CURSOR FOR 
  SELECT protocol_number 
  FROM PROTOCOL WHERE active = 'Y';
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET NO_MORE_PROTOCOLS = TRUE;

  OPEN PROTOCOL_CUR;

  protocol_loop: LOOP
    FETCH PROTOCOL_CUR INTO v_protocol_number;
    IF NO_MORE_PROTOCOLS THEN
  	  CLOSE PROTOCOL_CUR;
      LEAVE protocol_loop;
    END IF;

	  REVIEWTYPECODEBLOCK: BEGIN
      DECLARE reviewCode INT DEFAULT 2;
      review_code_loop: LOOP         
      	IF reviewCode > 3 THEN
        	LEAVE review_code_loop;
        END IF;
        SUBMISSIONBLOCK: BEGIN
        DECLARE SUBMISSION_CUR CURSOR FOR 
 		SELECT submission_id, protocol_id, protocol_number, sequence_number, submission_number, protocol_review_type_code
    	FROM protocol_submission
   		WHERE protocol_number like CONCAT(v_protocol_number, '%') AND protocol_review_type_code = reviewCode;

        DECLARE CONTINUE HANDLER FOR NOT FOUND SET NO_MORE_SUBMISSIONS = TRUE;
		
        OPEN SUBMISSION_CUR; 
        submission_loop: LOOP
        	FETCH SUBMISSION_CUR INTO v_submission_id, v_sub_protocol_id, v_sub_protocol_number, v_sub_sequence_number, v_submission_number, v_review_type_code;   
            IF NO_MORE_SUBMISSIONS THEN
            	set NO_MORE_SUBMISSIONS = false;
            	CLOSE SUBMISSION_CUR;
            	LEAVE submission_loop;
            END IF; 
			
			if v_review_type_code = '2' then
				select count(submission_id_fk) into v_record_count
				from PROTOCOL_EXPIDITED_CHKLST 
				where protocol_number like CONCAT(v_sub_protocol_number, '%') AND sequence_number = v_sub_sequence_number AND submission_number = v_submission_number;

				IF v_record_count = 0 THEN
					EXPEDITEDCHKLSTBLOCK: BEGIN
        			DECLARE EXPEDITED_CUR CURSOR FOR 
					SELECT EXPEDITED_REV_CHKLST_CODE, UPDATE_USER, UPDATE_TIMESTAMP
  					FROM PROTOCOL_EXPIDITED_CHKLST
   					WHERE protocol_number like CONCAT(v_sub_protocol_number, '%') AND sequence_number = 0 AND
					submission_number = v_submission_number;

        			DECLARE CONTINUE HANDLER FOR NOT FOUND SET NO_MORE_EXPEDITES = TRUE;
				
        			OPEN EXPEDITED_CUR; 
        			expedited_loop: LOOP
        				FETCH EXPEDITED_CUR INTO v_checklist_code, v_update_user, v_update_timestamp;   
            			IF NO_MORE_EXPEDITES THEN
            				set NO_MORE_EXPEDITES = false;
            				CLOSE EXPEDITED_CUR;
            				LEAVE expedited_loop;
            			END IF; 
				
    					INSERT INTO SEQ_PROTOCOL_ID VALUES (null);
	    				SELECT MAX(ID) + 1 INTO v_checklist_id FROM SEQ_PROTOCOL_ID;
				
            			insert into PROTOCOL_EXPIDITED_CHKLST(PROTOCOL_EXPEDITED_CHKLST_ID, PROTOCOL_ID, SUBMISSION_ID_FK,
						PROTOCOL_NUMBER, SEQUENCE_NUMBER, SUBMISSION_NUMBER, EXPEDITED_REV_CHKLST_CODE, UPDATE_TIMESTAMP,
						UPDATE_USER, VER_NBR, OBJ_ID)
                		values(v_checklist_id, v_sub_protocol_id, v_submission_id, v_sub_protocol_number, v_sub_sequence_number,
						v_submission_number, v_checklist_code, v_update_timestamp, v_update_user, 1, UUID()); 

        			END LOOP expedited_loop;
        			END EXPEDITEDCHKLSTBLOCK;
				
				END IF;	
			else
				select count(submission_id_fk) into v_record_count
				from PROTOCOL_EXEMPT_CHKLST 
				where protocol_number like CONCAT(v_sub_protocol_number, '%') AND sequence_number = v_sub_sequence_number AND submission_number = v_submission_number;

				IF v_record_count = 0 THEN
					EXEMPTCHKLSTBLOCK: BEGIN
        			DECLARE EXEMPT_CUR CURSOR FOR 
					SELECT EXEMPT_STUDIES_CHECKLIST_CODE, UPDATE_USER, UPDATE_TIMESTAMP
  					FROM PROTOCOL_EXEMPT_CHKLST
   					WHERE protocol_number like CONCAT(v_sub_protocol_number, '%') AND sequence_number = 0 AND
					submission_number = v_submission_number;

        			DECLARE CONTINUE HANDLER FOR NOT FOUND SET NO_MORE_EXEMPTS = TRUE;
				
        			OPEN EXEMPT_CUR; 
        			exempt_loop: LOOP
        				FETCH EXEMPT_CUR INTO v_checklist_code, v_update_user, v_update_timestamp;   
            			IF NO_MORE_EXEMPTS THEN
            				set NO_MORE_EXEMPTS = false;
            				CLOSE EXEMPT_CUR;
            				LEAVE exempt_loop;
            			END IF; 
				
    					INSERT INTO SEQ_PROTOCOL_ID VALUES (null);
	    				SELECT MAX(ID) + 1 INTO v_checklist_id FROM SEQ_PROTOCOL_ID;
				
            			insert into PROTOCOL_EXEMPT_CHKLST(PROTOCOL_EXEMPT_CHKLST_ID, PROTOCOL_ID, SUBMISSION_ID_FK,
						PROTOCOL_NUMBER, SEQUENCE_NUMBER, SUBMISSION_NUMBER, EXEMPT_STUDIES_CHECKLIST_CODE, UPDATE_TIMESTAMP,
						UPDATE_USER, VER_NBR, OBJ_ID)
                		values(v_checklist_id, v_sub_protocol_id, v_submission_id, v_sub_protocol_number, v_sub_sequence_number,
						v_submission_number, v_checklist_code, v_update_timestamp, v_update_user, 1, UUID()); 

        			END LOOP exempt_loop;
        			END EXEMPTCHKLSTBLOCK;
				
				END IF;	
			end if;
		
        END LOOP submission_loop;
        END SUBMISSIONBLOCK;
		select count(submission_id) INTO v_record_count
		from protocol_submission
		where protocol_number = v_protocol_number and sequence_number = 0 and submission_number = 1;
		
		IF v_record_count > 0 THEN
			select submission_id INTO v_submission_id
			from protocol_submission
			where protocol_number = v_protocol_number and sequence_number = 0 and submission_number = 1;
			if reviewCode = 2 then
				update PROTOCOL_EXPIDITED_CHKLST
				set SUBMISSION_ID_FK = v_submission_id
				where PROTOCOL_NUMBER = v_protocol_number AND
				SEQUENCE_NUMBER = 0 AND
				SUBMISSION_NUMBER = 1;
			else
				update PROTOCOL_EXEMPT_CHKLST
				set SUBMISSION_ID_FK = v_submission_id
				where PROTOCOL_NUMBER = v_protocol_number AND
				SEQUENCE_NUMBER = 0 AND
				SUBMISSION_NUMBER = 1;
			end if;
		END IF;
        SET reviewCode = reviewCode + 1;
   	  END LOOP review_code_loop;
      END REVIEWTYPECODEBLOCK;

  END LOOP;

END
/
CALL proto_checklists()
/

DROP PROCEDURE IF EXISTS proto_checklists
/

DELIMITER ;
