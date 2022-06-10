
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        AVLTree avlTree = new AVLTree();
        Scanner scanner = new Scanner(System.in);
        
        String message = "1.Eleman Ekle : ekle(value)\n" +
                         "2.Eleman Sil  : sil(value)\n" +
                         "3.Ata Node    : ata(value) \n" +
                         "4.Preorder Gezinme: preOrder\n" +
                         "5.Inorder Gezinme : inOrder\n" +
                         "6.PostOrder Gezinme : postOrder\n";
        System.out.println("********************************");
        System.out.println("AVL Tree Programina Hos Geldiniz");
        System.out.println("********************************");
        System.out.println("\nIslemler : ");
        System.out.println(message);
        String islem;
        while(true){

            System.out.println("Bir islem seciniz : ");
            islem = scanner.nextLine();
            String value = null;

            if (islem.indexOf("ekle(") != -1){
                int start = 5;
                int finish = islem.indexOf(')');
                if (finish <= start){
                    System.out.println("Hatali islem!");
                    continue;
                }
                value = islem.substring(start, finish);
                if (!isAlpha(value)){
                    System.out.println("Lütfen string ifade giriniz !");
                    continue;
                }
                avlTree.insert(value);

            }else if (islem.indexOf("sil(") != -1){
                int start = 4;
                int finish = islem.indexOf(')');
                if (finish <= start){
                    System.out.println("Hatali islem!");
                    continue;
                }
                value = islem.substring(start, finish);
                int isThere = avlTree.findNode(value);
                if (isThere == 0){
                    System.out.println("Eleman mevcut degildir.");
                    continue;
                }
                avlTree.deleteNode(value);
                System.out.println("Eleman basari ile silindi");

            }else if (islem.indexOf("ata(") != -1){
                int start = 4;
                int finish = islem.indexOf(')');
                if (finish <= start){
                    System.out.println("Hatali islem!");
                    continue;
                }
                value = islem.substring(start, finish);
                int isThere = avlTree.findNode(value);
                if(isThere == 0){
                    System.out.println("Eleman Mevcut degil");
                    continue;
                }
                avlTree.ataNodes(value);
                System.out.println();
            }else if (islem.equals("preOrder")){
                System.out.print("PreOrder : ");
                avlTree.preOrder();
            }else if (islem.equals("inOrder")){
                System.out.print("InOrder : ");
                avlTree.inOrder();
            }else if (islem.equals("postOrder")){
                System.out.print("PostOrder : ");
                avlTree.postOrder();
            }else{
                System.out.println("Hatali Islem!");
            }

        }


    }

    public static boolean isAlpha(String str) {
        if (str.isEmpty() || str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (Character.isAlphabetic(str.charAt(i))) {

            } else {
                return false;
            }
        }
        return true;
    }

}
