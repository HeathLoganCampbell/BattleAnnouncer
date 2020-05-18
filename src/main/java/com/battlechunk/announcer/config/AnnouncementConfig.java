package com.battlechunk.announcer.config;

import com.battlechunk.announcer.commons.EasyConfig;
import com.battlechunk.announcer.data.Announcement;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class AnnouncementConfig extends EasyConfig
{
    private List<Announcement> announcements = new ArrayList<>();

    public AnnouncementConfig( JavaPlugin plugin)
    {
        super("Announcements", plugin);

        reloadAnnouncementsList();
    }

    protected void onCreate()
    {
        this.getModifier().set("Announcements.0", Arrays.asList(
                "&b] ==----",
                "&lWelcome to my server!",
                "&b] =====-------"
        ));
    }

    protected void reloadAnnouncementsList()
    {
        this.announcements.clear();
        Set<String> keys = this.getModifier().getConfigurationSection("Announcements").getKeys(false);
        for (String key : keys)
        {
            List<String> list = this.getModifier().getStringList("Announcements." + key);
            String[] lines = new String[list.size()];
            for (int i = 0; i < list.size(); i++)
            {
                lines[i] = list.get(i);
            }

            announcements.add(new Announcement(lines));
        }
    }

    public Announcement getAnnouncement(int index)
    {
        return this.announcements.get(index % this.announcements.size());
    }

    public int size()
    {
        return this.announcements.size();
    }

    @Override
    public void reload()
    {
        super.reload();
        reloadAnnouncementsList();
    }
}
