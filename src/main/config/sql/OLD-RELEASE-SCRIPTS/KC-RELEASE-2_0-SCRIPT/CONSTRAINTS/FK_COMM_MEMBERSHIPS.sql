-- Foreign Key Constraint(s)   
ALTER TABLE comm_memberships 
    ADD CONSTRAINT FK_COMM_MEMBERSHIPS 
            FOREIGN KEY (COMMITTEE_ID_FK) 
                REFERENCES COMMITTEE (ID) ;

ALTER TABLE comm_memberships 
    ADD CONSTRAINT FK_COMM_MEMBERSHIPS_2 
            FOREIGN KEY (MEMBERSHIP_TYPE_CODE) 
                REFERENCES COMM_MEMBERSHIP_TYPE (membership_type_code) ; 