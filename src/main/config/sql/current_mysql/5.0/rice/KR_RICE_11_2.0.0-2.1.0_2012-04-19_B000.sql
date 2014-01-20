DELIMITER /
--
-- Copyright 2005-2014 The Kuali Foundation
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

CREATE TABLE KRNS_MAINT_DOC_ATT_LST_T  (
    ATT_ID      varchar(40) NOT NULL,
    DOC_HDR_ID  varchar(14) NOT NULL,
    ATT_CNTNT   longblob NOT NULL,
    FILE_NM     varchar(150) NULL,
    CNTNT_TYP   varchar(255) NULL,
    OBJ_ID      varchar(36) NOT NULL,
    VER_NBR     decimal(8,0) NOT NULL DEFAULT 0,
    PRIMARY KEY(ATT_ID),
    CONSTRAINT KRNS_MAINT_DOC_ATT_LST_FK1 foreign key (DOC_HDR_ID) references KRNS_MAINT_DOC_T (DOC_HDR_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
/

ALTER TABLE KRNS_MAINT_DOC_ATT_LST_T
    ADD CONSTRAINT KRNS_MAINT_DOC_ATT_LST_TC0
    UNIQUE (OBJ_ID)
/

create index KRNS_MAINT_DOC_ATT_LST_TI1 on KRNS_MAINT_DOC_ATT_LST_T (DOC_HDR_ID)
/

create table KRNS_MAINT_DOC_ATT_S (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table KRNS_MAINT_DOC_ATT_S auto_increment = 10000
/







DELIMITER ;
