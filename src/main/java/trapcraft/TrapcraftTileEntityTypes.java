package trapcraft;

import com.google.common.collect.Sets;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import trapcraft.lib.Reference;
import trapcraft.tileentity.TileEntityBearTrap;
import trapcraft.tileentity.TileEntityFan;
import trapcraft.tileentity.TileEntityIgniter;
import trapcraft.tileentity.TileEntityMagneticChest;

public class TrapcraftTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);

	public static final RegistryObject<TileEntityType<?>> MAGNETIC_CHEST = TILE_ENTITIES.register("magnetic_chest", () -> new TileEntityType<>(TileEntityMagneticChest::new, Sets.newHashSet(TrapcraftBlocks.MAGNETIC_CHEST.get()), null));
	public static final RegistryObject<TileEntityType<?>> FAN = TILE_ENTITIES.register("fan", () -> new TileEntityType<>(TileEntityFan::new, Sets.newHashSet(TrapcraftBlocks.FAN.get()), null));
	public static final RegistryObject<TileEntityType<?>> BEAR_TRAP = TILE_ENTITIES.register("bear_trap", () -> new TileEntityType<>(TileEntityBearTrap::new, Sets.newHashSet(TrapcraftBlocks.BEAR_TRAP.get()), null));
	public static final RegistryObject<TileEntityType<?>> IGNITER = TILE_ENTITIES.register("igniter", () -> new TileEntityType<>(TileEntityIgniter::new, Sets.newHashSet(TrapcraftBlocks.IGNITER.get()), null));
}