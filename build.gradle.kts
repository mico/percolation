plugins {
    // Apply the java plugin to add support for Java
    java
    application
    checkstyle
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    flatDir {
        dirs("lib")
    }
}

sourceSets["main"].java.srcDir("src/main")

checkstyle {
    toolVersion = "8.5"
}

tasks.checkstyleTest.configure {
    enabled = false
}

application {
    mainClassName = "PercolationStats"
}

dependencies {
    // This dependency is found on compile classpath of this component and consumers.
    implementation("com.google.guava:guava:27.0.1-jre")

    // Use TestNG framework, also requires calling test.useTestNG() below
    testImplementation("org.testng:testng:6.14.3")
    testImplementation("org.mockito:mockito-core:2.23.0")
    compile(fileTree("lib").matching{ include("*.jar") })
}

val test by tasks.getting(Test::class) {
    // Use TestNG for unit tests
    useTestNG()
}
