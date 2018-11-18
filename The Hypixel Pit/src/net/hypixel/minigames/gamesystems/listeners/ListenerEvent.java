package net.hypixel.minigames.gamesystems.listeners;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.vagdedes.mysql.database.SQL;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.npc.CitizensNPC;
import net.hypixel.minigames.Settings;
import net.hypixel.minigames.ThePit;
import net.hypixel.minigames.gamesystems.manager.BountyManager;
import net.hypixel.minigames.gamesystems.manager.ExperienceManager;
import net.hypixel.minigames.gamesystems.manager.GoldManager;
import net.hypixel.minigames.gamesystems.manager.PerkManager;
import net.hypixel.minigames.gamesystems.manager.PrestigeManager;
import net.hypixel.minigames.gamesystems.manager.TitleManager;
import net.hypixel.minigames.gamesystems.status.UserStatus;
import net.hypixel.minigames.upgrades.perks.PerkBarbarian;
import net.hypixel.minigames.upgrades.perks.PerkDirty;
import net.hypixel.minigames.upgrades.perks.PerkGoldenHead;
import net.hypixel.minigames.upgrades.perks.PerkOlympus;
import net.hypixel.minigames.upgrades.perks.PerkRambo;
import net.hypixel.minigames.upgrades.perks.PerkVampire;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;

@SuppressWarnings("all")
public class ListenerEvent implements Listener {

