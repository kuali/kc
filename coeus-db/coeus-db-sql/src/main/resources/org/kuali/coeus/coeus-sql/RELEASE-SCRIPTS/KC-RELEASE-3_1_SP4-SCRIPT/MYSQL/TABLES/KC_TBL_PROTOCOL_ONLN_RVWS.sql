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

alter table protocol_onln_rvws add column REVIEWER_APPROVED char(1);
alter table protocol_onln_rvws add column ADMIN_ACCEPTED char(1);
update protocol_onln_rvws set REVIEWER_APPROVED = 'Y', ADMIN_ACCEPTED = 'Y';
alter table protocol_onln_rvws modify REVIEWER_APPROVED char(1) not null;
alter table protocol_onln_rvws modify ADMIN_ACCEPTED char(1) not null;
