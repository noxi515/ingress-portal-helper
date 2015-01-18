Ingress Portal Helper
=====================

[このIITCプラグイン](https://github.com/noxi515/iitc-plugin-portal-data-sender)と一緒に使います。


## これは何をするもの？

ポータル情報の収集と、エクスポートを行います。


## 動作に必要なもの

* JavaSE Development Kit 8
* MySQL 5.7

MySQLは5.7で無くても良いが、その場合テーブルをMyISAMで作成しないとGeometoryフィールドのINDEXが利用出来ないので悲惨な事になります。
PostgresSQLでも動くかも知れない？


## 動かし方

1. 適当な場所にクローンします。
2. /src/main/resource/application.yml_templateをapplication.ymlにリネームし、DB接続文字列などの設定を書き換えます。
3. mvn spring-boot:run で実行します。

