public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        System.out.println("=== Тестирование двусвязного списка ===");
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);

        System.out.println("Список после добавлений: " + list);
        System.out.println("Размер списка: " + list.size());

        // Проверка поиска элементов
        System.out.println("Содержит 20? " + list.contains(20));
        System.out.println("Содержит 99? " + list.contains(99));

        // Удаление из середины, начала и конца
        System.out.println("\nУдаляем элемент 20: " + list.remove(20));
        System.out.println("Список после удаления 20: " + list);

        System.out.println("Удален первый элемент: " + list.removeFirst());
        System.out.println("Удален последний элемент: " + list.removeLast());
        System.out.println("Итоговый список: " + list + " (размер: " + list.size() + ")");

        // Перебор через цикл for-each с помощью реализованного итератора
        list.addLast(100);
        list.addLast(200);
        System.out.print("\nПеребор элементов через for-each: ");
        for (int item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
