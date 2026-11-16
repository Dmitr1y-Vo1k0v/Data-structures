import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();

        System.out.println("=== Тестирование базовых операций стека ===");
        System.out.println("Стек пуст? " + stack.isEmpty());

        // Добавляем элементы на вершину стека
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Размер стека после добавления 3 элементов: " + stack.size());
        System.out.println("Верхний элемент (peek): " + stack.peek());

        // Извлекаем элементы в порядке LIFO (30 -> 20 -> 10)
        System.out.println("Извлечен элемент (pop): " + stack.pop());
        System.out.println("Извлечен элемент (pop): " + stack.pop());
        System.out.println("Текущий верхний элемент (peek): " + stack.peek());
        System.out.println("Оставшийся размер стека: " + stack.size());

        // Очищаем стек и проверяем обработку ошибки при пустом стеке
        stack.pop();
        try {
            stack.pop();
        } catch (NoSuchElementException e) {
            System.out.println("Перехвачено ожидаемое исключение: " + e.getMessage());
        }

        System.out.println("\n=== Пример из лекции: калькулятор обратной польской записи ===");
        String expression = "4 2 + 3 *"; // (4 + 2) * 3 = 18
        System.out.println("Выражение: \"" + expression + "\" -> Результат: " + evaluateRPN(expression));
    }

    /**
     * Вычисляет значение арифметического выражения в обратной польской записи с помощью стека.
     */
    private static int evaluateRPN(String expression) {
        MyStack<Integer> values = new MyStack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            switch (token) {
                case "+":
                    values.push(values.pop() + values.pop());
                    break;
                case "-": {
                    int right = values.pop();
                    int left = values.pop();
                    values.push(left - right);
                    break;
                }
                case "*":
                    values.push(values.pop() * values.pop());
                    break;
                case "/": {
                    int right = values.pop();
                    int left = values.pop();
                    values.push(left / right);
                    break;
                }
                default:
                    // Если токен не оператор, преобразуем его в число и кладем в стек
                    values.push(Integer.parseInt(token));
                    break;
            }
        }
        return values.pop();
    }
}
