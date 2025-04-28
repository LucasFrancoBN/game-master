# 🎮 Game Master 🎮

## Sumário

1. [Descrição do projeto](#1-descrição-do-projeto)
2. [Documentação completa](#2-documentação-completa)
3. [Diagrama de caso de uso](#3-diagramas-de-caso-de-uso)
4. [Backend](./backend/README.md)
   - 4.1 [Arquitetura](#41-arquitetura)
   - 4.2 [Diagrama Entidade-Relacionamento](#42-diagrama-entidade-relacionamento)
5. Frontend
   - 5.1 [Game Master Manager](#51-game-master-manager)
6. [Ferramentas utilizadas](#6-ferramentas-utilizadas)
7. [Guia de implantação](#7-guia-de-implantação)
8. [Desenvolvedores](#8-desenvolvedores)

## 1. Descrição do projeto

**Game Master** é um e-commerce fictício cujo principal objetivo é comercializar produtos relacionados ao mundo dos games, incluindo jogos eletrônicos físicos, componentes de computador, consoles de mesa, consoles portáteis, periféricos de computador e outros itens associados.

## 2. Documentação completa

Este projeto conta com uma documentação detalhada, garantindo total clareza sobre o que está sendo desenvolvido e abrangendo todos os casos dentro da aplicação.
[Clique aqui](https://whimsical.com/game-master-documento-de-requisitos-E2h8HHX9cGiiZiPMb5tu6V) para acessá-la.

## 3 Diagramas de caso de uso

A seguir estão os diagramas de caso de uso:
![Diagrama caso de uso - game master manager](readme-img/caso-de-uso-gmm.png)
![Diagrama caso de uso - game master](readme-img/caso-de-uso-gm.png)

## 4.1 Arquitetura

A arquitetura escolhida para desenvolver esse projeto foi Clean Arch. Veja assim a estrutura do projeto:

```
io.github.lucasfrancobn.gamemaster
├── application
│   ├── gateway
│   ├── shared.pagination
│   └── usecase
├── domain
│   ├── entities
│   └── services
├── infra
│   ├── config
│   │   ├── bean
│   │   ├── scheduler
│   │   └── security
│   │       ├── authentication
│   │       ├── filter
│   │       ├── repository
│   │       ├── server
│   │       └── service
│   └── web
├── controller
├── gateway
├── persistence
│   ├── model
│   └── repository
├── presentation
│   ├── dtos
│   └── mappers
└── service
```

## 4.2 Diagrama Entidade-Relacionamento

A imagem a seguir representa toda a estrutura do banco de dados:

![Diagrama do projeto](readme-img/der.png)

## 5.1 Game Master Manager

Game Master Manager é o sistema de gerenciamento do Ecommerce a ser desenvolvido. Nele, é possível controlar todas as operações do ecommerce, como gerenciar produtos, pedidos, promoções, usuários e acessos, transporte, entre muitas outras funcionalidades.
Todo o sistema foi desenvolvido utilizando Angular. Veja a descrição completa do projeto [clicando aqui](./manager/README.md)

## 6. Ferramentas utilizadas

<div style="display: flex; gap: 15px">
<a href="https://www.java.com" target="_blank"> 
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="Java" width="40" height="40"/> 
</a>

<a href="https://spring.io/" target="_blank"> 
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/spring/spring-original.svg" alt="Spring" width="40" height="40"/> 
</a>

<a href="https://www.postman.com/" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postman/postman-original.svg" alt="Postman" width="40" /> 
</a>

<a href="https://www.postgresql.org/" target="_blank">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-plain.svg" width="40"/>
</a>

<a href="https://www.docker.com/" target="_blank">
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/docker/docker-plain.svg" width="40"/>
</a>

<a href="https://angular.dev/" target="_blank">
    <img src="https://img.alicdn.com/imgextra/i1/O1CN01RSfkps1J0vtVaKr0U_!!6000000000967-49-tps-1920-1920.webp" width="40"/>
</a>

<a href="https://ng.ant.design/docs/introduce/en" target="_blank"> 
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/antdesign/antdesign-original.svg" alt="Ng Zorro" width="40" height="40"/> 
</a>

</div>

## 7. Guia de implantação

### 🚧 Projeto ainda em construção 🚧

O projeto ainda está em construção, mas você pode testar o que já foi desenvolvido até então.
Lista de funcionalidades desenvolvidas:

- [x] Autenticação com Login e Senha utilizando Authorization Flow do protocolo OAuth2.
- [x] Gerenciamento de Usuários.
- [x] Gerenciamento de Clients.
- [x] Gerenciamento de Produtos.

Antes de iniciar o projeto, precisamos do [Docker](https://www.docker.com/) e [Git](https://git-scm.com/) instalados em nossas máquinas.
Tendo o git instlado, rode o seguinte comando no terminal da sua máquina:

```bash
git clone https://github.com/LucasFrancoBN/game-master.git
```

Após isso, rode o seguinte comando no diretório raiz do projeto (somente se tiver o docker instalado em sua máquina):

```bash
docker-compose up
```

## 8. Desenvolvedores

<table align="center">
  <tr>
    <td align="center">
      <div>
        <img src="https://avatars.githubusercontent.com/LucasFrancoBN" width="120px;" alt="Foto no GitHub" class="profile"/><br>
          <b> Lucas Franco   </b><br>
            <a href="https://www.linkedin.com/in/lucas-franco-barbosa-navarro-a51937221/" alt="Linkedin"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" height="20"></a>
            <a href="https://github.com/LucasFrancoBN" alt="Github"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white" height="20"></a>
      </div>
    </td>
  </tr>
</table>
