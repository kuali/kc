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

-- The purpose of this script is to make the description field "required" for protocol and personnel attachments.
-- Along that line the description field in both tables is being set to not null.  In order to ensure older data
-- migrates appropriately, we need to run updates first to ensure that any attachments with null descriptions are given
-- a default value.
update protocol_attachment_personnel a set a.description = concat( concat( (select description from protocol_attachment_type b where b.type_cd = a.type_cd), ' - '), a.update_timestamp) where a.description IS NULL;
commit;
alter table protocol_attachment_personnel modify column description varchar(200) not null;
