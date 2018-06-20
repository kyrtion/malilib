package fi.dy.masa.malilib.config.options;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import fi.dy.masa.malilib.LiteModMaLiLib;
import fi.dy.masa.malilib.config.ConfigType;
import fi.dy.masa.malilib.util.StringUtils;

public class ConfigColor extends ConfigInteger
{
    public ConfigColor(String name, String defaultValue, String comment)
    {
        super(name, StringUtils.getColor(defaultValue, 0), comment);
    }

    @Override
    public ConfigType getType()
    {
        return ConfigType.COLOR;
    }

    @Override
    public String getStringValue()
    {
        return String.format("0x%08X", this.getIntegerValue());
    }

    @Override
    public String getDefaultStringValue()
    {
        return String.format("0x%08X", this.getDefaultIntegerValue());
    }

    @Override
    public void setValueFromString(String value)
    {
        this.setIntegerValue(StringUtils.getColor(value, 0));
    }

    @Override
    public void setValueFromJsonElement(JsonElement element)
    {
        try
        {
            if (element.isJsonPrimitive())
            {
                this.setValueFromString(element.getAsString());
            }
            else
            {
                LiteModMaLiLib.logger.warn("Failed to set config value for '{}' from the JSON element '{}'", this.getName(), element);
            }
        }
        catch (Exception e)
        {
            LiteModMaLiLib.logger.warn("Failed to set config value for '{}' from the JSON element '{}'", this.getName(), element, e);
        }
    }

    @Override
    public JsonElement getAsJsonElement()
    {
        return new JsonPrimitive(this.getStringValue());
    }
}