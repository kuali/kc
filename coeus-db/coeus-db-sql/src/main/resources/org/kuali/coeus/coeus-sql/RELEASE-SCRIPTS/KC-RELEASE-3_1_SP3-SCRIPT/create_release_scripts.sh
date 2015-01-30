########################################
# Kuali Coeus, a comprehensive research administration system for higher education.
# 
# Copyright 2005-2015 Kuali, Inc.
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as
# published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
# 
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
########################################
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
	echo "exit"
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


