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

-- save old table
create table PERSON_EDITABLE_FIELDS_old as select * from PERSON_EDITABLE_FIELDS;

-- drop and recreate table
drop table PERSON_EDITABLE_FIELDS;
CREATE TABLE PERSON_EDITABLE_FIELDS ( 
    PERSON_EDITABLE_FIELD_ID   NUMBER(12,0) NOT NULL,
    MODULE_CODE         NUMBER(3,0) NOT NULL,
    FIELD_NAME          VARCHAR2(255) NOT NULL,
    ACTIVE_FLAG         CHAR(1) NULL,
    UPDATE_TIMESTAMP    DATE NOT NULL,
    UPDATE_USER         VARCHAR2(60) NOT NULL,
    VER_NBR             NUMBER(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID              VARCHAR2(36) NOT NULL);


ALTER TABLE PERSON_EDITABLE_FIELDS 
ADD CONSTRAINT PK_PERSON_EDITABLE_FIELDS 
PRIMARY KEY (PERSON_EDITABLE_FIELD_ID);

-- restore data for PD and add data for protocol.
insert into PERSON_EDITABLE_FIELDS(PERSON_EDITABLE_FIELD_ID, MODULE_CODE, FIELD_NAME, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 select SEQ_PERSON_EDITABLE_FIELD.nextval, '3', FIELD_NAME, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID from PERSON_EDITABLE_FIELDS_old;
insert into PERSON_EDITABLE_FIELDS(PERSON_EDITABLE_FIELD_ID, MODULE_CODE, FIELD_NAME, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID)
 select SEQ_PERSON_EDITABLE_FIELD.nextval, '7', FIELD_NAME, ACTIVE_FLAG, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, SYS_GUID() from PERSON_EDITABLE_FIELDS_old;

commit;

-- drop old table
drop table PERSON_EDITABLE_FIELDS_old;
