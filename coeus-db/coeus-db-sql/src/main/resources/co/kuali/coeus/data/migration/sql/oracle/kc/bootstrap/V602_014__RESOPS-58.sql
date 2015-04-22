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

alter table NARRATIVE_ATTACHMENT add UPLOAD_TIMESTAMP date;
alter table NARRATIVE_ATTACHMENT add UPLOAD_USER varchar2(100);
update NARRATIVE_ATTACHMENT set UPLOAD_TIMESTAMP = UPDATE_TIMESTAMP, UPLOAD_USER = UPDATE_USER;
alter table NARRATIVE_ATTACHMENT modify UPLOAD_TIMESTAMP date not null;
alter table NARRATIVE_ATTACHMENT modify UPLOAD_USER varchar2(100) not null;

alter table EPS_PROP_PERSON_BIO_ATTACHMENT add UPLOAD_TIMESTAMP date;
alter table EPS_PROP_PERSON_BIO_ATTACHMENT add UPLOAD_USER varchar2(100);
update EPS_PROP_PERSON_BIO_ATTACHMENT set UPLOAD_TIMESTAMP = UPDATE_TIMESTAMP, UPLOAD_USER = UPDATE_USER;
alter table EPS_PROP_PERSON_BIO_ATTACHMENT modify UPLOAD_TIMESTAMP date not null;
alter table EPS_PROP_PERSON_BIO_ATTACHMENT modify UPLOAD_USER varchar2(100) not null;
