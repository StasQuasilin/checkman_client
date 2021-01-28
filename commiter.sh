#! /bin/bash

git add .
git add -u

date=`date +"%d.%m.%Y dev"`
commit=git commit -m "${date}" 

