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
DELETE FROM IACUC_PROTO_ATTACHMENT_STATUS WHERE DESCRIPTION IN ('Draft', 'Finalized', 'Deleted', 'Superceded', 'Expired')
/
INSERT INTO IACUC_PROTO_ATTACHMENT_STATUS (STATUS_CD, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES (1, 'Complete', NOW(),'admin', 1, UUID())
/
INSERT INTO IACUC_PROTO_ATTACHMENT_STATUS (STATUS_CD, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES (2, 'Incomplete', NOW(),'admin', 1, UUID())
/

DELIMITER ;
