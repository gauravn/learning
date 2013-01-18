#!/bin/sh
#
# test script
#
filename="falcon.release"
release=""
oldRelease=""
releaseSource ()
{
	echo "Releasing New version : $release"
	echo $release>>$filename
	echo "Enter Release Message"
	read tagMessage
	git commit -m "v$release" -a
	git push origin master
	git tag -a v$release -m "$tagMessage"
	git push origin v$release
	echo "Version Release Finished!!"
	echo "Now new version of app is :$release"
	return
}

if [ ! -f $fileName ]; then
	echo "Filename $fileName does not exists"
	exit 1
else 
	release=$(tail -n 1 $filename)
	oldRelease=$release
	#release="0.0"
	majorNo=$(echo $release | cut -d'.' -f1)
	echo $majorNo
	minorNo=$(echo $release | cut -d'.' -f2)
	echo $minorNo
	revisionNo=$(echo $release | cut -d'.' -f3)
	echo $revisionNo

	case $1 in
	   "release") case $2 in
			   "major") majorNo=`expr $majorNo + 1`
				    release=$majorNo.0.0
				    releaseSource
				    echo "new major release : $release";;
			   "minor") minorNo=`expr $minorNo + 1`
				    release=$majorNo.$minorNo.0
				    releaseSource
				    echo "new minor release : $release";;
			   "revision") 	revisionNo=`expr $revisionNo + 1`
					release=$majorNo.$minorNo.$revisionNo
					releaseSource
					echo "new revision release : $release";;
			   *) echo "$2 : Not a release Type";;
			esac;;
	   "goto") 	git checkout v$2
			echo "jump to version: $2";;
	   *) echo "Sorry, $1 Not a valid argument";;
	esac
fi
