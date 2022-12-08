
VBoxManage list vms

VBoxManage startvm "serverli" --type headless
VBoxManage startvm "ubuntu-sonarqube" --type headless


docker start api-manager-4.1.0

docker network connect minikube api-manager-4.1.0

minikube start 
minikube start --memory 16000 --cpus 8


—---------


Save credential on git

git config --global credential.helper store


clone project

git clone https://github.com/pelsan/otumba-micro.git


On folder docker-compose-01

docker-compose up -d --force-recreate

si no levanta bien entonces 

docker-compose up -d 

y después ya ejecutar 

docker-compose start



check log docker image

docker ps -a
docker logs b2b189d36a7b


configuration file on config server

spring.cloud.config.server.git.uri=https://github.com/pelsan/config-repo.git
spring.cloud.config.server.git.default-label=main



delete local images docker

docker rmi $(docker images -q)

forzar borrado de imagen 


docker image rm 281198c24664 -f
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

https://www.digitalocean.com/community/tutorials/how-to-remove-docker-images-containers-and-volumes-es

borrado de todo

docker system prune

o
docker system prune -f 



cheke profiles and variables of each docker 
http://192.168.1.190:8762/


check configuration server

http://192.168.1.190:8888/config-client/development



default path swagger

http://192.168.1.190:8083/swagger-ui/


—---------------

Keycloak
realm: PaymentChain

select keys
use: 


RS256
RSA
LFOafZkJLzLSZI006fujRLwGmQlL3iGEUaMPUBMiTAY
SIG
100
rsa-generated
Public key
Certificate


configure tokens to 30 minutes
Access Token Lifespan from 5 to 30 minutes

add role

role name: Partners

add user

user name: billing

go to credentials:
password: qwerty
temporarily off


go to role mapping:
assign role: Partners

create new client
client id: front-angular-billing-app

save

valid redirect uris : *

save



—---------------
if this error on docker-compose:

docker.credentials.errors.InitializationError: docker-credential-desktop not installed or not available in PATH
then:

https://stackoverflow.com/questions/67642620/docker-credential-desktop-not-installed-or-not-available-in-path
if error credentials


—--------

WSO2


https://hub.docker.com/r/wso2/wso2am

https://github.com/wso2/docker-apim/tree/v3.0.0.1/docker-compose/apim-with-analytics

—------------
GIT

git add *
git commit -m "Un comentario de lo que se hizo"
git push origin main
 
—----------
kubernetes minukube
 
minikube delete
minikube start
—-------------
 
log into a pod in order to test a mysql database:
 
kubectl get all
 
kubectl exec webapp-595f987fd5-pphr6 -it sh

to validate service like “database”
 nslookup database

install in a pod mysql client

apk update
apk add mysql-client
mysql -h database -uroot -ppassword fleetman

—---------------------

codificar a base64 en linux para el tema de los secrets.yaml
echo -n "postgres" |base64

decodificar:
echo -n "cG9zdGdyZXM=" |base64 -d


—------------GRAFANE E ISTIO —---

check grafana
http://192.168.49.2:30200/browser/


check istio - kiali

http://192.168.49.2:31000

gateway 
http://localhost:31080/actuator

—--------------
https://joshgunh.medium.com/spring-cloud-config-vs-kubernetes-configmap-detailed-comparison-bce64b594af8
https://www.squadcast.com/blog/kubernetes-alternatives-to-spring-java-framework\

Spring Boot using config demo
https://capgemini.github.io/engineering/externalising-spring-boot-config-with-kubernetes/



—-------------WSO2—-------------

with  docker

https://hub.docker.com/r/wso2/wso2am
docker run -it -p 8280:8280 -p 8243:8243 -p 9443:9443 --name api-manager wso2/wso2am:4.1.0

si ya esta corriendouna imagen entonces solo ejecutar

docker ps -a
identificar la imagen

docker start api-manager





user and pass is:  admin:admin


create new role: internal role

   Admin permissions
	select configure
	select login
	under Manage 
		select API
		select API-M Admin


