ALTER TABLE protocol_submission 
    ADD CONSTRAINT FK_PROTOCOL_SUBMISSION1 
            FOREIGN KEY (PROTOCOL_ID) 
                REFERENCES PROTOCOL (protocol_id) ;

ALTER TABLE protocol_submission 
    ADD CONSTRAINT FK_PROTOCOL_SUBMISSION2 
            FOREIGN KEY (SCHEDULE_ID_FK) 
                REFERENCES COMM_SCHEDULE (ID) ;

ALTER TABLE protocol_submission 
    ADD CONSTRAINT FK_PROTOCOL_SUBMISSION3 
            FOREIGN KEY (COMMITTEE_ID_FK) 
                REFERENCES COMMITTEE (ID) ;

ALTER TABLE protocol_submission 
    ADD CONSTRAINT FK_PROTOCOL_SUBMISSION4 
            FOREIGN KEY (PROTOCOL_REVIEW_TYPE_CODE) 
                REFERENCES PROTOCOL_REVIEW_TYPE (protocol_review_type_code) ;

ALTER TABLE protocol_submission 
    ADD CONSTRAINT FK_PROTOCOL_SUBMISSION5 
            FOREIGN KEY (SUBMISSION_STATUS_CODE) 
                REFERENCES SUBMISSION_STATUS (submission_status_code) ;

ALTER TABLE protocol_submission 
    ADD CONSTRAINT FK_PROTOCOL_SUBMISSION6 
            FOREIGN KEY (SUBMISSION_TYPE_CODE) 
                REFERENCES SUBMISSION_TYPE (submission_type_code) ;

ALTER TABLE protocol_submission 
    ADD CONSTRAINT FK_PROTOCOL_SUBMISSION7 
            FOREIGN KEY (SUBMISSION_TYPE_QUAL_CODE) 
                REFERENCES SUBMISSION_TYPE_QUALIFIER (submission_type_qual_code) ; 

