package lab01_jar_of_T;

public class Main {
    public static void main(String[] args) {
        Jar<Pickle> jarOfPickles = new Jar<>();

        jarOfPickles.add(new Pickle("1"));
        jarOfPickles.add(new Pickle("2"));
        jarOfPickles.add(new Pickle("3"));

        Pickle pickle = jarOfPickles.remove();
        System.out.println(pickle.getName());
        pickle = jarOfPickles.remove();
        System.out.println(pickle.getName());
    }
}
