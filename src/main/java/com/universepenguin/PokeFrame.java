package com.universepenguin;

import java.util.ArrayList;
import java.util.UUID;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.AttackBase;
import com.pixelmonmod.pixelmon.battles.status.StatusPersist;
import com.pixelmonmod.pixelmon.entities.pixelmon.EnumSpecialTexture;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Moveset;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import com.pixelmonmod.pixelmon.enums.EnumPokerusType;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.enums.items.EnumPokeballs;

import net.minecraft.item.ItemStack;

public class PokeFrame {
	EnumSpecies species;
	int level;
	String gender;
	boolean shiny;
	EnumGrowth growth;
	EnumNature nature;
	String ability;
	int form;
	EnumPokeballs caughtBall;
	EnumPokerusType pokerus;
	boolean egg;
	String customTexture;
	boolean cured;
	boolean untradeable;
	boolean unbreedable;
	String status;
	int[] IVs = new int[6];
	int[] EVs = new int[6];
	EnumSpecialTexture specialTexture;
	UUID OTUUID;
	String OTName;
	boolean levelable;
	Integer eggSteps;
	Integer eggCycles;
	int friendship;
	int experience;
	int health;
	String nickname;
	String[] attacks;
	
	public PokeFrame() {
		species = EnumSpecies.Bulbasaur;
		level = 1;
		gender = "male";
		shiny = false;
		growth = EnumGrowth.Ordinary;
		nature = EnumNature.Adamant;
		ability = "Overgrow";
		form = 0;
		caughtBall = EnumPokeballs.PokeBall;
		pokerus = EnumPokerusType.UNINFECTED;
		egg = false;
		customTexture = "none";
		cured = false;
		untradeable = false;
		unbreedable = false;
		status = "healthy";
		for (int i = 0; i < IVs.length; i++) {
			IVs[i] = 0;
			EVs[i] = 0;
		}
		specialTexture = EnumSpecialTexture.None;
		OTUUID = UUID.randomUUID();
		OTName = "JohnDoe";
		levelable = true;
		eggSteps = -1;
		eggCycles = -1;
		friendship = 200;
		experience = 0;
		health = 1;
		nickname = "Subject001";
		attacks = new String[2];
		attacks[0] = "Tackle"; attacks[1] = "Growl";
	}
	
	public PokeFrame(Pokemon pokemon) {
		species = pokemon.getSpecies();
		level = pokemon.getLevel();
		gender = pokemon.getGender().name();
		shiny = pokemon.isShiny();
		growth = pokemon.getGrowth();
		nature = pokemon.getNature();
		ability = pokemon.getAbilityName();
		form = pokemon.getForm();
		caughtBall = pokemon.getCaughtBall();
		if (pokemon.getPokerus() != null) {
			if (pokemon.getPokerus().type != null ) {
				pokerus = pokemon.getPokerus().type;
			} else {
				pokerus = EnumPokerusType.UNINFECTED;
			}
		} else {
			pokerus = EnumPokerusType.UNINFECTED;
		}
		egg = pokemon.isEgg();
		if (!pokemon.getCustomTexture().equals("")) {
			customTexture = pokemon.getCustomTexture();
		} else {
			customTexture = "none";
		}
		cured = false;
		untradeable = false;
		unbreedable = false;
		status = "healthy";
		IVs[0] = pokemon.getIVs().hp;
		IVs[1] = pokemon.getIVs().attack;
		IVs[2] = pokemon.getIVs().defence;
		IVs[3] = pokemon.getIVs().specialAttack;
		IVs[4] = pokemon.getIVs().specialDefence;
		IVs[5] = pokemon.getIVs().speed;
		EVs[0] = pokemon.getEVs().hp;
		EVs[1] = pokemon.getEVs().attack;
		EVs[2] = pokemon.getEVs().defence;
		EVs[3] = pokemon.getEVs().specialAttack;
		EVs[4] = pokemon.getEVs().specialDefence;
		EVs[5] = pokemon.getEVs().speed;
		specialTexture = pokemon.getSpecialTexture();
		OTUUID = pokemon.getOriginalTrainerUUID();
		OTName = pokemon.getOriginalTrainer();
		levelable = pokemon.doesLevel();
		eggSteps = pokemon.getEggSteps();
		eggCycles = pokemon.getEggCycles();
		friendship = pokemon.getFriendship();
		experience = pokemon.getExperience();
		health = pokemon.getHealth();
		if (pokemon.getNickname() != "null" || pokemon.getNickname() != null) {
			nickname = pokemon.getNickname();
		} else {
			nickname = pokemon.getSpecies().name;
		}
		attacks = new String[pokemon.getMoveset().attacks.length];
		for (int i = 0; i < attacks.length && pokemon.getMoveset().attacks[i].baseAttack.getAttackName().contains(" "); i++) {
			attacks[i] = pokemon.getMoveset().attacks[i].baseAttack.getAttackName().replaceAll(" ", "_");
		}
		
	}
	
