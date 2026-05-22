plugins {
    id("org.springframework.boot") // Module duy nhất kích hoạt Spring Boot plugin để tạo fat JAR và chạy ứng dụng
}

dependencies {
    // fms-common là thư viện dùng chung
    implementation(project(":fms-common"))

    // Các module nghiệp vụ 
    implementation(project(":fms-user"))
    implementation(project(":fms-order"))
    implementation(project(":fms-customer"))
    implementation(project(":fms-product"))
    implementation(project(":fms-inventory"))
    implementation(project(":fms-manufacturing"))

    runtimeOnly("org.postgresql:postgresql")
}
