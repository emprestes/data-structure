# Searching

## Origem
Busca é um problema fundamental em computação desde arquivos indexados e tabelas simbólicas iniciais.

## Como funciona
Dado um conjunto e um alvo, retorna posição/elemento correspondente ou ausência.
Estratégias variam por estrutura: varredura linear, hashing, árvores, índices.

## Problemas que resolve
- Encontrar registros rapidamente
- Verificar existência de itens
- Base para consulta em sistemas e bancos

## Big O (típico)
- Busca linear em array/lista: `O(n)`
- Busca em hash map/set: média `O(1)`, pior `O(n)`
- Busca em árvore balanceada: `O(log n)`

## Referências (livros)
- CLRS, cap. 11 e 12
- Sedgewick & Wayne, *Algorithms*, capítulos de symbol tables/search
- Skiena, *The Algorithm Design Manual*
