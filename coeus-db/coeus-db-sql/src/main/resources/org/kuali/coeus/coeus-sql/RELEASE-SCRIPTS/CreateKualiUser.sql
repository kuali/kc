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

CREATE USER <USERNAME> IDENTIFIED BY "<password>" DEFAULT TABLESPACE <TABLESPACENAME> TEMPORARY TABLESPACE <TEMP TABLESPACE>;
alter user <USERNAME> quota unlimited on <tablespacename>;
grant create session to <USERNAME>;
grant create synonym to <USERNAME>;
grant create procedure to <USERNAME>;
grant create trigger to <USERNAME>;
grant create table to <USERNAME>;
grant create type to <USERNAME>;
grant create view to <USERNAME>;
grant create sequence to <USERNAME>;
quit
