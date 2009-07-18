ALTER TABLE comm_schedule_minutes 
    ADD CONSTRAINT fk_csm_schedule_id_fk 
            FOREIGN KEY (schedule_id_fk) 
                REFERENCES COMM_SCHEDULE (ID) ;

ALTER TABLE comm_schedule_minutes 
    ADD CONSTRAINT fk_csm_protocol_id_fk 
            FOREIGN KEY (protocol_id_fk) 
                REFERENCES PROTOCOL (protocol_id) ;

ALTER TABLE comm_schedule_minutes 
    ADD CONSTRAINT fk_csm_submission_id_fk 
            FOREIGN KEY (submission_id_fk) 
                REFERENCES PROTOCOL_SUBMISSION (submission_id) ; 