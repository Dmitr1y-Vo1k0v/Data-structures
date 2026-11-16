import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация обобщенного двусвязного списка (LinkedList) на чистой Java.
 * Не использует стандартные коллекции Java (java.util.LinkedList / ArrayList).
 */
public class MyLinkedList<T> implements Iterable<T> {

    // Внутренний класс, представляющий один узел списка
    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> head; // Указатель на первый узел списка
    private Node<T> tail; // Указатель на последний узел списка
    private int count;    // Количество элементов в списке

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Добавляет новый элемент в начало списка.
     * Сложность: O(1)
     */
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        count++;
    }

    /**
     * Добавляет новый элемент в конец списка.
     * Сложность: O(1)
     */
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        count++;
    }

    /**
     * Удаляет первый элемент списка и возвращает его значение.
     * Сложность: O(1)
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Невозможно удалить элемент: список пуст.");
        }

        T removedValue = head.value;
        head = head.next;
        count--;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }

        return removedValue;
    }

    /**
     * Удаляет последний элемент списка и возвращает его значение.
     * Сложность: O(1)
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Невозможно удалить элемент: список пуст.");
        }

        T removedValue = tail.value;
        tail = tail.prev;
        count--;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return removedValue;
    }

    /**
     * Удаляет первое вхождение указанного значения из списка.
     * Возвращает true, если элемент был найден и удален.
     * Сложность: O(n)
     */
    public boolean remove(T item) {
        Node<T> current = head;

        while (current != null) {
            boolean isMatch = (item == null && current.value == null)
                    || (item != null && item.equals(current.value));

            if (isMatch) {
                if (current == head) {
                    removeFirst();
                } else if (current == tail) {
                    removeLast();
                } else {
                    // Связываем соседние узлы напрямую друг с другом, исключая current
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    count--;
                }
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Проверяет, содержится ли элемент в списке.
     * Сложность: O(n)
     */
    public boolean contains(T item) {
        Node<T> current = head;
        while (current != null) {
            if ((item == null && current.value == null)
                    || (item != null && item.equals(current.value))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Возвращает количество элементов в списке.
     * Сложность: O(1)
     */
    public int size() {
        return count;
    }

    /**
     * Проверяет, пуст ли список.
     * Сложность: O(1)
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Полностью очищает список.
     * Сложность: O(1)
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    /**
     * Возвращает итератор для последовательного перебора элементов (for-each).
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(" <-> ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
