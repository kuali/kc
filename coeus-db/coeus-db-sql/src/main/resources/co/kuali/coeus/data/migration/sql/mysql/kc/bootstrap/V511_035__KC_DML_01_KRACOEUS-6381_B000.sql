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

INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 1, 'In Progress', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 2, 'Submitted For Review', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR )
VALUES ( 3, 'Assigned To Reviewer', 'N', NOW(), 'admin', UUID(), 1 )
/

INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR )
VALUES ( 4, 'Assigned Review Complete', 'N', NOW(), 'admin', UUID(), 1 )
/

INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 5, 'Review Complete', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 6, 'Received in COI Office', 'N', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 7, 'Awaiting Additional Information', 'N', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO COI_REVIEW_STATUS ( REVIEW_STATUS_CODE, DESCRIPTION, STATUS_UPDATED_ONLY_BY_ACTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 8, 'Under Review by COI Reviewer', 'N', NOW(), 'admin', UUID(), 1 ) 
/

DELIMITER ;
