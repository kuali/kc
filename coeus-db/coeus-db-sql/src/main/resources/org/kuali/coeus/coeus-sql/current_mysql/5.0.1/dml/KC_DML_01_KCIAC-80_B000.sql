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

DELIMITER /
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('1','Does the amendment involve a change in the number of animals requested?',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('2','How many times, and how often, will this procedure be done in any one animal?',NOW(),'admin',UUID(),1) 
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('3','How does this amendment relate to your originally stated objectives and experimental design?',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('4','Jolie Smith does not have the appropriate species qualifications on file in the IACUC office. Her qualification form must be updated before she can work with the species on this study unsupervised. Please let us know when this will be done.',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('5','As per the instructions, "Alternatives" needs to be used as one of the search terms. Please re-run the literature search using this keyword.',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('6','Joe Smith does not have an Animal Use Qualification Form on file in the IACUC office. I have attached a form to the email that brought you this letter. This should be done ASAP. Please advise when this will be done.',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('7','The mandatory IACUC orientation program has not been completed. This must be done prior to this protocol being approved.',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('8','Who will euthanize the animals?',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('9','As per the instructions in the literature search, the explicit statement "The proposed activities do not duplicate previous studies." needs to be included here. Please rewrite this section including this statement.',NOW(),'admin',UUID(),1)
/
INSERT INTO IACUC_PROTOCOL_CONTINGENCY (PROTOCOL_CONTINGENCY_CODE,DESCRIPTION,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR) 
VALUES ('10','The entire questionnaire on Hazardous Materials needs to be completed.',NOW(),'admin',UUID(),1)
/

DELIMITER ;
