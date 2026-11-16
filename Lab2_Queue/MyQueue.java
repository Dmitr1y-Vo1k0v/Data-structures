import java.util.NoSuchElementException;

/**
 * Реализация обобщенной структуры данных Очередь (FIFO) на базе связных узлов.
 * Не использует стандартные коллекции Java.
 */
public class MyQueue<T> {

    // Внутренний класс, представляющий один элемент очереди
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head; // Начало очереди (отсюда элементы извлекаются)
    private Node<T> tail; // Конец очереди (сюда добавляются новые элементы)
    private int count;    // Текущее количество элементов в очереди

    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Добавляет элемент в конец очереди.
     * Сложность: O(1)
     */
    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            // Если очередь пуста, новый узел становится и головой, и хвостом
            head = newNode;
            tail = newNode;
        } else {
            // Прикрепляем новый узел за текущим хвостом и обновляем ссылку tail
            tail.next = newNode;
            tail = newNode;
        }
        count++;
    }

    /**
     * Удаляет первый элемент из начала очереди и возвращает его значение.
     * Сложность: O(1)
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Невозможно выполнить dequeue: очередь пуста.");
        }

        T value = head.value;
        head = head.next; // Сдвигаем начало очереди на следующий элемент
        count--;

        // Если после удаления очередь опустела, обнуляем и указатель на хвост
        if (isEmpty()) {
            tail = null;
        }

        return value;
    }

    /**
     * Возвращает значение первого элемента в очереди без его удаления.
     * Сложность: O(1)
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Невозможно выполнить peek: очередь пуста.");
        }

        return head.value;
    }

    /**
     * Возвращает текущее количество элементов в очереди.
     * Сложность: O(1)
     */
    public int size() {
        return count;
    }

    /**
     * Проверяет, пуста ли очередь.
     * Сложность: O(1)
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Полностью очищает очередь.
     * Сложность: O(1)
     */
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }
}
