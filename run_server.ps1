# ✨ 한마츠 카와이 서버 실행 스크립트 ✨

$M2_REPO = "C:\Users\aik50\.m2\repository"
$TOMCAT_LIB = "C:\Program Files\Java\apache-tomcat-9.0.89\lib"
$TOMCAT_BIN = "C:\Program Files\Java\apache-tomcat-9.0.89\bin"
$PROJECT_ROOT = "C:\jspstudy26\hanmatsu"
$OUTPUT_DIR = "$PROJECT_ROOT\target\classes"

if (!(Test-Path $OUTPUT_DIR)) {
    New-Item -ItemType Directory -Path $OUTPUT_DIR
}

# 1. 라이브러리 목록 (JETTY + TOMCAT JSP 라이브러리 믹스!)
$LIBS = @(
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
    "$TOMCAT_LIB\servlet-api.jar",
    "$TOMCAT_LIB\jsp-api.jar",
    "$TOMCAT_LIB\el-api.jar",
    "$TOMCAT_LIB\jasper.jar",
    "$TOMCAT_LIB\jasper-el.jar",
    "$TOMCAT_LIB\ecj-4.20.jar",
    "$M2_REPO\com\oracle\database\jdbc\ojdbc8\19.3.0.0\ojdbc8-19.3.0.0.jar",
    "$M2_REPO\servlets\com\cos\05Nov2002\cos-05Nov2002.jar",
    "$M2_REPO\org\mindrot\jbcrypt\0.4\jbcrypt-0.4.jar",
    "$TOMCAT_LIB\annotations-api.jar",
    "$TOMCAT_LIB\catalina.jar",
    "$TOMCAT_BIN\tomcat-juli.jar",
    "$TOMCAT_LIB\tomcat-api.jar",
    "$TOMCAT_LIB\tomcat-util.jar",
    "$TOMCAT_LIB\tomcat-util-scan.jar",
    "$M2_REPO\javax\servlet\jstl\1.2\jstl-1.2.jar"
)

$CP = ($LIBS -join ";") + ";$OUTPUT_DIR"

# 2. 프로젝트 소스 컴파일 (필요한 경우)
Write-Host "🎨 프로젝트 소스 컴파일 중..." -ForegroundColor Cyan
$SOURCES = Get-ChildItem -Path "$PROJECT_ROOT\src\main\java" -Filter "*.java" -Recurse | Select-Object -ExpandProperty FullName
javac -encoding UTF-8 -d $OUTPUT_DIR -cp $CP $SOURCES

if ($LASTEXITCODE -ne 0) {
    Write-Host "❌ 컴파일 실패! 에러를 확인해 주세요." -ForegroundColor Red
    exit
}

# 3. 서버 실행
Write-Host "🚀 서버 실행 중... http://localhost:8893/hanmatsu/main.do" -ForegroundColor Green
java -cp $CP test.SimpleServer
