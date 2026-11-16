public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        // Строим дерево из примера в лекции
        int[] values = {8, 4, 10, 2, 6, 3, 7};
        for (int v : values) {
            tree.add(v);
        }

        System.out.println("Количество элементов в дереве: " + tree.size());

        System.out.print("Инфиксный обход (In-Order, по возрастанию): ");
        tree.inOrderTraversal(val -> System.out.print(val + " "));
        System.out.println();

        System.out.print("Префиксный обход (Pre-Order): ");
        tree.preOrderTraversal(val -> System.out.print(val + " "));
        System.out.println();

        System.out.print("Постфиксный обход (Post-Order): ");
        tree.postOrderTraversal(val -> System.out.print(val + " "));
        System.out.println();

        // Проверка поиска элементов
        System.out.println("\nСодержит 6? " + tree.contains(6));
        System.out.println("Содержит 99? " + tree.contains(99));

        // Удаление узла с двумя поддеревьями (корень 8)
        System.out.println("\nУдаляем узел 8: " + tree.remove(8));
        System.out.println("Новый размер дерева: " + tree.size());
        System.out.print("Дерево после удаления 8 (In-Order): ");
        tree.inOrderTraversal(val -> System.out.print(val + " "));
        System.out.println();
    }
}
