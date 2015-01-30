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

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6166, (select question_ref_id from question where QUESTION = 'Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.' and sequence_status = 'C'), 'A', 'The information submitted within the application is true, complete and accurate to the best of my knowledge. Any false, fictitious, or fraudulent statements or claims may subject me, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties. I agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application. This certification is being added at this time to meet a specific NIH requirement. It also reflects current federal regulations.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6167, (select question_ref_id from question where QUESTION = 'Can you certify that the information submitted within this application is true, complete and accurate to the best of your knowledge? That any false, fictitious, or fraudulent statements or claims may subject you, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties? That you agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application.' and sequence_status = 'C'), 'N', 'I cannot certify that this application is true, complete and accurate to the best of my knowledge; that I understand that any false, fictitious, or fraudulent statements or claims may subject me, as the PI/Co-PI/Co-I to criminal, civil or administrative penalties or that I agree to accept responsibility for the scientific conduct of the project and to provide the required progress reports if an award is made as a result of this application. This certification is being added at this time to meet a specific NIH requirement, and it also reflects current federal regulations.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6168, (select question_ref_id from question where QUESTION = 'Is there any potential for a perceived or real conflict of interest as defined in MIT''s Policies and Procedures with regard to this proposal?' and sequence_status = 'C'), 'A', 'I have identified a perceived, potential or real financial conflict of interest with regard to the proposed research of this proposal. I have (or will) seek advice and guidance to eliminate or to manage the conflict, by reviewing the circumstances with my department head, center or laboratory director, or with the Director of the Office of Sponsored Programs.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6169, (select question_ref_id from question where QUESTION = 'Is there any potential for a perceived or real conflict of interest as defined in MIT''s Policies and Procedures with regard to this proposal?' and sequence_status = 'C'), 'N', 'I do not have a perceived, potential or real conflict of interest as defined in MIT policies and procedures with regard to the proposed activity.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6170, (select question_ref_id from question where QUESTION = 'If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?' and sequence_status = 'C'), 'A', 'I submitted the required Financial Disclosure for this NIH/NSF proposal via the web based COEUS Conflict of Interest module on ____________________________.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6171, (select question_ref_id from question where QUESTION = 'If this is a NIH/NSF proposal have you submitted the required financial disclosures in the web based Coeus Conflict of Interest module?' and sequence_status = 'C'), 'N', 'I have not submitted the required Financial Disclosure for this NIH/NSF proposal in COEUS and I understand that this proposal cannot be submitted until I have completed this disclosure.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6172, (select question_ref_id from question where QUESTION = 'Have lobbying activities been conducted on behalf of this proposal?' and sequence_status = 'C'), 'A', 'I have and/or will lobby a federal agency on behalf of this proposal or I plan to have others do so. I have completed the Report on Lobbying Activity and have submitted it to the Office of Sponsored Programs, E19-750.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6173, (select question_ref_id from question where QUESTION = 'Have lobbying activities been conducted on behalf of this proposal?' and sequence_status = 'C'), 'N', 'I have not and will not lobby any federal agency on behalf of this proposal nor do I have any knowledge of anyone else doing so.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6174, (select question_ref_id from question where QUESTION = 'Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?' and sequence_status = 'C'), 'A', 'I am currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from the current transaction by a federal department or agency. I have notified the Office of Sponsored Programs and understand that I am precluded from receiving federally funded grant or contract awards or from being paid with federal funds.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6175, (select question_ref_id from question where QUESTION = 'Are you currently debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from current transactions by a federal department or agency?' and sequence_status = 'C'), 'N', 'I am not debarred, suspended, proposed for debarment, declared ineligible or voluntarily excluded from the current transaction by a federal department or agency.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6176, (select question_ref_id from question where QUESTION = 'Are you familiar with the requirements of the Procurement Liabilities Act?' and sequence_status = 'C'), 'A', 'I am familiar with the requirements of the Procurement Liabilities Act.', sysdate, 'admin', 1, sys_guid());

insert into QUESTION_EXPLANATION (QUESTION_EXPLANATION_ID, QUESTION_REF_ID_FK, EXPLANATION_TYPE, EXPLANATION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    VALUES (6177, (select question_ref_id from question where QUESTION = 'Are you familiar with the requirements of the Procurement Liabilities Act?' and sequence_status = 'C'), 'N', 'I am not familiar with the requirements of the Procurement Liabilities Act.', sysdate, 'admin', 1, sys_guid());
