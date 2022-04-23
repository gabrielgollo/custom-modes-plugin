package me.gabrielgollo.customModesPlugin;

import com.sun.istack.internal.NotNull;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public final class CustomModesPlugin extends JavaPlugin {
    @Override
    public void onEnable(){
        String enabledMsgLogger = "CustomModes -- is Enabled!";
        System.out.println(enabledMsgLogger);
    }

    @Override
    public void onDisable(){
        String disabledMsgLogger = "CustomModes -- has been Disabled!";
        System.out.println(disabledMsgLogger);
    }

    public boolean checkIsPlayer(CommandSender sender){
        if(!(sender instanceof Player)){
            sender.sendMessage("This command can only be run by a player");
            return false;
        }
        return true;
    }

    public void setFlySpeed(String[] args, Player player){
        if(args.length >= 2){

            boolean hasSpeedArg = args[0].equalsIgnoreCase("speed");
            float hasSpeedNumber = Float.parseFloat(args[1]);
            if(hasSpeedArg && (hasSpeedNumber > 0.5) ) {
                player.setFlySpeed(hasSpeedNumber);
            }

        }
    }

    public boolean flyCmd(CommandSender sender, String[] args){
        Player player = (Player) sender;
        try {
            boolean canFly = player.getAllowFlight();
            player.setAllowFlight(!canFly);
            player.setFlying(!canFly);

            if(canFly){
                setFlySpeed(args, player);
            }

        } catch (Exception error) {
            player.sendMessage("An Server error has been throw");
        }


        return true;
    }

    public boolean doGoldCmd(CommandSender sender){
        Player player = (Player) sender;
        Block targetBlock = player.getTargetBlock(null, 5);
        targetBlock.setType(Material.GOLD_BLOCK);
        return true;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender , @NotNull Command cmd, @NotNull String msg, @NotNull String[] args){
        if(cmd.getName().equalsIgnoreCase("fly")){
            if(checkIsPlayer(sender)){
                return flyCmd(sender, args);
            }
        }

        if(cmd.getName().equalsIgnoreCase("doGold")){
            if(checkIsPlayer(sender)){
                return doGoldCmd(sender);
            }

        }

        return false;
    }
}
