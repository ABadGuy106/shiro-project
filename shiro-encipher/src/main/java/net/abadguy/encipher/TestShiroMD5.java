package net.abadguy.encipher;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestShiroMD5 {
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toHex());


        //使用MD5+盐 处理
        Md5Hash md5Hash1 = new Md5Hash("123456", "qazwsx");
        System.out.println(md5Hash1.toHex());

        //使用 md5 + salt +hash散列
        //第三个参数代表散列的次数
        Md5Hash md5Hash2 = new Md5Hash("123456", "qazwsx", 1024);
        System.out.println(md5Hash2.toHex());

    }
}
