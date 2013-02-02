ckipclient-java
===============

[Apache License Version 2.0]:http://www.apache.org/licenses/LICENSE-2.0.html

The CKIP (Chinese Knowledge and Information Processing) is a online service for separate Chinese sentence. The service maintain by [CKIP group](http://godel.iis.sinica.edu.tw/CKIP/) in [Sinica](http://www.sinica.edu.tw).

CKIPClient-Java is a client library that simplify the procedure of TCP scoket communication between client and CKIP server. Developer can use CKIPClient-Java to access CKIP service more easily.

The CKIPClient-Java licensed under [Apache License Version 2.0].

CKIP 是[中研院詞庫小組](http://godel.iis.sinica.edu.tw/CKIP/)開發的[中文斷詞系統](http://ckipsvr.iis.sinica.edu.tw/)，此系統採用線上服務模式，採用 TCP Scoket 傳送文本到 Server 進行斷詞，再接收斷詞結果。

此專案是將上述斷詞流程採用 Java 實作，提供使用者方便地使用 CKIP 服務。本人並非中研院成員或是詞庫小組相關人士，本人只是將之前撰寫的 Client 端程式碼公開，並嘗試以不同的程式語言實做。所有中文斷詞相關技術及問題請洽中研院詞庫小組。

CKIPClient-Java 採用 [Apache License Version 2.0] 釋出