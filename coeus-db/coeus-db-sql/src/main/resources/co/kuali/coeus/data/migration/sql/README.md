# SQL Installation Instructions

The folders here contain scripts to facilitate the installation of the necessary Mysql or Oracle database in order to run KC. Inside the mysql and oracle directories you will find a series of scripts named similar to the following.

* 600_mysql_kc_demo.sql
* 600_mysql_kc_rice_server_upgrade.sql
* 600_mysql_kc_upgrade.sql
* 600_mysql_rice_demo.sql
* 600_mysql_rice_client_upgrade.sql
* 600_mysql_rice_server_upgrade.sql

The above scripts will upgrade a 5.2.1 database to 6.0.0 for mysql. Each release will have some of the above files although if a release is missing a file type, don't worry. That just means there were no changes required for that type of script. Each of these files has a slightly different purpose.

These scripts are designed to be run in an incremental way. So if you are creating a database from scratch or upgrading from a previous version, make sure you execute all applicable scripts for a specific version before executing the next versions scripts. For example, do not run 521_mysql_kc_upgrade.sql without first running 520_mysql_rice_server_upgrade.sql and 520_mysql_kc_rice_server_upgrade.sql.

##File Types and Usages
### kc_upgrade
These files are to always be run against your KC database
### rice_server_upgrade
These files need to be run against your Rice database. In the case of bundled mode, this is the same as your KC database. In embedded mode this needs to be run against the separate Rice server. 

If you are running in embedded mode and have upgraded your Rice server separately from these scripts, see the note below.
###kc_rice_server_upgrade
These files need to be run against your Rice database after the above rice_server_upgrade occurs. They will add necessary information to the Rice database such as permissions, roles and parameters.
###rice_client_upgrade
These files need to be applied against your KC database, BUT should only be run in the case that you are running in embedded mode and have separate KC and Rice databases. 

In the case of bundled mode you may experience problems if you run both the rice_server_upgrade and rice_client_upgrade as there are likely to be duplicate scripts executed against tables that are shared between embedded client and server databases.
###kc_demo and rice_demo
This last type of script is to be run only in the case you are trying to build or update a demo database which will contain additional sample information to get KC up and running quickly. It is not recommended to apply these scripts against a production database.

The kc_demo script should be run against KC and the rice_demo against Rice.

##Embedded Mode Caution
For embedded mode databases that are being maintained and upgraded separately from this process, you should not need to run the rice_server_upgrade scripts at all. But you will still need to run the kc_rice_server_upgrade scripts against Rice and the rice_client_upgrade scripts against KC.

##Custom Java Database Conversion Process
Finally KC has a few more complicated and cross KC->Rice database conversions included. These conversions could not be done through plain sql similarly to the rest of these conversions due to the complicated nature to trying to transfer data between KC and Rice databases for embedded mode instances. Information on running these scripts can be found in this [README.md](../../../../../../../../../../coeus-db-data-conv/README.md) file.

There are currently 7 custom conversion processes and they should be run as follows. Each conversion type supported by the database conversion process should only be run once when upgrading a database.

* Proposal (proposal) after all 600* scripts are run
* Proposal Person Role (pprole) after all 600* scripts are run
* KRMS Question & Questionnaire Sequence (questseq) after the 1505* scripts are run
* Time & Money Document Status (tmdocstatus) after the 1507* scripts are run
* Subaward Amount Info (subaward-amountinfo) after the 1511* scripts are run
* Time And Money Award Amount Info Duplicate Removal (tm-dups) after the 1603* scripts are run
* Proposal YNQ -> Questionnaire Conversion (proposal-ynq) after the 1603* scripts are run


##Mysql Specifics
When running these scripts with mysql, be aware that by default any errors will cause the script to stop at that point. If you want to override this behavior and proceed regardless of errors(not recommended) you can use the '-f' flag to mysql. Mysql will print out any errors onto stderr when running from the command line.

##Oracle Specifics
When running the Oracle scripts using sqlplus a log file will be generated for each script you run detailing any errors, if any, you receive.