	public Pokemon buildPokemon() {
		ArrayList<String> tempArgs = new ArrayList<String>();
    	
    	tempArgs.add(species.name);
    	tempArgs.add("lvl:" + level);
    	tempArgs.add("g:" + gender);
    	if (shiny) {
    		tempArgs.add("s");
    	} else {
    		tempArgs.add("!s");
    	}
    	tempArgs.add("gr:" + growth.toString());
    	tempArgs.add("n:" + nature.toString());
    	tempArgs.add("ab:" + ability);
    	tempArgs.add("f:" + form);
    	tempArgs.add("ba:" + caughtBall.toString());
    	tempArgs.add("pkrs:" + pokerus);
    	if (egg) {
    		tempArgs.add("egg");
    	} else {
    		tempArgs.add("!egg");
    	}
    	tempArgs.add("texture:" + customTexture);
    	if (cured) {
    		tempArgs.add("cured");
    	}
    	if (untradeable) {
    		tempArgs.add("untradeable");
    	}
    	if (unbreedable) {
    		tempArgs.add("unbreedable");
    	}
    	if (!status.equals("")) {
    		tempArgs.add("status:" + status);
    	}
    	
    	PokemonSpec pokeSpec = new PokemonSpec(arrayListToArray(tempArgs));
    	
    	Pokemon toReturn = Pixelmon.pokemonFactory.create(pokeSpec);
    	
    	toReturn.setSpecialTexture(specialTexture);
    	toReturn.setOriginalTrainer(OTUUID, OTName);
    	toReturn.setDoesLevel(levelable);
    	
    	if (eggSteps != -1) {
    		toReturn.setEggSteps(eggSteps);
    	} else if (eggCycles != -1) {
    		toReturn.setEggCycles(eggCycles);
    	}
    	
    	toReturn.setFriendship(friendship);
    	toReturn.setExperience(experience);
    	toReturn.setHealth(health);
    	toReturn.setNickname(nickname);
    	
    	toReturn.getMoveset().clear();
    	
    	Attack[] tempAttacks = new Attack[attacks.length];
    	for (int i = 0; i < attacks.length && toReturn.getMoveset().attacks[i].baseAttack.getAttackName().contains(" "); i++) {
    		tempAttacks[i] = new Attack(attacks[i].replaceAll("_", " "));
    	}
    	
    	for (int i = 0; i < tempAttacks.length; i++) {
    		toReturn.getMoveset().add(tempAttacks[i]);
    	}
    	
    	toReturn.getIVs().set(StatsType.HP, IVs[0]);
    	toReturn.getIVs().set(StatsType.Attack, IVs[1]);
    	toReturn.getIVs().set(StatsType.Defence, IVs[2]);
    	toReturn.getIVs().set(StatsType.SpecialAttack, IVs[3]);
    	toReturn.getIVs().set(StatsType.SpecialDefence, IVs[4]);
    	toReturn.getIVs().set(StatsType.Speed, IVs[5]);
    	
    	toReturn.getEVs().set(StatsType.HP, EVs[0]);
    	toReturn.getEVs().set(StatsType.Attack, EVs[1]);
    	toReturn.getEVs().set(StatsType.Defence, EVs[2]);
    	toReturn.getEVs().set(StatsType.SpecialAttack, EVs[3]);
    	toReturn.getEVs().set(StatsType.SpecialDefence, EVs[4]);
    	toReturn.getEVs().set(StatsType.Speed, EVs[5]);
    	
    	return toReturn;
	}
	
