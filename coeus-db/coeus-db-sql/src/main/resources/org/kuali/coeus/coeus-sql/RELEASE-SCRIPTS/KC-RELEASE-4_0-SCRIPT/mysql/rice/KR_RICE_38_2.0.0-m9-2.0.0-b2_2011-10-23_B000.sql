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

DELIMITER /

alter table krcr_cmpnt_t add column cmpnt_set_id varchar(40)
/

create table krcr_cmpnt_set_t (
  cmpnt_set_id varchar(40) not null,
  last_updt_ts datetime not null,
  chksm varchar(40) not null,
  ver_nbr decimal not null default 0,
  primary key (cmpnt_set_id))
ENGINE = InnoDB
/
DELIMITER ;
