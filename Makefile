compile :
	mvn --quiet clean compile assembly:single

test_01 :
	java -jar ./target/t09-using-maven-1.0-SNAPSHOT-jar-with-dependencies.jar
