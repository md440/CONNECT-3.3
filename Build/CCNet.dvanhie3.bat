FOR /F "TOKENS=4 DELIMS=\," %%A IN ('cd') DO set CodeLineName=%%A
FOR /F "TOKENS=3 DELIMS=\," %%A IN ('cd') DO set ProjectName=%%A

title NHINC %CodeLineName% DVANHIE3 Build Server
set PATH=%SystemRoot%\system32;%SystemRoot%;%SystemRoot%\System32\Wbem

cd server
CCNET.exe -config:"..\CCNet.dvanhie3.xml" -remoting:on