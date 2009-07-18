-- Foreign Key Constraint(s) 
ALTER TABLE comm_member_roles 
    ADD CONSTRAINT FK_COMM_MEMBER_ROLES 
            FOREIGN KEY (COMM_MEMBERSHIP_ID_FK) 
                REFERENCES COMM_MEMBERSHIPS (comm_membership_id) ;

ALTER TABLE comm_member_roles 
    ADD CONSTRAINT FK_COMM_MEMBER_ROLES_2 
            FOREIGN KEY (MEMBERSHIP_ROLE_CODE) 
                REFERENCES MEMBERSHIP_ROLE (membership_role_code) ; 