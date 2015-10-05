-- fix protocol sequence mismatch
DECLARE
  
CURSOR PROTOCOL_CUR IS
  SELECT p.sequence_number, a.protocol_action_id
  FROM protocol p, protocol_actions a
  WHERE a.protocol_id = p.protocol_id and a.protocol_number = p.protocol_number
  and a.sequence_number <> p.sequence_number;
	
BEGIN

FOR proto_rec in PROTOCOL_CUR
LOOP

	UPDATE protocol_actions
	SET sequence_number = proto_rec.sequence_number
	WHERE protocol_action_id = proto_rec.protocol_action_id;

END LOOP;


END;
/

	
-- fix protocol defer versioning

DECLARE
  
v_record_count number(12,0);
v_old_proto_corresp_id number(12,0);

CURSOR PROTOCOL_CUR IS
  SELECT protocol_action_id, protocol_id, protocol_number, sequence_number, action_id
  FROM protocol_actions
  where protocol_action_type_code = '201';
	
BEGIN

FOR proto_rec in PROTOCOL_CUR
LOOP

	select count(id) into v_record_count
	from protocol_correspondence 
	where protocol_number = proto_rec.protocol_number AND sequence_number = proto_rec.sequence_number AND 
	action_id = proto_rec.action_id;

	IF v_record_count = 0 THEN
		SELECT count(id) into v_record_count
		FROM protocol_correspondence 
		WHERE protocol_number = proto_rec.protocol_number and sequence_number < proto_rec.sequence_number and action_id = proto_rec.action_id;

		IF v_record_count > 0 THEN
			SELECT max(id) into v_old_proto_corresp_id FROM protocol_correspondence
			WHERE protocol_number = proto_rec.protocol_number and sequence_number < proto_rec.sequence_number and action_id = proto_rec.action_id;

			INSERT INTO protocol_correspondence(id, protocol_number, sequence_number, action_id, protocol_id, action_id_fk, proto_corresp_type_code,
			final_flag, update_timestamp, update_user, ver_nbr, correspondence, obj_id, create_timestamp, create_user, final_flag_timestamp)
			SELECT seq_protocol_id.nextval,proto_rec.protocol_number,proto_rec.sequence_number,proto_rec.action_id,proto_rec.protocol_id,proto_rec.protocol_action_id,proto_corresp_type_code,
			final_flag, update_timestamp, update_user, 1, correspondence, sys_guid(), create_timestamp, create_user, final_flag_timestamp
			FROM protocol_correspondence
			WHERE id = v_old_proto_corresp_id;
		END IF;
		 
	END IF;

END LOOP;


END;
/
	
