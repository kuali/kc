DELIMITER /

DROP PROCEDURE IF EXISTS proto_action_sequence
/
-- fix protocol sequence mismatch
CREATE PROCEDURE proto_action_sequence()
BEGIN
  DECLARE NO_MORE_PROTOCOLS BOOLEAN DEFAULT FALSE;    
  DECLARE v_sequence_number decimal(12,0);
  DECLARE v_protocol_action_id decimal(12,0);
  
  DECLARE PROTOCOL_CUR CURSOR FOR 
  SELECT p.sequence_number, a.protocol_action_id
  FROM protocol p, protocol_actions a
  WHERE a.protocol_id = p.protocol_id and a.protocol_number = p.protocol_number
  and a.sequence_number <> p.sequence_number;
  
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET NO_MORE_PROTOCOLS = TRUE;

  OPEN PROTOCOL_CUR;

  protocol_loop: LOOP
    FETCH PROTOCOL_CUR INTO v_sequence_number, v_protocol_action_id;
    IF NO_MORE_PROTOCOLS THEN
  	  CLOSE PROTOCOL_CUR;
      LEAVE protocol_loop;
    END IF;

	UPDATE protocol_actions
	SET sequence_number = v_sequence_number
	WHERE protocol_action_id = v_protocol_action_id;

  END LOOP;

END
/
CALL proto_action_sequence()
/

DROP PROCEDURE IF EXISTS proto_action_sequence
/

-- fix protocol defer versioning
DROP PROCEDURE IF EXISTS proto_defer_version
/

CREATE PROCEDURE proto_defer_version()
BEGIN
  DECLARE NO_MORE_PROTOCOLS BOOLEAN DEFAULT FALSE;    
  DECLARE v_action_id decimal(12,0);
  DECLARE v_protocol_id decimal(12,0);
  DECLARE v_protocol_number varchar(20);
  DECLARE v_sequence_number decimal(12,0);
  DECLARE v_protocol_action_id decimal(12,0);
  DECLARE v_record_count decimal(12,0);
  DECLARE v_old_proto_corresp_id decimal(12,0);
  DECLARE v_new_proto_corresp_id decimal(12,0);
  
  DECLARE PROTOCOL_CUR CURSOR FOR 
  SELECT protocol_action_id, protocol_id, protocol_number, sequence_number, action_id
  FROM protocol_actions
  where protocol_action_type_code = '201';
  
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET NO_MORE_PROTOCOLS = TRUE;

  OPEN PROTOCOL_CUR;

  protocol_loop: LOOP
    FETCH PROTOCOL_CUR INTO v_protocol_action_id, v_protocol_id, v_protocol_number, v_sequence_number, v_action_id;
    IF NO_MORE_PROTOCOLS THEN
  	  CLOSE PROTOCOL_CUR;
      LEAVE protocol_loop;
    END IF;

	select count(id) into v_record_count
	from protocol_correspondence 
	where protocol_number = v_protocol_number AND sequence_number = v_sequence_number AND 
	action_id = v_action_id;

	IF v_record_count = 0 THEN
		SELECT count(id) into v_record_count
		FROM protocol_correspondence 
		WHERE protocol_number = v_protocol_number and sequence_number < v_sequence_number and action_id = v_action_id;

		IF v_record_count > 0 THEN
			SELECT max(id) into v_old_proto_corresp_id FROM protocol_correspondence
			WHERE protocol_number = v_protocol_number and sequence_number < v_sequence_number and action_id = v_action_id;

    		INSERT INTO SEQ_PROTOCOL_ID VALUES (null);
	    	SELECT MAX(ID) + 1 INTO v_new_proto_corresp_id FROM SEQ_PROTOCOL_ID;
		
			INSERT INTO protocol_correspondence(id, protocol_number, sequence_number, action_id, protocol_id, action_id_fk, proto_corresp_type_code,
			final_flag, update_timestamp, update_user, ver_nbr, correspondence, obj_id, create_timestamp, create_user, final_flag_timestamp)
			SELECT v_new_proto_corresp_id,v_protocol_number,v_sequence_number,v_action_id,v_protocol_id,v_protocol_action_id,proto_corresp_type_code,
			final_flag, update_timestamp, update_user, 1, correspondence, UUID(), create_timestamp, create_user, final_flag_timestamp
			FROM protocol_correspondence
			WHERE id = v_old_proto_corresp_id;
		END IF;
		 
	END IF;

  END LOOP;

END
/
CALL proto_defer_version()
/

DROP PROCEDURE IF EXISTS proto_defer_version
/

DELIMITER ;
