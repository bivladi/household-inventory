DOCKER_COMPOSE_FILE := ./deploy/docker-compose.yml
DOCKER_FILE := ./deploy/Dockerfile
IMAGE_NAME ?= inventory
IMAGE_TAG ?= latest
IMAGE := $(IMAGE_NAME):$(IMAGE_TAG)

.PHONY: help clean-up config build build-image up-local build-and-run-local restart-local status down-local

help:
	@echo "Targets available: "
	@echo "  clean-up               - Clean the build artifacts"
	@echo "  config                 - Validate and view the Docker Compose configuration"
	@echo "  build                  - Build the project using Gradle"
	@echo "  build-image            - Build the Docker image for the application"
	@echo "  up-local               - Start the local environment using Docker Compose"
	@echo "  build-and-run-local    - Build the Docker image and start the local environment"
	@echo "  restart-local          - Restart the local environment"
	@echo "  status                 - Check the status of the Docker Compose services"
	@echo "  down-local             - Stop and remove the local environment"

clean-up:
	./gradlew clean

config:
	IMAGE=$(IMAGE) docker compose -f $(DOCKER_COMPOSE_FILE) config

build:
	echo "Building the project"; \
	./gradlew build && \
	echo "Project built successfully";

build-image:
	echo "Building image..." && \
	docker image build -f $(DOCKER_FILE) -t $(IMAGE) . && \
	echo "Image built successfully.";

up-local:
	echo "Starting local environment..." && \
	IMAGE=${IMAGE} docker compose -f $(DOCKER_COMPOSE_FILE) up -d --wait || make down-local &&\
	echo "Local environment is up and running."

build-and-run-local:
	@set -e; \
	if [ -z "$$(docker compose -f $(DOCKER_COMPOSE_FILE) ps --status running -q)" ]; then \
  		make clean-up; \
		make build-image && \
		make up-local; \
	else \
		echo "Local environment is already running."; \
		exit 0; \
	fi


restart-local: down-local up-local

status:
	docker compose -f $(DOCKER_COMPOSE_FILE) ps

down-local:
	docker compose -f $(DOCKER_COMPOSE_FILE) down