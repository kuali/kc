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
TRUNCATE TABLE EXEMPT_STUDIES_CHECKLIST
/
INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Educational Research Conducted in Educational Settings -  Research conducted in established or commonly accepted educational settings, involving normal educational practices, such as: i) research on regular and special education instructional strategies, or ii) research on the effectiveness of, or the comparison among instructional techniques, curricula, or classroom management methods.','admin',NOW(),UUID(),1)
/
INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Surveys/Interviews/Standardized Educational Tests/Observation of Public Behavior - Research involving the use of educational tests (cognitive, diagnostic, aptitude, achievement), survey procedures, interview procedures or observation of public behavior if: i) information obtained is recorded in such a manner that human subjects cannot be identified, directly or through identifiers linked to the subjects; or ii) any disclosure of the human subjects'' responses outside the research would not reasonably place the subjects at risk of criminal or civil liability or be damaging to the subjects'' financial standing, employability or reputation.','admin',NOW(),UUID(),1)
/
INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Surveys/ Interviews /Observation of Public Behavior - Research involving the use of educational tests (cognitive, diagnostic, aptitude, achievement), survey procedures, interview procedures or observation of public behavior if the subjects are elected or appointed public officials.','admin',NOW(),UUID(),1)
/
INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Secondary Use of Pre-Existing Data (Data must exist at the time the research is submitted for review.) - Research involving the collection or study of existing data, documents, records, pathological specimens or diagnostic specimens if :i) these sources are publicly available, or ii) the information is recorded by the investigator in such a manner that subjects cannot be identified, directly or through identifiers linked to the subjects.','admin',NOW(),UUID(),1)
/
INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Evaluation of Public Benefit or Service Program - Research and demonstration projects which are conducted by or subject to the approval of Department or Agency heads, and which are designed to study, evaluate, or otherwise examine public benefit or service programs. The following criteria must be satisfied to qualify for exemption: i) the program must deliver a public benefit (e.g. financial or medical benefits as provided under the Social Security Act) or service (e.g. social, supportive, or nutrition services as provided under the Older Americans Act);ii) project must be conducted pursuant to specific federal statutory authority; iii) there must be no statutory requirement that the project be reviewed by an IRB; iv) the project must not involve significant physical invasions or intrusions upon the privacy of participants.','admin',NOW(),UUID(),1)
/
INSERT INTO EXEMPT_STUDIES_CHECKLIST (EXEMPT_STUDIES_CHECKLIST_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Taste and Food Quality Evaluation - Taste and food quality evaluation and consumer acceptance studies: i) if wholesome foods without additive are consumed, or ii) If food is consumed that contains a food ingredient at or below the level and for a use found to be safe, or agricultural chemical or environmental contaminant at or below the level found to be safe, by the FDA or approved by the Environmental Protection Agency or the Food Safety and Inspection Service of the U.S. Department of Agriculture.','admin',NOW(),UUID(),1)
/
delimiter ;
