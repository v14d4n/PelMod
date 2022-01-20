package com.v14d4n.pelmenicraft.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.v14d4n.pelmenicraft.config.PelmeniCraftConfig;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.UUID;

public class DisableNotificationsCommand {

    public DisableNotificationsCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("pelmenicraft").then(Commands.literal("notifications").then(Commands.literal("false")
                .executes((command) -> disableUpdateNotifications(command.getSource())))));
    }

    private int disableUpdateNotifications(CommandSourceStack source) throws CommandSyntaxException {
        source.sendSuccess(new TranslatableComponent("chat.pelmenicraft.update_off"), true);
        PelmeniCraftConfig.showUpdates.set(false);
        return 1;
    }
}