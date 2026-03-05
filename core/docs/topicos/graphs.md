# Graphs

## Origem
A teoria dos grafos nasceu com Euler (problema das pontes de Konigsberg) e hoje é central em computação.

## Como funciona
Modelo `V` (vértices) e `E` (arestas), direcionado ou não, ponderado ou não.
Representações comuns: lista de adjacência e matriz de adjacência.

## Problemas que resolve
- Redes (social, transporte, comunicação)
- Caminhos mínimos e roteamento
- Dependências e ordenação topológica

## Big O (base)
- Lista de adjacência: espaço `O(V + E)`
- Matriz de adjacência: espaço `O(V^2)`
- Travessia BFS/DFS: `O(V + E)`

## Referências (livros)
- CLRS, cap. 22 a 25
- Sedgewick & Wayne, *Algorithms* (graphs)
- Kleinberg & Tardos, *Algorithm Design*