	public String buildNBT() {
		String toReturn = "{";
		
		toReturn += "form:" + form + "b,";
		if (gender.equals("male")) {
			toReturn += "gender:0b,";
		} else {
			toReturn += "gender:1b,";
		}
		toReturn += "ndex:" + EnumSpecies.getPokedexNumber(species.name) + "s,";
		if (shiny) {
			toReturn += "Shiny:1b";
		} else {
			toReturn += "Shiny:0b,";
		}
		
		toReturn += "Pokemon:";
		
		toReturn += "Species--" + species.name + "-";
		toReturn += "Level--" + level + "---";
		toReturn += "Gender--" + gender + "---";
		toReturn += "Shiny--" + shiny + "---";
		toReturn += "Growth--" + growth.toString() + "---";
		toReturn += "Nature--" + nature.toString() + "---";
		toReturn += "Ability--" + ability + "---";
		toReturn += "Form--" + form + "---";
		toReturn += "CaughtBall--" + caughtBall.toString() + "---";
		toReturn += "Pokerus--" + pokerus.toString() + "---";
		toReturn += "Egg--" + egg + "---";
		toReturn += "CustomTexture--" + customTexture + "---";
		toReturn += "Cured--" + cured + "---";
		toReturn += "Untradeable--" + untradeable + "---";
		toReturn += "Unbreedable--" + unbreedable + "---";
		toReturn += "Status--" + status + "---";
		toReturn += "IVHP--" + IVs[0] + "---";
		toReturn += "IVAttack--" + IVs[1] + "---";
		toReturn += "IVDefence--" + IVs[2] + "---";
		toReturn += "IVSpecialAttack--" + IVs[3] + "---";
		toReturn += "IVSpecialDefense--" + IVs[4] + "---";
		toReturn += "IVSpeed--" + IVs[5] + "---";
		toReturn += "EVHP--" + EVs[0] + "---";
		toReturn += "EVAttack--" + EVs[1] + "---";
		toReturn += "EVDefence--" + EVs[2] + "---";
		toReturn += "EVSpecialAttack--" + EVs[3] + "---";
		toReturn += "EVSpecialDefense--" + EVs[4] + "---";
		toReturn += "EVSpeed--" + EVs[5] + "---";
		if (specialTexture.toString().equals("")) {
			toReturn += "SpecialTexture--" + "none" + "---";
		} else {
			toReturn += "SpecialTexture--" + specialTexture.toString() + "---";
		}
		toReturn += "OTUUID--" + String.valueOf(OTUUID) + "---";
		toReturn += "OTName--" + OTName + "---";
		toReturn += "Levelable--" + levelable + "---";
		toReturn += "EggSteps--" + String.valueOf(eggSteps) + "---";
		toReturn += "EggCycles--" + String.valueOf(eggCycles) + "---";
		toReturn += "Friendship--" + friendship + "---";
		toReturn += "Experience--" + experience + "---";
		toReturn += "Health--" + health + "---";
		for (int i = 0; i < attacks.length; i++) {
			toReturn += "Attack" + i + "--" + attacks[i] + "---";
		}
		toReturn += "Nickname--" + nickname + "}";
		
		
		return toReturn;
	}
	
	public String[] arrayListToArray(ArrayList<String> input) {
    	String[] toReturn = new String[input.size()];
    	
    	for (int i = 0; i < input.size(); i++) {
    		toReturn[i] = input.get(i);
    	}
    	
    	return toReturn;
    }
	
}
