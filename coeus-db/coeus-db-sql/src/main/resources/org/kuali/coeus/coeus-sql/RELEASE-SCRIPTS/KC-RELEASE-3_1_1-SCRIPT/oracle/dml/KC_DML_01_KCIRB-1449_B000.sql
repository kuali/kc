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

INSERT INTO PROTOCOL_ACTION_TYPE (PROTOCOL_ACTION_TYPE_CODE, DESCRIPTION, TRIGGER_SUBMISSION, TRIGGER_CORRESPONDENCE, FINAL_ACTION_FOR_BATCH_CORRESP, UPDATE_TIMESTAMP,UPDATE_USER, obj_id) 
VALUES ('904', 'Funding Source', 'N', 'N', 'N', sysdate, 'admin', sys_guid())
/

insert into notification_type
(NOTIFICATION_TYPE_ID,MODULE_CODE,ACTION_CODE,DESCRIPTION,SUBJECT,MESSAGE,PROMPT_USER,SEND_NOTIFICATION,SYSTEM_GENERATED,UPDATE_USER,    
 UPDATE_TIMESTAMP,VER_NBR,OBJ_ID ) values(                             
 SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,7,904,'Funding Source','{MESSAGE_SUBJECT}','{MESSAGE_BODY}',  'N','Y','Y','admin',sysdate,1,
sys_guid())
/


insert into NOTIFICATION_TYPE_RECIPIENT(
NOTIFICATION_TYPE_RECIPIENT_ID,NOTIFICATION_TYPE_ID,ROLE_name,ROLE_QUALIFIER,TO_OR_CC,UPDATE_USER,UPDATE_TIMESTAMP,VER_NBR,OBJ_ID)
values(SEQ_NOTIFICATION_TYPE_ID.NEXTVAL,(select NOTIFICATION_TYPE_ID from notification_type where DESCRIPTION = 'Funding Source'),'KC-UNT:Funding Source Monitor','unitNumber', 'T','admin',sysdate,1, 
sys_guid())
/


