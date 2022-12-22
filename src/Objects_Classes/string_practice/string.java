package Objects_Classes.string_practice;

public class string {
    public static void main(String[] args) {
        String name1 = "Jatin";
        String name2 = "Jatin";
        String name3 = new String("Jatin");
        String name4 = "Nakul";
        String line = "A quick brown fox jumps over white lazy dog.";
        String lineLower = line.toLowerCase();
        String lineUpper = line.toUpperCase();

        System.out.println(name1 == name2);
        System.out.println(name1 == name3);
        System.out.println(name1.equals(name3));


    }

    public byte[] toByteArray(String s){
        return s.getBytes();
    }

    public boolean containsString(String sub, String str){
        return str.contains(sub);
    }

    public int firstIndexOfChar(Character ch, String s){
        return s.indexOf(ch);
    }

    public int firstIndexOfChar(Character ch, String s, int idx){
        return s.indexOf(ch,idx);
    }

    public int firstIndexOfString(String sub, String s){
        return s.indexOf(sub);
    }

    public int firstIndexOfString(String sub, String s, int idx){
        return s.indexOf(sub,idx);
    }

    public static boolean pallindrome(String s){
        String reversedString = reverse(s);
        return s.equals(reversedString);
    }
    public static String reverse(String s){
        char[] charArray = s.toCharArray();
        int start = 0, end = charArray.length - 1;
        while(start < end){
            char temp = charArray[start];
            charArray[end--] = charArray[start];
            charArray[start++] = temp;
        }
        return String.valueOf(charArray);
    }

    public static void anotherIterationMethod(String s){
        int length = s.length();
        for(int i = 0; i<length; i++)
            System.out.println(s.charAt(i));
    }

    public static void iterateOnAllCharactersOfString(String s){
        char[] charArray = s.toCharArray();
        for(char ch : charArray){
            System.out.println(ch);
        }
    }
}
