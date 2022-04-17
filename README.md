# Laboratuvar Raporlama Sistemi

## Açıklama

Bu proje; Java, Thymeleaf,  JPA, Maven, Spring ve Bootstrap teknolojileriyle
yapılmış bir laboratuvar raporlama sistemidir.    

Ayrıca thymeleaf kütüphanesini bana bu projeyi yazarken öğreten proje niteliğini taşımaktadır.

Uygulama açılırken test amaçları için gerekli olan modelleri oluşturması için 
CommandLineRunner arayüzünü implemente etmektedir. Açıldığında içerisinde 2 farklı kullanıcı (biri laborant diğeri hasta)
ve 2 farklı rapor ile açılmaktadır.

Uygulama h2 in-memory veritabanını kullanmaktadır.

Admin olarak giriş yapmak için 'YunusEmre' kullanıcı adı ve 'fplanet' şifresi ile giriş yapılabilir.

## Uygulama özellikleri
Uygulama içerisinde kayıt olma ve giriş yapma mekanizması bulunmaktadır.
Kayıt olan kişi eğer laborantsa rapor detaylarına ulaşabilir, yeni raporlar oluşturabilir ve
var olan raporları düzenleyebilir. Eğer bir adminse laborantın yapabildiklerini yapabilir ve ekstra olarak
var olan raporları silebilir.  

Yetki mekanizması oluşturulurken kullanıcı modelinde rol listesi bulunmaktadır. 
Bunun nedeni, bir laborant aynı hastanede bir hasta da olabilir. Ya da bir admin
aynı hastanede hem bir laborant hem de hasta olabilir.  

Bu nedenlerden dolayı eğer kullanıcı laborant olarak kayıt olursa 
sisteme kayıt edilirken iki farklı rol alarak kayıt edilir.


## Yakında eklenecekler
Şu an kullanıcılar kayıt olurken laborant olarak kayıt olabiliyor ancak
herhangi bir yetkili onaylamadan işlem yapabiliyorlar.

Eğer yeni kullanıcı kendisini laborant olarak kayıt ederse 
uygulamayı kullanmadan önce bir admin tarafından onaylanmalı.

## Kullanım

## Teknik Kabuller
Üretim aşamasında veritabanı değiştirilmelidir ve 