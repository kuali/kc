ALTER TABLE AWARD
ADD CONSTRAINT FK_AWARD 
FOREIGN KEY (DOCUMENT_NUMBER) 
REFERENCES AWARD_DOCUMENT (DOCUMENT_NUMBER)
/

ALTER TABLE PROTOCOL 
ADD CONSTRAINT FK_PROTOCOL_2 
FOREIGN KEY (PROTOCOL_STATUS_CODE) 
REFERENCES PROTOCOL_STATUS (PROTOCOL_STATUS_CODE) 
/

ALTER TABLE PROTOCOL 
ADD CONSTRAINT FK_PROTOCOL 
FOREIGN KEY (PROTOCOL_TYPE_CODE) 
REFERENCES PROTOCOL_TYPE (PROTOCOL_TYPE_CODE) 
/

ALTER TABLE AWARD_IDC_RATE
ADD CONSTRAINT FK_AWARD_IDC_RATE 
FOREIGN KEY (AWARD_ID) 
REFERENCES AWARD (AWARD_ID)
/

ALTER TABLE PROTOCOL_VULNERABLE_SUB 
ADD CONSTRAINT FK_PROTOCOL_VULNERABLE_SUB 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES PROTOCOL (PROTOCOL_ID) 
;

ALTER TABLE PROTOCOL_VULNERABLE_SUB 
ADD CONSTRAINT FK_PROTOCOL_VULNERABLE_SUB2 
FOREIGN KEY (VULNERABLE_SUBJECT_TYPE_CODE) 
REFERENCES VULNERABLE_SUBJECT_TYPE (VULNERABLE_SUBJECT_TYPE_CODE) 
;

ALTER TABLE PROTOCOL_RESEARCH_AREAS 
ADD CONSTRAINT FK_PROTOCOL_RESEARCH_AREAS 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES PROTOCOL (PROTOCOL_ID) 
/

ALTER TABLE PROTOCOL_RESEARCH_AREAS 
ADD CONSTRAINT FK_PROTOCOL_RESEARCH_AREAS2
FOREIGN KEY (RESEARCH_AREA_CODE) 
REFERENCES RESEARCH_AREAS (RESEARCH_AREA_CODE) 
/

ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT FK_PROTOCOL_RISK_LEVELS 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES PROTOCOL (PROTOCOL_ID) 
/

ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT FK_PROTOCOL_RISK_LEVELS2 
FOREIGN KEY (RISK_LEVEL_CODE) 
REFERENCES RISK_LEVEL (RISK_LEVEL_CODE) 
/

ALTER TABLE PROTOCOL
ADD CONSTRAINT FK_PROTOCOL_DOCUMENT 
FOREIGN KEY (DOCUMENT_NUMBER) 
REFERENCES PROTOCOL_DOCUMENT (DOCUMENT_NUMBER)
/

alter table AWARD_COMMENT ADD CONSTRAINT FK_AWARD_COMMENT_COMMENT_TYPE FOREIGN KEY(COMMENT_TYPE_CODE)REFERENCES COMMENT_TYPE(COMMENT_TYPE_CODE) ENABLE
/

alter table AWARD_COMMENT ADD CONSTRAINT FK_AWARD_COMMENT_AWARD_ID FOREIGN KEY (AWARD_ID) REFERENCES AWARD(AWARD_ID) ENABLE
/

alter table AWARD_COST_SHARE ADD CONSTRAINT U_AWARD_COST_SHARE UNIQUE(AWARD_NUMBER,SEQUENCE_NUMBER,FISCAL_YEAR,COST_SHARE_TYPE_CODE,SOURCE,DESTINATION) ENABLE
/

alter table AWARD_COST_SHARE ADD CONSTRAINT FK_AWARD_COST_SHARE_TYPE FOREIGN KEY(COST_SHARE_TYPE_CODE)REFERENCES COST_SHARE_TYPE(COST_SHARE_TYPE_CODE) ENABLE
/

alter table AWARD_COST_SHARE ADD CONSTRAINT FK_AWARD_COST_SHARE_AWARD_ID FOREIGN KEY (AWARD_ID) REFERENCES AWARD(AWARD_ID) ENABLE
/

ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT FK_PROTOCOL_LOCATION 
FOREIGN KEY (PROTOCOL_ID) 
REFERENCES PROTOCOL (PROTOCOL_ID) 
/

ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT FK_PROTOCOL_LOCATION_2 
FOREIGN KEY (ORGANIZATION_ID) 
REFERENCES ORGANIZATION (ORGANIZATION_ID) 
/

ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT FK_PROTOCOL_LOCATION_3 
FOREIGN KEY (PROTOCOL_ORG_TYPE_CODE) 
REFERENCES PROTOCOL_ORG_TYPE (PROTOCOL_ORG_TYPE_CODE) 
/

alter table protocol_references
  add constraint fk_protocol_ref_type_code
  foreign key (protocol_reference_type_code)
  references protocol_reference_type(protocol_reference_type_code)
/

alter table protocol_references
  add constraint fk_protocol_id
  foreign key (protocol_id)
  references protocol(protocol_id)
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK2_AWARD_REPORT_TERMS 
FOREIGN KEY (CONTACT_TYPE_CODE) 
REFERENCES CONTACT_TYPE (CONTACT_TYPE_CODE) 
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK3_AWARD_REPORT_TERMS 
FOREIGN KEY (OSP_DISTRIBUTION_CODE) 
REFERENCES DISTRIBUTION (OSP_DISTRIBUTION_CODE) 
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK10_AWARD_REPORT_TERMS 
FOREIGN KEY (FREQUENCY_CODE) 
REFERENCES FREQUENCY (FREQUENCY_CODE) 
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK11_AWARD_REPORT_TERMS 
FOREIGN KEY (FREQUENCY_BASE_CODE) 
REFERENCES FREQUENCY_BASE (FREQUENCY_BASE_CODE) 
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK9_AWARD_REPORT_TERMS 
FOREIGN KEY (REPORT_CODE) 
REFERENCES REPORT (REPORT_CODE) 
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK8_AWARD_REPORT_TERMS 
FOREIGN KEY (REPORT_CLASS_CODE) 
REFERENCES REPORT_CLASS (REPORT_CLASS_CODE) 
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK5_AWARD_REPORT_TERMS 
FOREIGN KEY (ROLODEX_ID) 
REFERENCES ROLODEX (ROLODEX_ID) 
/

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT FK_AWARD_REPORT_TERMS 
FOREIGN KEY (AWARD_ID) 
REFERENCES AWARD (AWARD_ID) 
/

