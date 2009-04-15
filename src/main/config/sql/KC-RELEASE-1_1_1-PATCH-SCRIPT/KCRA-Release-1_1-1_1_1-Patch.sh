##############
#
# Shell script to patch a release 1.0 DB to release 1.0.1 DB
# Created 6-AUG-2008 Tyler Wilson
#
# Use sqlplus to execute SQL scripts
# Use sqlldr to load LOB DML into tables
#
##############

sqlplus <username>/<password>@<service> @KCRA-Release-1_0-1_0_1-Patch.sql


