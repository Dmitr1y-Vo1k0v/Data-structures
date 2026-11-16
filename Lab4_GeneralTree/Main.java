public class Main {
    public static void main(String[] args) {
        // Создаем дерево с корнем (пример организационной структуры из лекции)
        GeneralTree<String> companyTree = new GeneralTree<>("Board of Directors");
        GeneralTree.TreeNode<String> root = companyTree.getRoot();

        // Добавляем трех непосредственных потомков к корню
        GeneralTree.TreeNode<String> ceo = root.addChild("CEO");
        GeneralTree.TreeNode<String> coo = root.addChild("COO");
        GeneralTree.TreeNode<String> cfo = root.addChild("CFO");

        // Добавляем потомков второго и третьего уровней
        GeneralTree.TreeNode<String> president = ceo.addChild("President");
        president.addChild("Sales");
        president.addChild("Marketing");

        cfo.addChild("Payroll");
        cfo.addChild("Billing");

        System.out.println("=== Структура дерева с произвольным числом потомков ===");
        companyTree.printTree();
        System.out.println("\nВсего узлов в дереве: " + companyTree.size());

        // Проверка поиска узла и добавления в него нового потомка
        GeneralTree.TreeNode<String> foundCoo = companyTree.find("COO");
        if (foundCoo != null) {
            foundCoo.addChild("Operations Manager");
            foundCoo.addChild("Logistics");
        }

        System.out.println("\n=== Дерево после добавления отделов к COO ===");
        companyTree.printTree();

        // Прямой обход всех узлов
        System.out.print("\nОбход всех узлов (Pre-Order): ");
        companyTree.traversePreOrder(val -> System.out.print("[" + val + "] "));
        System.out.println();
    }
}
