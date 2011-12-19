#!/bin/sh

if [ $# -lt 1 ]
then
	echo "Usage: $0 version"
	echo "   Ex: $0 3.1.1"
	exit
fi
current_version="$1"
if [ ! -d "../current/${current_version}" ]
then
	echo "${current_version} directory does not exist"
	exit
fi
converted_version=`echo ${current_version} | tr '.' '_'`
script_dir_name="KC-RELEASE-${converted_version}-SCRIPT"
if [ ! -d "${script_dir_name}" ]
then
	mkdir "${script_dir_name}"
fi

cd "${script_dir_name}"

app_names='KC KR KRC'
db_types='ORACLE MYSQL'
sql_dirs='sequences tables views dml constraints'

make_file()
{
	if [ "${thisDB}" = "ORACLE" ]
	then
		echo "set define off"
		echo "set sqlblanklines on"
		echo "spool ${logFile}"
		base_dir="../../current/${current_version}"
	else
		base_dir="./../../current_mysql/${current_version}"
	fi
	
	hadFiles='false'
	for thisDir in ${sql_dirs}
	do
		thisMask="${thisApp}_*"
		if [ "${thisDir}" = "dml" ]
		then
			if [ "${thisType}" = 'BS' ]
			then
				thisMask="${thisApp}_*_B000.sql"
			else
				thisMask="${thisApp}_*_???D.sql"
			fi
		else
			if [ "${thisType}" = 'DM' ]
			then
				continue
			fi
		fi
		#for thisFile in `ls ${thisDB}/${thisDir}/${thisApp}_* 2>/dev/null`
		for thisFile in `ls ${base_dir}/${thisDir}/${thisMask} 2>/dev/null`
		do
			if [ "${thisDB}" = "ORACLE" ]
			then
				printf '@'
			else
				printf '\. '
			fi
			echo "${thisFile}"
			hadFiles='true'
		done
	done
	echo "commit;"
	echo "exit"
	if [ "${hadFiles}" = 'false' ]
	then
#		echo 'Had no files' >&2
		return 1
	fi
}

dataTypes='BS DM'
for thisType in ${dataTypes}
do
	if [ "${thisType}" = 'DM' ]
	then
		typeStr='Demo'
	else
		typeStr='Upgrade'
	fi
	for thisApp in ${app_names}
	do
		for thisDB in ${db_types}
		do
			sqlFile="${thisApp}-RELEASE-${converted_version}-${typeStr}-${thisDB}.sql"
			logFile="${thisApp}-RELEASE-${converted_version}-${typeStr}-${thisDB}-Install.log"
			make_file > ${sqlFile}
			make_result="$?"
			if [ "${make_result}" != 0 ]
			then
				echo "Returned ${make_result} deleting ${sqlFile}"
				rm ${sqlFile}
			fi
		done
	done
done	


