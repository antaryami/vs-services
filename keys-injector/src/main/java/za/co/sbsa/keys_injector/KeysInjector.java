package za.co.sbsa.keys_injector;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Created by Jan-Rudolph on 7/18/2017.
 */
public class KeysInjector {
    static int AMOUNT = 10;

    public static void main(String[] args) throws SQLException, NoSuchProviderException, NoSuchAlgorithmException {
        new KeysInjector().run();
    }

    private void run() throws SQLException, NoSuchProviderException, NoSuchAlgorithmException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/sbsa","sbsa", "password");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        } 

        try {
            // STEP 4 Database insertions
            for (int i = 0; i < AMOUNT; i++) {
                KeyPair keyPair = generateKeypair();
                PrivateKey privateKey = keyPair.getPrivate();
                List<byte[]> halfKeys = splitKey(privateKey.getEncoded());

                System.out.println("Inserting records into the table sbsa_crypto");
//                Statement stmt = connection.createStatement();
//                PreparedStatement stmt = connection.prepareStatement(sql);
//                String sql = "INSERT INTO sbsa_crypto (key_id, halfkey_blob, public_key_blob, state) " +
//                        "VALUES (" + (i + 1) + "," + new String(halfKeys.get(0)) + ","
//                        + keyPair.getPrivate().getEncoded() + ",'Available')";
                String sql = "INSERT INTO sbsa.sbsa_crypto (key_id, halfkey_blob, public_key_blob, state) VALUES (?,?,?,?)";
               PreparedStatement stmt = connection.prepareStatement(sql);
               stmt.setLong(1, i + 1);
//               stmt.setBytes(2, halfKeys.get(0));
//               stmt.setBytes(3, keyPair.getPrivate().getEncoded());
               stmt.setBlob(2, new javax.sql.rowset.serial.SerialBlob(halfKeys.get(0)));
               stmt.setBlob(3, new javax.sql.rowset.serial.SerialBlob(keyPair.getPrivate().getEncoded()));
               stmt.setString(4, "Available");
               stmt.executeUpdate();

                System.out.println("Inserting records into the table vendor_crypto");
                sql = "INSERT INTO sbsa.vendor_crypto (key_id, half_key_blob, public_key_blob, state) " +
                        "VALUES (?,?,?,?)";
                stmt = connection.prepareStatement(sql);
//                "INSERT INTO vendor_crypto (key_id, half_key_blob, public_key_blob, state) " +
//                "VALUES (" + (i + 1) + "," + new String(halfKeys.get(2)) + ","
//                + keyPair.getPrivate().getEncoded() + ",'Available')";
                stmt.setLong(1, i + 1);
                stmt.setBlob(2, new javax.sql.rowset.serial.SerialBlob(halfKeys.get(1)));
                stmt.setBlob(3, new javax.sql.rowset.serial.SerialBlob(keyPair.getPrivate().getEncoded()));
                stmt.setString(4, "Available");
                stmt.executeUpdate();
            }
        } catch (RuntimeException rte) {
            connection.rollback();
        }

//        //STEP 5: Execute queries
//        System.out.println("Creating statement select from sbsa_crypto");
//        Statement stmt = connection.createStatement();
//
//        String sql = "SELECT * FROM sbsa_crypto";
//        ResultSet rs = stmt.executeQuery(sql);
//        //STEP 5: Extract data from result set
//        while(rs.next()){
//            //Retrieve by column name
//            int id  = rs.getInt("id");
//            int age = rs.getInt("age");
//            String first = rs.getString("first");
//            String last = rs.getString("last");
//
//            //Display values
//            System.out.print("ID: " + id);
//            System.out.print(", Age: " + age);
//            System.out.print(", First: " + first);
//            System.out.println(", Last: " + last);
//        }
//
//        System.out.println("Creating statement select from vendor_crypto");
//        stmt = connection.createStatement();
//
//        sql = "SELECT * FROM vendor_crypto";
//        rs = stmt.executeQuery(sql);
//        //STEP 5: Extract data from result set
//        while(rs.next()){
//            //Retrieve by column name
//            int id  = rs.getInt("id");
//            int age = rs.getInt("age");
//            String first = rs.getString("first");
//            String last = rs.getString("last");
//
//            //Display values
//            System.out.print("ID: " + id);
//            System.out.print(", Age: " + age);
//            System.out.print(", First: " + first);
//            System.out.println(", Last: " + last);
//        }
    }


    private static List<byte[]> splitKey(byte[] privateKey) {
        DigestRandomGenerator rGen = new DigestRandomGenerator(new SHA512Digest());
        byte[] randomHalfkey = new byte[privateKey.length];


        rGen.addSeedMaterial(new java.util.Date().getTime());
        rGen.nextBytes(randomHalfkey, 0, privateKey.length);
        System.out.println(privateKey);
        System.out.println("###");
        System.out.println(randomHalfkey);
//
//
//        for (int i = 0; i != 1024; i++)
//        {
//            rGen.nextBytes(randomHalfkey);
//        }

        List<byte[]> result = new ArrayList<byte[]>(2);
        result.add(randomHalfkey);
        result.add(xorBytes(randomHalfkey, privateKey));

        return result;
    }

    // TODO should call one in Crypto class in services maven module
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

    KeyPair generateKeypair() throws NoSuchProviderException, NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA", "BC");
        kpg.initialize(2048);  // 2024-bit DSA key pair
        KeyPair keyPair1 = kpg.genKeyPair();

        return keyPair1;
    }

}
