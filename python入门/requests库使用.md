# requests库使用

```
 requests使用举栗

安装

pip install requests


基本使用

  >>>import requests
  >>> r = requests.get('http://www.****.com')  # 发送请求
  >>> r.status_code  # 返回码 200
  >>> r.headers['content-type']  # 返回头部信息'text/html; charset=utf8'
  >>> r.encoding  # 编码信息'utf-8'
  >>> r.text  #内容部分（如果存在编码问题，也可以使用r.content，见下面的编码问题部分）
  u'<!DOCTYPE html>\n<html xmlns="http://www.***/xhtml"...'...


各种不同HTTP请求

  >>> r = requests.post("http://httpbin.org/post")
  >>> r = requests.put("http://httpbin.org/put")
  >>> r = requests.delete("http://httpbin.org/delete")
  >>> r = requests.head("http://httpbin.org/get")
  >>> r = requests.options("http://httpbin.org/get")


带参数的请求

  >>> payload = {'wd': '张亚楠', 'rn': '100'}
  >>> r = requests.get("http://www.baidu.com/s", params=payload)
  >>> print r.url
  u'http://www.baidu.com/s?rn=100&wd=%E5%BC%A0%E4%BA%9A%E6%A5%A0'

Note: 这里的params可以不用自己进行urlencode的。


获取json结果

  >>>r = requests.get('...'）
  >>>r.json()['data']['country']
  '中国'

>>> r = requests.get('https://github.com/timeline.json')
>>> r.json()
[{u'repository': {u'open_issues': 0, u'url': 'https://github.com/...

Note: 实际内容：{"message":"Hello there, wayfaring stranger......","documentation_url":"https://developer.github.com/v3/..."}

```

<!--
create time: 2018-06-13 12:41:25
Author: Alfred

This file is created by Marboo<http://marboo.io> template file $MARBOO_HOME/.media/starts/default.md
本文件由 Marboo<http://marboo.io> 模板文件 $MARBOO_HOME/.media/starts/default.md 创建
-->

