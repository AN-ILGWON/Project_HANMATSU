$M2_REPO = "C:\Users\aik50\.m2\repository"
$PROJECT_ROOT = "C:\jspstudy26\hanmatsu"
$OUTPUT_DIR = "$PROJECT_ROOT\target\classes"

if (!(Test-Path $OUTPUT_DIR)) {
    New-Item -ItemType Directory -Path $OUTPUT_DIR
}

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
    "$PROJECT_ROOT\src\main\webapp\WEB-INF\lib\cos-05Nov2002.jar",
    "$PROJECT_ROOT\src\main\webapp\WEB-INF\lib\jbcrypt-0.4.jar",
    "$PROJECT_ROOT\src\main\webapp\WEB-INF\lib\jstl-1.2.jar",
    "$PROJECT_ROOT\src\main\webapp\WEB-INF\lib\ojdbc8-19.3.0.0.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\servlet-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\jasper.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\jsp-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\el-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\jasper-el.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\tomcat-util.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\tomcat-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\tomcat-util-scan.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\bin\tomcat-juli.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\annotations-api.jar",
    "$PROJECT_ROOT\tomcat\apache-tomcat-9.0.100\lib\ecj-4.20.jar"
)

$EXISTING_LIBS = $LIBS | Where-Object { (Test-Path $_) -and !($_.Contains("oraclepki.jar")) }
$CP = ($EXISTING_LIBS -join ";") + ";$OUTPUT_DIR"

Write-Host "Compiling all classes..."
if (!(Test-Path "target\classes")) { New-Item -ItemType Directory -Force -Path "target\classes" }
& javac -encoding UTF-8 -d target\classes -cp $CP (Get-ChildItem -Path src\main\java -Filter *.java -Recurse | Select-Object -ExpandProperty FullName)

if ($LASTEXITCODE -eq 0) {
    Write-Host "Starting Server in background..."
    $pid8895 = netstat -ano | findstr :8895 | ForEach-Object { $_.Split(' ', [System.StringSplitOptions]::RemoveEmptyEntries)[-1] } | Select-Object -First 1
    if ($pid8895) {
        Write-Host "Killing process on 8895: $pid8895"
        taskkill /F /PID $pid8895
    }
    
    Write-Host "Running java with optimized arguments..."
    $JAVA_CMD = "java"
    $ARG_LIST = @(
        "-Dorg.apache.tomcat.util.descriptor.tld.TldParser.skipValidation=true",
        "-Dorg.apache.jasper.compiler.TldLocationsCache.noTldNextTime=true",
        "-Dorg.eclipse.jetty.annotations.AnnotationConfiguration.maxScanWait=1",
        "-Dtomcat.util.scan.StandardJarScanner.jarsToSkip=*.jar",
        "-Dtomcat.util.scan.DefaultJarScanner.jarsToSkip=*.jar",
        "-cp",
        "$CP",
        "test.SimpleServer"
    )
    $JAVA_ARGS_STR = ($ARG_LIST -join " ")
    Write-Host "Command: $JAVA_CMD $JAVA_ARGS_STR"
    # Run java and redirect output to a file to ensure we catch everything
    & $JAVA_CMD $ARG_LIST > server.log 2>&1
    Write-Host "Java process exited with code $LASTEXITCODE"
} else {
    Write-Host "Compilation failed."
}
