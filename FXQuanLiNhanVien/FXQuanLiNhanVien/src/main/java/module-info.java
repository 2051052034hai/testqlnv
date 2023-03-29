module com.nvn.fxquanlinhanvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires java.desktop;
    requires javafx.base;
    requires javafx.graphics;
    opens com.nvn.fxquanlinhanvien to javafx.fxml;
    exports com.nvn.fxquanlinhanvien;
    exports com.pojo;
}
