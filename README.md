# Laboratuvar Raporlama Sistemi

## Açıklama

Bu proje; Java, Thymeleaf,  JPA, Maven, Spring, Spring Security ve Bootstrap teknolojileriyle
yapılmış bir laboratuvar raporlama sistemidir.    

Uygulama açılırken test amaçları için gerekli olan modelleri oluşturması için 
CommandLineRunner arayüzünü implemente etmektedir. Açıldığında içerisinde 2 farklı kullanıcı (biri laborant diğeri hasta)
ve 2 farklı rapor ile açılmaktadır.

Uygulama h2 in-memory veritabanını kullanmaktadır.

Admin olarak giriş yapmak için 'YunusEmre' kullanıcı adı ve 'passwd' şifresi ile giriş yapılabilir.

Uygulama 8000 portunu kullanmaktadır.


~Yunus Emre Uyar

## Uygulama özellikleri
Uygulama içerisinde kayıt olma ve giriş yapma mekanizması bulunmaktadır.
Kayıt olan kişi eğer laborantsa rapor detaylarına ulaşabilir, yeni raporlar oluşturabilir ve
var olan raporları düzenleyebilir. Eğer bir admin'se laborantın yapabildiklerini yapabilir ve ekstra olarak
var olan raporları silebilir.  

Yetki mekanizması oluşturulurken kullanıcı modelinde rol listesi bulunmaktadır. 
Bunun nedeni, bir laborant aynı hastanede bir hasta da olabilir. Ya da bir admin
aynı hastanede hem bir laborant hem de hasta olabilir.  

Bu nedenlerden dolayı eğer kullanıcı laborant olarak kayıt olursa 
sisteme kayıt edilirken iki farklı rol alarak kayıt edilir.


## Yakında eklenecekler
Şu an kullanıcılar kayıt olurken laborant olarak kayıt olabiliyor ve
herhangi bir yetkili onaylamadan işlem yapabiliyorlar.

Eğer yeni kullanıcı laborant olarak kayıt olursa
uygulamayı kullanmadan önce bir admin tarafından onaylanmalıdır.

Test yazmayı henüz öğrendiğim için testler tamamlanmamıştır.

Admin sayfası eklenecektir.

## Kullanım

1. git clone https://github.com/YunusEmreUyar/LabReSys.git (Repo'yu indirin.)
2. cd LabReSys/main (cmd ya da explorer üzerinden main klasörüne gidin)
3. mvn clean install (cmd ile yandaki kodu çalıştırın)
4. cd target (build alınan hedef klasöre gidin)
5. java -jar LabReSys-Executable-Jar (java -jar komutu ile uygulama artık çalıştırılabilir)
6. Tarayıcı açın ve URL kısmına 127.0.0.1:8000 yazın ve kullanmaya başlayın.

## Teknik Kabuller
1. Kimlik bilgisi ile arama yapılabilmesi için kayıt esnasında tc kimlik ve istenilen kimliğin eşsiz olması istenmektedir.

2. Yukarıda da belirtildiği gibi kullanıcılar birden fazla role sahip olabileceği için
kullanıcı kayıt etme fonksiyonunda rolüne bağlı olarak ekstra roller de verilmektedir.

3. Uygulama hiyerarşisinde admin > laborant > standart kullanıcı

4. Test amaçları için uygulama açılırken iki kullanıcı ve iki rapor üretmektedir.
