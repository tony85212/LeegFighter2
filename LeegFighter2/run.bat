cls

rem compile
md build
javac -d build *.java

rem run
java -cp build LeegFighter2
pause

rem clean
rd /s/q build