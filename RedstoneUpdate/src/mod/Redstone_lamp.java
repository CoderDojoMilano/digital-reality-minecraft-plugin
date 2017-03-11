package mod;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
 
public class Redstone_lamp implements Listener{
	
	
	public Redstone_lamp(Dispatcher disp){
		disp.getServer().getPluginManager().registerEvents(this, disp);
		print("RL loaded");
	}
	
    @EventHandler
    public void OnRedstone(BlockRedstoneEvent event){
        Block a = event.getBlock();
        Sign s;
        print("Event: " + a + "-" +a.getType());
        if(a.getType().equals(Material.REDSTONE_LAMP_OFF)){
            s = checkForSign(a.getLocation());
            String str = s.getLine(0)+ s.getLine(1)+ s.getLine(2)+ s.getLine(3);
            if(!checkForError(str)){
            	Serial.write(str);
            }else{
            	print("SYNTAX ERROR!!");
            }
            /*
            //OLD, ONLY FOR RESETTING COMUNICATIONS
            int[] outputs = new int[13];
            List<List<Integer>> raw = splitString(s.getLine(0)+ s.getLine(1)+ s.getLine(2)+ s.getLine(3));
            
            print("raw:" + raw.toString());
            print(outputs.length + ", " + globalPins.length + ", " + raw.size());
            
            for(int i = 0; i < raw.size(); i++){
            	outputs[raw.get(i).get(0)] = raw.get(i).get(1);
            }
            
            for(int i = 0; i < outputs.length; i++){
            	
            	if(globalPins[i] == 0 && outputs[i]==1){
            		globalPins[i] = 1;
            	}
            	else if(globalPins[i] == 1 && outputs[i]==2){
            		globalPins[i] = 2;
            	}
            }
            
            for(int i = 0; i < globalPins.length; i++){
            	if(globalPins[i] !=0 && globalPins[i]!=1){
            		globalPins[i] = 0;
            	}
            	
            	print("i values:" + i + ": " + outputs[i] + ", " + globalPins[i] + ", " + oldPins[i]);
            	
            	if(oldPins[i] != globalPins[i]){
            		print("Writing to " + globalPins[i]);
            		SerialCom.write(i, globalPins[i]);
            	}
            }
            */
            
            
            print("Finished Redstone event");
        }
        
    }
 
    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event){
    	print("HI");
        Player a = event.getPlayer();
        String b = a.getName();
        Bukkit.getServer().broadcastMessage(b + " joined");
    }
    
    
    List<List<Integer>> splitString(String str){
    	for(int i = 0; i<str.length(); i++){
    		if(!Character.isDigit(str.charAt(i)) && str.charAt(i) != ';' && str.charAt(i) != ','){
    			str = str.replace(str.charAt(i), ' ');
    		}
    	}
    	str = str.replace(" ", "");
    	
    	String[] split = str.split(";");
    	List<List<Integer>> cont = new ArrayList<List<Integer>>();
    	
    	
    	for(int i=0; i<split.length; i++){
    		String[] splitPos = split[i].split(",");
    		List<Integer> pos = new ArrayList<Integer>();
    		for(int j = 0; j<splitPos.length; j++){
    			pos.add(Integer.parseInt(splitPos[j]));
    		}
    		cont.add(pos);
    	}
    	
    	
		return cont;
    }

    Sign checkForSign(Location l){
    	Sign sign = null;
    	print(l.toString());
    	
    	
    	List<Location> pos = new ArrayList<Location>();
    	
    	pos.add(new Location(l.getWorld(), l.getX()+1, l.getY(), l.getZ()));
    	pos.add(new Location(l.getWorld(), l.getX()-1, l.getY(), l.getZ()));
    	pos.add(new Location(l.getWorld(), l.getX(), l.getY(), l.getZ()+1));
    	pos.add(new Location(l.getWorld(), l.getX(), l.getY(), l.getZ()-1));
    	
    	for(int i = 0; i < pos.size(); i++){
    		Block b = pos.get(i).getBlock();
	    	if(b.getType() == Material.WALL_SIGN){
	    		sign = (Sign)b.getState();
	    	}
    	}

		return sign;
    }
    
    boolean checkForError(String str){
    	try{
	    	List<List<Integer>> raw = splitString(str);
	    	
	    	for(int i = 0; i < raw.size(); i++){
	    		int pin = raw.get(i).get(0);
	    		int pow = raw.get(i).get(1);
	        	if(pin < 0 || pin > 13){
	        		print("ERROR in pin: " + i);
	        		return true;
	        	}
	        	if(pow !=0 && pow !=1){
	        		print("ERROR in pow: " + i);
	        		return true;
	        	}
	        }
    	}catch(Exception e){
    		print("Exeption ERROR: ");
    		return true;
    	}
    	
    	return false;
    }
    
    
    void print(String s){
    	Bukkit.getLogger().info(s);
    }
 
}
