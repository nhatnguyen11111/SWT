
package service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String SECRET_KEY = "1234567890123456"; // 16 characters for AES-128

    public static String encrypt(String message) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedMessage) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            String message = "This is a secret message";

            // Encrypt the message
            String encryptedMessage = encrypt(message);
            System.out.println("Encrypted Message: " + encryptedMessage);

            // Decrypt the message
            String decryptedMessage = decrypt(encryptedMessage);
            System.out.println("Decrypted Message: " + decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
