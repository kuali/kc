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

alter table krcr_cmpnt_t drop column cmpnt_set_id
/

create table krcr_drvd_cmpnt_t (
  nmspc_cd varchar(20) not null,
  cmpnt_cd varchar(100) not null,
  nm varchar(255),
  cmpnt_set_id varchar(40) not null,
  primary key (nmspc_cd, cmpnt_cd))
ENGINE = InnoDB
/
DELIMITER ;
