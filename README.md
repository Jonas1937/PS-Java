## Requisitos:
    - JDK 1.8
## Executar Aplicação:
  -  Para executar a aplicação basta utilizar o seguinte comando no terminal ou prompt de comando:
```sh
  mvn spring-boot:run
  ```
## Instruções

*[GET]*   **./cart**  "Retorna todos os produtos salvos no banco de dados"

*[GET]*   **./cart/valor**  "Retorna os produtos do carrinho na ordem informada, que e definida ao substituir o nome [valor] pelo tipo, que pode ser **name**,**score** ou **price**"

*[GET]*   **./cart/checkout**  "Retorna os dados de check out, que são preço dos produtos, preço do frete, e preço total"

*[POST]*  **./cart/**  "Envia-se um produto pelo Body da requisição, para que este seja adicionado ao carrinho."

*[POST]*  **./**  "Envia-se um produto pelo Body da requisição, para que este seja adicionado ao banco de dados"

*[DELETE]*   **./cart**  "Envia-se um produto pelo Body da requisição, para que este seja removido do carrinho."


## Razoes de usar Spring Boot:
 
 - Rapidez e eficiência em desenvolvimento de API, com varias bibliotecas prontas e auto configuradas, o que facilita e deixa o desenvolvimento ao todo bem menos complexo.
 - Fácil roteamento, tem mais facilidade em possíveis manutenções futuras.

