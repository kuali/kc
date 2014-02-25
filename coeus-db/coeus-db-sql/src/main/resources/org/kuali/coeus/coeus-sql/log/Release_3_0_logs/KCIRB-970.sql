alter table PROTOCOL_REFERENCE_TYPE add active_flag char(1);
update PROTOCOL_REFERENCE_TYPE set active_flag = 'Y';

