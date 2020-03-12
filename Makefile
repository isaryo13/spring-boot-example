# Makefile

MIN_JAVA_VERSION := 11

ifneq ($(shell which java),)
	JAVA_VERSION := $(shell java -version 2>&1 | awk -F '"' '/version/ {print $$2}' | awk -F '.' '{print $$1}')
	ifeq ($(shell test ${JAVA_VERSION} -ge ${MIN_JAVA_VERSION}; echo $$?),0)
		ifeq ($(OS),Windows_NT)
			GRADLE := gradlew.bat
		else
			GRADLE := ./gradlew
		endif
	endif
endif

ifeq (${GRADLE},)
	GRADLE := docker-compose -f docker-compose-gradle.yml run gradle
endif

.DEFAULT_GOAL := help

.PHONY: $(shell egrep -o '^[a-zA-Z_-]+:' $(MAKEFILE_LIST) | sed 's/://')

build: ## Build the application.
	@${GRADLE} build -s

clean: ## Clean up the application build directory.
	@${GRADLE} clean -s

run: ## Run the application.
	@docker-compose up -d mysql redis
	@${GRADLE} bootRun -s

docker-build: ## Create images for the application.
	@docker-compose build

docker-clean: ## Stop and remove containers and images.
	@docker-compose down --rmi local --volumes

docker-run: ## Create and start containers.
	@docker-compose up -d

docker-stop: ## Stop and remove all containers.
	@docker-compose down

help: ## Show this message.
	@echo "Usage:\n    make \033[36m<command>\033[0m\n\nCommands:" >&2
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) \
		| awk 'BEGIN {FS = ":.*?## "}; {printf "%4s\033[36m%-10s\033[0m\n%8s%s\n", "", $$1, "", $$2}' >&2
