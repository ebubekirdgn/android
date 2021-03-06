package com.example.notification;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	       
		Button btn_bildirim1 = (Button)findViewById(com.example.notification.R.id.button_bildirim1);
		Button btn_bildirim2 = (Button)findViewById(com.example.notification.R.id.button_bildirim2);
		
		btn_bildirim1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			 bildirim1();				
			}
		});
		btn_bildirim2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bildirim2();
			}
		});
	}//onCreate

private void bildirim1() {
	/*bildirim yöneticisi oluşturuluyor:*/
NotificationManager bildiri_yoneticisi =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
/*Bildirimde kullanılacak NOT oluşturuluyor:*/
Notification not = new Notification(R.drawable.android, "Yeni Bir Bildirim...", System.currentTimeMillis()/*dilediğiniz zamanda çalışmasını burda belirleyebilirsiniz*/);
/*Zaman ayarlı bir Intent oluşturuluyor.Bu,belirlediğiniz saate çalışacak bir Intent türüdür.Alarm veya hatırlatıcı uygulamalar bu Intent'i kullanır.
 *Ayrıca içersinde bir Intent oluşturuluyor.Bu,bildiriyi tıkladığımızda tekrardan uygulamamıza dönebilmemize yarar.Dilediğiniz Aktivite veya servisi burada belirleyip
  bildiri tıklandığında çalışmasını sağlayabilirsiniz.*/
PendingIntent intent_zaman_ayarli = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), Notification.FLAG_ONGOING_EVENT);
/*Not belirleniyor:*/
not.setLatestEventInfo(this, "kodlab.com", "Bilişim Yayıncılığının Yeni Yüzü.", intent_zaman_ayarli);
/*yapılan tüm ayarlamalar set ediliyor:*/
bildiri_yoneticisi.notify(1, not);
}//bildirim1
	  
private void bildirim2(){
	/*bildirim1'in farklı bir yaklaşımıla tekrardan geliştiriyoruz..*/
	
NotificationManager bildiri_yoneticisi = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);	
Intent intent = new Intent(getApplicationContext(), MainActivity.class);
PendingIntent intent_zaman_ayarli = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
Notification not = new Notification.Builder(getApplicationContext()) 
  .setContentTitle("Yeni Bir Bildirim Aldınız...")
  .setContentText("www.kodlab.com").setSmallIcon(R.drawable.bildiri)
  .setContentIntent(intent_zaman_ayarli)
  /*birden fazla aksiyon tanımlanıyor.Herbirine ayrıca bir Intent tanımlanabilir.*/
  .addAction(R.drawable.android, "İleri Düzey Android", intent_zaman_ayarli)
  .addAction(R.drawable.bulut, "Bulut bilişim", intent_zaman_ayarli)
  .addAction(R.drawable.info_icon, "Detaylı Bilgi", intent_zaman_ayarli).build();
  /*bildirimi tıkladıktan sonra otomatik kapatılması-gizlenmesi için:*/
  not.flags |= Notification.FLAG_AUTO_CANCEL;
  /*yapılan tüm ayarlamalar set ediliyor:*/
  bildiri_yoneticisi.notify(0, not);
}//bildirim2

}//class
