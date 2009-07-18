ALTER TABLE comm_schedule_attendance 
    ADD CONSTRAINT fk_csa_schedule_id_new 
            FOREIGN KEY (schedule_id_new) 
                REFERENCES comm_schedule (ID) ;

ALTER TABLE comm_schedule_attendance 
    ADD CONSTRAINT fk_csa_person_id 
            FOREIGN KEY (person_id) 
                REFERENCES person (person_id) ;

ALTER TABLE comm_schedule_attendance 
    ADD CONSTRAINT fk_csa_rolodex_id 
            FOREIGN KEY (rolodex_id) 
                REFERENCES rolodex (rolodex_id) ; 