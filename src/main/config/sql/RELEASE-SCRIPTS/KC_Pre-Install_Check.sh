#!/bin/sh

usage () 
{
	echo "USAGE:"
	echo "./KC_Pre-Install_Check.sh username password DB_server_name"
	echo "   - username = The Database schema name to install database scripts to."
	echo "   - password = the password for username"
	echo "   - DB_server_name = the name used to locate the database server where scripts are stored"
}

if [ $# -lt 3 ]
then
	usage
	exit 1
fi

cd KC-RELEASE-1_1_1-PATCH-SCRIPT
sqlplus $1/$2@$3 @KCRA-Release-1_1-1_1_1-Pre-install-check.sql
mv *.log ..
cd ..
echo 'review files created beginning with install_kc_release-1_1_1-Patch_*.log for items requiring attention before patch is applied.'
ls *.log
