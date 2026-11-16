/**
 * Реализация алгоритма быстрой сортировки (Quick Sort) на чистой Java.
 * Сложность по времени: O(n log n) в среднем, O(n^2) в худшем случае.
 * Дополнительная память: O(log n) на стек рекурсивных вызовов.
 */
public class QuickSort {

    /**
     * Сортирует переданный массив по возрастанию.
     */
    public static <T extends Comparable<T>> void sort(T[] items) {
        if (items == null || items.length <= 1) {
            return;
        }
        quickSort(items, 0, items.length - 1);
    }

    // Рекурсивно разделяет массив относительно опорного элемента и сортирует обе части
    private static <T extends Comparable<T>> void quickSort(T[] items, int left, int right) {
        if (left < right) {
            // Выбираем средний элемент в качестве опорного (pivot)
            int pivotIndex = left + (right - left) / 2;
            int newPivot = partition(items, left, right, pivotIndex);

            // Рекурсивно сортируем левую и правую части вокруг опорного элемента
            quickSort(items, left, newPivot - 1);
            quickSort(items, newPivot + 1, right);
        }
    }

    /**
     * Перемещает элементы меньше опорного влево, а больше или равные — вправо.
     * Возвращает итоговый индекс опорного элемента.
     */
    private static <T extends Comparable<T>> int partition(T[] items, int left, int right, int pivotIndex) {
        T pivotValue = items[pivotIndex];

        // Временно перемещаем опорный элемент в конец рассматриваемого диапазона
        swap(items, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (items[i].compareTo(pivotValue) < 0) {
                swap(items, i, storeIndex);
                storeIndex++;
            }
        }

        // Возвращаем опорный элемент на его окончательную отсортированную позицию
        swap(items, storeIndex, right);
        return storeIndex;
    }

    // Вспомогательный метод обмена двух элементов массива местами
    private static <T> void swap(T[] items, int left, int right) {
        if (left != right) {
            T temp = items[left];
            items[left] = items[right];
            items[right] = temp;
        }
    }
}
