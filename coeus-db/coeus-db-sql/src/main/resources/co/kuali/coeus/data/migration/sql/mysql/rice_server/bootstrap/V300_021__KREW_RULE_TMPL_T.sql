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
TRUNCATE TABLE KREW_RULE_TMPL_T
/
INSERT INTO KREW_RULE_TMPL_T (NM,OBJ_ID,RULE_TMPL_DESC,RULE_TMPL_ID,VER_NBR)
  VALUES ('WorkflowDocumentDelegationTemplate','6166CBA1BA86644DE0404F8189D86C09','WorkflowDocumentDelegationTemplate',1015,2)
/
INSERT INTO KREW_RULE_TMPL_T (DLGN_RULE_TMPL_ID,NM,OBJ_ID,RULE_TMPL_DESC,RULE_TMPL_ID,VER_NBR)
  VALUES (1015,'WorkflowDocumentTemplate','6166CBA1BA87644DE0404F8189D86C09','Workflow Document Template',1016,4)
/
INSERT INTO KREW_RULE_TMPL_T (NM,OBJ_ID,RULE_TMPL_DESC,RULE_TMPL_ID,VER_NBR)
  VALUES ('ReviewersRouting','6166CBA1BA8C644DE0404F8189D86C09','Routes to channel reviewers',1023,2)
/
INSERT INTO KREW_RULE_TMPL_T (NM,OBJ_ID,RULE_TMPL_DESC,RULE_TMPL_ID,VER_NBR)
  VALUES ('NotificationRouting','6166CBA1BA8D644DE0404F8189D86C09','The standard rule template for sending notification messages.',1025,2)
/
INSERT INTO KREW_RULE_TMPL_T (NM,OBJ_ID,RULE_TMPL_DESC,RULE_TMPL_ID,VER_NBR)
  VALUES ('RuleRoutingTemplate','6166CBA1BA96644DE0404F8189D86C09','RuleRoutingTemplate',1320,2)
/
delimiter ;
