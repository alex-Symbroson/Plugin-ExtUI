
@echo off

echo setting variables

::user variables
set name=%1
echo Installing %name%

set adb=C:\Users\user\AppData\Local\Android\sdk\platform-tools\adb
set zip=C:\Programs\7-Zip\7z

::lowercase name for docs folder
set docs=%name%
call :LoCase docs

::target dir variables
set wd_out=build\outputs\apk
set ad_tmp=/sdcard/.DroidScript/%docs%
set ad_docs=/sdcard/DroidScript/.edit/docs/plugins/%docs%
set ad_plg=/sdcard/DroidScript/Plugins
set ad_privplg=/data/data/com.smartphoneremote.androidscriptfree/app_Plugins/%docs%

::extended path variables
set wd_tmp=%wd_out%\%name%
set wf_apk=%wd_out%\%name%.apk

set pwd=%cd%

::copy files to one temp folder
echo unzip %name%.apk and paste files to %name%
%zip% e -y "%wf_apk%" -o"%wd_tmp%" classes.dex assets\%name%.inc assets\%name%.html > NUL

cd "%wd_tmp%"

echo zip classes.dex to %name%.jar
%zip% a -bd "%name%.jar" classes.dex > NUL

if "%2" == "root" (
	::push files to device
	echo push files to device
	%adb% shell mkdir -p "%ad_tmp%" "%ad_docs%"
	%adb% push "%name%.jar" "%name%.inc" "%name%.html" "%pwd%\Install.sh" "%ad_tmp%"
	echo Install plugin
	%adb% shell su -c '%ad_tmp%/Install.sh %name% %ad_tmp% %ad_privplg% %ad_docs%'
) else (
	::create plugin zip
	echo create %name%.zip
	C:\Programs\7-Zip\7z a -bd "%name%.zip" "%name%.jar" "%name%.inc" "%name%.html" > NUL

	::push zip to plugin directory
	echo push %name%.zip to DroidScript
	%adb% push "%name%.zip" "%ad_plg%"
)

del "%name%.jar" "%name%.inc" "%name%.html" "%name%.zip" classes.dex
cd %pwd%
rmdir "%wd_tmp%"

::end
::pause
exit

:: convert variable value to lower case
:LoCase
for %%i in ("A=a" "B=b" "C=c" "D=d" "E=e" "F=f" "G=g" "H=h" "I=i" "J=j" "K=k" "L=l" "M=m" "N=n" "O=o" "P=p" "Q=q" "R=r" "S=s" "T=t" "U=u" "V=v" "W=w" "X=x" "Y=y" "Z=z") do call set "%1=%%%1:%%~i%%"
goto:eof
