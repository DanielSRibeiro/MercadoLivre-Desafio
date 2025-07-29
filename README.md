# 📦 Desafio do Mercado Livre

Este projeto foi desenvolvido como parte de um desafio técnico proposto pelo Mercado Livre. O objetivo principal é criar um aplicativo que consuma a API pública da empresa, exibindo produtos com base em uma busca do usuário.

## 📌 Tecnologias Utilizadas

- Kotlin
- Jetpack Compose
- Navigation e Paging3
- Arquitetura MVVM + Clean Architecture
- Dagger Hilt
- Room e Shared Preferences
- Coroutines e Flow
- Testes unitários com JUnit e Mockito

## ⚠️ Problemas com Permissão de API

Durante o desenvolvimento, foram encontrados problemas ao consumir os endpoints da API do Mercado Livre:

- O endpoint `https://api.mercadolibre.com/products/search` retornava `403 Forbidden`, mesmo com o token de acesso configurado corretamente.
- O endpoint de detalhes (`/items`) também retornava **"Access to the requested resource is forbidden"**, impedindo o carregamento de informações adicionais dos produtos.

Eles liberaram eu usar o endpoint de catálogo.

## Screenshot

<img src="imagens\tela1.jpeg" width="200"> <img src="imagens\tela2.jpeg" width="200"> <img src="imagens\tela3.jpeg" width="200">
