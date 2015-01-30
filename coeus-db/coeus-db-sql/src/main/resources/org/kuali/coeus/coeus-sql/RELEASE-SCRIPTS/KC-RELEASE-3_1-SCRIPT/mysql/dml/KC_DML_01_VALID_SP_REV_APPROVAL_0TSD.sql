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
INSERT INTO VALID_SP_REV_APPROVAL (VALID_SP_REV_APPROVAL_ID,SPECIAL_REVIEW_CODE,APPROVAL_TYPE_CODE,PROTOCOL_NUMBER_FLAG,APPLICATION_DATE_FLAG,APPROVAL_DATE_FLAG,EXEMPT_NUMBER_FLAG,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (1,(SELECT SPECIAL_REVIEW_CODE FROM SPECIAL_REVIEW WHERE DESCRIPTION = 'Human Subjects'),(SELECT APPROVAL_TYPE_CODE FROM SP_REV_APPROVAL_TYPE WHERE DESCRIPTION = 'Approved'),'Y','N','N','N','admin',NOW(),UUID(),1)
/
INSERT INTO VALID_SP_REV_APPROVAL (VALID_SP_REV_APPROVAL_ID,SPECIAL_REVIEW_CODE,APPROVAL_TYPE_CODE,PROTOCOL_NUMBER_FLAG,APPLICATION_DATE_FLAG,APPROVAL_DATE_FLAG,EXEMPT_NUMBER_FLAG,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (2,(SELECT SPECIAL_REVIEW_CODE FROM SPECIAL_REVIEW WHERE DESCRIPTION = 'Human Subjects'),(SELECT APPROVAL_TYPE_CODE FROM SP_REV_APPROVAL_TYPE WHERE DESCRIPTION = 'Exempt'),'N','N','N','Y','admin',NOW(),UUID(),1)
/
DELIMITER ;
