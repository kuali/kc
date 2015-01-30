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
INSERT INTO IACUC_PROC_CAT_CUSTOM_DATA ( ID, PROCEDURE_CATEGORY_CODE, NAME, LABEL, DATA_TYPE_CODE, DATA_LENGTH, DEFAULT_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG, SORT_ID, OBJ_ID, VER_NBR ) 
VALUES ( 11, 1, 'routeOfAdministration', 'Route of Administration', '1', 200, NULL, NOW(), 'admin', 'Y', 1, UUID(), 1 ) 
/

INSERT INTO IACUC_PROC_CAT_CUSTOM_DATA ( ID, PROCEDURE_CATEGORY_CODE, NAME, LABEL, DATA_TYPE_CODE, DATA_LENGTH, DEFAULT_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG, SORT_ID, OBJ_ID, VER_NBR ) 
VALUES ( 12, 1, 'whenUsed', 'When Used', '3', 0, NULL, NOW(), 'admin', 'Y', 2, UUID(), 1 ) 
/

INSERT INTO IACUC_PROC_CAT_CUSTOM_DATA ( ID, PROCEDURE_CATEGORY_CODE, NAME, LABEL, DATA_TYPE_CODE, DATA_LENGTH, DEFAULT_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG, SORT_ID, OBJ_ID, VER_NBR ) 
VALUES ( 13, 2, 'identifyCareSopPreOp', 'Identify the CARE SOP Pre-op', '1', 200, NULL, NOW(), 'admin', 'Y', 1, UUID(), 1 ) 
/

INSERT INTO IACUC_PROC_CAT_CUSTOM_DATA ( ID, PROCEDURE_CATEGORY_CODE, NAME, LABEL, DATA_TYPE_CODE, DATA_LENGTH, DEFAULT_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG, SORT_ID, OBJ_ID, VER_NBR ) 
VALUES ( 14, 2, 'identifyCareSopSurgery', 'Identify the CARE SOP surgery', '1', 200, NULL, NOW(), 'admin', 'Y', 2, UUID(), 1 ) 
/

INSERT INTO IACUC_PROC_CAT_CUSTOM_DATA ( ID, PROCEDURE_CATEGORY_CODE, NAME, LABEL, DATA_TYPE_CODE, DATA_LENGTH, DEFAULT_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG, SORT_ID, OBJ_ID, VER_NBR ) 
VALUES ( 15, 2, 'surgerySupervisor', 'Who will perform or supervise', '1', 200, NULL, NOW(), 'admin', 'Y', 3, UUID(), 1 ) 
/

INSERT INTO IACUC_PROC_CAT_CUSTOM_DATA ( ID, PROCEDURE_CATEGORY_CODE, NAME, LABEL, DATA_TYPE_CODE, DATA_LENGTH, DEFAULT_VALUE, UPDATE_TIMESTAMP, UPDATE_USER, ACTIVE_FLAG, SORT_ID, OBJ_ID, VER_NBR ) 
VALUES ( 16, 2, 'surgeryRemoveDate', 'When will sutures be removed', '3', 0, NULL, NOW(), 'admin', 'Y', 4, UUID(), 1 ) 
/

DELIMITER ;
