# BooksOnTheWeb

Este projeto é composto por três serviços Java Spring Boot 21:

- **sboot-atom-emprestimo**
- **sboot-atom-livros**
- **sboot-atom-usuarios**

Além disso, utiliza RabbitMQ e um banco de dados Postegre configurados através do Docker Compose. Para garantir a inicialização correta, siga os passos:

---

## 🛠️ Execute o Script de Rede

Navegue até a pasta network e execute o script de rede para configurar a network necessária:

- cd network
- ./run.sh

---

## 🚀 Inicialize os Serviços de Banco de Dados e RabbitMQ

- cd ../data
- docker-compose up -d

---

## 🚀 Suba os Serviços do Projeto

- cd ../deploy
- docker-compose up -d

## 🛠️ Os Projetos estão disponíveis em: 

- sboot-atom-emprestimo: http://localhost:8087/swagger-ui/index.html
- sboot-atom-livros: http://localhost:8085/swagger-ui/index.html
- sboot-atom-usuarios: http://localhost:8086/swagger-ui/index.html

---