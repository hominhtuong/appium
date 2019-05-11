## Thứ tự thực thi các annotations trong testNG
_Hiểu được trình tự thực thi của các annotations sẽ giúp chúng ta bố cục test class được tốt hơn._
#### Annotations (Chú thích) được sử dụng để cung cấp thông tin dữ liệu cho mã Java của bạn. Trong testNG có các annotations cơ bản như sau:
 

1. @BeforeSuite: Được chạy đầu tiên, bước này chúng ta nên khởi tạo các thư mục, report file, log file,... nếu có.
2. @BeforeTest: Được chạy tiếp theo @beforesuite, bước này chúng ta nên khởi động Appium và  khởi tạo Driver.
3. @BeforeClass: Được chạy sau @beforetest, và trước @beforeMethod.
4. @BeforeMethod: Sẽ được chạy trước mỗi method
5. @Test: Khi thực hiện 1 testcase 
6. @BeforeGroups: Được gọi khi @Test có group giống với group name. Sẽ được gọi trước khi gọi function test.
7. @AfterGroups: Được gọi sau khi kết thúc testgroup.
8. @AfterMethod: Được chạy sau mỗi method.
9. @AfterClass: Sau khi kết thúc testclass
10. @AfterTest: Được gọi sau khi đã chạy xong các testcases, gọi sau @afterclass. Ở bước này chúng ta nên shutdown appium và Driver.
11. @AfterSuite: Được gọi sau cùng. Lúc này có thể flush và close file report.


#### Ví dụ:

```java
public class TestClass {

    @BeforeGroups("security")
    public void setUpSecurity() {
        System.out.println("setUpSecurity");
    }

    @AfterGroups("security")
    public void tearDownSecurity() {
        System.out.println("tearDownSecurity");
    }

    @BeforeGroups("database")
    public void setUpDatabase() {
        System.out.println("setUpDatabase");
    }

    @AfterGroups("database")
    public void tearDownDatabase() {
        System.out.println("tearDownDatabase");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("beforeSuite");
    }
    
    @BeforeClass
    public void beforeClass() {
        System.out.println("beforeClass");
    }
        
    @AfterClass
    public void afterClass() {
        System.out.println("afterClass");
    }
    
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("beforeMethod");
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("afterMethod");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("beforeTest");
    }

    @AfterTest
    private void afterTest() {
        System.out.println("afterTest");
    }
    
    @AfterSuite
    public void afterSuite(){
        System.out.println("afterSuite");
    }
    
    @Test(groups = "security")
     public void Test1(){
        System.out.println("Test1");
    }
    @Test(groups = "database")
    public void Test2(){
        System.out.println("Test2");
    }
}

```
##### Output:
```
 > beforeSuite
 > beforeTest
 > beforeClass
 > setUpSecurity
 > beforeMethod
 > Test1
 > afterMethod
 > tearDownSecurity
 > setUpDatabase
 > beforeMethod
 > Test2
 > afterMethod
 > tearDownDatabase
 > afterClass
 > afterTest
 > afterSuite
 
```
