create index idx_qrtz_t_next_fire_time on kc_qrtz_triggers(NEXT_FIRE_TIME);
create index idx_qrtz_t_state on kc_qrtz_triggers(TRIGGER_STATE);
create index idx_qrtz_t_nft_st on kc_qrtz_triggers(NEXT_FIRE_TIME,TRIGGER_STATE);
create index idx_qrtz_t_volatile on kc_qrtz_triggers(IS_VOLATILE);