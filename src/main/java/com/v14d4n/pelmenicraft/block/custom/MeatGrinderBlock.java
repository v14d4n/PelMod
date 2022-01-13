package com.v14d4n.pelmenicraft.block.custom;

import com.v14d4n.pelmenicraft.container.MeatGrinderContainer;
import com.v14d4n.pelmenicraft.tileentity.MeatGrinderBlockEntity;
import com.v14d4n.pelmenicraft.tileentity.ModTileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;


import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class MeatGrinderBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public MeatGrinderBlock(Properties builder) {
        super(builder);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(7.06264, 6.5, 11.06264, 9.06264, 8, 12.06264),
            Block.box(7.76264, 1.9, 12.51264, 8.36264, 7.5, 13.11264),
            Block.box(7.76264, 1.9, 13.10964, 8.36264, 2.5, 14.77964),
            Block.box(7.76264, 6.9, 10.56264, 8.36264, 7.5, 12.56264),
            Block.box(6.06264, 0, 6.06264, 10.06264, 1, 10.31264),
            Block.box(5.81264, 0, 5.81264, 6.06264, 0.25, 10.56264),
            Block.box(10.06264, 0, 5.81264, 10.31264, 0.25, 10.56264),
            Block.box(6.06264, 0, 5.81264, 10.06264, 0.25, 6.06264),
            Block.box(6.06264, 0, 10.31264, 10.06264, 0.25, 10.56264),
            Block.box(6.31264, 1, 6.31264, 9.81264, 1.25, 10.06264),
            Block.box(6.56264, 10.5, 9.06264, 9.56264, 11, 9.56264),
            Block.box(6.56264, 10.5, 6.81264, 9.56264, 11, 7.31264),
            Block.box(6.56264, 9.75, 7.31264, 7.06264, 11, 9.06264),
            Block.box(9.06264, 9.75, 7.31264, 9.56264, 11, 9.06264),
            Block.box(7.06264, 9.75, 6.81264, 9.06264, 10.5, 7.31264),
            Block.box(7.06264, 9.75, 9.06264, 9.06264, 10.5, 9.56264),
            Block.box(7.56264, 1.25, 7.31264, 8.56264, 5.75, 9.06264),
            Block.box(7.06264, 8.75, 7.31264, 7.06264, 9.75, 9.06264),
            Block.box(9.06264, 8.75, 7.31264, 9.06264, 9.75, 9.06264),
            Block.box(7.06264, 8.75, 9.06264, 9.06264, 9.75, 9.06264),
            Block.box(7.06264, 8.75, 7.31264, 9.06264, 9.75, 7.31264),
            Block.box(7.16264, 1.25, 7.66264, 7.56264, 5.75, 8.66264),
            Block.box(8.56264, 1.25, 7.66264, 8.96264, 5.75, 8.66264),
            Block.box(7.56264, 1.25, 7.06264, 8.56264, 2.25, 7.31264),
            Block.box(7.56264, 1.25, 6.56264, 8.56264, 1.75, 7.06264),
            Block.box(7.56264, 1.25, 9.31264, 8.56264, 1.75, 9.81264),
            Block.box(7.56264, 1.25, 9.06264, 8.56264, 2.25, 9.31264),
            Block.box(7.06264, 5.75, 3.81264, 9.06264, 6.25, 10.81264),
            Block.box(6.56264, 6.25, 3.81264, 7.06264, 8.25, 11.06264),
            Block.box(9.06264, 6.25, 3.81264, 9.56264, 8.25, 11.06264),
            Block.box(7.06264, 6.25, 10.06264, 9.06264, 8.25, 11.06264),
            Block.box(7.06264, 8.25, 3.81264, 9.06264, 8.75, 10.81264),
            Block.box(6.56264, 8.25, 7.31264, 7.06264, 8.75, 9.06264),
            Block.box(9.06264, 8.25, 7.31264, 9.56264, 8.75, 9.06264),
            Block.box(6.31264, 6, 3.11264, 7.06264, 8.5, 3.81264),
            Block.box(9.06264, 6, 3.11264, 9.81264, 8.5, 3.81264),
            Block.box(6.81264, 8.25, 3.11264, 9.31264, 9, 3.81264),
            Block.box(6.81264, 5.5, 3.11264, 9.31264, 6.25, 3.81264),
            Block.box(7.06264, 6.25, 3.56264, 9.06264, 8.25, 3.56264)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(11.12528, 6.5, 7, 12.12528, 8, 9),
            Block.box(12.57528, 1.9, 7.699999999999999, 13.17528, 7.5, 8.299999999999999),
            Block.box(13.17228, 1.9, 7.699999999999999, 14.84228, 2.5, 8.299999999999999),
            Block.box(10.62528, 6.9, 7.699999999999999, 12.62528, 7.5, 8.299999999999999),
            Block.box(6.12528, 0, 6, 10.37528, 1, 10),
            Block.box(5.87528, 0, 10, 10.62528, 0.25, 10.25),
            Block.box(5.87528, 0, 5.75, 10.62528, 0.25, 6),
            Block.box(5.87528, 0, 6, 6.12528, 0.25, 10),
            Block.box(10.37528, 0, 6, 10.62528, 0.25, 10),
            Block.box(6.37528, 1, 6.25, 10.12528, 1.25, 9.75),
            Block.box(9.12528, 10.5, 6.5, 9.62528, 11, 9.5),
            Block.box(6.87528, 10.5, 6.5, 7.37528, 11, 9.5),
            Block.box(7.37528, 9.75, 9, 9.12528, 11, 9.5),
            Block.box(7.37528, 9.75, 6.5, 9.12528, 11, 7),
            Block.box(6.87528, 9.75, 7, 7.37528, 10.5, 9),
            Block.box(9.12528, 9.75, 7, 9.62528, 10.5, 9),
            Block.box(7.37528, 1.25, 7.5, 9.12528, 5.75, 8.5),
            Block.box(7.37528, 8.75, 9, 9.12528, 9.75, 9),
            Block.box(7.37528, 8.75, 7, 9.12528, 9.75, 7),
            Block.box(9.12528, 8.75, 7, 9.12528, 9.75, 9),
            Block.box(7.37528, 8.75, 7, 7.37528, 9.75, 9),
            Block.box(7.72528, 1.25, 8.5, 8.72528, 5.75, 8.9),
            Block.box(7.72528, 1.25, 7.1, 8.72528, 5.75, 7.5),
            Block.box(7.12528, 1.25, 7.5, 7.37528, 2.25, 8.5),
            Block.box(6.62528, 1.25, 7.5, 7.12528, 1.75, 8.5),
            Block.box(9.37528, 1.25, 7.5, 9.87528, 1.75, 8.5),
            Block.box(9.12528, 1.25, 7.5, 9.37528, 2.25, 8.5),
            Block.box(3.87528, 5.75, 7, 10.87528, 6.25, 9),
            Block.box(3.87528, 6.25, 9, 11.12528, 8.25, 9.5),
            Block.box(3.87528, 6.25, 6.5, 11.12528, 8.25, 7),
            Block.box(10.12528, 6.25, 7, 11.12528, 8.25, 9),
            Block.box(3.87528, 8.25, 7, 10.87528, 8.75, 9),
            Block.box(7.37528, 8.25, 9, 9.12528, 8.75, 9.5),
            Block.box(7.37528, 8.25, 6.5, 9.12528, 8.75, 7),
            Block.box(3.1752800000000008, 6, 9, 3.87528, 8.5, 9.75),
            Block.box(3.1752800000000008, 6, 6.25, 3.87528, 8.5, 7),
            Block.box(3.1752800000000008, 8.25, 6.75, 3.87528, 9, 9.25),
            Block.box(3.1752800000000008, 5.5, 6.75, 3.87528, 6.25, 9.25),
            Block.box(3.62528, 6.25, 7, 3.62528, 8.25, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(4, 6.5, 7, 5, 8, 9),
            Block.box(2.9499999999999993, 1.9, 7.700000000000001, 3.5500000000000007, 7.5, 8.3),
            Block.box(1.2829999999999995, 1.9, 7.700000000000001, 2.9529999999999994, 2.5, 8.3),
            Block.box(3.5, 6.9, 7.700000000000001, 5.5, 7.5, 8.3),
            Block.box(5.75, 0, 6, 10, 1, 10),
            Block.box(5.5, 0, 5.75, 10.25, 0.25, 6),
            Block.box(5.5, 0, 10, 10.25, 0.25, 10.25),
            Block.box(10, 0, 6, 10.25, 0.25, 10),
            Block.box(5.5, 0, 6, 5.75, 0.25, 10),
            Block.box(6, 1, 6.25, 9.75, 1.25, 9.75),
            Block.box(6.5, 10.5, 6.5, 7, 11, 9.5),
            Block.box(8.75, 10.5, 6.5, 9.25, 11, 9.5),
            Block.box(7, 9.75, 6.5, 8.75, 11, 7),
            Block.box(7, 9.75, 9, 8.75, 11, 9.5),
            Block.box(8.75, 9.75, 7, 9.25, 10.5, 9),
            Block.box(6.5, 9.75, 7, 7, 10.5, 9),
            Block.box(7, 1.25, 7.5, 8.75, 5.75, 8.5),
            Block.box(7, 8.75, 7, 8.75, 9.75, 7),
            Block.box(7, 8.75, 9, 8.75, 9.75, 9),
            Block.box(7, 8.75, 7, 7, 9.75, 9),
            Block.box(8.75, 8.75, 7, 8.75, 9.75, 9),
            Block.box(7.4, 1.25, 7.1, 8.4, 5.75, 7.5),
            Block.box(7.4, 1.25, 8.5, 8.4, 5.75, 8.9),
            Block.box(8.75, 1.25, 7.5, 9, 2.25, 8.5),
            Block.box(9, 1.25, 7.5, 9.5, 1.75, 8.5),
            Block.box(6.25, 1.25, 7.5, 6.75, 1.75, 8.5),
            Block.box(6.75, 1.25, 7.5, 7, 2.25, 8.5),
            Block.box(5.25, 5.75, 7, 12.25, 6.25, 9),
            Block.box(5, 6.25, 6.5, 12.25, 8.25, 7),
            Block.box(5, 6.25, 9, 12.25, 8.25, 9.5),
            Block.box(5, 6.25, 7, 6, 8.25, 9),
            Block.box(5.25, 8.25, 7, 12.25, 8.75, 9),
            Block.box(7, 8.25, 6.5, 8.75, 8.75, 7),
            Block.box(7, 8.25, 9, 8.75, 8.75, 9.5),
            Block.box(12.25, 6, 6.25, 12.95, 8.5, 7),
            Block.box(12.25, 6, 9, 12.95, 8.5, 9.75),
            Block.box(12.25, 8.25, 6.75, 12.95, 9, 9.25),
            Block.box(12.25, 5.5, 6.75, 12.95, 6.25, 9.25),
            Block.box(12.5, 6.25, 7, 12.5, 8.25, 9)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(7.06264, 6.5, 3.93736, 9.06264, 8, 4.93736),
            Block.box(7.762639999999999, 1.9, 2.8873599999999993, 8.362639999999999, 7.5, 3.4873600000000007),
            Block.box(7.762639999999999, 1.9, 1.2203599999999994, 8.362639999999999, 2.5, 2.8903599999999994),
            Block.box(7.762639999999999, 6.9, 3.43736, 8.362639999999999, 7.5, 5.43736),
            Block.box(6.06264, 0, 5.68736, 10.06264, 1, 9.93736),
            Block.box(10.06264, 0, 5.43736, 10.31264, 0.25, 10.18736),
            Block.box(5.81264, 0, 5.43736, 6.06264, 0.25, 10.18736),
            Block.box(6.06264, 0, 9.93736, 10.06264, 0.25, 10.18736),
            Block.box(6.06264, 0, 5.43736, 10.06264, 0.25, 5.68736),
            Block.box(6.31264, 1, 5.93736, 9.81264, 1.25, 9.68736),
            Block.box(6.56264, 10.5, 6.43736, 9.56264, 11, 6.93736),
            Block.box(6.56264, 10.5, 8.68736, 9.56264, 11, 9.18736),
            Block.box(9.06264, 9.75, 6.93736, 9.56264, 11, 8.68736),
            Block.box(6.56264, 9.75, 6.93736, 7.06264, 11, 8.68736),
            Block.box(7.06264, 9.75, 8.68736, 9.06264, 10.5, 9.18736),
            Block.box(7.06264, 9.75, 6.43736, 9.06264, 10.5, 6.93736),
            Block.box(7.56264, 1.25, 6.93736, 8.56264, 5.75, 8.68736),
            Block.box(9.06264, 8.75, 6.93736, 9.06264, 9.75, 8.68736),
            Block.box(7.06264, 8.75, 6.93736, 7.06264, 9.75, 8.68736),
            Block.box(7.06264, 8.75, 6.93736, 9.06264, 9.75, 6.93736),
            Block.box(7.06264, 8.75, 8.68736, 9.06264, 9.75, 8.68736),
            Block.box(8.56264, 1.25, 7.33736, 8.96264, 5.75, 8.33736),
            Block.box(7.16264, 1.25, 7.33736, 7.56264, 5.75, 8.33736),
            Block.box(7.56264, 1.25, 8.68736, 8.56264, 2.25, 8.93736),
            Block.box(7.56264, 1.25, 8.93736, 8.56264, 1.75, 9.43736),
            Block.box(7.56264, 1.25, 6.18736, 8.56264, 1.75, 6.68736),
            Block.box(7.56264, 1.25, 6.68736, 8.56264, 2.25, 6.93736),
            Block.box(7.06264, 5.75, 5.18736, 9.06264, 6.25, 12.18736),
            Block.box(9.06264, 6.25, 4.93736, 9.56264, 8.25, 12.18736),
            Block.box(6.56264, 6.25, 4.93736, 7.06264, 8.25, 12.18736),
            Block.box(7.06264, 6.25, 4.93736, 9.06264, 8.25, 5.93736),
            Block.box(7.06264, 8.25, 5.18736, 9.06264, 8.75, 12.18736),
            Block.box(9.06264, 8.25, 6.93736, 9.56264, 8.75, 8.68736),
            Block.box(6.56264, 8.25, 6.93736, 7.06264, 8.75, 8.68736),
            Block.box(9.06264, 6, 12.18736, 9.81264, 8.5, 12.88736),
            Block.box(6.31264, 6, 12.18736, 7.06264, 8.5, 12.88736),
            Block.box(6.81264, 8.25, 12.18736, 9.31264, 9, 12.88736),
            Block.box(6.81264, 5.5, 12.18736, 9.31264, 6.25, 12.88736),
            Block.box(7.06264, 6.25, 12.43736, 9.06264, 8.25, 12.43736)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch (pState.getValue(HORIZONTAL_FACING)) {
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
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) { // FIXME: тут коллизия должна быть
        if (!pLevel.isClientSide) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (pEntity instanceof ItemEntity && blockEntity instanceof MeatGrinderBlockEntity) {
                ItemStack itemStack = ((ItemEntity) pEntity).getItem();
                if (!((MeatGrinderBlockEntity) blockEntity).isItemHandlerFull()) {
                    int outCount = ((MeatGrinderBlockEntity) blockEntity).addItemToSlot(itemStack);
                    ((ItemEntity) pEntity).getItem().setCount(outCount);
                }
            }
        }
        super.entityInside(pState, pLevel, pPos, pEntity);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pLevel.isClientSide()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof MeatGrinderBlockEntity){
                if (pPlayer.isCrouching()) {
                    MenuProvider menuProvider = createContainerProvider(pLevel, pPos);
                    NetworkHooks.openGui((ServerPlayer) pPlayer, menuProvider, blockEntity.getBlockPos());
                } else {
                    // if player is not crouching
                    if (RANDOM.nextDouble() <= 0.4d && ((MeatGrinderBlockEntity) blockEntity).isItemInSlot()) {
                        float xSpawnPos = pPos.getX() + 0.5f;
                        float ySpawnPos = pPos.getY() + 0.2f;
                        float zSpawnPos = pPos.getZ() + 0.5f;
                        float spawnOffset = 0.45f;
                        float throwPower = 0.25f;
                        float zThrowPower = 0;
                        float xThrowPower = 0;

                        switch (pState.getValue(HORIZONTAL_FACING)) {
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

                        ItemEntity item = new ItemEntity(pLevel, xSpawnPos, ySpawnPos, zSpawnPos,
                                ((MeatGrinderBlockEntity) blockEntity).craftAndGetCraftedItem());
                        item.push(xThrowPower, -0.15f, zThrowPower);
                        pLevel.addFreshEntity(item);

                        pLevel.playSound(null, pPos,
                                SoundEvents.SHULKER_BULLET_HIT, SoundSource.BLOCKS, 0.8f, 1);
                    } else { // if random didn't work
                        if (((MeatGrinderBlockEntity) blockEntity).isItemInSlot()) {
                            pLevel.playSound(null, pPos,
                                    SoundEvents.ARMOR_EQUIP_CHAIN, SoundSource.BLOCKS, 0.8f, 1);
                        } else {
                            pLevel.playSound(null, pPos,
                                    SoundEvents.TRIDENT_HIT_GROUND, SoundSource.BLOCKS, 0.8f, 1);
                        }
                    }
                }
            } else {
                throw new RuntimeException("error with tile entity (meat_grinder)");
            }
        }
        return InteractionResult.SUCCESS;
    }

    private MenuProvider createContainerProvider(Level levelIn, BlockPos pos) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return new TranslatableComponent("screen.pelmenicraft.meat_grinder");
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
                return new MeatGrinderContainer(pContainerId, levelIn, pos, pInventory, pPlayer);
            }
        };
    }


    @Override
    protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
        for (int i = 0; i < 20; i++) {
            pLevel.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, pState),
                    pPos.getX() + 0.5f,
                    pPos.getY() + 0.6f - RANDOM.nextDouble() / 2f,
                    pPos.getZ() + 0.5f,
                    0.00d, 0.05d, 0.00d);
        }
        pLevel.playSound(null, pPos, SoundEvents.METAL_BREAK, SoundSource.BLOCKS, 1f, 1f);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        if (blockEntity instanceof MeatGrinderBlockEntity) {
            popResource(pLevel, pPos, ((MeatGrinderBlockEntity) blockEntity).getStackInSlot());
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(HORIZONTAL_FACING);
        super.createBlockStateDefinition(pBuilder);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(HORIZONTAL_FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModTileEntities.MEAT_GRINDER_TILE.get().create(pPos, pState);
    }
}
