import java.util.function.Consumer;

/**
 * Реализация обобщенного двоичного дерева поиска (Binary Search Tree) на чистой Java.
 * Поддерживает вставку, поиск, удаление узлов (все 3 случая) и три вида обхода.
 */
public class BinaryTree<T extends Comparable<T>> {

    // Внутренний класс, представляющий узел бинарного дерева
    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    // Вспомогательный контейнер для возврата найденного узла и его родителя
    private static class SearchResult<T> {
        Node<T> current;
        Node<T> parent;

        SearchResult(Node<T> current, Node<T> parent) {
            this.current = current;
            this.parent = parent;
        }
    }

    private Node<T> head; // Корневой узел дерева
    private int count;    // Количество узлов в дереве

    public BinaryTree() {
        this.head = null;
        this.count = 0;
    }

    /**
     * Добавляет новое значение в дерево на корректную позицию.
     * Сложность: O(log n) в среднем, O(n) в худшем случае.
     */
    public void add(T value) {
        if (head == null) {
            head = new Node<>(value);
        } else {
            addTo(head, value);
        }
        count++;
    }

    // Рекурсивный поиск подходящего места для вставки нового узла
    private void addTo(Node<T> node, T value) {
        if (value.compareTo(node.value) < 0) {
            // Значение меньше текущего узла -> идем в левое поддерево
            if (node.left == null) {
                node.left = new Node<>(value);
            } else {
                addTo(node.left, value);
            }
        } else {
            // Значение больше или равно текущему узлу -> идем в правое поддерево
            if (node.right == null) {
                node.right = new Node<>(value);
            } else {
                addTo(node.right, value);
            }
        }
    }

    /**
     * Проверяет, содержится ли указанное значение в дереве.
     * Сложность: O(log n) в среднем, O(n) в худшем случае.
     */
    public boolean contains(T value) {
        return findWithParent(value).current != null;
    }

    // Находит первый узел с заданным значением и возвращает его вместе с родителем
    private SearchResult<T> findWithParent(T value) {
        Node<T> current = head;
        Node<T> parent = null;

        while (current != null) {
            int result = current.value.compareTo(value);
            if (result > 0) {
                parent = current;
                current = current.left;
            } else if (result < 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Узел найден
            }
        }
        return new SearchResult<>(current, parent);
    }

    /**
     * Удаляет первый узел с заданным значением из дерева.
     * Возвращает true, если элемент был найден и удален.
     */
    public boolean remove(T value) {
        SearchResult<T> found = findWithParent(value);
        Node<T> current = found.current;
        Node<T> parent = found.parent;

        if (current == null) {
            return false;
        }

        count--;

        // Случай 1: У удаляемого узла нет правого потомка — левый потомок встает на его место
        if (current.right == null) {
            replaceNodeInParent(parent, current, current.left);
        }
        // Случай 2: У правого потомка нет левых детей — он занимает место удаляемого узла
        else if (current.right.left == null) {
            current.right.left = current.left;
            replaceNodeInParent(parent, current, current.right);
        }
        // Случай 3: У правого потомка есть левые дети — заменяем удаляемый узел крайним левым из правого поддерева
        else {
            Node<T> leftmost = current.right.left;
            Node<T> leftmostParent = current.right;

            while (leftmost.left != null) {
                leftmostParent = leftmost;
                leftmost = leftmost.left;
            }

            // Правое поддерево крайнего левого узла переходит к его родителю
            leftmostParent.left = leftmost.right;

            // Крайний левый узел перенимает детей удаляемого узла
            leftmost.left = current.left;
            leftmost.right = current.right;

            replaceNodeInParent(parent, current, leftmost);
        }

        return true;
    }

    // Вспомогательный метод для обновления ссылки у родительского узла (или корня)
    private void replaceNodeInParent(Node<T> parent, Node<T> oldNode, Node<T> newNode) {
        if (parent == null) {
            head = newNode;
        } else {
            int result = parent.value.compareTo(oldNode.value);
            if (result > 0) {
                parent.left = newNode;
            } else if (result < 0) {
                parent.right = newNode;
            }
        }
    }

    /**
     * Префиксный обход (Pre-Order): Корень -> Левое поддерево -> Правое поддерево.
     */
    public void preOrderTraversal(Consumer<T> action) {
        preOrder(action, head);
    }

    private void preOrder(Consumer<T> action, Node<T> node) {
        if (node != null) {
            action.accept(node.value);
            preOrder(action, node.left);
            preOrder(action, node.right);
        }
    }

    /**
     * Инфиксный обход (In-Order): Левое поддерево -> Корень -> Правое поддерево.
     * Выводит элементы дерева поиска в отсортированном порядке.
     */
    public void inOrderTraversal(Consumer<T> action) {
        inOrder(action, head);
    }

    private void inOrder(Consumer<T> action, Node<T> node) {
        if (node != null) {
            inOrder(action, node.left);
            action.accept(node.value);
            inOrder(action, node.right);
        }
    }

    /**
     * Постфиксный обход (Post-Order): Левое поддерево -> Правое поддерево -> Корень.
     */
    public void postOrderTraversal(Consumer<T> action) {
        postOrder(action, head);
    }

    private void postOrder(Consumer<T> action, Node<T> node) {
        if (node != null) {
            postOrder(action, node.left);
            postOrder(action, node.right);
            action.accept(node.value);
        }
    }

    /**
     * Возвращает количество узлов в дереве.
     */
    public int size() {
        return count;
    }

    /**
     * Полностью очищает дерево.
     */
    public void clear() {
        head = null;
        count = 0;
    }
}
