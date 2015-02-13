# Import DDL into database
**------------------------**

*SQL is now imported via Flyway so don't worry be happy.*

# Directory Structure

* config - contains grm specific workflow files. Internal to KualiCo
* java - custom Flyway migrator code
* resources - flyway versioned sql files

**Note:** this project does not yet support workflow ingester files

Within the resources package structure there are a series of folders for various configurations of the Kuali Coeus database.  There are three primary definitions to understand for this data: bootstrap, demo, stage.  

Bootstrap data contains things like required parameters, roles, kc code tables that are expected to be present in order for the application to function

Demo data contains things like example Kim principals, Role memberships, Units, Sponsors.  This represents a fully populated KC system without any transactional or document data.

Stage data contains things like document workflow data, proposals, awards, and other complex transactional data.  This represents a fully populated KC system that has been used for some period of time.

In most cases stage data will be separate from demo data which will be separate from bootstrap data.  In rare cases we may want to modify bootstrap data in either a demo or stage script or we might want to modify demo data in a stage script.

* grm - contains custom grm specific sql files.  Internal to KualiCo
* kc
  * bootstrap - bootstrap sql files. This will contain only files related to KC tables and data.
  * demo - this contains a set of demo data.  This will contain only files related to KC tables and data.
  * stage - this contains a set of staging data.  This will contain only files related to KC tables and data.
  * embedded_client_scripts - rice client side sql files when running rice in embedded mode.  This may contain rice or kc data but only for rice client side tables.
* rice
  * bootstrap - bootstrap sql files for the rice tables.  This may contain rice or kc data but only for rice tables.  Includes client side tables as well.
  * demo - this contains a set of demo data.  This may contain rice or kc data but only for rice tables.
  * stage - this contains a set of staging data.  This may contain rice or kc data but only for rice tables.

* rice_data_only
  * bootstrap - this contains the rice bootstrap sql files but only the DML files related to KC.  This is used for when you are managing the rice server separates in a standalone configuration where the rice server has already been upgraded.

* rice_server
  * bootstrap - bootstrap sql files for the rice tables.  This may contain rice or kc data but only for rice tables.  Does not includes client side tables.  This is used for when you are managing the rice server separates in a standalone configuration where the rice server has not been upgraded.

# File Naming

KC's Flyway process is using the default file naming structure of flyway, which will always be of the form V601_005__File_Name.sql. Additionally our convention is to use the current version of KC followed by a unique number within that version. So the 8th script to be added for version 6.0.1 of KC would be called V601_008__File_name.sql. After that number you must ensure there are two(2) underscores. '__'. Without this the flyway process will throw an exception and the KC server(with flyway enabled) will fail to start. Also we are using flyway for in-order processing only. If a script with a later version, V601_159__, is applied to a database, but afterwards a file with a lower verison, V601_158__, is added, the flyway process will throw an exception on startup.

Additionaly KC and Rice share a common flyway schema version. This means that when running in bundled mode each file must have a unique version number across all associated directories. This is an annoying aspect to the current process, but one which will hopefully be fixed in time by a separate CI job that will rename files after successfully being merged. But for now you should add the file to the kc-sql file as documented and then verify that your application still starts up without error.

And further, to add more complications, we also have custom Java based conversions. Currently there are 2 such conversions
```
src/main/java/co/kuali/coeus/data/migration/custom/coeus/V600_084__PropAwardPersonRoleConversion.java
/src/main/java/co/kuali/coeus/data/migration/custom/coeus/V600_085__ProposalRoleConversion.java
```
And as you can see those are similar version numbers and also compete with the existing SQL version numbers. So be aware of that if you get an error about a version you can't find amoungst the sql files.

# Running

Currently this project's primary method of being run is through the KC server startup itself. On startup it is configured to load and ensure the database(s) are up to date based on configuration provided. It will automatically apply any scripts not yet applied. The following config options and defaults have been added and can be overridden in the kc-config.xml file.
```
<!-- Flyway Properties -->
<param name="kc.flyway.applyDemoData">false</param>
<param name="kc.flyway.applyStagingData">false</param>
<param name="kc.flyway.manageRiceServer">true</param>
<param name="kc.flyway.embeddedMode">false</param>
<param name="kc.flyway.enabled">true</param>
<param name="kc.flyway.sql.migration.path">co/kuali/coeus/data/migration/sql/mysql</param>
<param name="kc.flyway.java.migration.path">co/kuali/coeus/data/migration/custom/coeus</param>

<!-- Auto-ingester properties -->
<param name="kc.kew.auto.ingestion.paths">org/kuali/coeus/rice-xml,org/kuali/coeus/coeus-xml</param>
<param name="kc.kew.auto.ingestion.principalId">admin</param>
<param name="kc.kew.auto.ingestion.enabled">true</param>
```

* kc.flyway.enabled -- Sets whether flyway will automatically run to upgrade the database. Default = true
* kc.flyway.applyDemoData -- Sets whether flyway will automatically apply demo data. Default = false
* kc.flyway.applyStagingData -- Sets whether flyway will apply staging data. Default = false
* kc.flyway.manageRiceServer -- Sets whether flyway will upgrade the Rice DDL or table structures. Only disabled this if you are upgrading your Rice server separate from KC. Default = true
* kc.flyway.embeddedMode -- Determines whether flyway will operate as if on an embedded database. This will run separate scripts for the client tables on the KC database instead of relying on similar Rice scripts. Default = false
* kc.flyway.sql.migration.path -- Determines the root path to look for sql migration files. Default = co/kuali/coeus/data/migration/sql/mysql
* kc.flyway.java.migration.path -- Determines the root path (package) too look for java migrations. Default = co/kuali/coeus/data/migration/custom/coeus

* kc.kew.auto.ingestion.paths -- a csv of paths to look for kew ingester files. Default = org/kuali/coeus/rice-xml,org/kuali/coeus/coeus-xml
* kc.kew.auto.ingestion.principalId -- the principal id to ingest as. Default = admin
* kc.kew.auto.ingestion.enabled -- Sets whether ingester will automatically upgrade the kew tables. Default = true
