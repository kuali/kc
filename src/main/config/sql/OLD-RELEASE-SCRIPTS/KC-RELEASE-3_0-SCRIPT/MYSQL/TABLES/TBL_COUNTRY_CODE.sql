-- The foreign key reference from rolodex and the state_code table
-- need to be dropped first.

ALTER TABLE ROLODEX DROP FOREIGN KEY FK_ROLODEX_COUNTRY_KRA;

DROP TABLE STATE_CODE;

DROP TABLE COUNTRY_CODE;
