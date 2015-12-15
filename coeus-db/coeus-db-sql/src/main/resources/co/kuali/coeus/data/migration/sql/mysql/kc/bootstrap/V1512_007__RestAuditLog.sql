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

create table SEQ_REST_AUDIT_LOG_ID
(
	id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;

create table REST_AUDIT_LOG (
	ID decimal(22,0) primary key,
	USERNAME varchar(200) not null,
	UPDATE_TIMESTAMP timestamp not null,
	CLASS_NAME varchar(4000) not null,
	ADDED_JSON longtext,
	MODIFIED_JSON longtext,
	DELETED_JSON longtext
);
