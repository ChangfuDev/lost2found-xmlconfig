package cn.sevenleave.xmlconfig.utils.plugins;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

/**
 * @author SevenLeave
 * @date 2018-08-06 11:25
 */
public class SerializablePlugin extends PluginAdapter {
    
    @Override
    public boolean validate(List<String> list) {
        return false;
    }
}
