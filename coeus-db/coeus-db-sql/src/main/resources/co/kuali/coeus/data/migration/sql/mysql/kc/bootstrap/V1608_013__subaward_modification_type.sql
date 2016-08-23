--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

CREATE TABLE SUBAWARD_MODIFICATION_TYPE (
  CODE varchar(30) PRIMARY KEY,
  DESCRIPTION varchar(2000) NOT NULL,
  ACTIVE CHAR(1) NOT NULL DEFAULT 'Y',
  UPDATE_USER VARCHAR(60) NOT NULL,
  UPDATE_TIMESTAMP DATETIME NOT NULL,
  VER_NBR DECIMAL (8,0) NOT NULL DEFAULT 1,
  OBJ_ID VARCHAR(36) NOT NULL);
  
alter table SUBAWARD_AMOUNT_INFO add MODIFICATION_TYPE_CODE varchar(30);
alter table SUBAWARD_AMOUNT_INFO add constraint FK3_SUBAWARD_AMOUNT_INFO foreign key (MODIFICATION_TYPE_CODE)
    REFERENCES SUBAWARD_MODIFICATION_TYPE (CODE);
    
insert into subaward_modification_type (code, description, active, update_user, update_timestamp, ver_nbr, obj_id)
	values ('RESBOOT1000', 'No Cost Extension', 'Y', 'admin', now(), 1, uuid());
	
insert into subaward_modification_type (code, description, active, update_user, update_timestamp, ver_nbr, obj_id)
	values ('RESBOOT1001', 'Increment', 'Y', 'admin', now(), 1, uuid());

insert into subaward_modification_type (code, description, active, update_user, update_timestamp, ver_nbr, obj_id)
	values ('RESBOOT1002', 'Continuation of Other', 'Y', 'admin', now(), 1, uuid());


	