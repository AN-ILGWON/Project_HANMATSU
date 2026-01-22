# ✨ 한마츠 전용 톰캣 서버 실행 스크립트 ✨

$PROJECT_ROOT = "C:\jspstudy26\hanmatsu"
$TOMCAT_HOME = "$PROJECT_ROOT\tomcat"
$JAVA_HOME = "C:\Program Files\Java\jdk-11" # 보통 이 경로에 있죠!

# 환경 변수 설정
$env:CATALINA_HOME = $TOMCAT_HOME
$env:JAVA_HOME = $JAVA_HOME

Write-Host "🚀 한마츠 전용 톰캣 서버 시작 중..." -ForegroundColor Green
Write-Host "🏠 CATALINA_HOME: $TOMCAT_HOME" -ForegroundColor Cyan
Write-Host "🌐 주소: http://localhost:8893/hanmatsu/main.do" -ForegroundColor Yellow

# 톰캣 실행 (별도 창이 아니라 현재 터미널에서 실행)
& "$TOMCAT_HOME\bin\catalina.bat" run

