echo off
cd D:\Updated_LowerEnv\PennyMac_Test1Smoke\
D:
mvn clean test -Dxml=custom -Denv=custom
