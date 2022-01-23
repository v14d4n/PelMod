package com.v14d4n.pelmenicraft.block.custom;

import com.v14d4n.pelmenicraft.container.MeatGrinderContainer;
import com.v14d4n.pelmenicraft.tileentity.MeatGrinderTile;
import com.v14d4n.pelmenicraft.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class MeatGrinderBlock extends HorizontalBlock {
    public MeatGrinderBlock(Properties builder) {
        super(builder);
    }
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.makeCuboidShape(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.makeCuboidShape(7, 1.25, 7, 9, 2.75, 9),
            Block.makeCuboidShape(7, 5.25, 7, 9, 6.75, 9),
            Block.makeCuboidShape(6.5, 6.75, 3, 9.5, 8.75, 11),
            Block.makeCuboidShape(7, 6.25, 3, 9, 9.25, 11),
            Block.makeCuboidShape(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.makeCuboidShape(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.makeCuboidShape(7.5, 1.25, 6.75, 8.5, 2.75, 7),
            Block.makeCuboidShape(7.5, 1.25, 6.25, 8.5, 1.75, 6.75),
            Block.makeCuboidShape(7.5, 1.25, 9, 8.5, 2.75, 9.25),
            Block.makeCuboidShape(7.5, 1.25, 9.25, 8.5, 1.75, 9.75),
            Block.makeCuboidShape(7.5, 1, 13, 8.5, 2, 15.5),
            Block.makeCuboidShape(7.75, 1.25, 12.5, 8.25, 1.75, 13),
            Block.makeCuboidShape(7.75, 1.25, 12, 8.25, 8, 12.5),
            Block.makeCuboidShape(7.75, 7.5, 11.5, 8.25, 8, 12),
            Block.makeCuboidShape(6.25, 7.5, 8.75, 9.75, 8, 10.25),
            Block.makeCuboidShape(6.25, 7.5, 3, 9.75, 8, 6.75),
            Block.makeCuboidShape(6.75, 8.75, 2.25, 9.25, 9.5, 3),
            Block.makeCuboidShape(6.75, 6, 2.25, 9.25, 6.75, 3),
            Block.makeCuboidShape(6.25, 6.5, 2.25, 7, 9, 3),
            Block.makeCuboidShape(9, 6.5, 2.25, 9.75, 9, 3),
            Block.makeCuboidShape(7.5, 1.25, 6.75, 8.5, 2.75, 9.25),
            Block.makeCuboidShape(7.5, 1.25, 6.25, 8.5, 1.75, 9.75),
            Block.makeCuboidShape(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.makeCuboidShape(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.makeCuboidShape(7.75, 6, 3, 8.25, 9.5, 7),
            Block.makeCuboidShape(7, 6.75, 5.25, 9, 8.75, 11.5),
            Block.makeCuboidShape(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.makeCuboidShape(7, 6.25, 7, 9, 11.25, 9),
            Block.makeCuboidShape(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.makeCuboidShape(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.makeCuboidShape(6.5, 11, 6.5, 9.5, 11.25, 9.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.makeCuboidShape(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.makeCuboidShape(7, 1.25, 7, 9, 2.75, 9),
            Block.makeCuboidShape(7, 5.25, 7, 9, 6.75, 9),
            Block.makeCuboidShape(3, 6.75, 6.5, 11, 8.75, 9.5),
            Block.makeCuboidShape(3, 6.25, 7, 11, 9.25, 9),
            Block.makeCuboidShape(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.makeCuboidShape(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.makeCuboidShape(6.75, 1.25, 7.5, 7, 2.75, 8.5),
            Block.makeCuboidShape(6.25, 1.25, 7.5, 6.75, 1.75, 8.5),
            Block.makeCuboidShape(9, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.makeCuboidShape(9.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.makeCuboidShape(13, 1, 7.5, 15.5, 2, 8.5),
            Block.makeCuboidShape(12.5, 1.25, 7.75, 13, 1.75, 8.25),
            Block.makeCuboidShape(12, 1.25, 7.75, 12.5, 8, 8.25),
            Block.makeCuboidShape(11.5, 7.5, 7.75, 12, 8, 8.25),
            Block.makeCuboidShape(8.75, 7.5, 6.25, 10.25, 8, 9.75),
            Block.makeCuboidShape(3, 7.5, 6.25, 6.75, 8, 9.75),
            Block.makeCuboidShape(2.25, 8.75, 6.75, 3, 9.5, 9.25),
            Block.makeCuboidShape(2.25, 6, 6.75, 3, 6.75, 9.25),
            Block.makeCuboidShape(2.25, 6.5, 9, 3, 9, 9.75),
            Block.makeCuboidShape(2.25, 6.5, 6.25, 3, 9, 7),
            Block.makeCuboidShape(6.75, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.makeCuboidShape(6.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.makeCuboidShape(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.makeCuboidShape(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.makeCuboidShape(3, 6, 7.75, 7, 9.5, 8.25),
            Block.makeCuboidShape(5.25, 6.75, 7, 11.5, 8.75, 9),
            Block.makeCuboidShape(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.makeCuboidShape(7, 6.25, 7, 9, 11.25, 9),
            Block.makeCuboidShape(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.makeCuboidShape(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.makeCuboidShape(6.5, 11, 6.5, 9.5, 11.25, 9.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.makeCuboidShape(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.makeCuboidShape(7, 1.25, 7, 9, 2.75, 9),
            Block.makeCuboidShape(7, 5.25, 7, 9, 6.75, 9),
            Block.makeCuboidShape(5, 6.75, 6.5, 13, 8.75, 9.5),
            Block.makeCuboidShape(5, 6.25, 7, 13, 9.25, 9),
            Block.makeCuboidShape(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.makeCuboidShape(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.makeCuboidShape(9, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.makeCuboidShape(9.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.makeCuboidShape(6.75, 1.25, 7.5, 7, 2.75, 8.5),
            Block.makeCuboidShape(6.25, 1.25, 7.5, 6.75, 1.75, 8.5),
            Block.makeCuboidShape(0.5, 1, 7.5, 3, 2, 8.5),
            Block.makeCuboidShape(3, 1.25, 7.75, 3.5, 1.75, 8.25),
            Block.makeCuboidShape(3.5, 1.25, 7.75, 4, 8, 8.25),
            Block.makeCuboidShape(4, 7.5, 7.75, 4.5, 8, 8.25),
            Block.makeCuboidShape(5.75, 7.5, 6.25, 7.25, 8, 9.75),
            Block.makeCuboidShape(9.25, 7.5, 6.25, 13, 8, 9.75),
            Block.makeCuboidShape(13, 8.75, 6.75, 13.75, 9.5, 9.25),
            Block.makeCuboidShape(13, 6, 6.75, 13.75, 6.75, 9.25),
            Block.makeCuboidShape(13, 6.5, 6.25, 13.75, 9, 7),
            Block.makeCuboidShape(13, 6.5, 9, 13.75, 9, 9.75),
            Block.makeCuboidShape(6.75, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.makeCuboidShape(6.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.makeCuboidShape(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.makeCuboidShape(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.makeCuboidShape(9, 6, 7.75, 13, 9.5, 8.25),
            Block.makeCuboidShape(4.5, 6.75, 7, 10.75, 8.75, 9),
            Block.makeCuboidShape(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.makeCuboidShape(7, 6.25, 7, 9, 11.25, 9),
            Block.makeCuboidShape(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.makeCuboidShape(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.makeCuboidShape(6.5, 11, 6.5, 9.5, 11.25, 9.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.makeCuboidShape(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.makeCuboidShape(7, 1.25, 7, 9, 2.75, 9),
            Block.makeCuboidShape(7, 5.25, 7, 9, 6.75, 9),
            Block.makeCuboidShape(6.5, 6.75, 5, 9.5, 8.75, 13),
            Block.makeCuboidShape(7, 6.25, 5, 9, 9.25, 13),
            Block.makeCuboidShape(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.makeCuboidShape(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.makeCuboidShape(7.5, 1.25, 9, 8.5, 2.75, 9.25),
            Block.makeCuboidShape(7.5, 1.25, 9.25, 8.5, 1.75, 9.75),
            Block.makeCuboidShape(7.5, 1.25, 6.75, 8.5, 2.75, 7),
            Block.makeCuboidShape(7.5, 1.25, 6.25, 8.5, 1.75, 6.75),
            Block.makeCuboidShape(7.5, 1, 0.5, 8.5, 2, 3),
            Block.makeCuboidShape(7.75, 1.25, 3, 8.25, 1.75, 3.5),
            Block.makeCuboidShape(7.75, 1.25, 3.5, 8.25, 8, 4),
            Block.makeCuboidShape(7.75, 7.5, 4, 8.25, 8, 4.5),
            Block.makeCuboidShape(6.25, 7.5, 5.75, 9.75, 8, 7.25),
            Block.makeCuboidShape(6.25, 7.5, 9.25, 9.75, 8, 13),
            Block.makeCuboidShape(6.75, 8.75, 13, 9.25, 9.5, 13.75),
            Block.makeCuboidShape(6.75, 6, 13, 9.25, 6.75, 13.75),
            Block.makeCuboidShape(9, 6.5, 13, 9.75, 9, 13.75),
            Block.makeCuboidShape(6.25, 6.5, 13, 7, 9, 13.75),
            Block.makeCuboidShape(7.5, 1.25, 6.75, 8.5, 2.75, 9.25),
            Block.makeCuboidShape(7.5, 1.25, 6.25, 8.5, 1.75, 9.75),
            Block.makeCuboidShape(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.makeCuboidShape(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.makeCuboidShape(7.75, 6, 9, 8.25, 9.5, 13),
            Block.makeCuboidShape(7, 6.75, 4.5, 9, 8.75, 10.75),
            Block.makeCuboidShape(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.makeCuboidShape(7, 6.25, 7, 9, 11.25, 9),
            Block.makeCuboidShape(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.makeCuboidShape(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.makeCuboidShape(6.5, 11, 6.5, 9.5, 11.25, 9.5)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(HORIZONTAL_FACING)) {
            case SOUTH:
                return SHAPE_S;
            case WEST:
                return SHAPE_W;
            case EAST:
                return SHAPE_E;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (entityIn instanceof ItemEntity && tileEntity instanceof MeatGrinderTile) {
                ItemStack itemStack = ((ItemEntity) entityIn).getItem();
                if (!((MeatGrinderTile) tileEntity).isItemHandlerFull()) {
                    int outCount = ((MeatGrinderTile) tileEntity).addItemToSlot(itemStack);
                    ((ItemEntity) entityIn).getItem().setCount(outCount);
                }
            }
        }
        super.onEntityCollision(state, worldIn, pos, entityIn);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof MeatGrinderTile){
                if (player.isCrouching()) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);
                    NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getPos());
                } else {
                    // if player is not crouching
                    if (RANDOM.nextDouble() <= 0.4d && ((MeatGrinderTile) tileEntity).isItemInSlot()) {
                        float xSpawnPos = pos.getX() + 0.5f;
                        float ySpawnPos = pos.getY() + 0.15f;
                        float zSpawnPos = pos.getZ() + 0.5f;
                        float spawnOffset = 0.45f;
                        float throwPower = 0.25f;
                        float zThrowPower = 0;
                        float xThrowPower = 0;

                        switch (state.get(HORIZONTAL_FACING)) {
                            case SOUTH:
                                zSpawnPos += spawnOffset;
                                zThrowPower += throwPower;
                                break;
                            case WEST:
                                xSpawnPos -= spawnOffset;
                                xThrowPower -= throwPower;
                                break;
                            case EAST:
                                xSpawnPos += spawnOffset;
                                xThrowPower += throwPower;
                                break;
                            default:
                                zSpawnPos -= spawnOffset;
                                zThrowPower -= throwPower;
                                break;
                        }

                        ItemEntity item = new ItemEntity(worldIn, xSpawnPos, ySpawnPos, zSpawnPos,
                                ((MeatGrinderTile) tileEntity).craftAndGetCraftedItem());
                        item.addVelocity(xThrowPower, -0.15f, zThrowPower);
                        worldIn.addEntity(item);

                        worldIn.playSound(null, pos,
                                SoundEvents.ENTITY_SHULKER_BULLET_HIT, SoundCategory.BLOCKS, 0.8f, 1);
                    } else { // if random didn't work
                        if (((MeatGrinderTile) tileEntity).isItemInSlot()) {
                            worldIn.playSound(null, pos,
                                    SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, SoundCategory.BLOCKS, 0.8f, 1);
                        } else {
                            worldIn.playSound(null, pos,
                                    SoundEvents.ITEM_TRIDENT_HIT_GROUND, SoundCategory.BLOCKS, 0.8f, 1);
                        }
                    }
                }
            } else {
                throw new RuntimeException("error with tile entity (meat_grinder)");
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.pelmenicraft.meat_grinder");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity player) {
                return new MeatGrinderContainer(i, worldIn, pos, playerInventory, player);
            }
        };
    }

    @Override
    public boolean addDestroyEffects(BlockState state, World world, BlockPos pos, ParticleManager manager) {
        for (int i = 0; i < 10; i++) {
            manager.addParticle(new BlockParticleData(ParticleTypes.BLOCK, state),
                    pos.getX() + 0.5f,
                    pos.getY() + 0.6f - RANDOM.nextDouble() / 2f,
                    pos.getZ() + 0.5f,
                    0.00d, 0.05d, 0.00d);
        }
        return true;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof MeatGrinderTile) {
            spawnAsEntity(worldIn, pos, ((MeatGrinderTile) tileEntity).getStackInSlot());
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public void onPlayerDestroy(IWorld worldIn, BlockPos pos, BlockState state) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof MeatGrinderTile) {
            spawnAsEntity((World)worldIn, pos, ((MeatGrinderTile) tileEntity).getStackInSlot());
        }
        super.onPlayerDestroy(worldIn, pos, state);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.MEAT_GRINDER_TILE.get().create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable IBlockReader worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.pelmenicraft.meat_grinder_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.pelmenicraft.meat_grinder"));
        }

        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
