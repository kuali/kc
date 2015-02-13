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
INSERT INTO IACUC_PROTOCOL_ACTION_TYPE ( PROTOCOL_ACTION_TYPE_CODE, DESCRIPTION, TRIGGER_SUBMISSION, TRIGGER_CORRESPONDENCE, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID ) 
VALUES ( '318', 'Record Committee Decision', 'N', 'N', NOW(), 'admin', UUID() ) 
/




INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(1, '318', 1, '204', 'N', NULL, NOW(), 'admin', UUID(), '1', 1)
/

INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(2, '318', 1, '301', 'N', NULL, NOW(), 'admin', UUID(), '2', 1)
/

INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(3, '318', 1, '209', 'N', NULL, NOW(), 'admin', UUID(), '3', 1)
/

INSERT INTO VALID_IACUC_PROTO_ACTN_ACTN(VALID_IACUC_PROTO_ACTN_ACTN_ID, PROTOCOL_ACTION_TYPE_CODE, ACTION_NUMBER, FOLLOWUP_ACTION_CODE, USER_PROMPT_FLAG, USER_PROMPT, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, COMM_DECISION_MOTION_TYPE_CODE, VER_NBR)
 VALUES(4, '318', 1, '211', 'N', NULL, NOW(), 'admin', UUID(), '4', 1)
/


DELIMITER ;
