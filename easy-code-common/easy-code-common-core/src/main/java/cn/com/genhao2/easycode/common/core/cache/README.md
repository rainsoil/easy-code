## 缓存处理

> 给cache缓存增加过期时间增强
> 增强方式: 在设置cachename的时候, 后面加# 然后设置失效时间和单位
>

例如:

```text

test_cache#1m
```

其他的并不变

## 目前实现的增强的类型有:

- caffeine