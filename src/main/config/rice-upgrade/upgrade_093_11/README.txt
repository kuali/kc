Run the scripts in the following order:

1. drop-rice-093-constraints.sql (some of these will fail; that's ok)
2. drop-rice-093-indexes.sql (some of these will fail; that's ok)
3. kc-rice-093-to-094-ddl.sql
*4. kc-rice-093-to-094-dml.sql (some of these will fail; that's ok)
*5. kc-rice-093-to-094-data-migration.sql
6. kc-rice-093-to-094-constraints.sql
7. drop-old-rice-tables.sql (2 of these might fail; that's ok)

*Ingest KEW config files:

1. src/main/config/rice-upgrade/upgrade_093_11/AllMaintenanceDocs1/2/3.xml
2. src/main/config/kew/xml/AwardDocument.xml
3. src/main/config/kew/xml/BudgetDocument.xml
4. src/main/config/kew/xml/CommitteeDocument.xml
5. src/main/config/kew/xml/ProposalDevelopmentDocument.xml
8. src/main/config/kew/xml/ProtocolDocument.xml

* = Skip these for unit test schemas
