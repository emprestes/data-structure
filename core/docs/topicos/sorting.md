# Sorting

## Origem
Ordenação é estudada formalmente desde os primeiros compiladores e sistemas de processamento de dados (décadas de 1950-60). Clássicos como Merge Sort, Quick Sort e Heap Sort consolidaram a área.

## Como funciona
Reorganiza elementos para uma ordem total (crescente/decrescente), usando comparações ou distribuição por chave.

## Problemas que resolve
- Preparar dados para busca eficiente
- Facilitar deduplicação e merge
- Permitir relatórios/consultas ordenadas

## Big O (típico)
- Bubble/Insertion: melhor `O(n)`, médio/pior `O(n^2)`
- Merge Sort: `O(n log n)` (estável)
- Quick Sort: médio `O(n log n)`, pior `O(n^2)`
- Heap Sort: `O(n log n)`

## Referências (livros)
- Cormen et al., *Introduction to Algorithms* (CLRS), cap. 2, 6, 7, 8
- Sedgewick & Wayne, *Algorithms*, capítulos de sorting
- Knuth, *The Art of Computer Programming*, Vol. 3
