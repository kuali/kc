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

create sequence SEQ_REST_AUDIT_LOG_ID INCREMENT BY 1 START WITH 1 NOCACHE;

create table REST_AUDIT_LOG (
	ID number(22,0) primary key,
	USERNAME varchar2(200) not null,
	UPDATE_TIMESTAMP timestamp not null,
	CLASS_NAME varchar2(4000) not null,
	ADDED_JSON clob,
	MODIFIED_JSON clob,
	DELETED_JSON clob
);
