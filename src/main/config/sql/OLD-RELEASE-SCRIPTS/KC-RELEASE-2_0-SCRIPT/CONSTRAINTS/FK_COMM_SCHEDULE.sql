-- Foreign Key Constraint(s) 
ALTER TABLE comm_schedule 
    ADD CONSTRAINT FK_COMM_SCHEDULE_2 
            FOREIGN KEY (COMMITTEE_ID_FK) 
                REFERENCES COMMITTEE (ID) ;

ALTER TABLE comm_schedule 
    ADD CONSTRAINT FK_COMM_SCHEDULE 
            FOREIGN KEY (SCHEDULE_STATUS_CODE) 
                REFERENCES SCHEDULE_STATUS (schedule_status_code) ; 