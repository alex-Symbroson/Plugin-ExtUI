
[ "$4" = "" ] && exit;

name=$1
ad_tmp=$2
ad_privplg=$3
ad_docs=$4

#copy files
mkdir -p $ad_privplg
cp $ad_tmp/$name.inc $ad_tmp/$name.jar $ad_tmp/$name.html $ad_privplg

#get DroidScript user group (using second argument of 'ls -l')
set -- $(ls -l /data/data/ | grep com.smartphoneremote.androidscriptfree);

#change owner to DroidScript
chown -R $2:$2 $ad_privplg

#copy files
mkdir -p $ad_tmp $ad_docs
cp $ad_tmp/$name.inc $ad_tmp/$name.jar $ad_tmp/$name.html $ad_docs

#delete files
rm -R $ad_tmp