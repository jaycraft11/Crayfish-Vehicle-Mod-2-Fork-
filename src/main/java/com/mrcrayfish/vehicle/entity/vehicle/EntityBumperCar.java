package com.mrcrayfish.vehicle.entity.vehicle;

import com.mrcrayfish.vehicle.client.EntityRaytracer.IEntityRaytraceable;
import com.mrcrayfish.vehicle.client.render.Wheel;
import com.mrcrayfish.vehicle.common.entity.PartPosition;
import com.mrcrayfish.vehicle.entity.EngineType;
import com.mrcrayfish.vehicle.entity.EntityLandVehicle;
import com.mrcrayfish.vehicle.entity.VehicleProperties;
import com.mrcrayfish.vehicle.init.ModItems;
import com.mrcrayfish.vehicle.init.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Author: MrCrayfish
 */
public class EntityBumperCar extends EntityLandVehicle implements IEntityRaytraceable
{
    static
    {
        VehicleProperties properties = new VehicleProperties();
        properties.setAxleOffset(-1.5F);
        properties.setWheelOffset(1.5F);
        properties.setBodyPosition(new PartPosition(0, 0, 0.4, 0, 0, 0, 1.2));
        properties.setFuelPortPosition(new PartPosition(-8.25, 6, -9.3, 0, -90, 0, 0.25));
        properties.setHeldOffset(new Vec3d(6.0, 0.0, 0.0));
        properties.setTrailerOffset(new Vec3d(0.0, -0.03125, -0.5625));
        properties.addWheel(Wheel.Side.LEFT, Wheel.Position.FRONT, 7.0F, 8.5F, 0.75F, false, true);
        properties.addWheel(Wheel.Side.RIGHT, Wheel.Position.FRONT, 7.0F, 8.5F, 0.75F, false, true);
        properties.addWheel(Wheel.Side.LEFT, Wheel.Position.REAR, 7.0F, -8.5F, 0.75F, false, true);
        properties.addWheel(Wheel.Side.RIGHT, Wheel.Position.REAR, 7.0F, -8.5F, 0.75F, false, true);
        VehicleProperties.setProperties(EntityBumperCar.class, properties);
    }

    /**
     * ItemStack instances used for rendering
     */
    @SideOnly(Side.CLIENT)
    public ItemStack steeringWheel;

    public EntityBumperCar(World worldIn)
    {
        super(worldIn);
        this.setMaxSpeed(10);
        this.setSize(1.5F, 1.0F);
        this.setTurnSensitivity(20);
        this.stepHeight = 0.625F;

        //TODO figure out fuel system
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onClientInit()
    {
        super.onClientInit();
        body = new ItemStack(ModItems.BUMPER_CAR_BODY);
        wheel = new ItemStack(ModItems.WHEEL);
        steeringWheel = new ItemStack(ModItems.GO_KART_STEERING_WHEEL);
    }

    @Override
    public void applyEntityCollision(Entity entityIn)
    {
        if(entityIn instanceof EntityBumperCar && this.isBeingRidden())
        {
            applyBumperCollision((EntityBumperCar) entityIn);
        }
    }

    private void applyBumperCollision(EntityBumperCar entity)
    {
        entity.motionX += vehicleMotionX * 2;
        entity.motionZ += vehicleMotionZ * 2;
        world.playSound(null, this.posX, this.posY, this.posZ, ModSounds.bonk, SoundCategory.NEUTRAL, 1.0F, 0.6F + 0.1F * this.getNormalSpeed());
        this.currentSpeed *= 0.25F;
    }

    @Override
    public SoundEvent getMovingSound()
    {
        return ModSounds.electricEngineMono;
    }

    @Override
    public SoundEvent getRidingSound()
    {
        return ModSounds.electricEngineStereo;
    }

    @Override
    public float getMaxEnginePitch()
    {
        return 0.8F;
    }

    @Override
    public double getMountedYOffset()
    {
        return 3 * 0.0625F;
    }

    @Override
    public EngineType getEngineType()
    {
        return EngineType.ELECTRIC_MOTOR;
    }

    @Override
    public boolean canBeColored()
    {
        return true;
    }

    @Override
    public boolean isLockable()
    {
        return false;
    }
}
