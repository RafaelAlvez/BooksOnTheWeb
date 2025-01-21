#!/bin/bash

# Nome da network
NETWORK_NAME="books-network"

# Verificar se a network já existe
if [ ! "$(docker network ls --filter name=^${NETWORK_NAME}$ --format="{{ .Name }}")" ]; then
    echo "Network '${NETWORK_NAME}' não encontrada. Criando..."
    docker network create ${NETWORK_NAME}
else
    echo "Network '${NETWORK_NAME}' já existe."
fi

# Rodar o docker-compose
docker-compose up --build
