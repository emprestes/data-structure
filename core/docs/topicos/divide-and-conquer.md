# Divide and Conquer

## Origem
Paradigma matemático e algorítmico consolidado por algoritmos como Merge Sort, Quick Sort e Karatsuba.

## Como funciona
1. Divide o problema em subproblemas menores.
2. Resolve recursivamente.
3. Combina os resultados.

## Problemas que resolve
- Ordenação e seleção
- Multiplicação e operações numéricas rápidas
- Processamento paralelo natural

## Big O (geral)
Frequentemente modelado por recorrência `T(n) = aT(n/b) + f(n)` (Teorema Mestre).
Exemplo clássico: Merge Sort `O(n log n)`.

## Referências (livros)
- CLRS, cap. 4 (recorrências) e cap. 2/7/9
- Kleinberg & Tardos, *Algorithm Design*
- Dasgupta, Papadimitriou, Vazirani, *Algorithms*
