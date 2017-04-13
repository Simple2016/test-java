package lqw.test.test_enum;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println(Vegetable.Banana);
        System.out.println(Vegetable.Banana.getStatus());
        System.out.println(Vegetable.getEnumByCode("3"));
        System.out.println(Vegetable.getEnumByName("Apple"));
    }

}
