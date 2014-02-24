-- The purpose of this script is to make the description field "required" for protocol and personnel attachments.
-- Along that line the description field in both tables is being set to not null.  In order to ensure older data
-- migrates appropriately, we need to run updates first to ensure that any attachments with null descriptions are given
-- a default value.
update protocol_attachment_personnel a set a.description = concat( concat( (select description from protocol_attachment_type b where b.type_cd = a.type_cd), ' - '), a.update_timestamp) where a.description IS NULL;
commit;
alter table protocol_attachment_personnel modify column description varchar(200) not null;