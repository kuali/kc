ALTER TABLE comm_research_areas 
    ADD CONSTRAINT FK_COMM_RESEARCH_AREAS1 
            FOREIGN KEY (COMMITTEE_ID_FK) 
                REFERENCES COMMITTEE (ID) ;

ALTER TABLE comm_research_areas 
    ADD CONSTRAINT FK_COMM_RESEARCH_AREAS2 
            FOREIGN KEY (RESEARCH_AREA_CODE) 
                REFERENCES RESEARCH_AREAS (research_area_code) ; 