import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Тест 1: Сортировка массива целых чисел из примера в лекции
        Integer[] numbers = {3, 7, 4, 4, 6, 5, 8};
        System.out.println("Исходный массив чисел:      " + Arrays.toString(numbers));

        QuickSort.sort(numbers);
        System.out.println("Отсортированный массив:     " + Arrays.toString(numbers));

        // Тест 2: Сортировка массива с отрицательными и дублирующимися числами
        Integer[] mixedNumbers = {10, -5, 0, 42, 7, -5, 19, 3};
        System.out.println("\nИсходный смешанный массив:  " + Arrays.toString(mixedNumbers));

        QuickSort.sort(mixedNumbers);
        System.out.println("Отсортированный массив:     " + Arrays.toString(mixedNumbers));

        // Тест 3: Сортировка массива строк в алфавитном порядке
        String[] words = {"Queue", "Stack", "BinaryTree", "Array", "Set", "List"};
        System.out.println("\nИсходный массив строк:      " + Arrays.toString(words));

        QuickSort.sort(words);
        System.out.println("Отсортированный массив:     " + Arrays.toString(words));
    }
}
