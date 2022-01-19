package com.v14d4n.pelmenicraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.v14d4n.pelmenicraft.config.PelmeniCraftConfig;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class DisableNotificationsCommand {

    public DisableNotificationsCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("pelmenicraft").then(Commands.literal("notifications").then(Commands.literal("false")
                .executes((command) -> disableUpdateNotifications(command.getSource())))));
    }

    private int disableUpdateNotifications(CommandSource source) throws CommandSyntaxException {
        source.sendFeedback(new TranslationTextComponent("chat.pelmenicraft.update_off"), true);
        PelmeniCraftConfig.showUpdates.set(false);
        return 1;
    }
}
