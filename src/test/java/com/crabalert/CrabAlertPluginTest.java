package com.crabalert;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CrabAlertPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CrabAlertPlugin.class);
		RuneLite.main(args);
	}
}