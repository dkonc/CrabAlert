package com.crabalert;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.inject.Inject;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.NPCManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class CrabAlertOverlay extends Overlay
{
    private static final int MAIDEN_NPC_ID = 10814;
    private static final int MAIDEN_HEALTH_THRESHOLD = 120;
    private static final int FLASH_DURATION = 100;
    private final CrabAlertConfig config;
    private final Client client;
    private int flashCounter = 0;
    private boolean flashedOnce = true;

    @Inject
    public CrabAlertOverlay(Client client, CrabAlertConfig config)
    {
        this.config = config;
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Inject
    private NPCManager npcManager;


    @Override
    public Dimension render(Graphics2D graphics)
    {
        NPC maiden = client.getNpcs().get(0);
        int herHealth = maiden.getHealthRatio();
        String herHealthString = String.valueOf(herHealth);
        if (flashCounter > 0)
        {
            flashCounter--;
        }
        else if (maiden != null && maiden.getHealthRatio() <= MAIDEN_HEALTH_THRESHOLD && maiden.getHealthRatio() != -1 && flashedOnce)
        {
            flashCounter = config.duration();
            flashedOnce = false;
        }
        if (flashCounter > 0)
        {
            int width = client.getCanvas().getWidth();
            int height = client.getCanvas().getHeight();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D imageGraphics = image.createGraphics();
            imageGraphics.setColor(config.flash_color());
            imageGraphics.fillRect(0, 0, width, height);
            graphics.drawImage(image, 0, 0, null);
        }

        return null;
    }

}