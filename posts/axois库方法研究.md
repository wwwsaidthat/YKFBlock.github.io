# axios

1. axios需要安装的 具有强大的拦截器功能

2. 请求拦截器

   1. 可以获取当前的token，如果当前是登录的状态那么会有正确的token反之会被拦截

   2. Builder

      await 并不是 fetch 专用的。**任何返回 Promise 的函数，都可以（也应该）在前面加 await。**

      在前端开发中，除了 fetch，还有很多常见的场景会用到 await：

      **逻辑**: “每次出门前，检查口袋里有没有通行证 (Token)”。

      **作用**: 这样你就不用在每个页面（比如获取用户信息、订单列表）都手动写代码去传 Token 了，Axios 会自动帮你带上

      HTTP协议是健忘的，每次访问都需要把那个token附带上，否则不知道是谁了

3. 响应拦截器

   1. 如果正常 就直接返回
   2. 如果请求头不正确 就处理错误

   

对比：

```js
const response = await fetch('/api/login', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ ... })
})
const data = await response.json()
```

```js
import request from '../utils/request'

// 只有一行代码，参数直接传对象，不用 stringify，不用手动解析 JSON
const response = await request.post('/login', { ... })
const data = response.data
```

