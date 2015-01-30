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

CREATE TABLE IACUC_PROTO_AMEND_RENEW_MOD ( 
    PROTO_AMEND_RENEW_MODULES_ID NUMBER(12,0) NOT NULL, 
    PROTO_AMEND_RENEWAL_NUMBER VARCHAR2(20) NOT NULL, 
    PROTO_AMEND_RENEWAL_ID NUMBER(12,0) NOT NULL, 
    PROTOCOL_NUMBER VARCHAR2(20) NOT NULL, 
    PROTOCOL_MODULE_CODE VARCHAR2(5) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL) 
/


ALTER TABLE IACUC_PROTO_AMEND_RENEW_MOD 
ADD CONSTRAINT PK_IACUC_PROTO_AMND_REN_MOD 
PRIMARY KEY (PROTO_AMEND_RENEW_MODULES_ID) 
/

