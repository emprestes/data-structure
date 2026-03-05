# Hashes and Maps

## Origem
Hashing evoluiu para acelerar acesso por chave, substituindo busca linear em muitas aplicações.

## Como funciona
Aplica função hash na chave para mapear em um bucket/posição, tratando colisões por encadeamento ou endereçamento aberto.

## Problemas que resolve
- Busca e atualização por chave em alta performance
- Indexação em memória
- Contagem/frequência de elementos

## Big O
- Média: `O(1)` para put/get/remove
- Pior caso: `O(n)` (muitas colisões)

## Referências (livros)
- CLRS, cap. 11
- Sedgewick & Wayne, *Algorithms* (hash tables)
- Goodrich et al., *Data Structures and Algorithms in Java*
