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
INSERT INTO SEQ_COMMITTEE_ID VALUES(NULL)
/
INSERT INTO COMMITTEE (ID,DOCUMENT_NUMBER,COMMITTEE_ID,SEQUENCE_NUMBER,COMMITTEE_NAME,COMMITTEE_TYPE_CODE,HOME_UNIT_NUMBER,DESCRIPTION,SCHEDULE_DESCRIPTION,MINIMUM_MEMBERS_REQUIRED,MAX_PROTOCOLS,ADV_SUBMISSION_DAYS_REQ,DEFAULT_REVIEW_TYPE_CODE,APPLICABLE_REVIEW_TYPE_CODE,CREATE_USER,CREATE_TIMESTAMP,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
VALUES ((SELECT (MAX(ID)) FROM SEQ_COMMITTEE_ID),(SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'IRB Committee'),'KC001',(SELECT COMMITTEE_TYPE_CODE FROM COMMITTEE_TYPE WHERE DESCRIPTION = 'IRB'),'KC IRB 1','1','000001','Test IRB Committee for Kuali Coeus','Meets monthly',2,22,2,null,(SELECT PROTOCOL_REVIEW_TYPE_CODE FROM PROTOCOL_REVIEW_TYPE WHERE DESCRIPTION = 'Full'),'quicksta',NOW(),'admin',NOW(),UUID(),0)
/
DELIMITER ;
