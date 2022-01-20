package com.v14d4n.pelmenicraft.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class PelmeniCraftConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> showUpdates;

    static {
        BUILDER.push("PelmeniCraft config");

        showUpdates = BUILDER.comment("Show updates. Default value is true.").define("showUpdates", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}