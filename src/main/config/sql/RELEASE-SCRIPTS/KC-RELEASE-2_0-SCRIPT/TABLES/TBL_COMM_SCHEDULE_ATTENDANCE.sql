CREATE TABLE COMM_SCHEDULE_ATTENDANCE (
    ID number (12, 0) NOT NULL, 
    SCHEDULE_ID_NEW number (12, 0) NOT NULL, 
    ROLODEX_ID number (12, 0) NOT NULL, 
    PERSON_ID varchar2 (9) NOT NULL, 
    SCHEDULE_ID varchar2 (10) NOT NULL, 
    GUEST_FLAG varchar2 (1) NOT NULL, 
    ALTERNATE_FLAG varchar2 (1) NOT NULL, 
    ALTERNATE_FOR varchar2 (9) , 
    NON_EMPLOYEE_FLAG varchar2 (1) NOT NULL, 
    COMMENTS varchar2 (2000) , 
    UPDATE_TIMESTAMP date, 
    UPDATE_USER varchar2 (8) , 
    VER_NBR number (8, 0) DEFAULT 1 NOT NULL, 
    OBJ_ID varchar2 (36) DEFAULT SYS_GUID () NOT NULL) ;

ALTER TABLE comm_schedule_attendance 
    ADD CONSTRAINT pk_csa_id 
            PRIMARY KEY (ID) ; 