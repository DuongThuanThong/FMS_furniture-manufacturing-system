plugins {
    `java-library`
}

dependencies {
    implementation(project(":fms-common"))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
