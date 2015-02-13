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
INSERT INTO IACUC_PROCEDURE_CATEGORY ( PROCEDURE_CATEGORY_CODE, PROCEDURE_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 1, 'Drugs', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROCEDURE_CATEGORY ( PROCEDURE_CATEGORY_CODE, PROCEDURE_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 2, 'Surgery', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROCEDURE_CATEGORY ( PROCEDURE_CATEGORY_CODE, PROCEDURE_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 3, 'Food/Fluid', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROCEDURE_CATEGORY ( PROCEDURE_CATEGORY_CODE, PROCEDURE_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 4, 'Euthanasia', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROCEDURE_CATEGORY ( PROCEDURE_CATEGORY_CODE, PROCEDURE_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 5, 'Hazardous Agents', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROCEDURE_CATEGORY ( PROCEDURE_CATEGORY_CODE, PROCEDURE_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 6, 'Non-surgical procedures', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
INSERT INTO IACUC_PROCEDURE_CATEGORY ( PROCEDURE_CATEGORY_CODE, PROCEDURE_CATEGORY, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 7, 'Restraint', 'Y', NOW(), 'admin', UUID(), 1 ) 
/
DELIMITER ;
