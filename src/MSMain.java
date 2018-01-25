public class MSMain {
    public static void main(String[] args) {

        CodeBehind cb = new CodeBehind();
        MSWindow window = new MSWindow(cb);
        cb.setWindow(window);

        System.out.println();
    }
}
