public class HelloUniverse {
    public static void main(String[] args) {
        int x = 9;
        int annee = 2010;
        String phrase ="En %d les planètes du système solaire étaient au nombre de : %d ";
        if(annee < 2006){
            System.out.printf(phrase,annee,x);
        }
        else{
            x--;
            System.out.printf(phrase,annee,x);
        }
    }
}
