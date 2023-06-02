# SocialMedia
jar файл доступен для скачивания по ссылке: https://www.dropbox.com/s/2kgzs2xpb8ytz20/SocialMedia-0.0.1-SNAPSHOT.jar?dl=0
В файле application.properties измените путь до директории upload-dir=D:/image на папку image на локальной машине.
Папка необходима для загрузки изображений, прикрепленных к post, в базе данных будет хранится только ссылка.
Для запуска наобходимо набрать в командной строке:
java -jar <ПУТЬ К ФАЙЛУ>SocialMedia-0.0.1-SNAPSHOT.jar
Пример:
java -jar /home/user/SocialMedia-0.0.1-SNAPSHOT.jar - для Linux
java -jar C:\Users\user\SocialMedia-0.0.1-SNAPSHOT.jar - для Windows
java -jar /Users/user/SocialMedia-0.0.1-SNAPSHOT.jar - для macOS
