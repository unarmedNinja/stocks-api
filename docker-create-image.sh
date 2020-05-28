# build spring boot image
# ./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=msdev/finance-api


#repackage
mvn clean
mvn package

# build generic image to run on port 80
docker build -t msdev/finance-api .