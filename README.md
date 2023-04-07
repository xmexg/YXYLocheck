# YXYLocheck
优学院定位签到解决方案  

# 现状

 - 目前没有自动化脚本,只能手机通过burp流量,在burp里抓取提交定位签到的数据包,修改其中学号和位置实现签到  
其中用于身份验证的 `Authorization` 只需是个有效的即可,不需是要签到的学号的  

 - 获取经纬度 https://api.map.baidu.com/lbsapi/getpoint/index.html  

# 抓包记录
[抓包记录](./抓包记录/抓包数据.md)
[逆向分析](./抓包记录/逆向笔记.md)
