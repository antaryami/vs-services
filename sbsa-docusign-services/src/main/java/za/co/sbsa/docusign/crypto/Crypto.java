package za.co.sbsa.docusign.crypto;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;

/**
 * Created by Jan-Rudolph on 7/18/2017.
 */
public class Crypto {

    private static byte[] xorBytes(byte[] bytes1, byte[] bytes2) {
        int length = bytes1.length;
        if (length != bytes2.length)
            throw new RuntimeException("Cannot xor byte arrays of unequal length!");

        byte[] result = new byte[length];

        for (int i = 0; i < length; i++) {
            result[i] = (byte)(bytes1[i] ^ bytes2[i]);
        }

        return result;
    }

    public static byte[] signAndVerify(byte[] halfkey1, byte[] halfkey2, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {
        Signature sigSign1 = Signature.getInstance("SHA3-512withDSA"); // we use DSA to sign a hash of document. Hash is generated with SHA3-512.
//        PrivateKey privateKey = keyPair1.getPrivate();
//        List<byte[]> halfKeys = splitKey(privateKey.getEncoded());
        byte[] recombinedPrivateKeyBytes = xorBytes(halfkey1, halfkey2);
        KeyFactory kf = KeyFactory.getInstance("DSA"); // or "EC" or whatever
        // https://stackoverflow.com/questions/19353748/how-to-convert-byte-array-to-privatekey-or-publickey-type
        PrivateKey recombinedPrivateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(recombinedPrivateKeyBytes));

        sigSign1.initSign(recombinedPrivateKey);

        sigSign1.update(data);

        return sigSign1.sign();  // signed hash


        // Now, to verify the signature we just created:
//
//        Signature sigVerify1 = Signature.getInstance("SHA3-512withDSA");
//        sigVerify1.initVerify(keyPair1.getPublic());
//        sigVerify1.update(data);
//
//        boolean verified = false;
//        try {
//            verified = sigVerify1.verify(sigBytes1);
//        } catch (Exception e) {
//            verified = false;
//        }
//
//        if (verified) {
//            System.out.println("VERIFIED!");
//        } else {
//            System.out.println("NOT VERIFIED!");
//        }
    }

    public static boolean verify(PublicKey publicKey, byte[] data, byte[] sigBytes) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {

        Signature sigVerify = Signature.getInstance("SHA3-512withDSA");
        sigVerify.initVerify(publicKey);
        sigVerify.update(data);

        return sigVerify.verify(sigBytes);
    }

}
