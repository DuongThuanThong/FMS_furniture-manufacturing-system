plugins {
    java
    id("org.springframework.boot") version "3.5.14" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
}

allprojects { // Cấu hình này áp dụng cho root project và tất cả subproject.
    group = "com.uth.fms"
    version = "1.0.0"
    repositories {
        mavenCentral()
    }
}

subprojects { //Cấu hình này áp dụng cho tất cả subproject
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management") //Kích hoạt cho phép dùng BOM

    java {
        sourceCompatibility = JavaVersion.VERSION_17
    }

    //Cung cấp cho biết BOM chứa các dependency tương thichs phiên bảng spring=boot: 3.5.14
    configure<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension> {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.14")
        }
    }

    dependencies {
        // MapStruct
        implementation("org.mapstruct:mapstruct:1.5.5.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
        annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
        //Lomok
        implementation("org.projectlombok:lombok:1.18.32")
        annotationProcessor("org.projectlombok:lombok:1.18.32")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        
    }

//    tasks.withType<Test> {
//        useJUnitPlatform()
//    }
}