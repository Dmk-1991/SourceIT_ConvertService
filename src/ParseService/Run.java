package ParseService;


public class Run {
    static String expression ="50+150/15-30+10*12";
    public static void main(String[] args) {
        ParseService run = new ParseService();
        System.out.println(run.finalResult());
        System.out.println();

    }
}
