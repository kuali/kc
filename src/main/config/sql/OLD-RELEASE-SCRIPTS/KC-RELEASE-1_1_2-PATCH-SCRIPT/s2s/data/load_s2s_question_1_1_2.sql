insert into osp$ynq (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,STATUS,EFFECTIVE_DATE,UPDATE_TIMESTAMP,
UPDATE_USER) VALUES 
('28','Is this a clinical trial?','P',2,'A',sysdate,sysdate,user)
/
insert into osp$ynq (QUESTION_ID,DESCRIPTION,QUESTION_TYPE,NO_OF_ANSWERS,STATUS,EFFECTIVE_DATE,UPDATE_TIMESTAMP,
UPDATE_USER) VALUES 
('29','Is the Government permitted to disclose the project title, and the contact information of the signing official, to organizations that may be interested in contacting you for further information ?','P',2,'A',sysdate,sysdate,user)
/
insert into OSP$PARAMETER (PARAMETER,EFFECTIVE_DATE,VALUE,UPDATE_TIMESTAMP,UPDATE_USER) values ('PI_CITIZENSHIP_FROM_CUSTOM_DATA',to_date('01-MAY-2006','DD-MON-YYYY'),'1',to_date('01-MAY-2006','DD-MON-YYYY'),'KRADEV');
/
commit;