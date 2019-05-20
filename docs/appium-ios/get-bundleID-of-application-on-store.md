## Cách lấy bundle ID của application đang trên AppStore.
_Bundle ID dùng để set capability, để có thể mở được app trên appium. Đối với các bạn thưc hiện automation test cho công ty, có thể dễ dàng hỏi dev team.
Còn đối với các bạn thích vọc vạch có thể tham khảo cách sau:_

#### Bước 1: Tìm ứng dụng trên appstore:

- tìm ứng dụng trên trang [Itunes Apple](https://itunes.apple.com/vn/app/vato/id1126633800?mt=8)
- Copy ID của ứng dụng:

<img src="https://github.com/hominhtuong/appium/blob/master/resources/find-bundleID.png" alt="Bundle ID" width="720" height ="478"/>

#### Lấy thông tin của ứng dụng:

- Thay thế id của ứng dụng vào id bên link dưới, sau đó nhấn Enter: 

>> https://itunes.apple.com/lookup?id=**1126633800**
 
#### Lấy bundle ID:

- Mở file .txt được trả về, tìm giá trị bundleID:

![Bundle ID](https://github.com/hominhtuong/appium/blob/master/resources/bundle-ID.png)

- Với ví dụ trên ta tìm thấy Bundle ID của Vato là "com.client.facecar"