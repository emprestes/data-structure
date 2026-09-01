package emprestes.ds.kotlin.array

/**
 * Array redimensionável implementado sobre um bloco contíguo de referências.
 *
 * A estrutura separa dois conceitos que costumam ser confundidos:
 * [size] é a quantidade de elementos visíveis, enquanto [capacity] é o número
 * de posições já reservado no armazenamento interno. Quando uma inserção não
 * cabe, a capacidade dobra e os elementos são copiados para um novo bloco.
 * Por isso, acrescentar ao final custa O(1) amortizado, embora a operação que
 * efetivamente cresce o array custe O(n).
 *
 * Esta implementação aceita valores nulos e não usa [MutableList] internamente,
 * para deixar explícita a mecânica da estrutura de dados.
 *
 * @param E tipo dos elementos armazenados.
 * @property initialCapacity quantidade inicial de posições reservadas; deve ser positiva.
 */
class DynamicArray<E>(initialCapacity: Int = DEFAULT_CAPACITY) : Iterable<E> {
    private var elements: Array<Any?>

    /** Quantidade de elementos atualmente armazenados. */
    var size: Int = 0
        private set

    /** Quantidade de elementos que cabe sem uma nova realocação. */
    val capacity: Int
        get() = elements.size

    /** Indica se a estrutura não contém elementos. */
    val isEmpty: Boolean
        get() = size == 0

    init {
        require(initialCapacity > 0) { "initialCapacity must be greater than zero" }
        elements = arrayOfNulls(initialCapacity)
    }

    /**
     * Acrescenta [element] ao final.
     *
     * Complexidade: O(1) amortizado e O(n) no pior caso, quando há crescimento.
     */
    fun add(element: E) {
        ensureCapacity(size + 1)
        elements[size++] = element
    }

    /**
     * Insere [element] em [index], deslocando os sucessores uma posição à direita.
     *
     * [index] pode ser igual a [size], caso em que a operação equivale a [add].
     * Complexidade: O(n).
     */
    fun add(index: Int, element: E) {
        checkPositionIndex(index)
        ensureCapacity(size + 1)
        elements.copyInto(elements, destinationOffset = index + 1, startIndex = index, endIndex = size)
        elements[index] = element
        size++
    }

    /** Retorna o elemento em [index]. Complexidade: O(1). */
    operator fun get(index: Int): E {
        checkElementIndex(index)
        return elementAt(index)
    }

    /** Substitui e retorna o valor anterior em [index]. Complexidade: O(1). */
    operator fun set(index: Int, element: E): E {
        checkElementIndex(index)
        val previous = elementAt(index)
        elements[index] = element
        return previous
    }

    /**
     * Remove e retorna o elemento em [index], fechando o espaço deixado.
     *
     * Complexidade: O(n), por causa do deslocamento dos sucessores.
     */
    fun removeAt(index: Int): E {
        checkElementIndex(index)
        val removed = elementAt(index)
        val lastIndex = size - 1
        if (index < lastIndex) {
            elements.copyInto(elements, destinationOffset = index, startIndex = index + 1, endIndex = size)
        }
        elements[lastIndex] = null
        size--
        return removed
    }

    /** Remove todos os elementos sem reduzir a capacidade reservada. Complexidade: O(n). */
    fun clear() {
        elements.fill(null, fromIndex = 0, toIndex = size)
        size = 0
    }

    /** Cria uma lista somente leitura com os elementos atuais. Complexidade: O(n). */
    fun toList(): List<E> = List(size) { elementAt(it) }

    /** Itera na ordem dos índices, do zero até [size] - 1. */
    override fun iterator(): Iterator<E> = object : Iterator<E> {
        private var cursor = 0

        override fun hasNext(): Boolean = cursor < size

        override fun next(): E {
            if (!hasNext()) throw NoSuchElementException()
            return elementAt(cursor++)
        }
    }

    private fun ensureCapacity(requiredCapacity: Int) {
        if (requiredCapacity <= capacity) return
        var newCapacity = capacity
        while (newCapacity < requiredCapacity) newCapacity *= GROWTH_FACTOR
        elements = elements.copyOf(newCapacity)
    }

    @Suppress("UNCHECKED_CAST")
    private fun elementAt(index: Int): E = elements[index] as E

    private fun checkElementIndex(index: Int) {
        if (index !in 0 until size) {
            throw IndexOutOfBoundsException("index=$index, size=$size")
        }
    }

    private fun checkPositionIndex(index: Int) {
        if (index !in 0..size) {
            throw IndexOutOfBoundsException("index=$index, size=$size")
        }
    }

    private companion object {
        const val DEFAULT_CAPACITY = 10
        const val GROWTH_FACTOR = 2
    }
}
