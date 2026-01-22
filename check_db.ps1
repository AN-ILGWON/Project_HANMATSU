$M2_REPO = "C:\Users\aik50\.m2\repository"
$PROJECT_ROOT = "C:\jspstudy26\hanmatsu"
$OUTPUT_DIR = "$PROJECT_ROOT\target\classes"

if (!(Test-Path $OUTPUT_DIR)) {
    New-Item -ItemType Directory -Path $OUTPUT_DIR
}

$LIBS = @(
    "$M2_REPO\com\oracle\database\jdbc\ojdbc8\19.3.0.0\ojdbc8-19.3.0.0.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-server\9.4.44.v20210927\jetty-server-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-servlet\9.4.44.v20210927\jetty-servlet-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-webapp\9.4.44.v20210927\jetty-webapp-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-util\9.4.44.v20210927\jetty-util-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-http\9.4.44.v20210927\jetty-http-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-io\9.4.44.v20210927\jetty-io-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-xml\9.4.44.v20210927\jetty-xml-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-security\9.4.44.v20210927\jetty-security-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\apache-jsp\9.4.44.v20210927\apache-jsp-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-annotations\9.4.44.v20210927\jetty-annotations-9.4.44.v20210927.jar",
    "$M2_REPO\org\eclipse\jetty\jetty-plus\9.4.44.v20210927\jetty-plus-9.4.44.v20210927.jar",
    "$M2_REPO\org\ow2\asm\asm\9.2\asm-9.2.jar",
    "$M2_REPO\org\ow2\asm\asm-commons\9.2\asm-commons-9.2.jar",
    "$M2_REPO\org\ow2\asm\asm-tree\9.2\asm-tree-9.2.jar",
    "$PROJECT_ROOT\src\main\webapp\WEB-INF\lib\cos-05Nov2002.jar",
    "$PROJECT_ROOT\src\main\webapp\WEB-INF\lib\jbcrypt-0.4.jar",
    "$PROJECT_ROOT\src\main\webapp\WEB-INF\lib\jstl-1.2.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\servlet-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\jasper.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\jsp-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\annotations-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\ecj-4.20.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\el-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\jasper-el.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\tomcat-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\tomcat-util.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\tomcat-util-scan.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\bin\tomcat-juli.jar"
)

$EXISTING_LIBS = $LIBS | Where-Object { Test-Path $_ }
$CP = ($EXISTING_LIBS -join ";") + ";$OUTPUT_DIR"

Write-Host "Compiling all classes..."
$javaFiles = Get-ChildItem -Path "$PROJECT_ROOT\src\main\java" -Filter *.java -Recurse | ForEach-Object { $_.FullName }
javac -encoding UTF-8 -d "$OUTPUT_DIR" -cp "$CP" $javaFiles

if ($LASTEXITCODE -eq 0) {
    Write-Host "Running DBCheck..."
    java -cp "$CP" test.DBCheck
} else {
    Write-Host "Compilation failed."
}