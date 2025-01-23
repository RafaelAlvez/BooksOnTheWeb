# BooksOnTheWeb

Este projeto é composto por três serviços Java Spring Boot 21 e uma inteface web em Angular 18:

- **sboot-atom-emprestimo**
- **sboot-atom-livros**
- **sboot-atom-usuarios**
- **front-books-on-the-web**

Além disso, utiliza RabbitMQ e um banco de dados Postegre configurados através do Docker Compose. Para garantir a inicialização correta, siga os passos:

---

## 🚀 Inicialize os Serviços de Banco de Dados e RabbitMQ

- cd ../data
- docker-compose up -d

---

## 🚀 Suba os Serviços do Projeto

- cd ../deploy
- docker-compose up -d

## 🚀 Suba o front-end Angular

- cd front-books-on-the-web
- npm install
- ng serve

## 🛠️ Os Projetos estão disponíveis em: 

- sboot-atom-emprestimo: http://localhost:8087/swagger-ui/index.html
- sboot-atom-livros: http://localhost:8085/swagger-ui/index.html
- sboot-atom-usuarios: http://localhost:8086/swagger-ui/index.html
- FrontEnd: http://localhost:4200

---