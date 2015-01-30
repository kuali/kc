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

INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES (NULL);
INSERT INTO NOTIFICATION_TYPE_RECIPIENT(NOTIFICATION_TYPE_RECIPIENT_ID,NOTIFICATION_TYPE_ID,ROLE_NAME,ROLE_QUALIFIER,TO_OR_CC,UPDATE_USER,UPDATE_TIMESTAMP,VER_NBR,OBJ_ID)
    VALUES ((SELECT MAX(ID) FROM SEQ_NOTIFICATION_TYPE_ID),(SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Batch correspondencenotification'),'KC-PROTOCOL:Protocol Aggregator','Aggregator','T','admin',NOW(),1,UUID());

INSERT INTO SEQ_NOTIFICATION_TYPE_ID VALUES (NULL);
INSERT INTO NOTIFICATION_TYPE_RECIPIENT(NOTIFICATION_TYPE_RECIPIENT_ID,NOTIFICATION_TYPE_ID,ROLE_NAME,ROLE_QUALIFIER,TO_OR_CC,UPDATE_USER,UPDATE_TIMESTAMP,VER_NBR,OBJ_ID)
    VALUES ((SELECT MAX(ID) FROM SEQ_NOTIFICATION_TYPE_ID),(SELECT NOTIFICATION_TYPE_ID FROM NOTIFICATION_TYPE WHERE DESCRIPTION = 'Batch correspondencenotification'),'KC-UNT:IRB Administrator','Irb Admin','T','admin',NOW(),1,UUID());
