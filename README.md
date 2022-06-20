## Run application
Install angular cli :
    
    npm install --global @angular/cli
    
Go to :

	social_network_monitoring/src/main/resources/front

run :

	npm install 

then:

	./mvnw package -Dmode=dev

Then run:

    java -jar target/socialnetworkmonitoring-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
    
## Using Docker to simplify development (optional)

Go to :

	social_network_monitoring/src/main/front

run :

	npm install 

To use docker run :

    ./mvnw clean verify jib:dockerBuild -Dmode=production

Then run:

    docker-compose -f src/main/docker/app.yml up -d

## To test Application

    http://194.163.143.239:9922/

## To connect to database

    psql -h 192.168.0.4 -d social-monitoring-database -U root_soci@l_mo_user

