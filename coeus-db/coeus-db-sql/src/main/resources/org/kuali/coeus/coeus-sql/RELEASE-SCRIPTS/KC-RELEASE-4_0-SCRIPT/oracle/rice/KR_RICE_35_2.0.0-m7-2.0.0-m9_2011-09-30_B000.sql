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



-- -----------------------------------------------------
-- Table krms_cntxt_vld_agenda_t
-- -----------------------------------------------------
-- begin execute immediate 'drop table krms_cntxt_vld_agenda_t'; exception when others then null; end;

CREATE  TABLE  krms_cntxt_vld_agenda_t (
  cntxt_vld_agenda_id VARCHAR2(40)  NOT NULL ,
  cntxt_id VARCHAR2(40)  NOT NULL ,
  agenda_typ_id VARCHAR2(40)  NOT NULL ,
  ver_nbr NUMBER(8) DEFAULT 0  NOT NULL ,
  PRIMARY KEY (cntxt_vld_agenda_id) ,
  -- CREATE INDEX krms_cntxt_vld_agenda_ti1 (cntxt_id ASC) ,
  CONSTRAINT krms_cntxt_vld_agenda_fk1
    FOREIGN KEY (cntxt_id )
    REFERENCES krms_cntxt_t (cntxt_id ) )
/

CREATE INDEX krms_cntxt_vld_agenda_ti1 on krms_cntxt_vld_agenda_t (cntxt_id ASC)
/

CREATE SEQUENCE krms_cntxt_vld_agenda_s INCREMENT BY 1 START WITH 10000 NOMAXVALUE NOCYCLE NOCACHE ORDER
/
