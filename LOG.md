# 记录日常

### 2023/11/17
针对apache tika.core.jar包中识别文件类型的接口进行了使用
```
    new Tika().detect()
```
对于OLE2对象，不在意性能的话，引入 tika.parser 包，以更准确判断文件类型
* [Apache Tika](https://tika.apache.org/)
* [Apache Tika Content Detection](https://tika.apache.org/2.9.1/detection.html)
* [Apache Tika Supported Document Formats](https://tika.apache.org/2.9.1/formats.html)