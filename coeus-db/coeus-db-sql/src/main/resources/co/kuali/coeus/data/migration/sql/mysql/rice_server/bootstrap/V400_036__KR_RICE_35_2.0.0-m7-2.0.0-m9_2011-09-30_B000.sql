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


-- -----------------------------------------------------
-- Table `krms_cntxt_vld_agenda_t`
-- -----------------------------------------------------


CREATE  TABLE IF NOT EXISTS `krms_cntxt_vld_agenda_t` (
  `cntxt_vld_agenda_id` VARCHAR(40) NOT NULL ,
  `cntxt_id` VARCHAR(40) NOT NULL ,
  `agenda_typ_id` VARCHAR(40) NOT NULL ,
  `ver_nbr` DECIMAL NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`cntxt_vld_agenda_id`) ,
  INDEX `krms_cntxt_vld_agenda_ti1` (`cntxt_id` ASC) ,
  CONSTRAINT `krms_cntxt_vld_agenda_fk1`
    FOREIGN KEY (`cntxt_id` )
    REFERENCES `krms_cntxt_t` (`cntxt_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
/

create table krms_cntxt_vld_agenda_s ( 
  id bigint(19) not null auto_increment, 
  primary key (id) 
) ENGINE MyISAM
/

alter table krms_cntxt_vld_agenda_s auto_increment = 1000
/
DELIMITER ;
