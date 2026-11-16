import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация обобщенной структуры данных Множество (Set) на чистой Java.
 * Хранит только уникальные элементы и поддерживает классические операции над множествами.
 * Не использует стандартные коллекции Java (java.util.*).
 */
public class MySet<T> implements Iterable<T> {

    private T[] items; // Внутренний массив для хранения уникальных элементов
    private int count; // Текущее количество элементов в множестве

    @SuppressWarnings("unchecked")
    public MySet() {
        this.items = (T[]) new Object[8];
        this.count = 0;
    }

    /**
     * Конструктор копирования: создает новое множество на основе существующего.
     */
    @SuppressWarnings("unchecked")
    public MySet(MySet<T> other) {
        if (other == null) {
            throw new IllegalArgumentException("Исходное множество не может быть null.");
        }
        this.items = (T[]) new Object[Math.max(8, other.count * 2)];
        this.count = other.count;
        System.arraycopy(other.items, 0, this.items, 0, other.count);
    }

    /**
     * Добавляет элемент в множество.
     * Если элемент уже присутствует, выбрасывает исключение (согласно спецификации из лекции).
     * Сложность: O(n)
     */
    public void add(T item) {
        if (contains(item)) {
            throw new IllegalStateException("Элемент '" + item + "' уже существует в множестве.");
        }
        if (count == items.length) {
            growArray();
        }
        items[count++] = item;
    }

    /**
     * Добавляет несколько элементов из переданного массива в множество.
     * Сложность: O(m * n)
     */
    public void addRange(T[] newItems) {
        if (newItems == null) {
            throw new IllegalArgumentException("Массив элементов не может быть null.");
        }
        for (T item : newItems) {
            add(item);
        }
    }

    /**
     * Удаляет указанный элемент из множества.
     * Возвращает true, если элемент был найден и удален, иначе false.
     * Сложность: O(n)
     */
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index == -1) {
            return false;
        }
        // Сдвигаем элементы влево, закрывая образовавшийся пробел
        for (int i = index; i < count - 1; i++) {
            items[i] = items[i + 1];
        }
        items[--count] = null;
        return true;
    }

    /**
     * Проверяет, содержит ли множество указанный элемент.
     * Сложность: O(n)
     */
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    // Вспомогательный поиск индекса элемента в массиве
    private int indexOf(T item) {
        for (int i = 0; i < count; i++) {
            if ((item == null && items[i] == null) || (item != null && item.equals(items[i]))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Возвращает количество элементов в множестве.
     * Сложность: O(1)
     */
    public int size() {
        return count;
    }

    /**
     * Проверяет, пусто ли множество.
     * Сложность: O(1)
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Объединение множеств (Union): возвращает новое множество с элементами из обоих множеств.
     * Сложность: O(m * n)
     */
    public MySet<T> union(MySet<T> other) {
        MySet<T> result = new MySet<>(this);
        for (T item : other) {
            if (!contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Пересечение множеств (Intersection): возвращает элементы, присутствующие в обоих множествах.
     * Сложность: O(m * n)
     */
    public MySet<T> intersection(MySet<T> other) {
        MySet<T> result = new MySet<>();
        for (T item : this) {
            if (other.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Разность множеств (Difference): возвращает элементы текущего множества, которых нет в other.
     * Сложность: O(m * n)
     */
    public MySet<T> difference(MySet<T> other) {
        MySet<T> result = new MySet<>(this);
        for (T item : other) {
            result.remove(item);
        }
        return result;
    }

    /**
     * Симметрическая разность (Symmetric Difference): элементы, входящие только в одно из множеств.
     * Реализована через разность объединения и пересечения, как в лекции.
     */
    public MySet<T> symmetricDifference(MySet<T> other) {
        MySet<T> unionSet = union(other);
        MySet<T> intersectionSet = intersection(other);
        return unionSet.difference(intersectionSet);
    }

    /**
     * Проверяет, является ли текущее множество подмножеством переданного (other).
     */
    public boolean isSubset(MySet<T> other) {
        return difference(other).isEmpty();
    }

    @SuppressWarnings("unchecked")
    private void growArray() {
        T[] newArray = (T[]) new Object[items.length * 2];
        System.arraycopy(items, 0, newArray, 0, count);
        items = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < count;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return items[cursor++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(items[i]);
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
