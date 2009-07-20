#!/bin/sh

usage ()
{
	echo "USAGE:"
	echo "KC_Install.bat Install_version username password DB_server_name"
	echo "   - Install_Version = Choose one: new, 1.0, 1.1"
	echo "      - new = New install with an empty database schema"
	echo "      - 1.0 = upgrading from 1.0 KC version"
	echo "      - 1.1 = upgrading from 1.1 KC version"
	echo "   - username = The Database schema name to install database scripts to."
	echo "   - password = the password for username"
	echo "   - DB_server_name = the name used to locate the database server where scripts are stored"
}

newInstall ()
{
	cd KRA-RELEASE-1_0-SCRIPT
	sqlplus $user/$pass@$db @KRA-Release1_0.sql

	sqlldr $user/$pass@$db control=DML/LOB-CTL/EXEMPTION_TYPE.ctl
	sqlldr $user/$pass@$db control=DML/LOB-CTL/EN_DOC_HDR_CNTNT_T.ctl
	sqlldr $user/$pass@$db control=DML/LOB-CTL/EN_RULE_ATTRIB_T.ctl
	sqlldr $user/$pass@$db control=DML/LOB-CTL/FP_MAINTENANCE_DOCUMENT_T.ctl
	sqlldr $user/$pass@$db control=DML/LOB-CTL/SPONSOR_FORM_TEMPLATES.ctl
	sqlldr $user/$pass@$db control=DML/LOB-CTL/YNQ_EXPLANATION.ctl
	mv *.log ..
	cd ..
	v10
}

v10 ()
{
	cd KRA-RELEASE-1_1-SCRIPT
	sqlplus $user/$pass@$db @KRA-Release1_1.sql
	mv *.log ..
	cd ..
	v11
}

v11 ()
{
	cd KC-RELEASE-1_1_1-PATCH-SCRIPT
	sqlplus $user/$pass@$db @KCRA-Release-1_1-1_1_1-Patch.sql
	mv *.log ..
	cd ..
}

if [ $# -lt 4 ]
then
	usage
	exit 1
fi

user="$2"
pass="$3"
db="$4"

case "$1" in
	'new')
		newInstall;;
	'1.0')
		v10;;
	'1.1')
		v11;;
	*)
		usage
		exit 1;;
esac

echo "review log files for errors during database install."
ls *.log


