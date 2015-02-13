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
INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 101, 'Food/Water restriction', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 102, 'Exemption from Dog exercise program', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 103, 'Exemption from the environmental enhancement plan for nonhuman primates', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 104, 'Multiple Major Survival Surgery', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 105, 'Maintaining temperatures outside of the recommended ranges', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 106, 'Exemptions to routine cleaning/husbandry requirements', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 107, 'Exemptions to the diurnal lighting cycle requirements', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 108, 'Exemptions to the space requirements including innovative caging and metabolism cages', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 109, 'Prolonged Restraint', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 110, 'Use of nonpharmacuetical grade drugs', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 111, 'Death as an Endpoint', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 112, 'Monoclonal antibody production via acites', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 113, 'Use of Wire bottom cages for rodents', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

INSERT INTO IACUC_EXCEPTION_CATEGORY ( EXCEPTION_CATEGORY_CODE, EXCEPTION_CATEGORY_DESC, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, VER_NBR ) 
VALUES ( 114, 'Singly housing social species', 'Y', NOW(), 'admin', UUID(), 1 ) 
/

DELIMITER ;
