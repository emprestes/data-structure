# Dynamic Programming and Memoization

## Origem
Formalizada por Richard Bellman nos anos 1950 para otimização e decisão sequencial.

## Como funciona
- DP: quebra em subproblemas sobrepostos com subestrutura ótima.
- Memoization: versão top-down com cache.
- Tabulation: versão bottom-up preenchendo tabela.

## Problemas que resolve
- Fibonacci eficiente
- Knapsack, LIS, Edit Distance
- Caminhos mínimos e contagem combinatória

## Big O
Depende do número de estados e transições.
Regra prática: `O(estados * transições_por_estado)`.

## Referências (livros)
- CLRS, cap. 14 e 15
- Kleinberg & Tardos, *Algorithm Design* (DP)
- Skiena, *The Algorithm Design Manual*
