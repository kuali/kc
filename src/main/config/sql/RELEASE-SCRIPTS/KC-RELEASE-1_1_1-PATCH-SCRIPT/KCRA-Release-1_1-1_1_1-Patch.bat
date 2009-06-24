REM ##############
REM #
REM # Bat script to patch a release 1.0 DB to release 1.0.1 DB
REM # Created 6-AUG-2008 Tyler Wilson
REM #
REM # Use sqlplus to execute SQL scripts
REM # Use sqlldr to load LOB DML into tables
REM #
REM ##############

sqlplus <username>/<password>@<service> @KCRA-Release-1_0-1_0_1-Patch.sql


