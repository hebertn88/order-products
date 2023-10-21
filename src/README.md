# Order Products

Projeto criado durante estudos do Bootcamp Santander 2023 FullStack Java+Angular.

Durante execução foi criada uma API REST com de Java utilizando SpringBoot e banco de dados Postgres.

A API disponibilização autenticação via JWT através de Bearer Token.

## Endpoints

| Descrição                      | Método HTTP | Endereço                     |
|--------------------------------|-------------|------------------------------|
| **Autenticação**               |
| Inserir Usuário                | POST        | ".../auth/register"          |
| Login                          | POST        | ".../auth/login"             |
| **Usuário**                    |
| Buscar todos Usuários          | GET         | ".../users"                  |
| Buscar Usuário por id          | GET         | ".../users/{id}"             |
| Atualizar Usuário              | PUT         | ".../users/{id}"             |
| Habilitar/Desabilitar Usuário  | PUT         | ".../users/{id}/status"      |
| Permissões do Usuário          | PUT         | ".../users/{id}/credentials" |
| **Produto**                    | 
| Buscar todos Produtos          | GET         | ".../products"               |
| Buscar Produto por id          | GET         | ".../products/{id}"          |
| Inserir Produto                | POST        | ".../products"               |
| Atualizar descrição do Produto | PUT         | ".../products/{id}"          |
| Alterar preço do Produto       | PUT         | ".../products/{id}/price"    |
| **Pedido**                     | 
| Buscar todos Pedidos           | GET         | ".../orders"                 |
| Buscar Pedido por id           | GET         | ".../orders/{id}"            |
| Inserir Pedido                 | POST        | ".../orders"                 |
| Remover Pedido                 | DELETE      | ".../orders/{id}"            |
| Adicionar itens no Pedido      | POST        | ".../orders/{id}/items"      |
| Remover item do Pedido         | DELETE      | ".../orders/{id}/items"      |

## Inspiração

Além do material fornecido durante o curso através da DIO foram utilizadas outras fontes de informações das quais gostaria de destacar:

* [Fernanda Kipper](https://github.com/Fernanda-Kipper) - Através do vídeo [Autenticação e Autorização com Spring Security e JWT Tokens](https://www.youtube.com/watch?v=5w-YCcOjPD0&t=2216s) 
* [Nelio Alves](https://github.com/acenelio) - Através do curso  [Java COMPLETO 2023 Programação Orientada a Objetos +Projetos](https://www.udemy.com/course/java-curso-completo/) 