package trapcraft.tileentity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import trapcraft.api.Properties;

/**
 * @author ProPercivalalb
 **/
public class TileEntityTC extends TileEntity {
	
	private String owner;
    private String customName;
    private String state;

    public TileEntityTC(TileEntityType<?> tileEntityTypeIn) {
    	super(tileEntityTypeIn);
        owner = "";
        customName = "";
        state = "";
    }
    
    /* Block Owner */
    public String getOwner() { return owner; }
    public boolean hasOwner() { return owner != null && owner.length() > 0; }
    public void setOwner(String owner) { this.owner = owner; }

    /* Tile Entity Custom name */
    public void setInvName(String customName) { this.customName = customName; }

    /* Block State */
    public String getState() { return state; }
    public void setState(String newState) { this.state = newState; }
    

    public boolean isUseableByPlayer(PlayerEntity player) { return owner.equals(player.getName()); }

    @Override
    public void read(CompoundNBT nbtTagCompound) {
        super.read(nbtTagCompound);

        if (nbtTagCompound.contains(Properties.NBT_STATE)) {
            state = nbtTagCompound.getString(Properties.NBT_STATE);
        }
        
        if (nbtTagCompound.contains(Properties.NBT_OWNER_KEY)) {
            owner = nbtTagCompound.getString(Properties.NBT_OWNER_KEY);
        }

        if (nbtTagCompound.contains(Properties.NBT_CUSTOM_NAME)) {
            customName = nbtTagCompound.getString(Properties.NBT_CUSTOM_NAME);
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT nbtTagCompound) {
        super.write(nbtTagCompound);

        nbtTagCompound.putString(Properties.NBT_STATE, state);
        
        if (hasOwner()) {
            nbtTagCompound.putString(Properties.NBT_OWNER_KEY, owner);
        }
        nbtTagCompound.putString(Properties.NBT_CUSTOM_NAME, customName);

        return nbtTagCompound;
    }
    //TODO
    //@Override
    //public Packet getDescriptionPacket() {
    //	return PacketDispatcher.getPacket(new MagneticChestTileMessage(this.pos, this.owner, this.customName, this.state));
    //}
}