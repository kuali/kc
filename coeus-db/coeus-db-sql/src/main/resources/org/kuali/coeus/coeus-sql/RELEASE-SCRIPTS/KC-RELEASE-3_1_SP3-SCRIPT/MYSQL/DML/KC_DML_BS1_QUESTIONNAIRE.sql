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

INSERT INTO QUESTIONNAIRE (QUESTIONNAIRE_REF_ID, QUESTIONNAIRE_ID, SEQUENCE_NUMBER, NAME, DESCRIPTION, UPDATE_USER, UPDATE_TIMESTAMP, IS_FINAL, VER_NBR, OBJ_ID, FILE_NAME, DOCUMENT_NUMBER) 
    VALUES (6111, 10001, 1, 'Proposal Person Certification', 'Questions to verify the proposal person is acceptable for the proposal development document.', 'admin', NOW(), 'N', 1, UUID(), NULL, (SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Proposal Person Preloaded Questionnaire'));

update questionnaire set IS_FINAL = 'Y' where name = 'Proposal Person Certification';
