# Binary Search

## Origem
A busca binária é um dos algoritmos clássicos mais antigos para listas ordenadas, amplamente documentado desde os primórdios da computação.

## Como funciona
Em um vetor ordenado, compara o alvo com o elemento do meio e descarta metade do espaço a cada passo.

## Problemas que resolve
- Busca rápida em coleções ordenadas
- Encontrar ponto de inserção
- Base para vários problemas de "resposta por decisão" (binary search on answer)

## Big O
- Tempo: `O(log n)`
- Espaço: `O(1)` iterativo, `O(log n)` recursivo (stack)

## Referências (livros)
- CLRS, cap. 2 (busca e ordenação básicas)
- Bentley, *Programming Pearls* (discussão clássica de implementação correta)
- Sedgewick & Wayne, *Algorithms*
