package com.v14d4n.pelmenicraft.block.custom;

import com.v14d4n.pelmenicraft.container.MeatGrinderContainer;
import com.v14d4n.pelmenicraft.tileentity.MeatGrinderBlockEntity;
import com.v14d4n.pelmenicraft.tileentity.ModTileEntities;
import net.minecraft.client.gui.screens.Screen;
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
import net.minecraft.world.item.TooltipFlag;
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
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;


import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

public class MeatGrinderBlock extends HorizontalDirectionalBlock implements EntityBlock {
    public MeatGrinderBlock(Properties builder) {
        super(builder);
    }

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.box(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.box(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.box(7, 1.25, 7, 9, 2.75, 9),
            Block.box(7, 5.25, 7, 9, 6.75, 9),
            Block.box(6.5, 6.75, 3, 9.5, 8.75, 11),
            Block.box(7, 6.25, 3, 9, 9.25, 11),
            Block.box(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.box(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.box(7.5, 1.25, 6.75, 8.5, 2.75, 7),
            Block.box(7.5, 1.25, 6.25, 8.5, 1.75, 6.75),
            Block.box(7.5, 1.25, 9, 8.5, 2.75, 9.25),
            Block.box(7.5, 1.25, 9.25, 8.5, 1.75, 9.75),
            Block.box(7.5, 1, 13, 8.5, 2, 15.5),
            Block.box(7.75, 1.25, 12.5, 8.25, 1.75, 13),
            Block.box(7.75, 1.25, 12, 8.25, 8, 12.5),
            Block.box(7.75, 7.5, 11.5, 8.25, 8, 12),
            Block.box(6.25, 7.5, 8.75, 9.75, 8, 10.25),
            Block.box(6.25, 7.5, 3, 9.75, 8, 6.75),
            Block.box(6.75, 8.75, 2.25, 9.25, 9.5, 3),
            Block.box(6.75, 6, 2.25, 9.25, 6.75, 3),
            Block.box(6.25, 6.5, 2.25, 7, 9, 3),
            Block.box(9, 6.5, 2.25, 9.75, 9, 3),
            Block.box(7.5, 1.25, 6.75, 8.5, 2.75, 9.25),
            Block.box(7.5, 1.25, 6.25, 8.5, 1.75, 9.75),
            Block.box(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.box(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.box(7.75, 6, 3, 8.25, 9.5, 7),
            Block.box(7, 6.75, 5.25, 9, 8.75, 11.5),
            Block.box(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.box(7, 6.25, 7, 9, 11.25, 9),
            Block.box(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.box(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.box(6.5, 11, 6.5, 9.5, 11.25, 9.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.box(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.box(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.box(7, 1.25, 7, 9, 2.75, 9),
            Block.box(7, 5.25, 7, 9, 6.75, 9),
            Block.box(3, 6.75, 6.5, 11, 8.75, 9.5),
            Block.box(3, 6.25, 7, 11, 9.25, 9),
            Block.box(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.box(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.box(6.75, 1.25, 7.5, 7, 2.75, 8.5),
            Block.box(6.25, 1.25, 7.5, 6.75, 1.75, 8.5),
            Block.box(9, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.box(9.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.box(13, 1, 7.5, 15.5, 2, 8.5),
            Block.box(12.5, 1.25, 7.75, 13, 1.75, 8.25),
            Block.box(12, 1.25, 7.75, 12.5, 8, 8.25),
            Block.box(11.5, 7.5, 7.75, 12, 8, 8.25),
            Block.box(8.75, 7.5, 6.25, 10.25, 8, 9.75),
            Block.box(3, 7.5, 6.25, 6.75, 8, 9.75),
            Block.box(2.25, 8.75, 6.75, 3, 9.5, 9.25),
            Block.box(2.25, 6, 6.75, 3, 6.75, 9.25),
            Block.box(2.25, 6.5, 9, 3, 9, 9.75),
            Block.box(2.25, 6.5, 6.25, 3, 9, 7),
            Block.box(6.75, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.box(6.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.box(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.box(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.box(3, 6, 7.75, 7, 9.5, 8.25),
            Block.box(5.25, 6.75, 7, 11.5, 8.75, 9),
            Block.box(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.box(7, 6.25, 7, 9, 11.25, 9),
            Block.box(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.box(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.box(6.5, 11, 6.5, 9.5, 11.25, 9.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.box(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.box(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.box(7, 1.25, 7, 9, 2.75, 9),
            Block.box(7, 5.25, 7, 9, 6.75, 9),
            Block.box(5, 6.75, 6.5, 13, 8.75, 9.5),
            Block.box(5, 6.25, 7, 13, 9.25, 9),
            Block.box(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.box(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.box(9, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.box(9.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.box(6.75, 1.25, 7.5, 7, 2.75, 8.5),
            Block.box(6.25, 1.25, 7.5, 6.75, 1.75, 8.5),
            Block.box(0.5, 1, 7.5, 3, 2, 8.5),
            Block.box(3, 1.25, 7.75, 3.5, 1.75, 8.25),
            Block.box(3.5, 1.25, 7.75, 4, 8, 8.25),
            Block.box(4, 7.5, 7.75, 4.5, 8, 8.25),
            Block.box(5.75, 7.5, 6.25, 7.25, 8, 9.75),
            Block.box(9.25, 7.5, 6.25, 13, 8, 9.75),
            Block.box(13, 8.75, 6.75, 13.75, 9.5, 9.25),
            Block.box(13, 6, 6.75, 13.75, 6.75, 9.25),
            Block.box(13, 6.5, 6.25, 13.75, 9, 7),
            Block.box(13, 6.5, 9, 13.75, 9, 9.75),
            Block.box(6.75, 1.25, 7.5, 9.25, 2.75, 8.5),
            Block.box(6.25, 1.25, 7.5, 9.75, 1.75, 8.5),
            Block.box(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.box(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.box(9, 6, 7.75, 13, 9.5, 8.25),
            Block.box(4.5, 6.75, 7, 10.75, 8.75, 9),
            Block.box(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.box(7, 6.25, 7, 9, 11.25, 9),
            Block.box(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.box(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.box(6.5, 11, 6.5, 9.5, 11.25, 9.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.box(5.25, 0, 5.25, 10.75, 1, 10.75),
            Block.box(5.75, 1, 5.75, 10.25, 1.25, 10.25),
            Block.box(7, 1.25, 7, 9, 2.75, 9),
            Block.box(7, 5.25, 7, 9, 6.75, 9),
            Block.box(6.5, 6.75, 5, 9.5, 8.75, 13),
            Block.box(7, 6.25, 5, 9, 9.25, 13),
            Block.box(7.5, 2.75, 7, 8.5, 6.75, 9),
            Block.box(7, 2.75, 7.5, 9, 6.75, 8.5),
            Block.box(7.5, 1.25, 9, 8.5, 2.75, 9.25),
            Block.box(7.5, 1.25, 9.25, 8.5, 1.75, 9.75),
            Block.box(7.5, 1.25, 6.75, 8.5, 2.75, 7),
            Block.box(7.5, 1.25, 6.25, 8.5, 1.75, 6.75),
            Block.box(7.5, 1, 0.5, 8.5, 2, 3),
            Block.box(7.75, 1.25, 3, 8.25, 1.75, 3.5),
            Block.box(7.75, 1.25, 3.5, 8.25, 8, 4),
            Block.box(7.75, 7.5, 4, 8.25, 8, 4.5),
            Block.box(6.25, 7.5, 5.75, 9.75, 8, 7.25),
            Block.box(6.25, 7.5, 9.25, 9.75, 8, 13),
            Block.box(6.75, 8.75, 13, 9.25, 9.5, 13.75),
            Block.box(6.75, 6, 13, 9.25, 6.75, 13.75),
            Block.box(9, 6.5, 13, 9.75, 9, 13.75),
            Block.box(6.25, 6.5, 13, 7, 9, 13.75),
            Block.box(7.5, 1.25, 6.75, 8.5, 2.75, 9.25),
            Block.box(7.5, 1.25, 6.25, 8.5, 1.75, 9.75),
            Block.box(6.5, 6.25, 6.5, 9.5, 9.25, 9.5),
            Block.box(6.75, 9.25, 7.75, 9.25, 10.5, 8.25),
            Block.box(7.75, 6, 9, 8.25, 9.5, 13),
            Block.box(7, 6.75, 4.5, 9, 8.75, 10.75),
            Block.box(7.75, 9.25, 6.75, 8.25, 10.5, 9.25),
            Block.box(7, 6.25, 7, 9, 11.25, 9),
            Block.box(7, 10.5, 6.5, 9, 11.25, 9.5),
            Block.box(6.5, 10.5, 7, 9.5, 11.25, 9),
            Block.box(6.5, 11, 6.5, 9.5, 11.25, 9.5)
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
                        float ySpawnPos = pPos.getY() + 0.15f;
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

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {

        if (Screen.hasShiftDown()) {
            pTooltip.add(new TranslatableComponent("tooltip.pelmenicraft.meat_grinder_shift"));
        } else {
            pTooltip.add(new TranslatableComponent("tooltip.pelmenicraft.meat_grinder"));
        }

        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
