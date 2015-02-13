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
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 11, 'Manual Award', NOW(), 'admin',  UUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 12, 'Manual Proposal', NOW(), 'admin',  UUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 13, 'Manual IRB Protocol', NOW(), 'admin',  UUID() )
/
INSERT INTO COI_DISCLOSURE_EVENT_TYPE ( EVENT_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( 14, 'Annual', NOW(), 'admin',  UUID() )
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'Under COI Office Review' WHERE COI_DISCLOSURE_STATUS_CODE = '102'
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'Other Review in Progress' WHERE COI_DISCLOSURE_STATUS_CODE = '103'
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'Under COI Committee Review' WHERE COI_DISCLOSURE_STATUS_CODE = '104'
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'No Conflict Exists' WHERE COI_DISCLOSURE_STATUS_CODE = '200'
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'Disclosable Interests Eliminated' WHERE COI_DISCLOSURE_STATUS_CODE = '201'
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'Disclosable Interests Reduced' WHERE COI_DISCLOSURE_STATUS_CODE = '202'
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'Disclosable Interests Managed' WHERE COI_DISCLOSURE_STATUS_CODE = '203'
/
UPDATE COI_DISCLOSURE_STATUS SET DESCRIPTION = 'Exempt' WHERE COI_DISCLOSURE_STATUS_CODE = '204'
/

DELIMITER ;
