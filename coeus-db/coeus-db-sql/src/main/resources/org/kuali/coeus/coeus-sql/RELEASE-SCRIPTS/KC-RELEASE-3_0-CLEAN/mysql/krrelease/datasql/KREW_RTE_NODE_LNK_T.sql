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
TRUNCATE TABLE KREW_RTE_NODE_LNK_T
/
INSERT INTO KREW_RTE_NODE_LNK_T (FROM_RTE_NODE_ID,TO_RTE_NODE_ID)
  VALUES (2041,2042)
/
INSERT INTO KREW_RTE_NODE_LNK_T (FROM_RTE_NODE_ID,TO_RTE_NODE_ID)
  VALUES (2042,2043)
/
INSERT INTO KREW_RTE_NODE_LNK_T (FROM_RTE_NODE_ID,TO_RTE_NODE_ID)
  VALUES (2898,2899)
/
INSERT INTO KREW_RTE_NODE_LNK_T (FROM_RTE_NODE_ID,TO_RTE_NODE_ID)
  VALUES (2901,2902)
/
INSERT INTO KREW_RTE_NODE_LNK_T (FROM_RTE_NODE_ID,TO_RTE_NODE_ID)
  VALUES (2904,2905)
/
INSERT INTO KREW_RTE_NODE_LNK_T (FROM_RTE_NODE_ID,TO_RTE_NODE_ID)
  VALUES (2905,2906)
/
delimiter ;
