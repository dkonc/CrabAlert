package com.crabalert;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;


@PluginDescriptor(
		name = "Crab Alert",
		description = "Displays a flashing screen when the Maiden boss in ToB reaches 75% of her hitpoints",
		tags = {"theatre", "of", "blood", "maiden", "alert"}
)
public class CrabAlertPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private CrabAlertOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private CrabAlertConfig config;

	@Provides
	CrabAlertConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CrabAlertConfig.class);
	}

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}


}