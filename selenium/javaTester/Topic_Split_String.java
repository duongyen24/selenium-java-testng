package webdriver.javaTester;

public class Topic_Split_String {
    public static void main(String[] args) {
        String basicAuthen = "http://the-internet.herokuapp.com/basic_auth";
        String[] authenArray = basicAuthen.split("//");

        System.out.println(authenArray[0]);
        System.out.println(authenArray[1]);

        basicAuthen = authenArray[0] + "//" + "admin" + ":" +"admin" + "@" + authenArray[1];

        System.out.println(basicAuthen);
    }
}