next, finish


new role: subscribers
application


Admin permissions
	select login
	under manage - API
		select Suscribe


create new user
	primerusuario
	role: creators

create new user
	primersuscriber
	role:
	suscribers


manager

https://localhost:9443/carbon



una vez creato una api en publisher

https://localhost:9443/publisher/
https://0.0.0.0:9443/publisher/


se puede consultar detalles


y en Dev portal 

https://localhost:9443/devportal/apis/


		







—-----DOCKER CLEAN ALL —---

docker ps -a
identificar la imagen
parar las imagenes corriendo en docker 

docker stop api-manager


borrar las imagenes que aparecen en docker ps -a


docker system prune

o
docker system prune -f 

borrar las imagenes de  docker images

docker rmi $(docker images -q)

forzar borrado de imagenes

docker image rm 281198c24664 -f




—---------- WSO2 version 3.2.0 —----


docker run -it -p 8280:8280 -p 8243:8243 -p 9443:9443 --name api-manager wso2/wso2am:3.2.0

Documentation
https://apim.docs.wso2.com/en/3.2.0/getting-started/quick-start-guide/


—---------- WSO2 version 4.1.0 —----


docker run -it -p 8280:8280 -p 8243:8243 -p 9443:9443 --name api-manager-4.1.0 wso2/wso2am:4.1.0

Documentation
https://apim.docs.wso2.com/en/4.1.0/design/create-api/create-rest-api/create-a-rest-api/



manager

https://localhost:9443/carbon



una vez creato una api en publisher

https://localhost:9443/publisher/
https://0.0.0.0:9443/publisher/


se puede consultar detalles


y en Dev portal 

https://localhost:9443/devportal/apis/

—-------Configuracion servicio SOAP —---

https://apim.docs.wso2.com/en/latest/design/create-api/create-rest-api/expose-a-soap-service-as-a-rest-api/

—----------
En publisher y endpoints se configura OAUTH2 que susan los API expuestas

—---

https://localhost:9443/devportal/apis




bd-busyflights-service.default.svc.cluster.local/10.109.40.35:8089
bd-busyflights-service



—------ Alta API con swagger —------

Go to url on service 

http://192.168.49.2:31089/swagger/index.html

click on first blue url to get json configuration

go to 
https://editor.swagger.io/

paste code and yes to convert to yaml
edit port if there is no port

save as yaml

import into WSO2

—------------- REDES DOCKER—-----

listar redes

docker network ls

conectar WSO2 que esta en docker a la misma red de minikube para que vea los servicios

docker network connect minikube api-manager










—-----------INSTALAR PLUGIN DOCKER JENKINS—---


instalar plugin “CloudBees Docker Build and Publish”

now you can add docker and publish step


add step and configure:


repository name: pelsan/namedockerimage

tag: 0.1.1

— configure docker access: —-------
sudo vi /lib/systemd/system/docker.service
comment and add this lines:

#ExecStart=/usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
ExecStart=/usr/bin/dockerd -H fd:// -H=tcp://0.0.0.0:2375

execute:
sudo systemctl daemon-reload
sudo service docker restart

check configuration with

curl http://localhost:2375/images/json


tcp://ipmachinedocker:2375

—---------------

al configurar publish docker


Additional Build Arguments
--build-arg JAR_FILE=target/*.jar

—------- SSH TUNNEL —-----
login to machine 192.168.1.102 with user peter and create a tunnel that
use port localhost 31000 mapped to machine (that only can reach remote machine) 192.168.49.2 port 31000

ssh -L 31000:192.168.49.2:31000 peter@192.168.1.102
ssh -L 31001:192.168.49.2:31001 peter@192.168.1.102

—-- checking microservices on project otumba-parent

curl http://192.168.49.2:31090/customer/preparecheck

curl http://192.168.49.2:31093/dog



—---WSO2-----

change ip 


docker ps -a

docker exec -it 014a2428b76a /bin/bash

