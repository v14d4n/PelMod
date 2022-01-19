package com.v14d4n.pelmenicraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.v14d4n.pelmenicraft.config.PelmeniCraftConfig;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.TranslationTextComponent;

public class EnableNotificationsCommand {

    public EnableNotificationsCommand(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("pelmenicraft").then(Commands.literal("notifications").then(Commands.literal("true")
                .executes((command) -> enableUpdateNotifications(command.getSource())))));
    }

    private int enableUpdateNotifications(CommandSource source) throws CommandSyntaxException {
        source.sendFeedback(new TranslationTextComponent("chat.pelmenicraft.update_on"), true);
        PelmeniCraftConfig.showUpdates.set(true);
        return 1;
    }
}
