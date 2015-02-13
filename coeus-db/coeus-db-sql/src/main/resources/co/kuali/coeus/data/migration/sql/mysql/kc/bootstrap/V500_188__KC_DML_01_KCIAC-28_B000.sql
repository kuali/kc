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
INSERT INTO IACUC_PAIN_CATEGORY ( PAIN_CATEGORY_CODE, PAIN_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 1, 'A', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PAIN_CATEGORY ( PAIN_CATEGORY_CODE, PAIN_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 2, 'B', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PAIN_CATEGORY ( PAIN_CATEGORY_CODE, PAIN_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 3, 'C', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PAIN_CATEGORY ( PAIN_CATEGORY_CODE, PAIN_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 4, 'D', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PAIN_CATEGORY ( PAIN_CATEGORY_CODE, PAIN_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 5, 'E', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PAIN_CATEGORY ( PAIN_CATEGORY_CODE, PAIN_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 6, 'None', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
DELIMITER ;
