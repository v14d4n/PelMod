package com.v14d4n.pelmenicraft.events;

import com.v14d4n.pelmenicraft.PelmeniCraft;
import com.v14d4n.pelmenicraft.commands.DisableNotificationsCommand;
import com.v14d4n.pelmenicraft.commands.EnableNotificationsCommand;
import com.v14d4n.pelmenicraft.config.PelmeniCraftConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.*;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.VersionChecker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.server.command.ConfigCommand;

import java.util.UUID;

import static net.minecraftforge.fml.VersionChecker.Status.BETA_OUTDATED;

@Mod.EventBusSubscriber(modid = PelmeniCraft.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new DisableNotificationsCommand(event.getDispatcher());
        new EnableNotificationsCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (PelmeniCraftConfig.showUpdates.get()) {
            IModInfo modInfo = ModList.get().getModContainerById(PelmeniCraft.MOD_ID).get().getModInfo();
            VersionChecker.CheckResult updateCheckResult = VersionChecker.getResult(modInfo);

            if (updateCheckResult.status().equals(BETA_OUTDATED)) {
                String currentVersion = modInfo.getVersion().toString().substring(modInfo.getVersion().toString().lastIndexOf('-') + 1);
                String actualVersion = updateCheckResult.target().toString().substring(updateCheckResult.target().toString().lastIndexOf('-') + 1);

                String info = new TranslatableComponent("chat.pelmenicraft.update_message").getString() + " \u00A7c" + currentVersion + "\u00A7r -> \u00A7a" + actualVersion + "\u00A7r";
                MutableComponent link = new TranslatableComponent("chat.pelmenicraft.link").setStyle(Style.EMPTY.applyFormat(ChatFormatting.UNDERLINE).withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, updateCheckResult.url())));

                MutableComponent message = new TextComponent(info).append(" [").append(link).append("]");

                event.getPlayer().sendMessage(message, UUID.randomUUID());
            }
        }
    }
}