#!/bin/sh

main_dir=`pwd`
main_dir=`basename $main_dir`
release_version="${main_dir%-*}"
base_version="${release_version#KC-*}"

app_names='KC KR KRC'
db_types='ORACLE MYSQL'
sql_dirs='SEQUENCES TABLES DML CONSTRAINTS VIEWS'

make_file()
{
	if [ "${thisDB}" = "ORACLE" ]
	then
		echo "set define off"
		echo "set sqlblanklines on"
		echo "spool ${logFile}"
	fi
	for thisDir in ${sql_dirs}
	do
		for thisFile in `ls ${thisDB}/${thisDir}/${thisApp}_* 2>/dev/null`
		do
			if [ "${thisDB}" = "ORACLE" ]
			then
				printf '@'
			else
				printf '\. '
			fi
			echo "${thisFile}"
		done
	done
	echo "commit;"
	if [ "${thisDB}" = "ORACLE" ]
    then
        echo "exit;"
    fi
}

for thisApp in ${app_names}
do
	for thisDB in ${db_types}
	do
		sqlFile="${thisApp}-${base_version}-Upgrade-${thisDB}.sql"
		logFile="${thisApp}-${base_version}-Upgrade-${thisDB}-Install.log"
		make_file > ${sqlFile}
	done
done	


