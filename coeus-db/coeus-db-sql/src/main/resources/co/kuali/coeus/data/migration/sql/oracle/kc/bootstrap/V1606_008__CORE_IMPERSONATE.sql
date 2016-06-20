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
create sequence SEQ_CORE_IMPERSONATION INCREMENT BY 1 START WITH 1 NOCACHE;

create table CORE_IMPERSONATION (
  ID decimal(22,0),
  LOGIN_TIMESTAMP TIMESTAMP NOT NULL ,
  IMPERSONATED_USER_ID varchar(100) NOT NULL,
  IMPERSONATED_BY_USER varchar(100) NOT NULL,
  IMPERSONATION_DISPLAY_NAME varchar(250),
  REQUESTED_RESOURCE varchar(2000),
  UPDATE_USER VARCHAR(60) NOT NULL,
  UPDATE_TIMESTAMP TIMESTAMP NOT NULL,
  VER_NBR DECIMAL (8,0) default 1 NOT NULL,
  OBJ_ID VARCHAR(36) NOT NULL);
