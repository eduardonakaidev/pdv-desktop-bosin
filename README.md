# pdv juliane e bosin

### Eduardo Issao Nakai Frasson RA: 239648-1

### Vitor Shimizu RA:

## Objetivo do projeto

Desenvolver um aplicativo de Ponto de Venda (PDV) que integre uma aplicação desktop com uma aplicação web, permitindo que os usuários acessem um banco de dados na aplicação web para gerenciar produtos e clientes.

**Objetivos Específicos DESKTOP:**

- A tela de venda deve exibir a lista de clientes e produtos obtidos a partir da API.
- Atualizar as listas de clientes e produtos a cada 5 minutos.
- Cada vez que a aplicação consumir dados da API de clientes e produtos, deve ser registrado um log da operação em um arquivo.txt na pasta raiz da aplicação.
- O log deve conter as seguintes informações: data e hora da operação, o tipo de operação (obtenção de clientes ou produtos) e o status da operação.

Ao confirmar a venda na tela de venda, a operação de salvar a venda deve ser realizada de forma assíncrona. Evitando bloquear a interface do usuário enquanto a operação é executada. Traga um feedback quando o servidor der o OK da inserção. Atenção, o armazenamento será feito na base de dados da aplicação WEB, só iremos armazenar localmente os arquivos de log.

![der.png](pdv%20juliane%20e%20bosin%2048f44f21c4504bfe96d5a4915fcc10dd/der.png)

## requisitos funcionais

- [ ]  deve ser possível realizar uma venda;
- [ ]  deve ser possível listar os clientes;
- [ ]  deve ser possível listar os produtos;
- [ ]  deve ser possível listar os itemvenda de uma venda;
- [ ]  deve ser possível buscar o produto por id;
- [ ]  deve ser possível criar um produto;
- [ ]  deve ser possivel buscar o cliente por id;
- [ ]  deve ser possível criar um cliente;
- [ ]  deve ser possível listar os itemvenda;
- [ ]  deve ser possível criar um itemvenda;
- [ ]  deve ser possível atualizar o itemvenda;
- [ ]  deve ser possível deletar o itemvenda;
- [ ]  deve ser possível criar um venda;
- [ ]  deve ser possível atualizar a venda;
- [ ]  deve ser possível recuperar a venda que esta incompleta do usuario;
- [ ]  deve ser possivel fazer login;
- [ ]  deve ser possivel se registrar;

## regras de negocio
- [ ] não deve ser possível começar uma nova venda se a venda esta incompleta;
- [ ] cada venda so deve ter um cliente;
- [ ] a venda não deve ser confirmada se não tiver nem um item;
- [ ] a venda não deve ser confirmada sem um cliente definido;
- [ ] as informações de valortotal deve ser definido no sistema;
- [ ] o itemvenda extende as informações do produto;
- [ ] as rotas deve ser acessadas so quando logada menos o registrar e o login do usuario;

## requisitos não funcionais 
- [ ] a aplicação deve ser feita em spring boot;
- [ ] deve ter autenticação com o spring security;
- [ ] deve ser matido os dados em um banco de dados postgresql;
- [ ] quem vai consumir a aplicação é uma aplicação java swing desktop;