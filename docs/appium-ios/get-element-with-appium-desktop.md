## Hướng dẫn lấy Element bằng Appium Desktop trên MacOS



#### Đầu tiên: Khởi động 1 session mới bằng Appium Desktop [(Tham Khảo)](https://github.com/hominhtuong/appium/blob/master/docs/appium-ios/appium-ios.md) 

![New Session](https://github.com/hominhtuong/appium/blob/master/resources/new-session.png)

##### Lấy Element bằng Accessibility id:

- Nhấn chuột vào Element cần lấy ra.

Ví dụ chọn element `"TRƯỢT ĐỂ BẮT ĐẦU"`

![Accessibility id](https://github.com/hominhtuong/appium/blob/master/resources/accessibility-id.png)

- Ở đây ta thấy `accessibility id` của element là `"TRƯỢT ĐỂ BẮT ĐẦU"`
- Cách lấy ra Element bằng `accessibility id`: 
```java
IOSElement element = (IOSElement) driver.findElementByAccessibilityId("TRƯỢT ĐỂ BẮT ĐẦU");
```

##### Lấy Element bằng XPath:

- Nhấn chuột vào Element cần lấy ra.

Ví dụ chọn element `Button LeftMenu`

![XPath](https://github.com/hominhtuong/appium/blob/master/resources/xpath.png)

- Ở đây ta thấy element không có `accessibility id`, chỉ có `Xpath`.
- Cách lấy ra Element bằng `Xpath`: 

```java
String xpath = "//XCUIElementTypeNavigationBar[@name="Ngoại tuyến"]/XCUIElementTypeButton[1]";
IOSElement element = (IOSElement) driver.findElementByXPath(xpath);
```

- Tuy nhiên ở đây ta dễ thấy, element NavigationBar với `name = "Ngoại tuyến"` sẽ bị thay đổi nếu Trạng thái Navigation chuyển thành `"Trực tuyến"`.
- Vậy nên ta sẽ bỏ `@name` đi, `xpath` sẽ trở thành: 
 
```java
String xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]";
IOSElement element = (IOSElement) driver.findElementByXPath(xpath);
```

- Xpath của element con sẽ được đặt sau dấu `'/'`, và chính là field `type` trong `Attribute` của element đó.
- **Trong trường hợp có 2 element con cùng 1 kiểu type, ta xác định `xpath` dựa trên thứ tự sắp trên UI, ví dụ:**
-   ".../XCUIElementTypeButton[1]";
-   ".../XCUIElementTypeButton[2]";
-   ".../XCUIElementTypeButton[n]";
- Element đầu tiên là vị trí số 1, tiếp theo là số 2, ... n.
- Lưu ý, index này dựa theo kiểu type, ví dụ trong trường hợp sau:

![Type Xpath](https://github.com/hominhtuong/appium/blob/master/resources/type-xpath.png)

- `Navigation Bar` bên trên có 3 elements con, nhưng chỉ có 2 kiểu type là `XCUIElementTypeButton` (2 elements) và `XCUIElementTypeOther` (1 element).
- Vậy `XCUIElementTypeButton` đầu tiên sẽ là `".../XCUIElementTypeButton[1]"`, tiếp theo sẽ là `".../XCUIElementTypeButton[2]"`
- Và `XCUIElementTypeOther` sẽ bắt đầu lại là `XCUIElementTypeOther[1]`
- Với trường hợp chỉ có 1 element, ta có thể bỏ qua index, ví dụ: `".../XCUIElementTypeOther"`

##### Lấy Element bằng XPath Kết hợp Contains:

- Tương tự cách lấy bằng `xpath`, tuy nhiên ta xem ví dụ sau:

![Xpath Contains](https://github.com/hominhtuong/appium/blob/master/resources/xpath-contains.png)

- Nếu muốn lấy nhanh bằng `xpath`, ta sẽ dùng như sau:
```java
String xpath = "(//XCUIElementTypeOther[@name="Phiên bản 5.10.0"])[1]";
IOSElement element = (IOSElement) driver.findElementByXPath(xpath);
```

- Tuy nhiên, con số `5.10.0` có thể sẽ thay đổi qua các phiên bản của ứng dụng, nên ta sẽ có cách kết hợp như sau:

```java
String xpath = "(//XCUIElementTypeOther[contains(@name, 'Phiên bản')])[1]";
IOSElement element = (IOSElement) driver.findElementByXPath(xpath);
```

- Ý nghĩa ở đây là ta sẽ tìm `xpath` với `type` là `XCUIElementTypeOther`, index thứ [1], và có chứa `name` là `"Phiên bản"`
- Tương tự với các element là email ta sẽ gán `contains(@name, '@')` hay số điện thoại `contains(@name, '0')` với điều kiện chúng ta biết chắc chắn element đó có chứa ký tự hoặc từ đó.

###### Bên trên là 3 cách lấy element căn bản và được dùng thường xuyên, đủ để chúng ta lấy element mà viết script.

[Tham khảo thêm](http://appium.io/docs/en/writing-running-appium/ios/ios-predicate/)