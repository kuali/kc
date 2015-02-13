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

insert into COMMENT_TYPE (COMMENT_TYPE_CODE, DESCRIPTION, TEMPLATE_FLAG, CHECKLIST_FLAG, 
		AWARD_COMMENT_SCREEN_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values ('CG1', 'Stop Work Reason', 'N', 'N', 'N', NOW(), 'admin', 1, UUID())	
/

insert into COMMENT_TYPE (COMMENT_TYPE_CODE, DESCRIPTION, TEMPLATE_FLAG, CHECKLIST_FLAG, 
		AWARD_COMMENT_SCREEN_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values ('CG2', 'Additional Forms Description', 'N', 'N', 'N', NOW(), 'admin', 1, UUID())	
/

insert into COMMENT_TYPE (COMMENT_TYPE_CODE, DESCRIPTION, TEMPLATE_FLAG, CHECKLIST_FLAG, 
		AWARD_COMMENT_SCREEN_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
	values ('CG3', 'Reason for Excluding from Invoicing', 'N', 'N', 'N', NOW(), 'admin', 1, UUID())	
/

DELIMITER ;