	@EventHandler
	public void onLoggedIn(PlayerJoinEvent event) {
		if(!SQL.exists("uuid", event.getPlayer().getUniqueId().toString(), "thepit")) {
			SQL.insertData("uuid, prestige, level, xp, total_xp, kills, assists, sword_hits, arrow_shot, arrow_hits, melee_damage_dealt, bow_damage_dealt, highest_streak, deaths, melee_damage_taken, bow_damage_taken, hours_played, total_gold, golds, gold_current_prestige, left_clicks, gold_earned, diamond_purchased, chat, block_place, block_break, jump_pit, launcher, gapple_eat, ghead_eat, lava_bucket_use, fishing_rod, contract_count, bounty, renown", "'" + event.getPlayer().getUniqueId().toString() + "', '0', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'", "thepit");
		}
		
		if(!SQL.exists("uuid", event.getPlayer().getUniqueId().toString(), "thepit_upgrades")) {
			SQL.insertData("uuid, slot1_perk, slot2_perk, slot3_perk, slot4_perk, xpboost_tier, goldboost_tier, meleedamage_tier, bowdamage_tier, damagereduction_tier, buildbattler_tier, elgato_tier", "'" + event.getPlayer().getUniqueId().toString() + "', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'", "thepit_upgrades");
		}
		event.setJoinMessage("");
		event.getPlayer().sendMessage(" \n ");
		
		Settings.userStatus.put(event.getPlayer(), UserStatus.IDLE);
		Player player = event.getPlayer();
		
		String tabAndChatSyntax = ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", event.getPlayer().getUniqueId().toString(), "userdata"), "rankdata")).substring(0, 2) + event.getPlayer().getName();
		
		
		player.setDisplayName("」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString())] + "] " + tabAndChatSyntax);
		player.setPlayerListName("」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", event.getPlayer().getUniqueId().toString(), "thepit").toString())] + "] " + tabAndChatSyntax);
		Bukkit.getScheduler().runTaskLater(ThePit.getInstance(), new BukkitRunnable() {
			public void run() {
				IChatBaseComponent msg = ChatSerializer.a("{\"text\":\"」e」lPIT! 」fLatest update: 」b" + Settings.getMinigame().getVersion() + "! 」e」lCLICK HERE!\",\"clickEvent\":{\"action\":\"open_url\", \"value\":\"http://127.0.0.1\"}, \"hoverEvent\":{\"action\":\"show_text\", \"value\":\"」eClick to view on the forums!\"}}");
				PacketPlayOutChat chat = new PacketPlayOutChat(msg);
				((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(chat);
			}
		}, 5);
		
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		// Register Rank
		
		String send = "";
		
		String tabAndChatSyntax = ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", e.getPlayer().getUniqueId().toString(), "userdata"), "rankdata") + "」" + SQL.get("rankPlusColor", "uuid", "=", e.getPlayer().getUniqueId().toString(), "userdata") + SQL.get("plus", "id", "=", (String) SQL.get("rank", "uuid", "=", e.getPlayer().getUniqueId().toString(), "userdata"), "rankdata") + SQL.get("suffix", "id", "=", (String) SQL.get("rank", "uuid", "=", e.getPlayer().getUniqueId().toString(), "userdata"), "rankdata") + e.getPlayer().getName());
		
		if(Integer.parseInt(SQL.get("rank", "uuid", "=", e.getPlayer().getUniqueId().toString(), "userdata").toString()) == 0) {
			send = "」7: ";
		} else {
			send = "」f: ";
		}
		
		// Chat
		if(Integer.parseInt(SQL.get("prestige", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString()) != 0) {
			e.setFormat("」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())] + "[」e" + Settings.prestigeStringList[Integer.parseInt(SQL.get("prestige", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())] + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())] + "-" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())] + "] " + tabAndChatSyntax + send + e.getMessage());
		} else {
			e.setFormat("」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", e.getPlayer().getUniqueId().toString(), "thepit").toString())] + "] " + tabAndChatSyntax + send + e.getMessage());
		}
	}
	
	@EventHandler
	public void onLoggedOut(PlayerQuitEvent event) {
		if(Settings.userStatus.containsKey(event.getPlayer())) {
			Settings.userStatus.remove(event.getPlayer());
		}
	}
	
	@EventHandler
	public void onDamaged(EntityDamageEvent e) {
		if(e.getCause() == DamageCause.FALL) {
			e.setDamage(0);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && (e.getDamager() instanceof Player || e.getDamager() instanceof Projectile || e.getDamager() instanceof TNTPrimed)) {
			Player damaged = (Player) e.getEntity();
			Player attacker = null;
			if(e.getDamager() instanceof Projectile) {
				attacker = (Player) ((Projectile) e.getDamager()).getShooter();
			}
			if(e.getDamager() instanceof Player){
				attacker = (Player) e.getDamager();
			}
			if(e.getDamager() instanceof TNTPrimed) {
				attacker = (Player) ((TNTPrimed) e.getDamager()).getSource();
			}
			
			String damagedTabAndChatSyntax = ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", damaged.getUniqueId().toString(), "userdata"), "rankdata")).substring(0, 2) + damaged.getName();
			String attackerTabAndChatSyntax = ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", attacker.getUniqueId().toString(), "userdata"), "rankdata")).substring(0, 2) + attacker.getName();
			if(PerkManager.hasPerk(attacker, new PerkVampire()) && attacker.getHealth() < attacker.getMaxHealth()) {
				attacker.setHealth(attacker.getHealth()+1);
			}
			if(damaged.getHealth() <= e.getFinalDamage()) {
				e.setCancelled(true);
				Random rand = new Random();
				int killXP = 12 + rand.nextInt(14);
				if(Integer.parseInt(SQL.get("prestige", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString()) > Integer.parseInt(SQL.get("prestige", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString())) {
					killXP = 50 + rand.nextInt(10);
				}
				
				ExperienceManager.addExp(attacker, killXP);
				
				double gold = rand.nextInt(7200)/100;
				
				if(BountyManager.hasBounty(damaged)) {
					Bukkit.broadcastMessage("」6」lBOUNTY CLAIMED! 」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString())] + "] " + attackerTabAndChatSyntax + " 」7killed 」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())] + "] " + damagedTabAndChatSyntax + " 」7for 」6" + BountyManager.getBounty(damaged) + "g");
					gold += BountyManager.getBounty(damaged);
					BountyManager.removeBounty(damaged);
				}
				
				GoldManager.addGold(attacker, gold);
				
				String killRecap = UUID.randomUUID().toString();
				
				IChatBaseComponent deathMessage = ChatSerializer.a("{\"text\":\"」c」lDEATH! 」7by 」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", attacker.getUniqueId().toString(), "thepit").toString())] + "] " + attackerTabAndChatSyntax + " \", \"clickEvent\":{\"action\":\"run_command\", \"value\":\"/killrecap " + killRecap + "\"}, \"hoverEvent\":{\"action\":\"show_text\", \"value\":\"」eClick to view kill recap!\"}}");
				PacketPlayOutChat chatPacket = new PacketPlayOutChat(deathMessage);
				IChatBaseComponent singleKillMessage = ChatSerializer.a("{\"text\": \"」a」lKILL! 」7on " + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())] + "] " + damagedTabAndChatSyntax + " 」b+" + killXP + "XP 」6+" + gold + "g\", \"hoverEvent\":{\"action\":\"show_text\", \"value\":\"」eClick to view kill recap\"}, \"clickEvent\":{\"action\":\"run_command\",\"value\":\"/killrecap " + killRecap + "\"}}");
				IChatBaseComponent doubleKillMessage = ChatSerializer.a("{\"text\": \"」a」lDOUBLE KILL! 」7on " + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())] + "[" + Settings.levelColor[Integer.parseInt(SQL.get("level", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())-1] + SQL.get("level", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString() + "」" + Settings.prestigeColorArray[Integer.parseInt(SQL.get("prestige", "uuid", "=", damaged.getUniqueId().toString(), "thepit").toString())] + "] " + damagedTabAndChatSyntax + " 」b+" + killXP + "XP 」6+" + gold + "g\", \"hoverEvent\":{\"action\":\"show_text\", \"value\":\"」eClick to view kill recap\"}, \"clickEvent\":{\"action\":\"run_command\",\"value\":\"/killrecap " + killRecap + "\"}}");
				PacketPlayOutChat singleKillChatPacket = new PacketPlayOutChat(singleKillMessage);
				((CraftPlayer) damaged).getHandle().playerConnection.sendPacket(chatPacket);
				((CraftPlayer) attacker).getHandle().playerConnection.sendPacket(singleKillChatPacket);
				damaged.getActivePotionEffects().clear();
				Location spawnLoc = new Location(Bukkit.getWorld(ThePit.getInstance().getConfig().getString("locations.spawn.worldname")), ThePit.getInstance().getConfig().getDouble("locations.spawn.x"), ThePit.getInstance().getConfig().getDouble("locations.spawn.y"), ThePit.getInstance().getConfig().getDouble("locations.spawn.z"));
				spawnLoc.setYaw((float) ThePit.getInstance().getConfig().getDouble("locations.spawn.yaw"));
				spawnLoc.setPitch((float) ThePit.getInstance().getConfig().getDouble("locations.spawn.pitch"));
				damaged.teleport(spawnLoc);
				damaged.setHealth(damaged.getMaxHealth());
				TitleManager.sendTitle(damaged, "」cYOU DIED", "");
				
				if(PerkManager.hasPerk(attacker, new PerkVampire())) {
					PotionEffect effect = new PotionEffect(PotionEffectType.REGENERATION, 20*8, 0);
					attacker.addPotionEffect(effect, true);
				}
				
				if(PerkManager.hasPerk(attacker, new PerkGoldenHead()) && !PerkManager.hasPerk(attacker, new PerkVampire()) && !PerkManager.hasPerk(attacker, new PerkRambo()) && !PerkManager.hasPerk(attacker, new PerkOlympus())){
					ItemStack goldenHead = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
					SkullMeta goldenHeadMeta = (SkullMeta) goldenHead.getItemMeta();
					goldenHeadMeta.setDisplayName("」6Golden Head");
					ArrayList<String> desc = new ArrayList<String>();
					desc.add("」9Speed I (0:08)");
					desc.add("」9Regeneration II (0:05)");
					desc.add("」72 absorption hearts!");
					desc.add("」71 second between eats");
					goldenHeadMeta.setLore(desc);
					goldenHead.setItemMeta(goldenHeadMeta);
					if(!(attacker.getInventory().getItem(goldenHead.getTypeId()).getAmount() != 2)) {
						attacker.getInventory().addItem(goldenHead);
					}
				}
				
				if(PerkManager.hasPerk(attacker, new PerkRambo())) {
					attacker.setHealth(attacker.getMaxHealth());
					
				}
				
				if(PerkManager.hasPerk(attacker, new PerkOlympus())) {
					ItemStack potion = new ItemStack(Material.POTION, 1, (short) 8203);
					PotionMeta potionMeta = (PotionMeta) potion.getItemMeta();
					potionMeta.setDisplayName("」bOlympus Potion");
					ArrayList<String> desc = new ArrayList<String>();
					desc.add("」9Speed I (0:24)");
					desc.add("」9Regeneration III (0:10)");
					desc.add("」7Can only hold 1");
					potionMeta.setLore(desc);
					potionMeta.setMainEffect(new PotionEffect(PotionEffectType.SPEED, 20*24, 0).getType());
					potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
					potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 20*24, 0), true);
					potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*10, 2), true);
					potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*4, 1), true);
					potion.setItemMeta(potionMeta);
					if(!(attacker.getInventory().contains(potion))) {
						attacker.getInventory().addItem(potion);
					}
				}
				
				if(PerkManager.hasPerk(attacker, new PerkDirty())) {
					attacker.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*4, 1));
				}
				
				damaged.getInventory().clear();
				
				if(PerkManager.hasPerk(damaged, new PerkBarbarian())) {
					ItemStack barbarianAxe = new ItemStack(Material.IRON_AXE);
					ItemMeta barbarianAxeMeta = barbarianAxe.getItemMeta();
					barbarianAxeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("」7Perk item");
					lore.add("」f");
					lore.add("」9+7 Attack Damage");
					barbarianAxeMeta.setLore(lore);
					barbarianAxeMeta.spigot().setUnbreakable(true);
					barbarianAxe.setItemMeta(barbarianAxeMeta);
					damaged.getInventory().addItem(barbarianAxe);
				}
				
				
				
				
			}
			
			if(attacker.getItemInHand().getItemMeta().getLore().contains("」9+7 Attack Damage")) {
				e.setDamage(7);
			}
			
			if(attacker.getItemInHand().getItemMeta().getLore().contains("」9+8 Attack Damage")) {
				e.setDamage(8);
			}

			//Bukkit.getConsoleSender().sendMessage("Damage: " + e.getDamage());
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		try {
			if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.PHYSICAL) {
				if(e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName().contains("Golden Head")) {
					e.setUseInteractedBlock(Result.DENY);
					if(e.getPlayer().getInventory().getItemInHand().getAmount() == 1) {
						e.getPlayer().getInventory().remove(e.getItem());
					} else {
						e.getPlayer().getItemInHand().setAmount(e.getPlayer().getItemInHand().getAmount()-1);
					}
					PotionEffect speed = new PotionEffect(PotionEffectType.SPEED, 20*8, 0);
					PotionEffect regeneration = new PotionEffect(PotionEffectType.REGENERATION, 20*5, 1);
					e.getPlayer().removePotionEffect(PotionEffectType.SPEED);
					e.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
					e.getPlayer().addPotionEffect(speed, true);
					e.getPlayer().addPotionEffect(regeneration, true);
					return;
					
				}
			}
		} catch(Exception except) {
			
		}
	}
	
	@EventHandler
	public void entityTouched(PlayerInteractAtEntityEvent e) {
		if(ThePit.getInstance().getConfig().getInt("npc.items") == e.getRightClicked().getEntityId()) {
			
		}
	}
	
	@EventHandler
	public void npcAction(PlayerInteractAtEntityEvent e) {
		Player player = e.getPlayer();
		if(e.getRightClicked() instanceof NPC) {
			NPC npc = (NPC) e.getRightClicked();
			if(npc.getFullName().startsWith("」7Resets & Renown")) {
				// NPC - Prestige
				
			}
			if(npc.getFullName().startsWith("」7Non-permanent")) {
				// NPC - Items Shop
				
			}
			if(npc.getFullName().startsWith("」7Permanent")) {
				// NPC - Upgrades Shop
				
			}
			if(npc.getFullName().startsWith("」7View your stats")) {
				// NPC - Stats
				
			}
			if(npc.getFullName().startsWith("」7Quests & Contracts")) {
				// NPC - Quest Master
				
			}
			if(npc.getFullName().startsWith("」7Back to Lobby")) {
				// NPC - The Keeper
				
			}
			
			
		}
	}
	
	/*@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		if(e.getClickedInventory().getHolder() != e.getWhoClicked()) {
			if(e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null || e.getSlotType() == null) {
				return;
			}
			e.setCancelled(true);
			Player player = (Player) e.getWhoClicked();
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Prestige")) {
				if(ExperienceManager.getLevel(player) == 120 && PrestigeManager.getPrestige(player) != 30 && (Double.parseDouble(SQL.get("gold_current_prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())/100) >= Settings.neededGoldToPrestige[Integer.parseInt(SQL.get("prestige", "uuid", "=", player.getUniqueId().toString(), "thepit").toString())+1] && PrestigeManager.getPrestige(player) != 30) {
					Settings.prestige.getReadyPrestige(player);
				}
			}
			
			if(Settings.prestige.ready && e.getCurrentItem().getItemMeta().getDisplayName().contains("ARE YOU SURE?")) {
				PrestigeManager.setPrestige(player, PrestigeManager.getPrestige(player) + 1);
				String playerText = ((String) SQL.get("prefix", "id", "=", (String) SQL.get("rank", "uuid", "=", player.getUniqueId().toString(), "userdata"), "rankdata")).substring(0, 2) + player.getName();
				TitleManager.sendTitle(player, "」ePRESTIGE", "」7You unlocked prestige " + Settings.prestigeStringList[PrestigeManager.getPrestige(player)]);
				Bukkit.broadcastMessage("」ePRESTIGE! " + playerText + " 」7unlocked prestige " + Settings.prestigeStringList[PrestigeManager.getPrestige(player)] + "」7, gg!");
			}
		}
	}*/
	
	@EventHandler
	public void changeWeather(WeatherChangeEvent wce) {
		wce.setCancelled(true);
	}
}
