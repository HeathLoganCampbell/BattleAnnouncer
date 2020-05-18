package com.battlechunk.announcer;

import com.battlechunk.announcer.commons.commandframework.Command;
import com.battlechunk.announcer.commons.commandframework.CommandArgs;
import com.battlechunk.announcer.commons.commandframework.CommandFramework;
import com.battlechunk.announcer.config.AnnouncementConfig;
import com.battlechunk.announcer.data.Announcement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class AnnouncerPlugin extends JavaPlugin
{
    public static int BROADCAST_INTERVAL_SECS = 45;
    private CommandFramework commandFramework;
    private AnnouncementConfig announcementConfig;
    private int index = 0;

    @Override
    public void onEnable()
    {
        this.commandFramework = new CommandFramework(this);
        this.announcementConfig = new AnnouncementConfig(this);

        Bukkit.getScheduler().runTaskTimer(this, () ->
                        this.announcementConfig.getAnnouncement(index++).broadcastMessage()
                , BROADCAST_INTERVAL_SECS * 20, BROADCAST_INTERVAL_SECS * 20);

        this.commandFramework.registerCommands(this);
    }

    @Override
    public void onDisable()
    {

    }

    @Command(name = "Announcer.reload", aliases = { "battleAnnouncer.reload" }, description = "Refersh from config", permission = "battleAnnouncer.command.reload")
    public void onAnnouncer(CommandArgs args)
    {
        this.announcementConfig.reload();
        args.getSender().sendMessage(ChatColor.GREEN + "reloaded!");
    }

    @Command(name = "Announcer.display", inGameOnly = true, aliases = { "battleAnnouncer.display" }, description = "Refersh from config", permission = "battleAnnouncer.command.display")
    public void onDisplay(CommandArgs args)
    {
        for (int i = 0; i < this.announcementConfig.size(); i++) {
            this.announcementConfig.getAnnouncement(i).broadcastPlayer(args.getPlayer());
        }
    }
}
