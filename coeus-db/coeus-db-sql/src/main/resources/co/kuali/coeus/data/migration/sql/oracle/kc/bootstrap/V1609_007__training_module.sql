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

alter table training drop column module_code;

CREATE SEQUENCE SEQ_TRAINING_MODULE_ID INCREMENT BY 1 START WITH 1 NOCACHE;

create table training_modules (
ID VARCHAR2(40) NOT NULL,
TRAINING_CODE NUMBER(4) NOT NULL,
MODULE_CODE NUMBER(3) NOT NULL,
UPDATE_TIMESTAMP DATE NOT NULL,
UPDATE_USER VARCHAR2(60) NOT NULL,
VER_NBR NUMBER(8) default 1 NOT NULL,
OBJ_ID VARCHAR2(36)
);

ALTER TABLE training_modules ADD CONSTRAINT fk_tm_module_code
FOREIGN KEY (module_code)
REFERENCES coeus_module (module_code);

ALTER TABLE training_modules ADD CONSTRAINT fk_tm_training_code
FOREIGN KEY (training_code)
REFERENCES training (training_code);

ALTER TABLE training_modules ADD CONSTRAINT uq_training_modules UNIQUE (module_code, training_code);
