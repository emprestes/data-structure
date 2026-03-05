# Greedy Algorithms

## Origem
Abordagem clássica de otimização local, consolidada em problemas como MST (Kruskal/Prim), Huffman e interval scheduling.

## Como funciona
Escolhe a melhor decisão local em cada passo, sem reconsiderar escolhas anteriores.

## Problemas que resolve
- Troco (em sistemas monetários canônicos)
- Seleção de intervalos
- Árvores geradoras mínimas
- Compressão (Huffman)

## Big O
Varia por estrutura usada:
- com ordenação: geralmente `O(n log n)`
- com heap: comum `O((V+E) log V)` em grafos

## Referências (livros)
- CLRS, cap. 16
- Kleinberg & Tardos, *Algorithm Design* (Greedy)
- Dasgupta et al., *Algorithms*
