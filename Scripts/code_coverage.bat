@echo off

rem Directorio del proyecto de git (donde esta la carpeta .git)
set /P dir="Enter git directory: "
cd /d "%dir%"

rem Get code coverage
set /P bash="Enter bash directory (C:\Program Files\Git\bin i.e.)"
"%bash%\bash.exe" --login -i -c "scp 56f3dfa47628e1de68000081@smartcampus-smartcampus.rhcloud.com:./app-root/repo/target/site/jacoco/index.html ./Reports/index.html"

pause