package com.mrcrayfish.vehicle.entity.vehicle;

import com.mrcrayfish.vehicle.entity.LandVehicleEntity;
import com.mrcrayfish.vehicle.init.ModSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

/**
 * Author: MrCrayfish
 */
public class MiniBusEntity extends LandVehicleEntity
{
    public MiniBusEntity(EntityType<? extends MiniBusEntity> type, World worldIn)
    {
        super(type, worldIn);
    }
}
