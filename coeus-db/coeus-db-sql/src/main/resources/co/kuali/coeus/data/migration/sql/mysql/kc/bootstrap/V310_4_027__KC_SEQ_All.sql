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

CREATE TABLE SEQ_NOTIFICATION_ID (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_NOTIFICATION_ID auto_increment = 1;

CREATE TABLE SEQ_SPONSOR_FORMS (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_SPONSOR_FORMS auto_increment = 1;

CREATE TABLE SEQ_SPONSOR_FORM_TEMPLATES (
  id bigint(19) not null auto_increment, primary key (id)
) ENGINE MyISAM;
ALTER TABLE SEQ_SPONSOR_FORM_TEMPLATES auto_increment = 1;
