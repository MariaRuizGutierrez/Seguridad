    package prueba;
    import java.io.File;
    import java.security.KeyPair;
    import java.security.KeyPairGenerator;
    import java.security.NoSuchAlgorithmException;
    import java.security.PrivateKey;
    import java.security.PublicKey;
    import javax.crypto.Cipher;
    import org.apache.commons.io.FileUtils;
    
    class MyRSACipher {
      public static KeyPair getRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        return kp;
      }
      public static byte[] encryptFile(byte[] inputBytes, PublicKey key, String xform) throws Exception {
        Cipher cipher = Cipher.getInstance(xform);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(inputBytes);
      }
      public static byte[] decryptFile(byte[] inputBytes, PrivateKey key, String xform) throws Exception {
        Cipher cipher = Cipher.getInstance(xform);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(inputBytes);
      }
    }
    public class RSATest {
      public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
    	Long exTime = System.nanoTime();
    	//Mi ruta es: /Users/Maria/Desktop/Readme.txt
        String fileToEncrypt = "C:\\Users\\Alfredo\\Desktop\\test3.txt";
        String encryptedFile = "C:\\Users\\Alfredo\\Desktop\\test3.txt";
        String decryptedFile = "C:\\Users\\Alfredo\\Desktop\\test3.txt";
        // Generate a key-pair
        KeyPair keyPari = MyRSACipher.getRSAKeyPair();
        PublicKey publicKey = keyPari.getPublic();
        PrivateKey privatekey = keyPari.getPrivate();
        File file = new File(fileToEncrypt);
        byte[] dataBytes = FileUtils.readFileToByteArray(file);
        
        
        //--------------------------- Encrypt the file-----------------------------------------------
        String algo = "RSA/ECB/PKCS1Padding";
        byte[] encryptedBytes = MyRSACipher.encryptFile(dataBytes, publicKey, algo);
        file = new File(encryptedFile);
        FileUtils.writeByteArrayToFile(file, encryptedBytes);
        System.out.println("Encrypted file : " + encryptedFile);
        
        
        
        // ---------------------------------------Decrypt the file--------------------------------------------
        byte[] decryptedBytes = MyRSACipher.decryptFile(encryptedBytes, privatekey, algo);
        file = new File(decryptedFile);
        FileUtils.writeByteArrayToFile(file, decryptedBytes);
        System.out.println("Decrypted file : " + decryptedFile);
        System.out.println(System.nanoTime() - exTime);
      }
    }