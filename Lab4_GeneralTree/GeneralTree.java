import java.util.function.Consumer;

/**
 * Реализация обобщенного дерева с произвольным количеством потомков у каждого узла (N-арное дерево).
 * Реализовано на чистой Java без использования стандартных коллекций (java.util.*).
 */
public class GeneralTree<T> {

    /**
     * Класс, представляющий отдельный узел дерева.
     * Каждый узел хранит свое значение и собственный динамический массив дочерних узлов.
     */
    public static class TreeNode<T> {
        private T value;
        private TreeNode<T>[] children;
        private int childCount;

        @SuppressWarnings("unchecked")
        public TreeNode(T value) {
            this.value = value;
            this.children = (TreeNode<T>[]) new TreeNode[4]; // Начальная емкость массива потомков
            this.childCount = 0;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getChildCount() {
            return childCount;
        }

        /**
         * Возвращает дочерний узел по индексу.
         */
        public TreeNode<T> getChild(int index) {
            if (index < 0 || index >= childCount) {
                throw new IndexOutOfBoundsException("Некорректный индекс потомка: " + index);
            }
            return children[index];
        }

        /**
         * Добавляет нового потомка к текущему узлу.
         * При необходимости автоматически расширяет внутренний массив в 2 раза.
         */
        public TreeNode<T> addChild(T childValue) {
            TreeNode<T> childNode = new TreeNode<>(childValue);
            addChild(childNode);
            return childNode;
        }

        public void addChild(TreeNode<T> childNode) {
            if (childNode == null) {
                throw new IllegalArgumentException("Дочерний узел не может быть null.");
            }
            if (childCount == children.length) {
                growChildrenArray();
            }
            children[childCount++] = childNode;
        }

        /**
         * Удаляет дочерний узел по ссылке со сдвигом оставшихся элементов влево.
         */
        public boolean removeChild(TreeNode<T> target) {
            for (int i = 0; i < childCount; i++) {
                if (children[i] == target) {
                    for (int j = i; j < childCount - 1; j++) {
                        children[j] = children[j + 1];
                    }
                    children[--childCount] = null;
                    return true;
                }
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        private void growChildrenArray() {
            TreeNode<T>[] newArray = (TreeNode<T>[]) new TreeNode[children.length * 2];
            System.arraycopy(children, 0, newArray, 0, childCount);
            children = newArray;
        }
    }

    private TreeNode<T> root; // Корневой узел дерева

    public GeneralTree() {
        this.root = null;
    }

    public GeneralTree(T rootValue) {
        this.root = new TreeNode<>(rootValue);
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Ищет первый узел с заданным значением с помощью обхода в глубину (DFS).
     */
    public TreeNode<T> find(T value) {
        return findRecursive(root, value);
    }

    private TreeNode<T> findRecursive(TreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }
        if ((value == null && current.value == null) || (value != null && value.equals(current.value))) {
            return current;
        }
        for (int i = 0; i < current.childCount; i++) {
            TreeNode<T> found = findRecursive(current.children[i], value);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    /**
     * Прямой обход дерева в глубину (Pre-Order DFS): сначала родитель, затем все его потомки.
     */
    public void traversePreOrder(Consumer<T> action) {
        traversePreOrderRecursive(root, action);
    }

    private void traversePreOrderRecursive(TreeNode<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }
        action.accept(node.value);
        for (int i = 0; i < node.childCount; i++) {
            traversePreOrderRecursive(node.children[i], action);
        }
    }

    /**
     * Подсчитывает общее количество узлов во всем дереве.
     */
    public int size() {
        return countNodes(root);
    }

    private int countNodes(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int total = 1;
        for (int i = 0; i < node.childCount; i++) {
            total += countNodes(node.children[i]);
        }
        return total;
    }

    /**
     * Выводит структуру дерева в консоль с отступами по уровням вложенности.
     */
    public void printTree() {
        printRecursive(root, 0);
    }

    private void printRecursive(TreeNode<T> node, int depth) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("- " + node.value);
        for (int i = 0; i < node.childCount; i++) {
            printRecursive(node.children[i], depth + 1);
        }
    }
}
