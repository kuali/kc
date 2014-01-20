INSERT INTO SCHEDULE_ACT_ITEM_TYPE ( SCHEDULE_ACT_ITEM_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 'New Member Consideration', sysdate, user ); 
INSERT INTO SCHEDULE_ACT_ITEM_TYPE ( SCHEDULE_ACT_ITEM_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 'Other Business', sysdate, user ); 
INSERT INTO SCHEDULE_ACT_ITEM_TYPE ( SCHEDULE_ACT_ITEM_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 'Non-Compliance', sysdate, user ); 
INSERT INTO SCHEDULE_ACT_ITEM_TYPE ( SCHEDULE_ACT_ITEM_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 'Adverse Event', sysdate, user ); 
INSERT INTO SCHEDULE_ACT_ITEM_TYPE ( SCHEDULE_ACT_ITEM_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 'Protocol Deviation', sysdate, user ); 
commit;

INSERT INTO MINUTE_ENTRY_TYPE ( MINUTE_ENTRY_TYPE_CODE, SORT_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 5, 5, 'Adverse Events', sysdate, user ); 
INSERT INTO MINUTE_ENTRY_TYPE ( MINUTE_ENTRY_TYPE_CODE, SORT_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 1, 1, 'General Comments', sysdate, user ); 
INSERT INTO MINUTE_ENTRY_TYPE ( MINUTE_ENTRY_TYPE_CODE, SORT_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 2, 2, 'Attendance', sysdate, user ); 
INSERT INTO MINUTE_ENTRY_TYPE ( MINUTE_ENTRY_TYPE_CODE, SORT_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 3, 3, 'Protocol', sysdate, user ); 
INSERT INTO MINUTE_ENTRY_TYPE ( MINUTE_ENTRY_TYPE_CODE, SORT_ID, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( 4, 4, 'Other Business', sysdate, user ); 
commit;
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '1', 'Inclusion of a statement in the informed consent that the project is research and an explanation of the scope, aims and purposes of the research.  This statement should include a description of the procedures to be followed.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '2', 'Inclusion of a statement in the informed consent regarding the expected amount of time for the subject''s participation.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '3', 'Inclusion of a statement in the informed consent regarding the availability of compensation and/or medical treatment if injury occurs.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '4', 'Inclusion of a statement in the informed consent describing the extent to which confidentiality of records identifying the subject will be maintained or if subjects will be anonymous.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '5', 'Inclusion of a statemint in the informed consent that participation is voluntary and that nonparticipation will not result in penalty or loss of benefits to which the jubject is otherwise entitled.  If the subjects are students, this statement should include the reassurance that non-participation will not affect their class standing.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '6', 'Inclusion of a statement in the informed consent which instructs the subject whom to contact for answers to pertinent questions about the research and research subjects rights, and whom to contact in the event of a research related injury to the subject. ''This statement should include the investigator''s name, phone number and the faculty sponsor''s name and phone number, if the investigator Is a student.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '7', 'Inclusion of a statement in the informed consent describing any reasonably forseeable risks or discomforts to the subject.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '8', 'Inclusion of a statement in the informed consent which describes any benefits to the subject or to others which may be reasonably expected from the research.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '9', 'Inclusion of a statement in the informed consent identifying the responsible Investigator and that person''s affiliation with the institution.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '10', 'Inclusion of the following IRB review statement In ALL written consent documents (including cover letters): Any questions regarding your rights as a research subject may be addressed to: Massachusetts Institute of Technology Committe on the Use of Humans as Experiments Subjects (617)253-6787.  All research projects that are carried out by Investigators at the Institute are governed by requirements of the Institute and the Federal Government.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '11', 'Inclusion of a statement in the informed consent regarding the storage and disposition of any audio and/or videotapes.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '12', 'Inclusion of a statement in the parental consent and the child assent that the child''s responses can be shared with the parent.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '13', 'Inclusion of a statement in the informed consent that some of the material may be regarded as sensitive and/or distressing. Some sample questions should be provided to the potential subjects.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '14', 'Inclusion of a statement In the informed consent describing circumstances under which the subject''s participation may be terminated by the investigator without regard to the subject''s consent.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '15', 'Inclusion of a statement In the informed consent regarding extra course credit or financial compensation. Where it is appropriate, this statement should describe pro rated or partial credit and/or compensation.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '16', 'Inclusion of a publication statement in the informed consent.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '17', 'Inclusion of a statement in the informed consent which provides an emergency, after hours contact person and phone number.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '18', 'Inclusion of a statement in the informed consent offering additional assistance or referrals.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '19', 'Reformulation of the informed consent in language appropriate to the subject population.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '20', 'The language of the informed consent should invite rather than assume participation.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '21', 'Receipt of consent from the cooperating facility or site.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '22', 'Formulation and receipt of a child assent or procedure for subjects under 18 years of age.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '23', 'Formulation and receipt of a phone procedure.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '24', 'Receipt of a copy of the interview script and/or the instruments to be used.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '25', 'Appropriate signatures on the application. ', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '26', 'Clarification to the IRB regarding the inclusion/exclusion criteria for the subjects.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '27', 'Formulation and/or receipt of a copy of the debriefing.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '28', 'Clarification to the IRB regarding the project duration; i.e. beginning and ending date.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '29', 'Clarification to the IRB regarding the recruitment procedures.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '30', 'The correct telephone number for the Committee on the Use of Humans as Experimental Subjects is (617)253 6787.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '31', 'Clarification to the IRB regarding the data retrieval method to be employed.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '32', 'The Committee suggests that the investigator consider removing his/her home phone number.', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '33', 'The IRB requests a copy of the recruitment ad to be used', sysdate, user ); 
INSERT INTO PROTOCOL_CONTINGENCY ( PROTOCOL_CONTINGENCY_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) 
VALUES ( '34', 'The IRB suggests that the Investigator may want to review the consent document and/or the instrument for typographical/grammar/syntax error.', sysdate, user ); 
commit;
