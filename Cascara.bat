java -jar Cascara.jar
start /b "" cmd /c mvn -N io.takari:maven:0.7.7:wrapper
start /b "" cmd /c del Cascara.jar /s /f /q
start /b "" cmd /c del "%~f0"&exit /b
