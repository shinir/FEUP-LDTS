public class Application {
    public static void main(String[] args){
        try {
           Menu menu = new Menu(60, 30);
           menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}