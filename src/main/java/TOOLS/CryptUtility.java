package TOOLS;

import java.io.*;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author rajay
 */
class EncryptionException extends Exception {

    /**
     *
     * @param e
     */
    public EncryptionException(Exception e) {
        System.out.println(e.getLocalizedMessage());
    }

}

public final class CryptUtility {

    private final static String K_STRING = "ThisISaSeCretKEy";

    /**
     *
     * @param in
     * @param out
     * @throws InvalidAlgorithmParameterException
     */
    public static void encryptVideo(String in, String out) throws InvalidAlgorithmParameterException, Exception {
        File input = new File(in + ".mp4");
        File output = new File(out + ".cryvid");
        doCrypto(Cipher.ENCRYPT_MODE, K_STRING, input, output);
    }

    /**
     *
     * @param in
     * @param out
     * @throws InvalidAlgorithmParameterException
     */
    public static void decryptVideo(String in, String out) throws InvalidAlgorithmParameterException, Exception {
        File input = new File(in + ".cryvid");
        File output = new File(out + ".mp4");
        doCrypto(Cipher.DECRYPT_MODE, K_STRING, input, output);
    }

    /**
     *
     * @param in
     * @param out
     * @throws InvalidAlgorithmParameterException
     */
    public static void encryptImage(String in, String out) throws InvalidAlgorithmParameterException, Exception {
        File input = new File(in + ".jpg");
        File output = new File(out + ".cryimg");
        doCrypto(Cipher.ENCRYPT_MODE, K_STRING, input, output);
    }

    /**
     *
     * @param in
     * @param out
     * @throws InvalidAlgorithmParameterException
     */
    public static void decryptImage(String in, String out) throws InvalidAlgorithmParameterException, Exception {
        File input = new File(in + ".cryimg");
        File output = new File(out + ".jpg");
        doCrypto(Cipher.DECRYPT_MODE, K_STRING, input, output);
    }

    /**
     *
     * @param in
     * @return
     * @throws TOOLS.EncryptionException
     */
    public static String encryptString(String in) throws EncryptionException {
        try {
            Key secrectKey = new SecretKeySpec(K_STRING.getBytes(), "AES");
            Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1};
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            ciper.init(Cipher.ENCRYPT_MODE, secrectKey, ivParameterSpec);
            byte outputBytes[] = ciper.doFinal(in.getBytes());
            return new String(outputBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(CryptUtility.class.getName()).log(Level.SEVERE, null, ex);
            throw new EncryptionException(ex);
        }
    }

    /**
     *
     * @param in
     * @return
     * @throws TOOLS.EncryptionException
     */
    public static String decryptString(String in) throws EncryptionException {
        try {
            Key secrectKey = new SecretKeySpec(K_STRING.getBytes(), "AES");
            Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1};
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            ciper.init(Cipher.DECRYPT_MODE, secrectKey, ivParameterSpec);
            byte outputBytes[] = ciper.doFinal(in.getBytes());
            return new String(outputBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e);
            throw new EncryptionException(e);
        }
    }

    public static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws Exception {
        try {
            Key secrectKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = {1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 1};
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            ciper.init(cipherMode, secrectKey, ivParameterSpec);

            FileInputStream inputStream = new FileInputStream(inputFile);
            byte inputBytes[] = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte outputBytes[] = ciper.doFinal(inputBytes);
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();

        } catch (IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println(e);
            throw new EncryptionException(e);
        }
    }

}
