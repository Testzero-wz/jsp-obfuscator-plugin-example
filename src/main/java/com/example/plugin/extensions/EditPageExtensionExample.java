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