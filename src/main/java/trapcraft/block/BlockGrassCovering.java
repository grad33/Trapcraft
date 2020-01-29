package trapcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author ProPercivalalb
 **/
public class BlockGrassCovering extends Block {
    
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 15.0D, 0.0D, 16.0D, 16D, 16.0D);
	
	public BlockGrassCovering() {
		super(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.2F, 1F).sound(SoundType.GROUND).tickRandomly());
    }
 
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext) {
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext) {
		return VoxelShapes.empty();
	}
	
	@Override
	public boolean isSolid(BlockState state) {
		return false;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.SOLID;
	}
    
    @Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		for(Direction facing : new Direction[] {Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH}) {
    		BlockPos posOff = pos.offset(facing);
    		BlockState blockstate = worldIn.getBlockState(posOff);
    		if(Block.hasSolidSide(blockstate, worldIn, posOff, facing.getOpposite()) || blockstate.getBlock() == this)
    			return true;

    	}
		
		return false;
	}
	
	//@Override
	//public int getItemsToDropCount(BlockState state, int fortune, World worldIn, BlockPos pos, Random random) {
	//	return 2;
	//}
	
    @Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    	if(entity instanceof LivingEntity && !world.isRemote) {
    		world.destroyBlock(pos, true);
        }
    }
}