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
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COLLATE = utf8_bin
/

ALTER TABLE KRNS_MAINT_DOC_ATT_LST_T
    ADD CONSTRAINT KRNS_MAINT_DOC_ATT_LST_TC0
    UNIQUE (OBJ_ID)
/

create index KRNS_MAINT_DOC_ATT__LST_TI1 on KRNS_MAINT_DOC_ATT_LST_T (DOC_HDR_ID)
/

create table KRNS_MAINT_DOC_ATT_S (
  id bigint(19) not null auto_increment,
  primary key (id)
) ENGINE MyISAM
/
alter table KRNS_MAINT_DOC_ATT_S auto_increment = 10000
/







DELIMITER ;
