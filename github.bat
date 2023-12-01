@echo off
setlocal enabledelayedexpansion

:: Get today's date in the format YYYY-MM-DD
for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "year=!dt:~0,4!"
set "month=!dt:~4,2!"
set "day=!dt:~6,2!"
set "today=!year!-!month!-!day!"

set "repo_path=D:\SSN-Class\SSN-SEMESTER-7"

:: Check for changes in the local repository
cd /d "%repo_path%"

:: Add, commit, and push changes
git add .
git commit -m "%today%"
git push -f origin main

endlocal
exit

pause