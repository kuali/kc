alter table protocol_attachment_protocol add CREATE_TIMESTAMP DATE;
update protocol_attachment_protocol  set CREATE_TIMESTAMP = update_timestamp;
alter table protocol_attachment_protocol modify  CREATE_TIMESTAMP DATE NOT NULL;