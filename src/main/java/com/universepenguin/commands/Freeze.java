package com.universepenguin.commands;

import java.util.Optional;
import java.util.ArrayList;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.Text;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.api.storage.PartyStorage;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.universepenguin.CryoFreeze;
import com.universepenguin.PokeFrame;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Freeze implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        
        if (src instanceof Player) {
        	
        	Integer slotNumber = args.<Integer>getOne("slotNumber").get();
        	Player player = (Player)src;
        	
        	player.sendMessage(Text.of("Slot Number: " + String.valueOf(slotNumber)));
        	
        	PartyStorage party = Pixelmon.storageManager.getParty(player.getUniqueId());
        	
        	if (party.get(slotNumber - 1) != null && slotNumber > 0 && slotNumber < 7) {
        		if (party.get(slotNumber - 1).getHeldItem().getDisplayName().equals("Air")) {
        		
	        		Pokemon thisPokemon = party.get(slotNumber - 1);
	        		
	        		Pokemon newPokemon = new PokeFrame(thisPokemon).buildPokemon();
	        		
	        		party.set(slotNumber, newPokemon);
	        		
	        		Sponge.getCommandManager().process(Sponge.getGame().getServer().getConsole(), "give " + player.getName() + " pixelmon:pixelmon_sprite 1 0 " + new PokeFrame(thisPokemon).buildNBT());
	        		src.sendMessage(Text.of(new PokeFrame(thisPokemon).buildNBT()));
        		} else {
        			src.sendMessage(Text.of("You can't freeze a pokemon with an item in it's hand"));
        		}
        		
        	} else {
        		src.sendMessage(Text.of("There's no Pokemon in this slot"));
        	}
        	
        } else if (src instanceof ConsoleSource) {
        	src.sendMessage(Text.of("This command must be run by a player in order to work."));
        } else if (src instanceof CommandBlockSource) {
        	src.sendMessage(Text.of("Don't run this command from command blocks."));
        }
        
        return CommandResult.success();
    }
}