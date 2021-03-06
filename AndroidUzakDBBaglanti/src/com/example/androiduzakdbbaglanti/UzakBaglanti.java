package com.example.androiduzakdbbaglanti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import android.util.Log;

public class UzakBaglanti {
	/*Global nesne ve değişkenlerimizi tanımlıyoruz:*/
    static Connection baglanti = null;
    static Statement statement = null;
    /*
     * Bağlanacağımız sunucuya ait ip adresi ve mysql port numarasını belirtiyoruz.
     * Ama öncesinde kullandığımız veritabanına ait JDBC anahtar sözcüğünü belirtiyoruz.
     * Benim kullandığım mysql portu "3306" varsayılan portudur.
     * Sizde farklılık gösterebilir.
     * Sunucu linkini belirttikten sonra veritabanı adını belirtmemiz gerekiyor.
     * Güvenlik nedeniyle sunucu bilgilerim yerine temsili bilgiler verilmiştir.
     */
    static String DB_URL = "jdbc:mysql://ip_adresi:3306/VeritabaniAdi";
    /*Veritabanına ait kullanıcı adı:*/
    static String DB_KADI = "root"; 
    /*Veritabanına ait şifre:*/
    static String DB_SIFRE ="123456";  
    
	public UzakBaglanti(){
		//Constructor
	}
	
	public StringBuilder uzakBaglantiVeriGetir(){
		ResultSet sonuclarKumesi =null;
		StringBuilder str = new StringBuilder();
	    try {
	    	/*JDBC yi belirtiyoruz:*/
	    	Class.forName("com.mysql.jdbc.Driver").newInstance();
	    	/*Veritabanına bağlantı oluşturuyoruz:*/
			baglanti = DriverManager.getConnection(DB_URL, DB_KADI, DB_SIFRE);
	        statement = baglanti.createStatement();
	        /*Sql sorgusu:*/
	        String sorgu ="SELECT *FROM TabloAdi"; 
	        /*Sorguyu çalıştırıyoruz ve geri dönen sorgu değerini "ResultSet" nesnesine aktarıyoruz:*/
	        sonuclarKumesi = statement.executeQuery(sorgu);
	        /*Bir döngü vasıtasıyla tüm kayıtlara tek tek erişiyoruz:*/
	        while(sonuclarKumesi.next()){
	        	/*Sonuç kümesi içersinde tutulan değerlere veri türlerini belirterek elde ediyoruz:*/
		         int id     = sonuclarKumesi.getInt("id"); 
		         String adi  = sonuclarKumesi.getString("Adi");
		         String soyadi  = sonuclarKumesi.getString("Soyadi");
		         String email  = sonuclarKumesi.getString("EMail");
		         str.append(String.valueOf(id) +" "+adi +" "+ soyadi +" " +email +"\n");
		       }
	    }catch(Exception e){
	       Log.e("HATA", e.getMessage());
	     return null;
	    }
	    return str;
	}

}
