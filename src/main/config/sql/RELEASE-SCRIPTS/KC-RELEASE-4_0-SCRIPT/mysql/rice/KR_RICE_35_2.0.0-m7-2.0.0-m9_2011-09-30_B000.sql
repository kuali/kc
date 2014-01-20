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
) ENGINE MyISAM; 
alter table krms_cntxt_vld_agenda_s auto_increment = 1000
/
DELIMITER ;
