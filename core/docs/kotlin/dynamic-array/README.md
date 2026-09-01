# Array dinâmico em Kotlin

Um array comum reserva um bloco de tamanho fixo. O array dinâmico mantém essa
vantagem — acesso direto por índice — mas troca o bloco por outro maior quando
fica cheio.

## Modelo mental

Há duas medidas independentes:

- **tamanho (`size`)**: quantos elementos pertencem à coleção;
- **capacidade (`capacity`)**: quantas posições o bloco atual comporta.

Ao inserir em um array cheio, a implementação cria um bloco com o dobro da
capacidade e copia os elementos. O crescimento geométrico evita copiar tudo a
cada inserção.

```text
size = 3, capacity = 4

índice:       0      1      2      3
armazenado: [ A ]  [ B ]  [ C ]  [   ]
```

## Operações e complexidade

| Operação | Tempo | Motivo |
| --- | --- | --- |
| consultar ou substituir por índice | O(1) | o endereço é calculado diretamente |
| adicionar ao final | O(1) amortizado | a maioria das inserções não copia o bloco |
| inserir ou remover no meio | O(n) | elementos precisam ser deslocados |
| procurar por valor | O(n) | no pior caso, é preciso percorrer todos |
| copiar para uma lista | O(n) | cada elemento entra no novo snapshot |

O espaço usado é O(n). Logo após um crescimento, parte dele fica reservada mas
ainda vazia; esse é o custo que compra inserções rápidas no final.

## Exemplo

```kotlin
val names = DynamicArray<String>(initialCapacity = 2)
names.add("Ada")
names.add("Edsger")
names.add(1, "Grace")

println(names[1])       // Grace
println(names.toList()) // [Ada, Grace, Edsger]
```

## Decisões didáticas

- O armazenamento é `Array<Any?>`, pois a JVM não permite criar diretamente
  um array de um tipo genérico apagado em tempo de execução.
- `MutableList` não é usada internamente: delegar a ela esconderia justamente
  a realocação e os deslocamentos que queremos estudar.
- Valores nulos são aceitos. O `null` fora da faixa lógica não é observável e
  também libera referências removidas para o coletor de lixo.

## Onde está o código

- Implementação: `core/src/main/kotlin/emprestes/ds/kotlin/array/DynamicArray.kt`
- Testes: `core/src/test/kotlin/emprestes/ds/kotlin/array/DynamicArrayTest.kt`
- API HTML: execute `./gradlew :core:dokkaGeneratePublicationHtml`
