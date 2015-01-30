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
TRUNCATE TABLE AWARD_BASIS_OF_PAYMENT
/
INSERT INTO AWARD_BASIS_OF_PAYMENT (BASIS_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('1','Firm fixed price','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BASIS_OF_PAYMENT (BASIS_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('2','Cost reimbursement','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BASIS_OF_PAYMENT (BASIS_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('3','Fixed price level of effort','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BASIS_OF_PAYMENT (BASIS_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('4','Other','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BASIS_OF_PAYMENT (BASIS_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('5','Gift','admin',NOW(),UUID(),1)
/
INSERT INTO AWARD_BASIS_OF_PAYMENT (BASIS_OF_PAYMENT_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES ('6','No Payment','admin',NOW(),UUID(),1)
/
delimiter ;
