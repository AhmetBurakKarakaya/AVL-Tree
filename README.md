# AVL-Tree
 
Geliştirilme Ortamları: 

1.) Programlama Dili: Java YAZILIM TASARIMI 
1.Programın Amacı:
   	Sol ve sağ alt ağacın yüksekliği arasındaki farkın -1, 0 veya +1 olduğu ikili arama ağaçlarıdır. AVL ağaçlarına kendi kendini dengeleyen ikili arama ağacı da denir. Bu ağaçlar, logaritmik arama süresinin korunmasına yardımcı olur. AVL ağaçları, arama işleminin ekleme ve silme işlemlerine göre daha sık olduğu yerlerde kullanılır. Düzgün dengelenmiş bir AVL ağacında düğümleri nasıl ekleyeceğimizi, sileceğimizi ve arayacağımızı öğreneceğiz.

AVL Ağaçlarında Denge Faktörü

Denge faktörü (BF), ağacın yüksekliğini izlemeye yardımcı olan AVL ağaçlarındaki her düğümün temel bir özelliğidir.
Denge Faktörünün Özellikleri

•	Denge faktörü, sol alt ağacın yüksekliği ile sağ alt ağacın yüksekliği arasındaki fark olarak düşünebiliriz.
•	Denge faktörü (düğüm) = yükseklik (düğüm-> sol) - yükseklik (düğüm-> sağ)
•	BF'nin izin verilen değerleri –1, 0 ve +1'dir.
•	-1 değeri, soldaki alt ağacın fazladan bir tane içerdiğini, yani ağacın ağır kaldığını gösterir.
•	+1 değeri, soldaki alt ağacın fazladan bir tane içerdiğini, yani ağacın ağır bırakıldığını gösterir.
•	0 değeri, ağacın her iki tarafında eşit düğümler içerdiğini, yani ağacın mükemmel şekilde dengelendiğini gösterir.
AVL Rotasyonları
AVL Ağacı dengesini kendisi yapmak için, ağaçtan bir düğüm eklerken veya silerken rotasyonlar gerçekleştirilir.
Left Left Rotation
Bu rotasyon, sol alt ağacın sol çocuğuna yeni bir düğüm eklendiğinde gerçekleştirilir.
Tek bir sağa dönüş gerçekleştirilir. Bu tür bir dönüş, bir düğümün +2 olarak dengeli bir faktöre sahip olması ve sol çocuğunun +1 olarak bir denge faktörüne sahip olması durumunda tanımlanır.

Right Right Rotation
Bu rotasyon, sağ alt ağacın sağ çocuğuna yeni bir düğüm eklendiğinde gerçekleştirilir.
Tek bir sola dönüş gerçekleştirilir. Bu tür bir dönüş, bir düğümün -2 olarak dengeli bir faktörü ve sağ çocuğu -1 olarak bir denge faktörüne sahip olduğunda tanımlanır.

Right Left Rotation
Bu rotasyon, sol alt ağacın sağ çocuğuna yeni bir düğüm eklendiğinde gerçekleştirilir. 
Bu rotasyon, bir düğümün -2 olarak bir denge faktörüne sahip olduğu ve sağ çocuğunun +1 olarak bir denge faktörüne sahip olduğu zaman gerçekleştirilir.

Left Right Rotation
Bu rotasyon, sol alt ağacın sağ çocuğuna yeni bir düğüm eklendiğinde gerçekleştirilir.
Bu rotasyon, bir düğümün denge faktörü +2 ve sağ çocuğu -1 olarak denge faktörüne sahip olduğunda gerçekleştirilir.

AVL Ağaçlarında Ekleme

Adım 1 : BST'nin aynı ekleme algoritmasını kullanarak düğümü AVL ağacına ekleyin. 
Adım 2 : Düğüm eklendiğinde, her düğümün denge faktörü güncellenir. 
Adım 3 : Şimdi, denge faktörü ihlal edilmişse herhangi bir düğümün denge faktörü aralığını ihlal edip etmediğini kontrol edin, ardından aşağıdaki durumu kullanarak rotasyonları gerçekleştirin.
1.	BF (düğüm) = +2 ve BF (düğüm -> sol çocuk) = +1 ise, LL dönüşü gerçekleştirin.
2.	BF (düğüm) = -2 ve BF (düğüm -> sağ çocuk) = 1 ise, RR dönüşü gerçekleştirin.
3.	BF (düğüm) = -2 ve BF (düğüm -> sağ çocuk) = +1 ise, RL dönüşü gerçekleştirin.
4.	BF (düğüm) = +2 ve BF (düğüm -> sol çocuk) = -1 ise, LR dönüşü gerçekleştirin.

AVL Ağaçlarında Silme

Silme işlemi çok basittir. Silme işleminden sonra, denge yüksekliğini korumak için gerekirse ağacı yeniden yapılandırıyoruz. Ağaçtaki öğe bulunur. BST silme işlemi uyarınca düğüm silinir.

Durum 1: Sağ alt ağaçtan silme.
•	1 A. BF (düğüm) = +2 ve BF (düğüm -> sol çocuk) = +1 ise, LL dönüşü gerçekleştirin.
•	1B. BF (düğüm) = +2 ve BF (düğüm -> sol çocuk) = -1 ise, LR dönüşü gerçekleştirin.
•	1C. BF (düğüm) = +2 ve BF (düğüm -> sol çocuk) = 0 ise, LL dönüşü gerçekleştirin.

Durum 2 : Sol alt ağaçtan silme.
•	2A. BF (düğüm) = -2 ve BF (düğüm -> sağ çocuk) = -1 ise, RR dönüşü gerçekleştirin.
•	2B. BF (düğüm) = -2 ve BF (düğüm -> sağ çocuk) = +1 ise, RL dönüşü gerçekleştirin.
•	2C. BF (düğüm) = -2 ve BF (düğüm -> sağ çocuk) = 0 ise, RR dönüşü gerçekleştirin.

minValue() kullanımı: Silinen veri iki çocuğa sahipse sağ alt ağacındaki en küçük veriye (successor) bulmak için yazılmıştır.
isAlpha() kullanımı: Value değerini kontrol ediyor. Karakter dışında başka değere izin vermiyor.
Ata Node: İstenilen düğüm ağaçta mevcut mu değil mi bakılır. Değilse bilgisi verilir. Mevcutsa parent düğümünden, kök düğümüne doğru ata düğümleri yazılır.
 
   
