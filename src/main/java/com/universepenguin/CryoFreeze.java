package com.universepenguin;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.inject.Inject;
import com.pixelmonmod.pixelmon.battles.rules.clauses.BattleClause;
import com.pixelmonmod.pixelmon.battles.rules.clauses.BattleClauseRegistry;
import com.universepenguin.commands.Freeze;

import java.util.List;

import org.slf4j.Logger;

@Plugin(id = "cryofreeze", name = "CryoFreeze", version = "1.0", description = "Turn pixelmon into items.")
public class CryoFreeze {
	public static CryoFreeze instance;
	
	@Inject
	private Logger logger;
	
	@Listener
	public void preInit(GamePreInitializationEvent e) {
		
	}
	
	@Listener
    public void onServerStart(GameStartedServerEvent event) {
        createCommands();
        instance = this;
        logger.info("Successfully running ExamplePlugin!!!");
    }
	
	public void createCommands() {
		
		CommandSpec freezeCommand = CommandSpec.builder()
				.description(Text.of("Freeze your Pixelmon"))
				.permission("cryofreeze.command.freeze")
				.arguments(
						GenericArguments.onlyOne(GenericArguments.integer(Text.of("slotNumber"))))
				.executor(new Freeze())
				.build();
		
		Sponge.getCommandManager().register(this, freezeCommand, "cryofreeze");
	}
	
	public CryoFreeze getInstance() {
		return instance;
	}
}
