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

delimiter /
TRUNCATE TABLE AWARD_METHOD_OF_PAYMENT
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Established ACH mechanism for sponsor','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Cost invoice ','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Fixed price invoice','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Level of effort invoices','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Progress payment invoices','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','Invoices for fees from members or participants','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('7','Advanced payment invoice','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('8','Automatic payment','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('9','SF270/Request for Advance','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('10','Special Handling--see comments','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('11','Scheduled payment invoices','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('12','Gift','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('13','DoD Advance Payment Pool','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('14','Cost Invoice with Certification','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_METHOD_OF_PAYMENT (METHOD_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('15','No Payment or Billed by Department','admin',NOW(),UUID(),1)
/
delimiter ;
