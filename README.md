# household-inventory

# TODO
- [ ] Add project description
- [ ] Add getting started instructions
- [ ] Add tests
- [ ] Add test containers
- [x] Add docker file
- [x] Add docker compose file
- [x] Add startup/shutdown scripts
  - [x] makefile/bash 
    - [x] build image
    - [x] use it in docker compose
  - [x] up/start/stop/down/restart
- [x] Implement environment-based configuration
    - [x] for docker/docker compose
- [X] Add code style 
  - [x] checkstyle
  - [x] PMD
  - [X] spotless
- [x] Github actions
  - [x] CI
    - checks
    - build
    - test
    - executed always 
  - [x] CD to AWS
    - build image
    - push image (optional)
    - deploy to AWS
    - only on commit to main
- [ ] Add persistance
    - Entities, Repositories
    - Flyway migrations
    - Basic Controller/Service
    - expose DB port outside
- [ ] Add security
  - Form based authentication
  - Keycloak/OAuth2
- [ ] Add API documentation (Swagger/OpenAPI)
- [ ] Add mapstruct for DTOs
- [ ] Add logging
- [ ] Metrics
- [ ] Dependency version management

ADR

Project structure:
    
    - 
