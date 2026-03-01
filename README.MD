# jsp-obfuscator-plugin-example


你可以通过实现以下扩展点来扩展功能：

| 扩展点 | 说明     |
|--------|--------|
| `GadgetExtension` | 代码片段生成 |
| `EditPageExtension` | 页面内容编辑 |
| `OutputPageExtension` | 输出结果处理 |
| `PageFactoryExtension` | 页面类型工厂 |

## 简单示例

### 安装依赖

需要先安装正确的`jsp-obfuscator`的`api`模块依赖:

```bash
mvn install:install-file -Dfile=path/to/api-xxx.jar
```

（可选）如有需要也可安装`plugin-common`模块依赖:
```bash
mvn install:install-file -Dfile=path/to/plugin-common-xxx.jar
```

安装的版本需要和`pom.xml`指定的版本保持一致，否则需要修改`pom.xml`中`com.t3stzer0.jspobfuscator`的版本号。

### 文件组成

一个完整的Gadget插件需要包含：插件主类 + 扩展实现类（可多个） + 配置文件

**文件 1：MyPlugin.java（插件主类）**

```java
package com.example.myplugin;

import org.pf4j.Plugin;

public class MyPlugin extends Plugin {
    @Override
    public void start() {
        System.out.println("My Plugin started");
    }
    
    @Override
    public void stop() {
        System.out.println("My Plugin stopped");
    }
}
```

**文件 2：YourExtension.java（扩展实现类）**

参考 extensions 目录： `src/main/java/com/example/plugin/extensions/`

以 `EditPageExtension` 为例，作用为将代码全部变为大写字母:

```java
package com.example.plugin.extensions;

import com.t3stzer0.jspobfuscator.api.page.Page;
import com.t3stzer0.jspobfuscator.api.page.node.CodePageNode;
import com.t3stzer0.jspobfuscator.api.plugin.obfus.EditPageExtension;
import com.t3stzer0.jspobfuscator.api.plugin.context.ExtensionContext;
import org.pf4j.Extension;

@Extension
public class EditPageExtensionExample implements EditPageExtension {
    @Override
    public void editPage(ExtensionContext ctx, Page page) {
        // 遍历页面所有节点
        for (var node : page.getNodes()) {
            if (node instanceof CodePageNode) {
                String text = node.getText();
                // 你的处理逻辑：转换为大写
                node.setText(text.toUpperCase());
            }
        }
    }
}
```

**文件 3：plugin.properties（配置文件）**

```properties
plugin.id=my-plugin
plugin.class=com.example.myplugin.MyPlugin
plugin.version=0.0.1
plugin.provider=YourName
```

## 使用插件
打包成 jar，放到 `plugins` 目录重启即可自动加载。

## 依赖

- Java 17+
- pf4j 3.0.0+
