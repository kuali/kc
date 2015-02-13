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
INSERT INTO SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID VALUES(NULL)
/
INSERT INTO IACUC_PROTOCOL_PRSN_ROLE_MPPNG (ROLE_MAPPING_ID, SOURCE_ROLE_ID, TARGET_ROLE_ID, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID), 'PI', 'COI', NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID VALUES(NULL)
/
INSERT INTO IACUC_PROTOCOL_PRSN_ROLE_MPPNG (ROLE_MAPPING_ID, SOURCE_ROLE_ID, TARGET_ROLE_ID, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID), 'COI', 'PI', NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID VALUES(NULL)
/
INSERT INTO IACUC_PROTOCOL_PRSN_ROLE_MPPNG (ROLE_MAPPING_ID, SOURCE_ROLE_ID, TARGET_ROLE_ID, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID), 'CRC', 'SP', NOW(),'admin',1,UUID())
/
INSERT INTO SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID VALUES(NULL)
/
INSERT INTO IACUC_PROTOCOL_PRSN_ROLE_MPPNG (ROLE_MAPPING_ID, SOURCE_ROLE_ID, TARGET_ROLE_ID, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
VALUES ((SELECT (MAX(ID)) FROM SEQ_IAC_PRTCL_PRSNRL_MPPNG_ID), 'SP', 'CRC', NOW(),'admin',1,UUID())
/
DELIMITER ;
