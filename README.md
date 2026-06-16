# Cafe Management System

Hệ thống quản lý quán Cafe xây dựng với JavaFX và SQL Server

## Yêu cầu
- Java 11+
- Maven 3.6+
- SQL Server 2019+

## Cài đặt

1. Clone repository:
```bash
git clone https://github.com/tranquangkhanh74qt-stack/CafeManagementSystem.git
cd CafeManagementSystem
```

2. Cấu hình Database:
   - Chạy script `CafeDB_Schema.sql` trên SQL Server
   - Cập nhật thông tin kết nối trong `src/main/java/org/example/demo/database/ConnectionManager.java`

3. Build project:
```bash
mvn clean install
```

4. Chạy ứng dụng:
```bash
mvn javafx:run
```

## Tính năng

### 1. Quản lý Nhân viên
- Xem danh sách nhân viên
- Thêm nhân viên mới
- Sửa thông tin nhân viên
- Xóa nhân viên

### 2. Quản lý Sản phẩm
- Xem danh sách sản phẩm
- Thêm sản phẩm mới
- Sửa thông tin sản phẩm
- Xóa sản phẩm

### 3. Quản lý Nguyên liệu
- Xem danh sách đơn hàng/nguyên liệu
- Quản lý trạng thái đơn hàng

### 4. Báo cáo Doanh thu
- Tổng doanh thu
- Tổng số hóa đơn
- Giá trị trung bình đơn hàng
- Lợi nhuận dự kiến

### 5. Dashboard
- Thống kê tổng quan
- Top sản phẩm bán chạy
- Tóm tắt doanh thu

## Cấu trúc Project

```
src/main/java/org/example/demo/
├── Main.java
├── controller/
│   └── MainController.java
├── database/
│   └── ConnectionManager.java
├── model/
│   ├── Category.java
│   ├── Product.java
│   ├── Customer.java
│   ├── Order.java
│   └── OrderDetail.java
├── dao/
│   ├── CategoryDAO.java
│   ├── ProductDAO.java
│   ├── CustomerDAO.java
│   └── OrderDAO.java
└── view/
    ├── LoginWindow.java
    ├── MainWindow.java
    ├── DashboardView.java
    ├── CustomerView.java
    ├── ProductView.java
    ├── OrderView.java
    └── ReportView.java
```

## Tác giả
Trần Quang Khánh

## License
MIT
