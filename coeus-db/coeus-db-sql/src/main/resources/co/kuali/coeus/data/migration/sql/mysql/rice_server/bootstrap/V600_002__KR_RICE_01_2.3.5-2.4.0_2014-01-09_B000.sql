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

-- KULRICE-9198 - krms_attr_defn_t.attr_defn_id is a varchar(255),
--                but referencing columns are a mixture of varchar(40) and varchar(255)
DELIMITER /

ALTER TABLE KRMS_TYP_ATTR_T DROP FOREIGN KEY `krms_typ_attr_fk1`
/

ALTER TABLE KRMS_ACTN_ATTR_T DROP FOREIGN KEY `krms_actn_attr_fk2`
/

ALTER TABLE KRMS_AGENDA_ATTR_T DROP FOREIGN KEY `krms_agenda_attr_fk2`
/

ALTER TABLE KRMS_TERM_RSLVR_ATTR_T DROP FOREIGN KEY krms_term_rslvr_attr_fk2
/

ALTER TABLE KRMS_RULE_ATTR_T DROP FOREIGN KEY `krms_rule_attr_fk2`
/

ALTER TABLE KRMS_CNTXT_ATTR_T DROP FOREIGN KEY `krms_cntxt_attr_fk2`
/

ALTER TABLE KRMS_NL_TMPL_ATTR_T DROP FOREIGN KEY krms_nl_tmpl_attr_fk2
/

ALTER TABLE KRMS_NL_USAGE_ATTR_T DROP FOREIGN KEY krms_nl_usage_attr_fk2
/

ALTER TABLE KRMS_TYP_ATTR_T MODIFY ATTR_DEFN_ID VARCHAR(40)
/

ALTER TABLE KRMS_ATTR_DEFN_T MODIFY ATTR_DEFN_ID VARCHAR(40)
/

ALTER TABLE KRMS_TYP_ATTR_T
  ADD CONSTRAINT KRMS_TYP_ATTR_FK1
    FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_ACTN_ATTR_T 
  ADD CONSTRAINT KRMS_ACTN_ATTR_FK2
    FOREIGN KEY (ATTR_DEFN_ID )
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_AGENDA_ATTR_T
  ADD CONSTRAINT KRMS_AGENDA_ATTR_FK2
    FOREIGN KEY (ATTR_DEFN_ID)
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_TERM_RSLVR_ATTR_T 
  ADD CONSTRAINT KRMS_TERM_RSLVR_ATTR_FK2
    FOREIGN KEY (ATTR_DEFN_ID )
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID)
/

ALTER TABLE KRMS_RULE_ATTR_T
  ADD CONSTRAINT KRMS_RULE_ATTR_FK2
    FOREIGN KEY (ATTR_DEFN_ID )
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID )
/

ALTER TABLE KRMS_CNTXT_ATTR_T
  ADD CONSTRAINT KRMS_CNTXT_ATTR_FK2
    FOREIGN KEY (ATTR_DEFN_ID )
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID )
/

ALTER TABLE KRMS_TERM_RSLVR_ATTR_T
  ADD CONSTRAINT KRMS_ASSET_RSLVR_ATTR_FK2
    FOREIGN KEY (ATTR_DEFN_ID )
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID )
/

ALTER TABLE krms_nl_tmpl_attr_t
  ADD CONSTRAINT krms_nl_tmpl_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID )
/

ALTER TABLE krms_nl_usage_attr_t
  ADD CONSTRAINT krms_nl_usage_attr_fk2
    FOREIGN KEY (attr_defn_id )
    REFERENCES KRMS_ATTR_DEFN_T (ATTR_DEFN_ID )
/
    

DELIMITER ;
