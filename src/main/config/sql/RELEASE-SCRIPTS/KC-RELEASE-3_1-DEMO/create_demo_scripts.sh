#!/bin/sh

main_dir=`pwd`
main_dir=`basename $main_dir`
demo_version="${main_dir%-*}"
base_version="${demo_version#KC-*}"

app_names='KC KR'
db_types='ORACLE MYSQL'

make_file()
{
	if [ "${thisDB}" = "ORACLE" ]
	then
		echo "set define off"
		echo "set sqlblanklines on"
		echo "spool ${logFile}"
	fi
	for thisFile in `ls ${thisDB}/${thisApp}_* 2>/dev/null`
	do
		if [ "${thisDB}" = "ORACLE" ]
		then
			printf '@'
		else
			printf '\. '
		fi
		echo "${thisFile}"
	done
	echo "commit;"
	echo "exit"
}

for thisApp in ${app_names}
do
	for thisDB in ${db_types}
	do
		sqlFile="${thisApp}-${base_version}-Demo-${thisDB}.sql"
		logFile="${thisApp}-${base_version}-Demo-${thisDB}-Install.log"
		make_file > ${sqlFile}
	done
done	


