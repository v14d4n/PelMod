package com.v14d4n.pelmenicraft.block.custom;

import com.v14d4n.pelmenicraft.container.MeatGrinderContainer;
import com.v14d4n.pelmenicraft.tileentity.MeatGrinderTile;
import com.v14d4n.pelmenicraft.tileentity.ModTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.particle.ParticleManager;
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
            Block.makeCuboidShape(7.06264, 6.5, 11.06264, 9.06264, 8, 12.06264),
            Block.makeCuboidShape(7.76264, 1.9, 12.51264, 8.36264, 7.5, 13.11264),
            Block.makeCuboidShape(7.76264, 1.9, 13.10964, 8.36264, 2.5, 14.77964),
            Block.makeCuboidShape(7.76264, 6.9, 10.56264, 8.36264, 7.5, 12.56264),
            Block.makeCuboidShape(6.06264, 0, 6.06264, 10.06264, 1, 10.31264),
            Block.makeCuboidShape(5.81264, 0, 5.81264, 6.06264, 0.25, 10.56264),
            Block.makeCuboidShape(10.06264, 0, 5.81264, 10.31264, 0.25, 10.56264),
            Block.makeCuboidShape(6.06264, 0, 5.81264, 10.06264, 0.25, 6.06264),
            Block.makeCuboidShape(6.06264, 0, 10.31264, 10.06264, 0.25, 10.56264),
            Block.makeCuboidShape(6.31264, 1, 6.31264, 9.81264, 1.25, 10.06264),
            Block.makeCuboidShape(6.56264, 10.5, 9.06264, 9.56264, 11, 9.56264),
            Block.makeCuboidShape(6.56264, 10.5, 6.81264, 9.56264, 11, 7.31264),
            Block.makeCuboidShape(6.56264, 9.75, 7.31264, 7.06264, 11, 9.06264),
            Block.makeCuboidShape(9.06264, 9.75, 7.31264, 9.56264, 11, 9.06264),
            Block.makeCuboidShape(7.06264, 9.75, 6.81264, 9.06264, 10.5, 7.31264),
            Block.makeCuboidShape(7.06264, 9.75, 9.06264, 9.06264, 10.5, 9.56264),
            Block.makeCuboidShape(7.56264, 1.25, 7.31264, 8.56264, 5.75, 9.06264),
            Block.makeCuboidShape(7.06264, 8.75, 7.31264, 7.06264, 9.75, 9.06264),
            Block.makeCuboidShape(9.06264, 8.75, 7.31264, 9.06264, 9.75, 9.06264),
            Block.makeCuboidShape(7.06264, 8.75, 9.06264, 9.06264, 9.75, 9.06264),
            Block.makeCuboidShape(7.06264, 8.75, 7.31264, 9.06264, 9.75, 7.31264),
            Block.makeCuboidShape(7.16264, 1.25, 7.66264, 7.56264, 5.75, 8.66264),
            Block.makeCuboidShape(8.56264, 1.25, 7.66264, 8.96264, 5.75, 8.66264),
            Block.makeCuboidShape(7.56264, 1.25, 7.06264, 8.56264, 2.25, 7.31264),
            Block.makeCuboidShape(7.56264, 1.25, 6.56264, 8.56264, 1.75, 7.06264),
            Block.makeCuboidShape(7.56264, 1.25, 9.31264, 8.56264, 1.75, 9.81264),
            Block.makeCuboidShape(7.56264, 1.25, 9.06264, 8.56264, 2.25, 9.31264),
            Block.makeCuboidShape(7.06264, 5.75, 3.81264, 9.06264, 6.25, 10.81264),
            Block.makeCuboidShape(6.56264, 6.25, 3.81264, 7.06264, 8.25, 11.06264),
            Block.makeCuboidShape(9.06264, 6.25, 3.81264, 9.56264, 8.25, 11.06264),
            Block.makeCuboidShape(7.06264, 6.25, 10.06264, 9.06264, 8.25, 11.06264),
            Block.makeCuboidShape(7.06264, 8.25, 3.81264, 9.06264, 8.75, 10.81264),
            Block.makeCuboidShape(6.56264, 8.25, 7.31264, 7.06264, 8.75, 9.06264),
            Block.makeCuboidShape(9.06264, 8.25, 7.31264, 9.56264, 8.75, 9.06264),
            Block.makeCuboidShape(6.31264, 6, 3.11264, 7.06264, 8.5, 3.81264),
            Block.makeCuboidShape(9.06264, 6, 3.11264, 9.81264, 8.5, 3.81264),
            Block.makeCuboidShape(6.81264, 8.25, 3.11264, 9.31264, 9, 3.81264),
            Block.makeCuboidShape(6.81264, 5.5, 3.11264, 9.31264, 6.25, 3.81264),
            Block.makeCuboidShape(7.06264, 6.25, 3.56264, 9.06264, 8.25, 3.56264)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(11.12528, 6.5, 7, 12.12528, 8, 9),
            Block.makeCuboidShape(12.57528, 1.9, 7.699999999999999, 13.17528, 7.5, 8.299999999999999),
            Block.makeCuboidShape(13.17228, 1.9, 7.699999999999999, 14.84228, 2.5, 8.299999999999999),
            Block.makeCuboidShape(10.62528, 6.9, 7.699999999999999, 12.62528, 7.5, 8.299999999999999),
            Block.makeCuboidShape(6.12528, 0, 6, 10.37528, 1, 10),
            Block.makeCuboidShape(5.87528, 0, 10, 10.62528, 0.25, 10.25),
            Block.makeCuboidShape(5.87528, 0, 5.75, 10.62528, 0.25, 6),
            Block.makeCuboidShape(5.87528, 0, 6, 6.12528, 0.25, 10),
            Block.makeCuboidShape(10.37528, 0, 6, 10.62528, 0.25, 10),
            Block.makeCuboidShape(6.37528, 1, 6.25, 10.12528, 1.25, 9.75),
            Block.makeCuboidShape(9.12528, 10.5, 6.5, 9.62528, 11, 9.5),
            Block.makeCuboidShape(6.87528, 10.5, 6.5, 7.37528, 11, 9.5),
            Block.makeCuboidShape(7.37528, 9.75, 9, 9.12528, 11, 9.5),
            Block.makeCuboidShape(7.37528, 9.75, 6.5, 9.12528, 11, 7),
            Block.makeCuboidShape(6.87528, 9.75, 7, 7.37528, 10.5, 9),
            Block.makeCuboidShape(9.12528, 9.75, 7, 9.62528, 10.5, 9),
            Block.makeCuboidShape(7.37528, 1.25, 7.5, 9.12528, 5.75, 8.5),
            Block.makeCuboidShape(7.37528, 8.75, 9, 9.12528, 9.75, 9),
            Block.makeCuboidShape(7.37528, 8.75, 7, 9.12528, 9.75, 7),
            Block.makeCuboidShape(9.12528, 8.75, 7, 9.12528, 9.75, 9),
            Block.makeCuboidShape(7.37528, 8.75, 7, 7.37528, 9.75, 9),
            Block.makeCuboidShape(7.72528, 1.25, 8.5, 8.72528, 5.75, 8.9),
            Block.makeCuboidShape(7.72528, 1.25, 7.1, 8.72528, 5.75, 7.5),
            Block.makeCuboidShape(7.12528, 1.25, 7.5, 7.37528, 2.25, 8.5),
            Block.makeCuboidShape(6.62528, 1.25, 7.5, 7.12528, 1.75, 8.5),
            Block.makeCuboidShape(9.37528, 1.25, 7.5, 9.87528, 1.75, 8.5),
            Block.makeCuboidShape(9.12528, 1.25, 7.5, 9.37528, 2.25, 8.5),
            Block.makeCuboidShape(3.87528, 5.75, 7, 10.87528, 6.25, 9),
            Block.makeCuboidShape(3.87528, 6.25, 9, 11.12528, 8.25, 9.5),
            Block.makeCuboidShape(3.87528, 6.25, 6.5, 11.12528, 8.25, 7),
            Block.makeCuboidShape(10.12528, 6.25, 7, 11.12528, 8.25, 9),
            Block.makeCuboidShape(3.87528, 8.25, 7, 10.87528, 8.75, 9),
            Block.makeCuboidShape(7.37528, 8.25, 9, 9.12528, 8.75, 9.5),
            Block.makeCuboidShape(7.37528, 8.25, 6.5, 9.12528, 8.75, 7),
            Block.makeCuboidShape(3.1752800000000008, 6, 9, 3.87528, 8.5, 9.75),
            Block.makeCuboidShape(3.1752800000000008, 6, 6.25, 3.87528, 8.5, 7),
            Block.makeCuboidShape(3.1752800000000008, 8.25, 6.75, 3.87528, 9, 9.25),
            Block.makeCuboidShape(3.1752800000000008, 5.5, 6.75, 3.87528, 6.25, 9.25),
            Block.makeCuboidShape(3.62528, 6.25, 7, 3.62528, 8.25, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(4, 6.5, 7, 5, 8, 9),
            Block.makeCuboidShape(2.9499999999999993, 1.9, 7.700000000000001, 3.5500000000000007, 7.5, 8.3),
            Block.makeCuboidShape(1.2829999999999995, 1.9, 7.700000000000001, 2.9529999999999994, 2.5, 8.3),
            Block.makeCuboidShape(3.5, 6.9, 7.700000000000001, 5.5, 7.5, 8.3),
            Block.makeCuboidShape(5.75, 0, 6, 10, 1, 10),
            Block.makeCuboidShape(5.5, 0, 5.75, 10.25, 0.25, 6),
            Block.makeCuboidShape(5.5, 0, 10, 10.25, 0.25, 10.25),
            Block.makeCuboidShape(10, 0, 6, 10.25, 0.25, 10),
            Block.makeCuboidShape(5.5, 0, 6, 5.75, 0.25, 10),
            Block.makeCuboidShape(6, 1, 6.25, 9.75, 1.25, 9.75),
            Block.makeCuboidShape(6.5, 10.5, 6.5, 7, 11, 9.5),
            Block.makeCuboidShape(8.75, 10.5, 6.5, 9.25, 11, 9.5),
            Block.makeCuboidShape(7, 9.75, 6.5, 8.75, 11, 7),
            Block.makeCuboidShape(7, 9.75, 9, 8.75, 11, 9.5),
            Block.makeCuboidShape(8.75, 9.75, 7, 9.25, 10.5, 9),
            Block.makeCuboidShape(6.5, 9.75, 7, 7, 10.5, 9),
            Block.makeCuboidShape(7, 1.25, 7.5, 8.75, 5.75, 8.5),
            Block.makeCuboidShape(7, 8.75, 7, 8.75, 9.75, 7),
            Block.makeCuboidShape(7, 8.75, 9, 8.75, 9.75, 9),
            Block.makeCuboidShape(7, 8.75, 7, 7, 9.75, 9),
            Block.makeCuboidShape(8.75, 8.75, 7, 8.75, 9.75, 9),
            Block.makeCuboidShape(7.4, 1.25, 7.1, 8.4, 5.75, 7.5),
            Block.makeCuboidShape(7.4, 1.25, 8.5, 8.4, 5.75, 8.9),
            Block.makeCuboidShape(8.75, 1.25, 7.5, 9, 2.25, 8.5),
            Block.makeCuboidShape(9, 1.25, 7.5, 9.5, 1.75, 8.5),
            Block.makeCuboidShape(6.25, 1.25, 7.5, 6.75, 1.75, 8.5),
            Block.makeCuboidShape(6.75, 1.25, 7.5, 7, 2.25, 8.5),
            Block.makeCuboidShape(5.25, 5.75, 7, 12.25, 6.25, 9),
            Block.makeCuboidShape(5, 6.25, 6.5, 12.25, 8.25, 7),
            Block.makeCuboidShape(5, 6.25, 9, 12.25, 8.25, 9.5),
            Block.makeCuboidShape(5, 6.25, 7, 6, 8.25, 9),
            Block.makeCuboidShape(5.25, 8.25, 7, 12.25, 8.75, 9),
            Block.makeCuboidShape(7, 8.25, 6.5, 8.75, 8.75, 7),
            Block.makeCuboidShape(7, 8.25, 9, 8.75, 8.75, 9.5),
            Block.makeCuboidShape(12.25, 6, 6.25, 12.95, 8.5, 7),
            Block.makeCuboidShape(12.25, 6, 9, 12.95, 8.5, 9.75),
            Block.makeCuboidShape(12.25, 8.25, 6.75, 12.95, 9, 9.25),
            Block.makeCuboidShape(12.25, 5.5, 6.75, 12.95, 6.25, 9.25),
            Block.makeCuboidShape(12.5, 6.25, 7, 12.5, 8.25, 9)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(7.06264, 6.5, 3.93736, 9.06264, 8, 4.93736),
            Block.makeCuboidShape(7.762639999999999, 1.9, 2.8873599999999993, 8.362639999999999, 7.5, 3.4873600000000007),
            Block.makeCuboidShape(7.762639999999999, 1.9, 1.2203599999999994, 8.362639999999999, 2.5, 2.8903599999999994),
            Block.makeCuboidShape(7.762639999999999, 6.9, 3.43736, 8.362639999999999, 7.5, 5.43736),
            Block.makeCuboidShape(6.06264, 0, 5.68736, 10.06264, 1, 9.93736),
            Block.makeCuboidShape(10.06264, 0, 5.43736, 10.31264, 0.25, 10.18736),
            Block.makeCuboidShape(5.81264, 0, 5.43736, 6.06264, 0.25, 10.18736),
            Block.makeCuboidShape(6.06264, 0, 9.93736, 10.06264, 0.25, 10.18736),
            Block.makeCuboidShape(6.06264, 0, 5.43736, 10.06264, 0.25, 5.68736),
            Block.makeCuboidShape(6.31264, 1, 5.93736, 9.81264, 1.25, 9.68736),
            Block.makeCuboidShape(6.56264, 10.5, 6.43736, 9.56264, 11, 6.93736),
            Block.makeCuboidShape(6.56264, 10.5, 8.68736, 9.56264, 11, 9.18736),
            Block.makeCuboidShape(9.06264, 9.75, 6.93736, 9.56264, 11, 8.68736),
            Block.makeCuboidShape(6.56264, 9.75, 6.93736, 7.06264, 11, 8.68736),
            Block.makeCuboidShape(7.06264, 9.75, 8.68736, 9.06264, 10.5, 9.18736),
            Block.makeCuboidShape(7.06264, 9.75, 6.43736, 9.06264, 10.5, 6.93736),
            Block.makeCuboidShape(7.56264, 1.25, 6.93736, 8.56264, 5.75, 8.68736),
            Block.makeCuboidShape(9.06264, 8.75, 6.93736, 9.06264, 9.75, 8.68736),
            Block.makeCuboidShape(7.06264, 8.75, 6.93736, 7.06264, 9.75, 8.68736),
            Block.makeCuboidShape(7.06264, 8.75, 6.93736, 9.06264, 9.75, 6.93736),
            Block.makeCuboidShape(7.06264, 8.75, 8.68736, 9.06264, 9.75, 8.68736),
            Block.makeCuboidShape(8.56264, 1.25, 7.33736, 8.96264, 5.75, 8.33736),
            Block.makeCuboidShape(7.16264, 1.25, 7.33736, 7.56264, 5.75, 8.33736),
            Block.makeCuboidShape(7.56264, 1.25, 8.68736, 8.56264, 2.25, 8.93736),
            Block.makeCuboidShape(7.56264, 1.25, 8.93736, 8.56264, 1.75, 9.43736),
            Block.makeCuboidShape(7.56264, 1.25, 6.18736, 8.56264, 1.75, 6.68736),
            Block.makeCuboidShape(7.56264, 1.25, 6.68736, 8.56264, 2.25, 6.93736),
            Block.makeCuboidShape(7.06264, 5.75, 5.18736, 9.06264, 6.25, 12.18736),
            Block.makeCuboidShape(9.06264, 6.25, 4.93736, 9.56264, 8.25, 12.18736),
            Block.makeCuboidShape(6.56264, 6.25, 4.93736, 7.06264, 8.25, 12.18736),
            Block.makeCuboidShape(7.06264, 6.25, 4.93736, 9.06264, 8.25, 5.93736),
            Block.makeCuboidShape(7.06264, 8.25, 5.18736, 9.06264, 8.75, 12.18736),
            Block.makeCuboidShape(9.06264, 8.25, 6.93736, 9.56264, 8.75, 8.68736),
            Block.makeCuboidShape(6.56264, 8.25, 6.93736, 7.06264, 8.75, 8.68736),
            Block.makeCuboidShape(9.06264, 6, 12.18736, 9.81264, 8.5, 12.88736),
            Block.makeCuboidShape(6.31264, 6, 12.18736, 7.06264, 8.5, 12.88736),
            Block.makeCuboidShape(6.81264, 8.25, 12.18736, 9.31264, 9, 12.88736),
            Block.makeCuboidShape(6.81264, 5.5, 12.18736, 9.31264, 6.25, 12.88736),
            Block.makeCuboidShape(7.06264, 6.25, 12.43736, 9.06264, 8.25, 12.43736)
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
                        float ySpawnPos = pos.getY() + 0.2f;
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
}
