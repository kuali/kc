DECLARE

v_submission_id number(12,0);
v_submission_number number(12,0);
v_record_count number(12,0);
  
CURSOR PROTOCOL_CUR IS
  SELECT protocol_number 
  FROM PROTOCOL WHERE active = 'Y';

CURSOR SUBMISSION_CUR(v_protocol_number IN VARCHAR2, v_review_type_code IN VARCHAR2) IS
  SELECT submission_id, protocol_id, protocol_number, sequence_number, submission_number, protocol_review_type_code
  FROM protocol_submission
  WHERE protocol_number like CONCAT(v_protocol_number, '%') AND protocol_review_type_code = v_review_type_code;
	
CURSOR EXPEDITED_CUR(v_protocol_number IN VARCHAR2, v_submission_number IN NUMBER) IS
	SELECT EXPEDITED_REV_CHKLST_CODE, UPDATE_USER, UPDATE_TIMESTAMP
  	FROM PROTOCOL_EXPIDITED_CHKLST
   	WHERE protocol_number like CONCAT(v_protocol_number, '%') AND sequence_number = 0 AND
	submission_number = v_submission_number;
  
CURSOR EXEMPT_CUR(v_protocol_number IN VARCHAR2, v_submission_number IN NUMBER) IS
	SELECT EXEMPT_STUDIES_CHECKLIST_CODE, UPDATE_USER, UPDATE_TIMESTAMP
  	FROM PROTOCOL_EXEMPT_CHKLST
   	WHERE protocol_number like CONCAT(v_protocol_number, '%') AND sequence_number = 0 AND
	submission_number = v_submission_number;
	
BEGIN

FOR proto_rec in PROTOCOL_CUR
LOOP
  FOR reviewCode IN 2..3 LOOP
	FOR subm_rec in SUBMISSION_CUR(proto_rec.protocol_number, reviewCode)
	LOOP
			if subm_rec.protocol_review_type_code = '2' then
				select count(submission_id_fk) into v_record_count
				from PROTOCOL_EXPIDITED_CHKLST 
				where protocol_number like CONCAT(subm_rec.protocol_number, '%') AND sequence_number = subm_rec.sequence_number AND submission_number = subm_rec.submission_number;

				IF v_record_count = 0 THEN
					FOR exped_rec in EXPEDITED_CUR(subm_rec.protocol_number, subm_rec.submission_number)
					LOOP
            			insert into PROTOCOL_EXPIDITED_CHKLST(PROTOCOL_EXPEDITED_CHKLST_ID, PROTOCOL_ID, SUBMISSION_ID_FK,
						PROTOCOL_NUMBER, SEQUENCE_NUMBER, SUBMISSION_NUMBER, EXPEDITED_REV_CHKLST_CODE, UPDATE_TIMESTAMP,
						UPDATE_USER, VER_NBR, OBJ_ID)
                		values(seq_protocol_id.nextval, subm_rec.protocol_id, subm_rec.submission_id, subm_rec.protocol_number, subm_rec.sequence_number,
						subm_rec.submission_number, exped_rec.EXPEDITED_REV_CHKLST_CODE, exped_rec.update_timestamp, exped_rec.update_user, 1, sys_guid()); 
					END LOOP;
				END IF;	
			else
				select count(submission_id_fk) into v_record_count
				from PROTOCOL_EXEMPT_CHKLST 
				where protocol_number like CONCAT(subm_rec.protocol_number, '%') AND sequence_number = subm_rec.sequence_number AND submission_number = subm_rec.submission_number;

				IF v_record_count = 0 THEN
					FOR exempt_rec in EXEMPT_CUR(subm_rec.protocol_number, subm_rec.submission_number)
					LOOP
            			insert into PROTOCOL_EXEMPT_CHKLST(PROTOCOL_EXEMPT_CHKLST_ID, PROTOCOL_ID, SUBMISSION_ID_FK,
						PROTOCOL_NUMBER, SEQUENCE_NUMBER, SUBMISSION_NUMBER, EXEMPT_STUDIES_CHECKLIST_CODE, UPDATE_TIMESTAMP,
						UPDATE_USER, VER_NBR, OBJ_ID)
                		values(seq_protocol_id.nextval, subm_rec.protocol_id, subm_rec.submission_id, subm_rec.protocol_number, subm_rec.sequence_number,
						subm_rec.submission_number, exempt_rec.EXEMPT_STUDIES_CHECKLIST_CODE, exempt_rec.update_timestamp, exempt_rec.update_user, 1, sys_guid()); 
					END LOOP;
				END IF;
			END IF;	
	END LOOP;	

	select count(submission_id) INTO v_record_count
	from protocol_submission
	where protocol_number = proto_rec.protocol_number and sequence_number = 0 and submission_number = 1;
	
	IF v_record_count > 0 THEN
		select submission_id INTO v_submission_id
		from protocol_submission
		where protocol_number = proto_rec.protocol_number and sequence_number = 0 and submission_number = 1;
			
		if reviewCode = 2 then
			update PROTOCOL_EXPIDITED_CHKLST
			set SUBMISSION_ID_FK = v_submission_id
			where PROTOCOL_NUMBER = proto_rec.protocol_number AND
			SEQUENCE_NUMBER = 0 AND SUBMISSION_NUMBER = 1;
		else
			update PROTOCOL_EXEMPT_CHKLST
			set SUBMISSION_ID_FK = v_submission_id
			where PROTOCOL_NUMBER = proto_rec.protocol_number AND
			SEQUENCE_NUMBER = 0 AND SUBMISSION_NUMBER = 1;
		end if;
	END IF;

  END LOOP;
END LOOP;


END;
/
	
