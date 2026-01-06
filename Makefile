DOCKER_COMPOSE_FILE := ./deploy/docker-compose.yml
DOCKER_FILE := ./deploy/Dockerfile
IMAGE_NAME ?= inventory
IMAGE_TAG ?= latest
IMAGE := $(IMAGE_NAME):$(IMAGE_TAG)

.PHONY: help clean-up config build build-image up-local restart-local status down-local

help:
	@echo "Targets available:"
	@echo "  clean-up          - Clean build artifacts"
	@echo "  up-local          - Full local setup"
	@echo "  restart-local     - Restart local deployment"
	@echo "  status            - Status checks"
	@echo "  down-local        - Full teardown"

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
	@set -e; \
	if [ -z "$$(docker compose -f $(DOCKER_COMPOSE_FILE) ps --status running -q)" ]; then \
  		make clean-up; \
  		make build && \
		make build-image && \
		echo "Starting local environment..." && \
		IMAGE=${IMAGE} docker compose -f $(DOCKER_COMPOSE_FILE) up -d || make down-local &&\
		echo "Local environment is up and running."; \
	else \
		echo "Local environment is already running."; \
		exit 0; \
	fi


restart-local: down-local up-local

status:
	docker compose -f $(DOCKER_COMPOSE_FILE) ps

down-local:
	docker compose -f $(DOCKER_COMPOSE_FILE) down