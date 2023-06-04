package reflect.myString;

public class MyString {

    private MyString() {
    }

    public static String toUpperIndex(String str, int index) {
        char[] chars = str.toCharArray();
        int taille = chars.length;
        if (index < 0 || index >= taille) {
            return null;
        }

        for (int i = 0; i < taille; i++) {
            if (i == index) {
                chars[i] = Character.toUpperCase(chars[i]);
                break;
            }
        }
        return new String(chars);
    }

    public static String onLine(String[] strs, String separator) {

        String valiny = "";
        int taille = strs.length;

        for (int i = 0; i < taille; i++) {
            String string = (i == taille - 1) ? strs[i] : strs[i] + separator;
            valiny = valiny + string;
        }
        return valiny;
    }

}
