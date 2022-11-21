# spi扩展

## 获取扩展点

```text

Filter xss = ExtensionLoader.getExtensionLoader(Filter.class).getExtension("xss");

```

## 获取默认的扩展点

> 只有加了@SPI注解,并且指定value值的才有默认的扩展点, 否则没有

```text
Filter defaultExtensionFilter = ExtensionLoader.getExtensionLoader(Filter.class).getDefaultExtension();

```

## 获取所有扩展点的名称

```text
List<String> extensionNames = ExtensionLoader.getExtensionLoader(Filter.class).getExtensionNames();
```

## 判断是否包含扩展点

```text
ExtensionLoader.getExtensionLoader(Filter.class).hasExtension("xss")
```