-- these changes come from
-- https://test.kuali.org/svn/rice/branches/rice-release-1-0-3-1-br/scripts/upgrades/1.0.3 to 1.0.4/db-updates/oracle/2010-12-23.sql

UPDATE krew_rule_t SET RULE_TMPL_ID='1030' WHERE RULE_ID='1049';
UPDATE krew_rule_t SET RULE_TMPL_ID='1032' WHERE RULE_ID='1051';
DELETE FROM krew_rule_t WHERE RULE_ID='1052';
UPDATE krew_rule_rsp_t SET RULE_ID='1050' WHERE RULE_RSP_ID='2029';
UPDATE krew_rule_rsp_t SET RULE_ID='1051' WHERE RULE_RSP_ID='2031';
COMMIT;