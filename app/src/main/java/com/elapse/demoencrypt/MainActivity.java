package com.elapse.demoencrypt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.elapse.demoencrypt.utils.AESUtil;
import com.elapse.demoencrypt.utils.Base64Util;
import com.elapse.demoencrypt.utils.DESUtil;
import com.elapse.demoencrypt.utils.RSAUtil;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String type = "android";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBase64();
        setRSA();
        setDES();
        setAES();
        // DES 加密 - AES 加密 - AES 解密 - DES 解密
        setDesAndAes();
    }

    private void setBase64() {

        String oldWord = "大家要注意身体，不要熬夜写代码";
        try {
            // 编码
            String encode = Base64Util.encodeWord(oldWord);
            System.out.println("编码：" + encode);
            // 解码
            String decode = Base64Util.decodeWord(encode);
            System.out.println("编码：" + decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void setRSA() {
        // 获取到密钥对
        KeyPair keyPair = RSAUtil.generateRSAKeyPair(1024);

        // 获取公钥和私钥
        PublicKey aPublic = keyPair.getPublic();
        PrivateKey aPrivate = keyPair.getPrivate();
        ;
        byte[] aPublicEncoded = aPublic.getEncoded();
        byte[] aPrivateEncoded = aPrivate.getEncoded();

        try {

            // 公钥加密
            byte[] bytes = RSAUtil.encryptByPublicKey(type, "123".getBytes(), aPublicEncoded);
            String s1 = Base64Util.encodeWord(bytes.toString());
//            String encode = Base64Utils.encode(bytes);
//            Log.d(TAG, "公钥加密文件: " + encode);
            Log.d(TAG, s1);

            // 私钥解密
            byte[] bytes1 = RSAUtil.decryptByPrivateKey(type, bytes, aPrivateEncoded);
            String s = new String(bytes1);
            Log.d(TAG, "私钥解密文件: " + s);

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("");

        try {
            // 私钥加密
            byte[] bytes = RSAUtil.encryptByPrivateKey(type, "456".getBytes(), aPrivateEncoded);
//            String encode = Base64Utils.encode(bytes);
            String encode = Base64Util.encodeWord(Arrays.toString(bytes));
            Log.d(TAG, "私钥加密文件: " + encode);

            // 公钥解密
            byte[] bytes1 = RSAUtil.decryptByPublicKey(type, bytes, aPublicEncoded);
            String s = new String(bytes1);
            Log.d(TAG, "公钥解密文件: " + s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDES() {

        /// ---------------------------------------- 静态 ------------------------------------------

        String key = "2012j214";        // 键值必须大于8位
        try {
            // 加密
            String s = DESUtil.desEncrypt("欧拉蕾", key);
            Log.d(TAG, "静态加密: " + s);

            // 解密
            String s1 = DESUtil.desDecrypt(s, key);
            Log.d(TAG, "静态解密: " + s1);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ----------------------------------------- 动态 ------------------------------------------

        // 动态生成秘钥
        String key2 = DESUtil.generateKey();

        try {

            // 动态加密
            String encode = DESUtil.encode(key2, "风里来，雨里去");
            Log.d(TAG, "动态加密: " + encode);

            // 动态解密
            String decode = DESUtil.decode(key2, encode);
            Log.d(TAG, "动态解密: " + decode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAES() {

        // 动态生成秘钥
        String key = AESUtil.generateKey();

        try {

            // 加密
            String encrypt = AESUtil.encrypt(key, "人有悲欢离合，月有阴晴圆缺");
            Log.d(TAG, "加密: " + encrypt);

            // 解密
            String decrypt = AESUtil.decrypt(key, encrypt);
            Log.d(TAG, "解密: " + decrypt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDesAndAes() {
        String key = DESUtil.generateKey();
        String key2 = AESUtil.generateKey();
        String message = "风里来，雨里去";
        try {

            // DES 动态加密
            String encode = DESUtil.encode(key, message);
            Log.d(TAG, "DES加密: " + encode);

            // AES 加密
            String encrypt = AESUtil.encrypt(key2, encode);
            Log.d(TAG, "AES加密: " + encrypt);

            // AES 解密
            String decrypt = AESUtil.decrypt(key2, encrypt);
            Log.d(TAG, "AES解密: " + decrypt);

            // DES 解密
            String decode = DESUtil.decode(key, decrypt);
            Log.d(TAG, "DES解密: " + decode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
