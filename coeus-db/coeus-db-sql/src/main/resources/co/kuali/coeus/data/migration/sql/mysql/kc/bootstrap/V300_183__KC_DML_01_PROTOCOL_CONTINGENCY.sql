--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

delimiter /
TRUNCATE TABLE PROTOCOL_CONTINGENCY
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Inclusion of a statement in the informed consent that the project is research and an explanation of the scope, aims and purposes of the research.  This statement should include a description of the procedures to be followed.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Inclusion of a statement in the informed consent regarding the expected amount of time for the subject''s participation.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Inclusion of a statement in the informed consent regarding the availability of compensation and/or medical treatment if injury occurs.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Inclusion of a statement in the informed consent describing the extent to which confidentiality of records identifying the subject will be maintained or if subjects will be anonymous.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Inclusion of a statemint in the informed consent that participation is voluntary and that nonparticipation will not result in penalty or loss of benefits to which the jubject is otherwise entitled.  If the subjects are students, this statement should include the reassurance that non-participation will not affect their class standing.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Inclusion of a statement in the informed consent which instructs the subject whom to contact for answers to pertinent questions about the research and research subjects rights, and whom to contact in the event of a research related injury to the subject. ''This statement should include the investigator''s name, phone number and the faculty sponsor''s name and phone number, if the investigator Is a student.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Inclusion of a statement in the informed consent describing any reasonably forseeable risks or discomforts to the subject.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Inclusion of a statement in the informed consent which describes any benefits to the subject or to others which may be reasonably expected from the research.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','Inclusion of a statement in the informed consent identifying the responsible Investigator and that person''s affiliation with the institution.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Inclusion of the following IRB review statement In ALL written consent documents (including cover letters): Any questions regarding your rights as a research subject may be addressed to: Massachusetts Institute of Technology Committe on the Use of Humans as Experiments Subjects (617)253-6787.  All research projects that are carried out by Investigators at the Institute are governed by requirements of the Institute and the Federal Government.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Inclusion of a statement in the informed consent regarding the storage and disposition of any audio and/or videotapes.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Inclusion of a statement in the parental consent and the child assent that the child''s responses can be shared with the parent.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','Inclusion of a statement in the informed consent that some of the material may be regarded as sensitive and/or distressing. Some sample questions should be provided to the potential subjects.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('14','Inclusion of a statement In the informed consent describing circumstances under which the subject''s participation may be terminated by the investigator without regard to the subject''s consent.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('15','Inclusion of a statement In the informed consent regarding extra course credit or financial compensation. Where it is appropriate, this statement should describe pro rated or partial credit and/or compensation.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('16','Inclusion of a publication statement in the informed consent.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('17','Inclusion of a statement in the informed consent which provides an emergency, after hours contact person and phone number.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('18','Inclusion of a statement in the informed consent offering additional assistance or referrals.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('19','Reformulation of the informed consent in language appropriate to the subject population.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('20','The language of the informed consent should invite rather than assume participation.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('21','Receipt of consent from the cooperating facility or site.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('22','Formulation and receipt of a child assent or procedure for subjects under 18 years of age.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('23','Formulation and receipt of a phone procedure.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('24','Receipt of a copy of the interview script and/or the instruments to be used.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('25','Appropriate signatures on the application. ','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('26','Clarification to the IRB regarding the inclusion/exclusion criteria for the subjects.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('27','Formulation and/or receipt of a copy of the debriefing.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('28','Clarification to the IRB regarding the project duration; i.e. beginning and ending date.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('29','Clarification to the IRB regarding the recruitment procedures.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('30','The correct telephone number for the Committee on the Use of Humans as Experimental Subjects is (617)253 6787.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('31','Clarification to the IRB regarding the data retrieval method to be employed.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('32','The Committee suggests that the investigator consider removing his/her home phone number.','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('33','The IRB requests a copy of the recruitment ad to be used','admin',NOW(),UUID(),1)
/
INSERT INTO PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('34','The IRB suggests that the Investigator may want to review the consent document and/or the instrument for typographical/grammar/syntax error.','admin',NOW(),UUID(),1)
/
delimiter ;
