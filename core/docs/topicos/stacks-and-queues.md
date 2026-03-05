# Stacks and Queues

## Origem
Modelos clássicos de acesso restrito: LIFO (stack) e FIFO (queue), usados desde compiladores iniciais e sistemas operacionais.

## Como funciona
- Stack: último que entra é o primeiro que sai (`push/pop`).
- Queue: primeiro que entra é o primeiro que sai (`enqueue/dequeue`).

## Problemas que resolve
- Stack: avaliação de expressões, undo/redo, DFS
- Queue: agendamento, buffering, BFS

## Big O
- Stack (`push/pop/top`): `O(1)`
- Queue (`enqueue/dequeue/front`): `O(1)` com implementação adequada

## Referências (livros)
- CLRS, estruturas elementares
- Sedgewick & Wayne, *Algorithms*
- Goodrich et al., *Data Structures and Algorithms in Java*
