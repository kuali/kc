spool drop-rice-093-constraints.txt
PROMPT **************************************************************
PROMPT Dropping old constraints (some of these will fail; that is ok)
PROMPT **************************************************************
@RICE/upgrade_093_11/drop-rice-093-constraints.sql

spool drop-rice-093-indexes.txt
PROMPT **********************************************************
PROMPT Dropping old indexes (some of these will fail; that is ok)
PROMPT **********************************************************
@RICE/upgrade_093_11/drop-rice-093-indexes.sql

spool kc-rice-093-to-094-ddl.txt
PROMPT *****************
PROMPT Creating rice DDL
PROMPT *****************
@RICE/upgrade_093_11/kc-rice-093-to-094-ddl.sql

spool kc-rice-093-to-094-dml.txt
PROMPT ********************************************************
PROMPT Inserting rice DML (some of these will fail, that is ok)
PROMPT ********************************************************
@RICE/upgrade_093_11/kc-rice-093-to-094-dml.sql

spool kc-rice-093-to-094-data-migration.txt
PROMPT ****************************
PROMPT Migrating existing rice data
PROMPT ****************************
@RICE/upgrade_093_11/kc-rice-093-to-094-data-migration.sql

spool kc-rice-093-to-094-constraints.txt
PROMPT ***************************
PROMPT Installing rice constraints
PROMPT ***************************
@RICE/upgrade_093_11/kc-rice-093-to-094-constraints.sql

spool drop-old-rice-tables.sql
PROMPT **************************************************************
PROMPT Dropping old rice tables (2 statements might fail, that is ok)
PROMPT **************************************************************
@RICE/upgrade_093_11/drop-old-rice-tables.sql

COMMIT;

spool rice-upgrade-part-1.txt
PROMPT ***************************
PROMPT Running rice upgrade part 1
PROMPT ***************************
@RICE/upgrade_11_10kc/rice-upgrade-part-1.sql

COMMIT;

spool rice-upgrade-part-2.txt
PROMPT ***************************
PROMPT Running rice upgrade part 2
PROMPT ***************************
@RICE/upgrade_11_10kc/rice-upgrade-part-2.sql

COMMIT;

spool rice-upgrade-part-3.txt
PROMPT ***************************
PROMPT Running rice upgrade part 3
PROMPT ***************************
@RICE/upgrade_11_10kc/rice-upgrade-part-3.sql

COMMIT;

spool KRACOEUS-3153.txt
PROMPT ***************************
PROMPT Running KRACOEUS-3153
PROMPT ***************************
@RICE/upgrade_11_10kc/KRACOEUS-3153.sql

COMMIT;

spool KRACOEUS-3250.txt
PROMPT ***************************
PROMPT Running KRACOEUS-3250
PROMPT ***************************
@RICE/upgrade_11_10kc/KRACOEUS-3250.sql

COMMIT;

spool KRACOEUS-3423.txt
PROMPT ***************************
PROMPT Running KRACOEUS-3423
PROMPT ***************************
@RICE/upgrade_11_10kc/KRACOEUS-3423.sql

COMMIT;

