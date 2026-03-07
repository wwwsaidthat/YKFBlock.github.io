# 将含视频网页URL转换成视频URL的小调查

## 网站1 ： vimeo

1. 网站介绍：Vimeo 是一个 高清视频托管平台 ，与 YouTube 类似，但它更侧重于服务专业创作者、艺术家和电影制作人 。

2. ```python
   import json
   import re
   from .base import fetch, Extractor
   
   
   def _id_from_url(url: str) -> str | None:
       #用来获取视频的id eg：https://vimeo.com/76979871 id就是后面的那串数字
       m = re.search(r'vimeo\.com/(?:video/)?(\d+)', url)
       if m:
           return m.group(1)
       try:
           html = fetch(url).decode("utf-8", errors="ignore")
       except Exception:
           return None
       m = re.search(r'player\.vimeo\.com/video/(\d+)', html)
       return m.group(1) if m else None
   
   def extract(url: str) -> list[str]:
       # 构造一个新的url 比原来的url更加稳定可以解析出来 
       # 这是一个 Vimeo 播放器内部使用的接口，会返回一个包含该视频所有流媒体信息的 JSON 数据包。这个接口比解析 HTML 更稳定、更高效。
       vid = _id_from_url(url)
       if not vid:
           return []
       cfg_url = f"https://player.vimeo.com/video/{vid}/config"
       try:
           raw = fetch(cfg_url, referer=url)
           config = json.loads(raw.decode("utf-8", errors="ignore"))
       except Exception:
           return []
       return _links_from_config(config)
   
   def _links_from_config(config: dict) -> list[str]:
       links = []
       try:
           files = config.get("request", {}).get("files", {})
           if isinstance(files, dict):
               hls = files.get("hls")
               if isinstance(hls, dict):
                   cdns = hls.get("cdns", {})
                   for v in cdns.values():
                       u = v.get("url")
                       if u:
                           links.append(u)
               progressive = files.get("progressive")
               if isinstance(progressive, list):
                   for p in progressive:
                       u = p.get("url")
                       if u:
                           links.append(u)
       except Exception:
           pass
       text = json.dumps(config, ensure_ascii=False)
       for m in re.findall(r'https?://[^\s"\']+?\.m3u8[^\s"\']*', text):
           links.append(m)
       return list(dict.fromkeys(links))
   
   
   #正常的逻辑是这样的，但是实际编程第二个函数要放在下面因为他要调用第三个函数
   
   
   ```

3. 原理：根据网站的url构造出这个网站特有的接口的url可以返回json，解析json，并获取其中包含视频的url 



## 网站2：Bilibili

音频视频是分开的流

策略1：尝试从页面源代码中的 window.__playinfo__ JSON 对象中直接提取视频和音频流地址。这是最快且最稳定的方法，因为它不需要额外的 API 请求。从页面源码直接提取

策略2：如果第一种方法失败，提取视频的 CID 和 BVID ，然后调用 Bilibili 的官方 API ( api.bilibili.com/x/player/playurl ) 来获取播放地址。

上面这两种都在<script>里面的json格式里面提取

## 







当前的网页里面根本就没有视频的url，没法直接提取，而是需要根据网站url提取出来视频id，再根据这个视频id构造出来api，获得返回的json



如果网页是通过一个一个小的切片连在一起合成的，这种没办法直接拿到



动态生成的url也是没办法直接拿到的，有些网站的视频 URL 是通过复杂的 JavaScript 逻辑动态生成，或者在页面加载后通过 AJAX 请求获取的。对于这类网站，静态分析 HTML 是无效的，通常需要使用浏览器自动化工具（如 Selenium, Puppeteer）来模拟用户行为并拦截网络流量。