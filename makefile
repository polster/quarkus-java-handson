#!makefile

COMPOSE_FILE_INFRA="./project-resources/docker-compose/local-dev-infra.yml"
COMPOSE_FILE_SERVICES="./project-resources/docker-compose/local-dev-services.yml"
SERVICES_PATH="./services"

docker-infra-up:
	docker-compose -f ${COMPOSE_FILE_INFRA} up -d

docker-services-up:
	docker-compose -f ${COMPOSE_FILE_SERVICES} up -d

docker-infra-down:
	docker-compose -f ${COMPOSE_FILE_INFRA} down

docker-services-down:
	docker-compose -f ${COMPOSE_FILE_SERVICES} down

application-start:
	mvn -f "${SERVICES_PATH}/$(APP)/pom.xml" compile quarkus:dev

docker-build:
	mvn -f "${SERVICES_PATH}/$(APP)/pom.xml" -Dquarkus.profile=docker clean install

.PHONY: docker-infra-up \
	docker-services-up \
	docker-infra-down \
	docker-services-down \
	application-start \
	docker-build