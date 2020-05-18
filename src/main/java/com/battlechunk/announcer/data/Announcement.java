package com.battlechunk.announcer.data;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Announcement
{
    public String[] messages;

    public Announcement(String... messages) {
        this.messages = messages;
    }

    public void broadcastMessage()
    {
        for (String message : this.messages)
        {
            message = ChatColor.translateAlternateColorCodes('&', message);
            Bukkit.broadcastMessage(message);
        }
    }

    public void broadcastPlayer(Player player)
    {
        for (String message : this.messages)
        {
            message = ChatColor.translateAlternateColorCodes('&', message);
            player.sendMessage(message);
        }
    }
}
