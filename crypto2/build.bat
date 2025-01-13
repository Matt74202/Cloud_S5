@echo off
setlocal

:: Paramètres de configuration
set PROJECT_DIR=C:\Users\nalya\OneDrive\Bureau\IT\S5\Web\Cloud_S5\crypto2
set TOMCAT_DIR="C:\Program Files\Apache Software Foundation\Tomcat 10.1"
set WEB_INF_DIR=%PROJECT_DIR%\web\WEB-INF
set WEB_CLASSES_DIR=%WEB_INF_DIR%\classes
set WEB_LIB_DIR=%WEB_INF_DIR%\lib
set WAR_FILE=%PROJECT_DIR%\dist\crypto.war
set BUILD_DIR=%PROJECT_DIR%\build
set SRC_DIR=%PROJECT_DIR%\src\java

:: Vérifier que le répertoire WEB-INF/classes existe
if not exist "%WEB_CLASSES_DIR%" (
    echo Le répertoire %WEB_CLASSES_DIR% n'existe pas. Veuillez vérifier votre structure de projet.
    exit /b 1
)

:: Nettoyer le répertoire de construction
echo Nettoyage du répertoire de construction...
rd /s /q %BUILD_DIR%
mkdir %BUILD_DIR%

:: Vérifier s'il y a des fichiers Java à compiler dans les répertoires model et controller
set FILES_FOUND=false

for %%f in (%SRC_DIR%\controller\*.java) do set FILES_FOUND=true
for %%f in (%SRC_DIR%\model\*.java) do set FILES_FOUND=true

if "%FILES_FOUND%"=="false" (
    echo Aucun fichier Java trouvé dans les répertoires model et controller. La compilation est ignorée.
) else (
    :: Préparer le classpath avec les JAR dans WEB-INF/lib
    set CLASSPATH=%WEB_LIB_DIR%\*;%SRC_DIR%\controller\;%SRC_DIR%\model\;

    :: Compiler les fichiers Java
    echo Compilation des fichiers Java...
    javac -d %WEB_CLASSES_DIR% -classpath %CLASSPATH% %SRC_DIR%\controller\*.java %SRC_DIR%\model\*.java

    :: Vérifier si la compilation a réussi
    if errorlevel 1 (
        echo Erreur lors de la compilation des fichiers Java.
        exit /b 1
    )
)

:: Créer le fichier WAR
echo Création du fichier WAR...
jar -cvf %WAR_FILE% -C %PROJECT_DIR%\web .

:: Vérifier si le fichier WAR a été créé
if not exist "%WAR_FILE%" (
    echo Erreur lors de la création du fichier WAR.
    exit /b 1
)

:: Copier le fichier WAR dans le répertoire webapps de Tomcat
echo Déploiement du fichier WAR sur Tomcat...
copy /y %WAR_FILE% %TOMCAT_DIR%\webapps\

:: Lancer Tomcat (assurez-vous que Tomcat est bien configuré)
echo Démarrage de Tomcat...
start %TOMCAT_DIR%\bin\startup.bat

echo Déploiement terminé et Tomcat démarré.

endlocal
