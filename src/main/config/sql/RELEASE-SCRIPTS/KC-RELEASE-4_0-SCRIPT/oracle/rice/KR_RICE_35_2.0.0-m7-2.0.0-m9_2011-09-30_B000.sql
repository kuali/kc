--
-- Copyright 2005-2012 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
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
