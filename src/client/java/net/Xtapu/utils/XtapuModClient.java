package net.Xtapu.utils;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.tinyremapper.extension.mixin.common.Logger;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;

import static net.minecraft.text.Text.literal;

public class XtapuModClient implements ClientModInitializer {
	private static boolean flyEnabled = false;
	@Override
	public void onInitializeClient() {
		register();
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
	public static void register() {
		try {
			ClientCommandManager.getActiveDispatcher().register(
					LiteralArgumentBuilder.<FabricClientCommandSource>literal("fly")
							.executes(XtapuModClient::toggleFly)
			);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private static int toggleFly(CommandContext context) {
		flyEnabled = !flyEnabled;
		MinecraftClient.getInstance().player.sendMessage(literal("Fly mode " + (flyEnabled ? "enabled" : "disabled")), false);
		return 1;
	}
}