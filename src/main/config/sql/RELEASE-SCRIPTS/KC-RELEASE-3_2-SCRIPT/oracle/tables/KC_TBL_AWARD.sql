alter table AWARD add AWARD_SEQUENCE_STATUS VARCHAR2(10) default 'PENDING' not null
/

update award aw
set award_sequence_status = (select v.version_status from version_history v
  where aw.award_number = v.seq_owner_version_name_value
	and aw.sequence_number = v.seq_owner_seq_number
  and v.seq_owner_class_name = 'org.kuali.kra.award.home.Award'
  and v.version_history_id in (select max(version_history_id) from version_history group by seq_owner_version_name_value, seq_owner_seq_number))
/
