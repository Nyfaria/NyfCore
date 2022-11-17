package com.nyfaria.nyfcore.client.model;

import com.nyfaria.nyfcore.entity.api.IVariantEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GenericEntityModel<T extends Entity & IAnimatable> extends AnimatedGeoModel<T> {
    
    private final String modId;

    public GenericEntityModel(String modId) {
        super();
        this.modId = modId;
    }

    @Override
    public ResourceLocation getModelResource(T object) {
        return new ResourceLocation(modId, "geo/entity/" + ForgeRegistries.ENTITY_TYPES.getKey(object.getType()).getPath() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(T object) {

        if(object instanceof IVariantEntity variantEntity){
            return new ResourceLocation(modId, "textures/entity/" + ForgeRegistries.ENTITY_TYPES.getKey(object.getType()).getPath() + "_" + variantEntity.getVariantName() + ".png");
        }
        return new ResourceLocation(modId, "textures/entity/" + ForgeRegistries.ENTITY_TYPES.getKey(object.getType()).getPath() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(T animatable) {
        return new ResourceLocation(modId, "animations/entity/" + ForgeRegistries.ENTITY_TYPES.getKey(animatable.getType()).getPath() + ".animation.json");
    }
}
