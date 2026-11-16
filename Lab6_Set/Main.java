public class Main {
    public static void main(String[] args) {
        MySet<Integer> setA = new MySet<>();
        setA.addRange(new Integer[]{1, 2, 3, 4});

        MySet<Integer> setB = new MySet<>();
        setB.addRange(new Integer[]{3, 4, 5, 6});

        System.out.println("Множество A: " + setA);
        System.out.println("Множество B: " + setB);

        // Демонстрация операций над множествами из лекции
        System.out.println("\nОбъединение (A union B): " + setA.union(setB));
        System.out.println("Пересечение (A intersection B): " + setA.intersection(setB));
        System.out.println("Разность (A difference B): " + setA.difference(setB));
        System.out.println("Симметрическая разность (A symmetricDifference B): " + setA.symmetricDifference(setB));

        // Проверка подмножества
        MySet<Integer> sub = new MySet<>();
        sub.addRange(new Integer[]{1, 2, 3});
        System.out.println("\n" + sub + " является подмножеством " + setA + "? " + sub.isSubset(setA));

        // Проверка запрета на дубликаты
        try {
            setA.add(3);
        } catch (IllegalStateException e) {
            System.out.println("\nПерехвачено ожидаемое исключение при дубликате: " + e.getMessage());
        }
    }
}
