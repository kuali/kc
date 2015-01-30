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
insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
	(21, 9, 1, 'Amendment / Renewal', NOW(), 'admin', 1, UUID())
/

insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
	(22, 9, 3, 'Renewal', NOW(), 'admin', 1, UUID())
/

insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
	(23, 9, 4, 'Amendment', NOW(), 'admin', 1, UUID())
/

insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
	(24, 9, 5, 'Continuation', NOW(), 'admin', 1, UUID())
/




DELIMITER ;
