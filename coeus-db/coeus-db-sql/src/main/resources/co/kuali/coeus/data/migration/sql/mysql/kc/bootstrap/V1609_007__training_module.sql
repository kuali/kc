--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

alter table training drop foreign key fk_training_module_code;
alter table training drop column module_code;
CREATE TABLE SEQ_TRAINING_MODULE_ID (
  id bigint(19) not null auto_increment, primary key (id)
);

create table training_modules (
ID BIGINT(19) NOT NULL PRIMARY KEY,
TRAINING_CODE decimal(4,0),
MODULE_CODE decimal(3,0),
UPDATE_TIMESTAMP DATE NOT NULL,
UPDATE_USER VARCHAR(60) NOT NULL,
VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
OBJ_ID VARCHAR(36) NOT NULL) CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE training_modules
ADD CONSTRAINT MODULE_TRAINING
UNIQUE (TRAINING_CODE, MODULE_CODE);

ALTER TABLE training_modules ADD CONSTRAINT fk_tm_module_code
FOREIGN KEY (module_code)
REFERENCES coeus_module (module_code);

ALTER TABLE training_modules ADD CONSTRAINT fk_tm_training_code
FOREIGN KEY (training_code)
REFERENCES training (training_code);