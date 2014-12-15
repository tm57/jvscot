all:
	mvn package
run:
	java -cp target/ScotlandYardApp-1.0.jar zw.scotlandyardapp.app.App

