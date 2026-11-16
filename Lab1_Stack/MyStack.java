import java.util.NoSuchElementException;

/**
 * Реализация обобщенной структуры данных Стек (LIFO) на базе односвязных узлов.
 * Не использует стандартные коллекции Java.
 */
public class MyStack<T> {

    // Внутренний класс, представляющий один узел стека
    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> top; // Ссылка на вершину стека
    private int count;   // Текущее количество элементов в стеке

    public MyStack() {
        this.top = null;
        this.count = 0;
    }

    /**
     * Добавляет новый элемент на вершину стека.
     * Сложность: O(1)
     */
    public void push(T value) {
        // Новый узел становится новой вершиной и ссылается на предыдущую вершину
        top = new Node<>(value, top);
        count++;
    }

    /**
     * Удаляет верхний элемент стека и возвращает его значение.
     * Сложность: O(1)
     */
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Невозможно выполнить pop: стек пуст.");
        }

        T value = top.value;
        top = top.next; // Сдвигаем вершину на следующий узел
        count--;
        return value;
    }

    /**
     * Возвращает значение верхнего элемента без его удаления из стека.
     * Сложность: O(1)
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Невозможно выполнить peek: стек пуст.");
        }

        return top.value;
    }

    /**
     * Возвращает текущее количество элементов в стеке.
     * Сложность: O(1)
     */
    public int size() {
        return count;
    }

    /**
     * Проверяет, пуст ли стек.
     * Сложность: O(1)
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Очищает стек.
     * Сложность: O(1)
     */
    public void clear() {
        top = null;
        count = 0;
    }
}
