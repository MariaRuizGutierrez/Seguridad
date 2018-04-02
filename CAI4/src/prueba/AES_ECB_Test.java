package prueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES_ECB_Test {

   static void fileProcessor(int cipherMode,String key,File inputFile,File outputFile) throws InvalidAlgorithmParameterException{
	 try {
		   
		   SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	       Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
	       cipher.init(cipherMode, skeySpec);

	       FileInputStream inputStream = new FileInputStream(inputFile);
	       byte[] inputBytes = new byte[(int) inputFile.length()];
	       inputStream.read(inputBytes);

	       byte[] outputBytes = cipher.doFinal(inputBytes);

	       FileOutputStream outputStream = new FileOutputStream(outputFile);
	       outputStream.write(outputBytes);

	       inputStream.close();
	       outputStream.close();

	    } catch (NoSuchPaddingException | NoSuchAlgorithmException 
                     | InvalidKeyException | BadPaddingException
	             | IllegalBlockSizeException | IOException e) {
		e.printStackTrace();
            }
     }
	
     public static void main(String[] args) {
    	 Long exTime = System.nanoTime();
	String key = "This is a secret"; //128 bytes key
	File inputFile = new File("C:\\Users\\Alfredo\\Desktop\\100kb.jpg");
	File encryptedFile = new File("C:\\Users\\Alfredo\\Desktop\\100kb.jpg");
	File decryptedFile = new File("C:\\Users\\Alfredo\\Desktop\\100kb.jpg");
		
	try {
	     //AES_ECB_Test.fileProcessor(Cipher.ENCRYPT_MODE,key,inputFile,encryptedFile);
	     AES_ECB_Test.fileProcessor(Cipher.DECRYPT_MODE,key,encryptedFile,decryptedFile);
	     System.out.println("Sucess");
	 } catch (Exception ex) {
	     System.out.println(ex.getMessage());
             ex.printStackTrace();
	 }
	 System.out.println(System.nanoTime() - exTime);
     }
	
}

