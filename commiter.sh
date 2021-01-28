#! /bin/bash
changes=`git status -s | wc -l`

if [[ changes -gt 0 ]]
then
	git add .
	git add -u

	msg=`date +"%d.%m.%Y dev"`
	git commit -m "${msg}" 

	git push
fi

