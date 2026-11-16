import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        System.out.println("=== Тестирование базовых операций очереди (FIFO) ===");
        System.out.println("Очередь пуста? " + queue.isEmpty());

        // Добавляем элементы в конец очереди
        queue.enqueue("Первый документ");
        queue.enqueue("Второй документ");
        queue.enqueue("Третий документ");

        System.out.println("Размер очереди после добавления 3 элементов: " + queue.size());
        System.out.println("Первый в очереди (peek): " + queue.peek());

        // Извлекаем элементы в порядке поступления (FIFO)
        System.out.println("Обработан (dequeue): " + queue.dequeue());
        System.out.println("Обработан (dequeue): " + queue.dequeue());
        System.out.println("Следующий на очереди (peek): " + queue.peek());
        System.out.println("Оставшийся размер очереди: " + queue.size());

        // Извлекаем последний элемент и проверяем исключение на пустой очереди
        System.out.println("Обработан (dequeue): " + queue.dequeue());
        try {
            queue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Перехвачено ожидаемое исключение: " + e.getMessage());
        }
    }
}
