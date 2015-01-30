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
UPDATE QUESTIONNAIRE SET DOCUMENT_NUMBER = '' WHERE NAME = 'PHS Fellowship Supplemental Form V1-0 & 1-1'
/
DELETE FROM KRNS_MAINT_DOC_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 1')
/
DELETE FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 1'
/

UPDATE QUESTIONNAIRE SET DOCUMENT_NUMBER = '' WHERE NAME = 'NSF s2s form supporting questions'
/
DELETE FROM KRNS_MAINT_DOC_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 2')
/
DELETE FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 2'
/

UPDATE QUESTIONNAIRE SET DOCUMENT_NUMBER = '' WHERE NAME = 'PHS398 Training Budget Form version 1-0'
/
DELETE FROM KRNS_MAINT_DOC_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 3')
/
DELETE FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 3'
/

UPDATE QUESTIONNAIRE SET DOCUMENT_NUMBER = '' WHERE NAME = 'PHS Fellowship Supplemental Form V1-2'
/
DELETE FROM KRNS_MAINT_DOC_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 4')
/
DELETE FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Grants.gov Preloaded Questionnaire 4'
/

UPDATE QUESTIONNAIRE SET DOCUMENT_NUMBER = '' WHERE NAME = 'Proposal Person Certification'
/
DELETE FROM KRNS_MAINT_DOC_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Proposal Person Preloaded Questionnaire')
/
DELETE FROM KRNS_DOC_HDR_T WHERE FDOC_DESC = 'Proposal Person Preloaded Questionnaire'
/
DELIMITER ;
