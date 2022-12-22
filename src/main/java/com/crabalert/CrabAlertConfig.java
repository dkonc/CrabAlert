package com.crabalert;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;

@ConfigGroup("CrabAlert")
public interface CrabAlertConfig extends Config
{
	@ConfigItem(
		keyName = "duration",
		name = "Duration of the flash (in ms)",
		description = "Specify for how long do you want the duration to be"
	)
	default int duration()
	{
		return 100;
	}

	@ConfigItem(
			keyName = "flash_color",
			name = "Color of the flash",
			description = "Select what color do you want the flash to be"
	)
	@Alpha
	default Color flash_color()
	{
		return new Color(255, 255, 255, 128);
	}

}
